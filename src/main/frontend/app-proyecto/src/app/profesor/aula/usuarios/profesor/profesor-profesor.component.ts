import {Component, inject} from '@angular/core';
import {NgClass, NgFor, NgIf} from "@angular/common";
import {ProfesorRow} from "../../../../interfaces/profesor";
import {NgbModal, NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import {FormsModule} from "@angular/forms";
import {Option} from "../../../../interfaces/option";
import {ModalComponent} from "../../../../util/modal/modal.component";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AulaService} from "../../../../services/aula.service";

@Component({
  selector: 'app-profesor-profesor',
  standalone: true,
  imports: [NgFor, NgIf, NgbPaginationModule, NgClass, FormsModule, RouterLink,
  ],
  templateUrl: './profesor-profesor.component.html',
  styleUrl: './profesor-profesor.component.css'
})
export class ProfesorProfesorComponent {

  private modalService = inject(NgbModal);
  profesores:ProfesorRow[]=[];
  searchTerm:string="";
  sortColumn= '';
  sortDirection= '';
  id:number=0
  constructor(private aulaService:AulaService,private route:ActivatedRoute) {
    this.route.parent?.params.subscribe(p => {
      this.id = Number(p['id'])||0;
      this.getProfesores();
    })
  }
  onSort(column:string) {
    if (this.sortColumn==column)
      if (this.sortDirection=="desc")
        this.sortColumn="";
      else
        this.sortDirection = this.sortDirection =="asc"?"desc":"asc";
    else{
      this.sortColumn=column
      this.sortDirection = "asc";
    }
    this.sort()
  }
  sort(){
    if (!(this.sortDirection === '' || this.sortColumn === '')) {
      const compare = (a: ProfesorRow, b: ProfesorRow) => {
        const v1 = a[this.sortColumn];
        const v2 = b[this.sortColumn];
        let res = v1 < v2 ? -1 : v1 > v2 ? 1 : 0
        return this.sortDirection === 'asc' ? res : -res;
      };
      this.profesores.sort(compare);
    }
  }
  getProfesores(){
    this.aulaService.getProfesores(this.id,this.searchTerm).subscribe({
      next: (data:any) => {
        this.profesores = (data as ProfesorRow[])
        this.sort()
      },
      error: (error) => {
        alert(error);
      }
    })
  }
  anadirProfesor(){

  }
  eliminarProfesor(id: number) {
    this.aulaService.removeProfesor(this.id,id).subscribe({
      next: () => {
        this.getProfesores()
      },
      error: (error) => {
        alert(error);
      }
    });
  }
  openModal(option:ProfesorRow) {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.name = 'profesor';
    modalRef.componentInstance.option = {id: option.id, nombre: option.nombre + " " + option.apellidos};
    modalRef.closed.subscribe((o: Option) => {
      this.eliminarProfesor(o.id)
    });
  }
}
