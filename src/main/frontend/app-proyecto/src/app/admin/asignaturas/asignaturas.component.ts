import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {AsignaturaService} from "../../services/asignatura.service";
import {RouterLink} from "@angular/router";
import {Option} from "../../interfaces/option";
import {MdbModalModule, MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";
import {ModalComponent} from "../../util/modal/modal.component";
import {MdbDropdownModule} from "mdb-angular-ui-kit/dropdown";

@Component({
    selector: 'app-asignaturas',
    standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    RouterLink,
    MdbModalModule,
    MdbDropdownModule
  ],
    templateUrl: './asignaturas.component.html',
    styleUrl: './asignaturas.component.css'
})
export class AsignaturasComponent {
  modalRef: MdbModalRef<ModalComponent> | null = null;
  searchTerm:string="";
  asignaturas: Option[] = [];
  nombreAsignatura:string="";
  existeAsignatura:boolean=false;
  constructor(private modalService: MdbModalService,private asignaturaService: AsignaturaService) {
      this.buscar();
  }
  openModal(option:Option) {
    this.modalRef = this.modalService.open(ModalComponent, {
      data: { action: 'Eliminar',name:"asignatura",option:option },
    });
    this.modalRef.onClose.subscribe((o: Option) => {
      if (o!=null){
        this.eliminarAsignatura(o.id)
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
        this.existeAsignatura=this.asignaturas.filter(a=>a.nombre==this.nombreAsignatura).length==1
        if (!this.existeAsignatura){
            this.asignaturaService.crearAsignatura(this.nombreAsignatura).subscribe({
                next: (data) => {
                    this.asignaturas.push(data as Option)
                },
                error: (error) => {
                    console.error(error);
                }
            });
            // Establecer un temporizador para cambiar existeAsignatura a false después de 2 segundos
        }else{
            setTimeout(() => {
                this.existeAsignatura = false;
            }, 1500);
        }
    }
    eliminarAsignatura(id: number) {
        this.asignaturaService.deleteAsignatura(id).subscribe({
            next: (data) => {
                this.asignaturas = this.asignaturas.filter(a => a.id != id)
            },
            error: (error) => {
                console.error(error);
            }
        });
    }
}
