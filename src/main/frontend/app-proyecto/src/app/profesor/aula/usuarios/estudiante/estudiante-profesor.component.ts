import {Component, inject} from '@angular/core';
import {NgClass, NgFor, NgIf} from "@angular/common";
import {EstudianteRow} from "../../../../interfaces/estudiante";
import {NgbModal, NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import {FormsModule} from "@angular/forms";
import {Option} from "../../../../interfaces/option";
import {ModalComponent} from "../../../../util/modal/modal.component";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AulaService} from "../../../../services/aula.service";

@Component({
  selector: 'app-estudiante-profesor',
  standalone: true,
  imports: [NgFor, NgIf, NgbPaginationModule, NgClass, FormsModule, RouterLink,
  ],
  templateUrl: './estudiante-profesor.component.html',
  styleUrl: './estudiante-profesor.component.css'
})
export class EstudianteProfesorComponent {

  private modalService = inject(NgbModal);
  estudiantes:EstudianteRow[]=[];
  searchTerm:string="";
  sortColumn= '';
  sortDirection= '';
  id:number=0
  constructor(private aulaService:AulaService,private route:ActivatedRoute) {
    this.route.parent?.params.subscribe(p => {
      this.id = Number(p['id'])||0;
      this.getEstudiantes();
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
  sort() {
    if (!(this.sortDirection === '' || this.sortColumn === '')) {
      const compare = (a: EstudianteRow, b: EstudianteRow) => {
        const v1 = a[this.sortColumn];
        const v2 = b[this.sortColumn];
        let res = v1 < v2 ? -1 : v1 > v2 ? 1 : 0
        return this.sortDirection === 'asc' ? res : -res;
      };
      this.estudiantes.sort(compare);
    }
  }
  getEstudiantes(){
    this.aulaService.getEstudiantes(this.id,this.searchTerm).subscribe({
      next: (data:any) => {
        this.estudiantes = (data as EstudianteRow[])
        this.sort()
      },
      error: (error) => {
        alert(error);
      }
    })
  }
  anadirEstudiante(){

  }
  eliminarEstudiante(id: number) {
    this.aulaService.removeEstudiante(this.id,id).subscribe({
      next: () => {
        this.getEstudiantes()
      },
      error: (error) => {
        alert(error);
      }
    });
  }
  openModal(option:EstudianteRow) {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.name = 'estudiante';
    modalRef.componentInstance.option = {id: option.id, nombre: option.nombre + " " + option.apellidos};
    modalRef.closed.subscribe((o: Option) => {
      this.eliminarEstudiante(o.id)
    });
  }
}
