import {Component, inject} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Option} from "../../../../interfaces/option";
import {ClaseService} from "../../../../services/clase.service";
import {ModalComponent} from "../../../../util/modal/modal.component";
import {ProfesorRow} from "../../../../interfaces/profesor";

@Component({
  selector: 'app-tareas',
  standalone: true,
    imports: [
        RouterLink,
        FormsModule,
        NgForOf,
        ReactiveFormsModule
    ],
  templateUrl: './tareas.component.html',
  styleUrl: './tareas.component.css'
})
export class TareasComponent {
  private modalService = inject(NgbModal);
  profesores:ProfesorRow[]=[];
  aulas:Option[]=[];
  temas:Option[]=[];
  profesoresSeleccionados:number[]=[];
  aulasSeleccionados:number[]=[];
  id:number=0;
  constructor(private claseService:ClaseService,private route:ActivatedRoute) {
    this.route.parent?.params.subscribe(p => {
      this.id = Number(p['id'])||0;
      this.claseService.getProfesoresWithTarea(this.id).subscribe({
        next: (data) => {
          console.log(data)
          this.profesores = (data)
        },
        error: (error) => {
          alert(error);
        }
      });
      this.claseService.getAllAulas(this.id).subscribe({
        next: (data) => {
          this.aulas = (data)
        },
        error: (error) => {
          alert(error);
        }
      });    })
  }
  ngOnInit(){

  }
  openModal(option:Option) {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.name = 'clase';
    modalRef.componentInstance.option = option;
    modalRef.closed.subscribe((o: Option) => {
      this.eliminarTema(o.id)
    });
  }
  filter(){
    this.claseService.filterTema(this.profesoresSeleccionados,this.aulasSeleccionados).subscribe({
      next: (data) => {
        this.temas = (data)
      },
      error: (error) => {
        alert(error);
      }
    });
  }
  eliminarTema(id:number){
    this.claseService.deleteTema(this.id,id).subscribe({
      next: () => {
        this.temas=this.temas.filter(t=>t.id!=id)
      },
      error: (error) => {
        alert(error);
      }
    });
  }
}
