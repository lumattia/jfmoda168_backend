import {Component, EventEmitter, Output} from '@angular/core';
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {EstudianteRow} from "../../../interfaces/estudiante";
import {EstudianteService} from "../../../services/estudiante.service";
import {FormsModule} from "@angular/forms";
import {NgbPagination} from "@ng-bootstrap/ng-bootstrap";
import {MdbModalModule, MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";
import {ModalComponent} from "../../../util/modal/modal.component";
import {Option} from "../../../interfaces/option";
import {RouterLink} from "@angular/router";
@Component({
  selector: 'app-estudiante',
  standalone: true,
  imports: [
    NgForOf,
    FormsModule,
    NgIf,
    NgbPagination,
    NgClass,
    MdbModalModule,
    RouterLink
  ],
  templateUrl: './estudiante.component.html',
  styleUrl: './estudiante.component.css'
})
export class EstudianteComponent {
  modalRef: MdbModalRef<ModalComponent> | null = null;
  estudiantes:EstudianteRow[]=[];
  page= 1;
  pageSize:number=10;
  searchTerm:string="";
  @Output() bloquearODesbloquear = new EventEmitter<EstudianteRow>();
  collectionSize:number=0;

  sortColumn= '';
  sortDirection= '';

  constructor(private modalService: MdbModalService, private estudianteService: EstudianteService) {
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
  eliminarEstudiante(id: number) {
    this.estudianteService.deleteEstudiante(id).subscribe({
      next: () => {
        this.pageChanged()
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
  openModal(option:EstudianteRow) {
    this.modalRef = this.modalService.open(ModalComponent, {
      data: { name:"profesor",option: {id:option.id,nombre:option.nombre+" "+option.apellidos} },
    });
    this.modalRef.onClose.subscribe((o: Option) => {
      if (o!=null){
        this.eliminarEstudiante(o.id)
      }
    });
  }
}
