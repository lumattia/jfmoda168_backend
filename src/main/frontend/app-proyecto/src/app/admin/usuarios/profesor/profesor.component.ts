import {Component, EventEmitter, Output} from '@angular/core';
import {NgClass, NgFor, NgIf} from "@angular/common";
import {ProfesorService} from "../../../services/profesor.service";
import {ProfesorRow} from "../../../interfaces/profesor";
import {NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import {FormsModule} from "@angular/forms";
import {Option} from "../../../interfaces/option";
import {ModalComponent} from "../../../util/modal/modal.component";
import {MdbModalModule, MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-profesores',
  standalone: true,
  imports: [NgFor, NgIf, NgbPaginationModule, NgClass, FormsModule, MdbModalModule, RouterLink,
  ],
  templateUrl: './profesor.component.html',
  styleUrl: './profesor.component.css'
})
export class ProfesorComponent {
  modalRef: MdbModalRef<ModalComponent> | null = null;
  profesores:ProfesorRow[]=[];
  page:number= 1;
  pageSize:number=10;
  searchTerm:string="";
  @Output() bloquearODesbloquear = new EventEmitter<ProfesorRow>();
  collectionSize:number=0;

  sortColumn= '';
  sortDirection= '';

  constructor(private modalService: MdbModalService,private profesorService: ProfesorService) {
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
        this.profesores = (data.content as ProfesorRow[])
        this.page=data.pageable.pageNumber+1
        this.collectionSize=data.totalElements
      },
      error: (error) => {
        console.error(error);
      }
    })
  }
  cambiarEstado(profesor:ProfesorRow){
    this.bloquearODesbloquear.emit(profesor);
  }
  eliminarProfesor(id: number) {
    this.profesorService.deleteProfesor(id).subscribe({
      next: () => {
        this.pageChanged()
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
  openModal(option:ProfesorRow) {
    this.modalRef = this.modalService.open(ModalComponent, {
      data: { name:"profesor",option: {id:option.id,nombre:option.nombre+" "+option.apellidos} },
    });
    this.modalRef.onClose.subscribe((o: Option) => {
      if (o!=null){
        this.eliminarProfesor(o.id)
      }
    });
  }
}
