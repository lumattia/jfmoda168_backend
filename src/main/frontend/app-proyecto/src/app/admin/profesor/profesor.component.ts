import {Component, Input, QueryList, ViewChildren} from '@angular/core';
import {AsyncPipe, DecimalPipe, NgFor, NgIf} from "@angular/common";
import {ProfesorService} from "../../services/profesor.service";
import {Profesor} from "../../interfaces/profesor";
import {NgbHighlight, NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import {NgbdSortableHeader, SortEvent} from "../../utils/sortable.directive";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-profesores',
  standalone: true,
  imports: [NgFor, DecimalPipe, FormsModule, AsyncPipe, NgbHighlight, NgbdSortableHeader, NgbPaginationModule, NgIf],
  templateUrl: './profesor.component.html',
  styleUrl: './profesor.component.css'
})
export class ProfesorComponent {
  profesores:Profesor[]=[];
  page= 1;
  pageSize:number=10;
  @Input() searchTerm:string="";
  sortColumn= '';
  sortDirection= '';
  collectionSize:number=0;
  profesorABorrar:any={}




  onSort({ column, direction }: SortEvent) {
    this.sortColumn = column;
    this.sortDirection = direction;
  }
  constructor(private profesorService: ProfesorService) {
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
  borrar(profesor:Profesor){
    this.profesorABorrar=profesor;
  }
  eliminarProfesor(id: number) {
    this.profesorService.deleteProfesor(id).subscribe({
      next: (data) => {
        this.profesores = this.profesores.filter(c => c.id != id)
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}
