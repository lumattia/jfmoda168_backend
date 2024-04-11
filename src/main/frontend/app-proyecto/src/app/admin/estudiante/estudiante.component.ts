import {Component, EventEmitter, Output} from '@angular/core';
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {EstudianteRow} from "../../interfaces/estudiante";
import {EstudianteService} from "../../services/estudiante.service";
import {FormsModule} from "@angular/forms";
import {NgbPagination} from "@ng-bootstrap/ng-bootstrap";
@Component({
  selector: 'app-estudiante',
  standalone: true,
  imports: [
    NgForOf,
    FormsModule,
    NgIf,
    NgbPagination,
    NgClass
  ],
  templateUrl: './estudiante.component.html',
  styleUrl: './estudiante.component.css'
})
export class EstudianteComponent {
  estudiantes:EstudianteRow[]=[];
  page= 1;
  pageSize:number=10;
  searchTerm:string="";
  @Output() bloquearODesbloquear = new EventEmitter<EstudianteRow>();
  collectionSize:number=0;
  estudianteABorrar:any={}

  sortColumn= '';
  sortDirection= '';

  constructor(private estudianteService: EstudianteService) {
    this.pageChanged()
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
    this.pageChanged()
  }
  pageChanged(){
    this.estudianteService.buscarEstudiante(this.searchTerm,this.page,this.pageSize,this.sortColumn,this.sortDirection).subscribe({
      next: (data:any) => {
        this.estudiantes = (data.content as EstudianteRow[])
        this.page=data.pageable.pageNumber+1
        this.collectionSize=data.totalElements
      },
      error: (error) => {
        console.error(error);
      }
    })
  }
  cambiarEstado(estudiante:EstudianteRow){
    this.bloquearODesbloquear.emit(estudiante);
  }
  borrar(estudiante:EstudianteRow){
    this.estudianteABorrar=estudiante;
  }
  eliminarEstudiante(id: number) {
    this.estudianteService.deleteEstudiante(id).subscribe({
      next: () => {
        this.estudiantes = this.estudiantes.filter(c => c.id != id)
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}
