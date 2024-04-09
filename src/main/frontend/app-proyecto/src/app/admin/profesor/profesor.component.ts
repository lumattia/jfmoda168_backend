import {Component, EventEmitter, Output} from '@angular/core';
import {NgClass, NgFor, NgIf} from "@angular/common";
import {ProfesorService} from "../../services/profesor.service";
import {Profesor} from "../../interfaces/profesor";
import {NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-profesores',
  standalone: true,
  imports: [NgFor, NgIf, NgbPaginationModule, NgClass, FormsModule],
  templateUrl: './profesor.component.html',
  styleUrl: './profesor.component.css'
})
export class ProfesorComponent {
  profesores:Profesor[]=[];
  page= 1;
  pageSize:number=10;
  searchTerm:string="";
  @Output() bloquearODesbloquear = new EventEmitter<Profesor>();
  collectionSize:number=0;
  profesorABorrar:any={}

  sortColumn= '';
  sortDirection= '';

  constructor(private profesorService: ProfesorService) {
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
    this.profesorService.buscarProfesor(this.searchTerm,this.page,this.pageSize,this.sortColumn,this.sortDirection).subscribe({
      next: (data:any) => {
        this.profesores = (data.content as Profesor[])
        this.page=data.pageable.pageNumber+1
        this.collectionSize=data.totalElements
      },
      error: (error) => {
        console.error(error);
      }
    })
  }
  cambiarEstado(profesor:Profesor){
    this.bloquearODesbloquear.emit(profesor);
  }
  borrar(profesor:Profesor){
    this.profesorABorrar=profesor;
  }
  eliminarProfesor(id: number) {
    this.profesorService.deleteProfesor(id).subscribe({
      next: () => {
        this.profesores = this.profesores.filter(c => c.id != id)
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}
