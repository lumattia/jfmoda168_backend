import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {AsignaturaService} from "../../services/asignatura.service";
import {RouterLink} from "@angular/router";
import {Option} from "../../interfaces/option";
import {MdbModalModule, MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";
import {ModalComponent} from "../../util/modal/modal.component";
import {FormModalComponent} from "../../util/form-modal/form-modal.component";

@Component({
    selector: 'app-asignaturas',
    standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    RouterLink,
    MdbModalModule
  ],
    templateUrl: './asignaturas.component.html',
    styleUrl: './asignaturas.component.css'
})
export class AsignaturasComponent {
  modalRef: MdbModalRef<ModalComponent> | null = null;
  searchTerm:string="";
  asignaturas: Option[] = [];
  nombreAsignatura:string="";
  constructor(private modalService: MdbModalService,private asignaturaService: AsignaturaService) {
      this.buscar();
  }
  openEliminarModal(option:Option) {
    this.modalRef = this.modalService.open(ModalComponent, {
      data: { name:"asignatura",option:option },
    });
    this.modalRef.onClose.subscribe((o: Option) => {
      if (o!=null){
        this.eliminarAsignatura(o.id)
      }
    });
  }
  openEditarModal(o:Option) {
    this.modalRef = this.modalService.open(FormModalComponent, {
      modalClass: 'modal-dialog-centered',
      data: { name:"asignatura",option: {id:o.id,nombre:o.nombre} },
    });
    this.modalRef.onClose.subscribe((o: Option) => {
      if (o!=null){
        this.editarAsignatura(o)
      }
    });
  }
    buscar(){
      this.asignaturaService.buscarAsignatura(this.searchTerm).subscribe({
        next: (data:any) => {
          this.asignaturas = (data as Option[])
        },
        error: (error) => {
          console.error(error);
        }
      })
    }
    crearAsignatura() {
        if (this.existe(this.nombreAsignatura)){
            this.asignaturaService.crearAsignatura(this.nombreAsignatura).subscribe({
                next: (data) => {
                    this.asignaturas.push(data as Option)
                },
                error: (error) => {
                    console.error(error);
                }
            });
        }else{
          alert("¡La asignatura ya existe!")
        }
    }
    editarAsignatura(o:Option){
      if (this.existe(o.nombre)){
          this.asignaturaService.actualizarAsignatura(o).subscribe({
          next: (data) => {
            this.asignaturas.splice(this.asignaturas.findIndex(a=>a.id==o.id), 1, o);
          },
          error: (error) => {
            console.error(error);
          }
        })
      }else{
        alert("¡La asignatura ya existe!")
      }
    }
    eliminarAsignatura(id: number) {
        this.asignaturaService.deleteAsignatura(id).subscribe({
            next: (data) => {
                this.asignaturas = this.asignaturas.filter(a => a.id != id)
            },
            error: (error) => {
              alert("No se ha podido eliminar la asignatura")
              console.error(error);
            }
        });
    }
    existe(nombre:string):boolean{
      return this.asignaturas.filter(a=>a.nombre==nombre).length==0;
    }
}
