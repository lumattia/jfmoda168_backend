import { Component } from '@angular/core';
import {AulaService} from "../../services/aula.service";
import {MdbModalModule, MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";
import {Option} from "../../interfaces/option";
import {TemaService} from "../../services/tema.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {Aula} from "../../interfaces/aula";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {NgbPagination} from "@ng-bootstrap/ng-bootstrap";
import {ModalComponent} from "../../util/modal/modal.component";
import {FormModalComponent} from "../../util/form-modal/form-modal.component";
import {Tema} from "../../interfaces/tema";

@Component({
  selector: 'app-aula',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    ReactiveFormsModule,
    NgIf,
    NgbPagination,
    RouterLink,
    MdbModalModule
  ],
  templateUrl: './aula.component.html',
  styleUrl: './aula.component.css'
})
export class AulaComponent {
  modalRef: MdbModalRef<ModalComponent> | null = null;
  aula: Aula=<Aula>{};
  temaACrear:Tema=<Tema>{};
  constructor(private aulaService:AulaService,private temaService:TemaService,
              private modalService: MdbModalService,private route:ActivatedRoute) {
    this.route.params.subscribe(p => {
      let id = Number(p['id'])||0;
      this.temaACrear.aula.id=id
      aulaService.getAula(id).subscribe({
        next: (a) => {
          this.aula = a
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }
  openEliminarModal(option:Option) {
    this.modalRef = this.modalService.open(ModalComponent, {
      data: { name:"tema",option:option },
    });
    this.modalRef.onClose.subscribe((o: Option) => {
      if (o!=null){
        this.eliminarTema(o.id)
      }
    });
  }
  openEditarModal(o:Option) {
    this.modalRef = this.modalService.open(FormModalComponent, {
      modalClass: 'modal-dialog-centered',
      data: { name:"tema",option: {id:o.id,nombre:o.nombre} },
    });
    this.modalRef.onClose.subscribe((o: Option) => {
      if (o!=null){
        this.editarTema(o)
      }
    });
  }
  crearTema() {
    if (this.existe(this.temaACrear.nombre)){
      this.temaService.crearTema(this.temaACrear).subscribe({
        next: (data) => {
          this.aula.temas.push(data as Tema)
        },
        error: (error) => {
          console.error(error);
        }
      });
    }else{
      alert("¡La asignatura ya existe!")
    }
  }
  editarTema(o:Option){
    if (this.existe(o.nombre)){
      this.temaService.actualizarTema(o).subscribe({
        next: (data) => {
          let t=this.aula.temas.find(t=>t.id)
          if (t!=undefined){
            t.nombre=o.nombre;
          }else{
            alert("Tema cambiado pero algo pasó.")
          }
        },
        error: (error) => {
          console.error(error);
        }
      })
    }else{
      alert("¡El tema ya existe!")
    }
  }
  eliminarTema(id: number) {
    this.temaService.deleteTema(id).subscribe({
      next: (data) => {
        this.aula.temas = this.aula.temas.filter(a => a.id != id)
      },
      error: (error) => {
        alert("No se ha podido eliminar el tema")
        console.error(error);
      }
    });
  }
  existe(nombre:string):boolean{
    return this.aula.temas.filter(a=>a.nombre==nombre).length==0;
  }
  getYear() {
    return this.aula.año;
  }
}
