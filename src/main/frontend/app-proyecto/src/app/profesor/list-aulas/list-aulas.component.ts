import { Component } from '@angular/core';
import {MdbModalModule, MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";
import {Aula, AulaForm} from "../../interfaces/aula";
import {Option} from "../../interfaces/option";
import {ProfesorService} from "../../services/profesor.service";
import {AulaService} from "../../services/aula.service";
import {StorageService} from "../../services/storage.service";
import {FormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {AulaEditModalComponent} from "../aula-edit-modal/aula-edit-modal.component";

@Component({
  selector: 'app-list-aulas',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    RouterLink,
    MdbModalModule
  ],
  templateUrl: './list-aulas.component.html',
  styleUrl: './list-aulas.component.css'
})
export class ListAulasComponent {
  modalRef: MdbModalRef<AulaEditModalComponent>|null= null;
  aula:AulaForm={
    id:0,
    clase:{id:-1,nombre:""},
    grupo:"",
    año:""
  }
  aulas:Option[]=[];
  clases:Option[]=[];
  constructor(private profesorService:ProfesorService,private aulaService:AulaService,
              private modalService: MdbModalService,private storageService: StorageService) {
    let id=storageService.getUser().content.id;
    profesorService.getAulas(id).subscribe({
      next: (a) => {
        this.aulas = a as Option[]
      },
      error: (error) => {
        console.error(error);
      }
    })
    profesorService.getClases(id).subscribe({
      next: (c) => {
        this.clases = c as Option[]
      },
      error: (error) => {
        console.error(error);
      }
    })
  }
  openEditarModal(id:number) {
    this.aulaService.getAula(id).subscribe({
      next: (a) => {
        this.modalRef = this.modalService.open(AulaEditModalComponent, {
          modalClass: 'modal-dialog-centered',
          data: { aula:{
            id:a.id,
              grupo:a.grupo,
              año:a.año,
              clase:{nombre:a.clase.nombre},
              tema:[]
            }},
        });
        this.modalRef.onClose.subscribe((a: Aula) => {
          if (a!=null){
            this.editarAula(a)
          }
        });
      },
      error: (error) => {
        console.error(error);
      }
    })


  }
  openEliminarModal(option:Option) {
    this.modalRef = this.modalService.open(AulaEditModalComponent, {
      data: { name:"Aula",option:option },
    });
    this.modalRef.onClose.subscribe((o: Option) => {
      if (o!=null){
        this.eliminarAula(o.id)
      }
    });
  }
  ngOnInit(){
    const anioActual = new Date().getFullYear()-2000;
    this.aula.año = `${anioActual}/${anioActual + 1}`;
  }
  crearAula(){
    console.log(this.aula)
    if (this.aula.clase.id>0){
      this.aulaService.crearAula(this.aula).subscribe({
        next: (data) => {
          console.log(data)
          this.aulas.push(data as Option)
        },
        error: (error) => {
          console.error(error);
        }
      })
    }
  }
  editarAula(o:Aula){
    this.aulaService.actualizarAula(o).subscribe({
      next: (data) => {
        this.aulas.splice(this.aulas.findIndex(a=>a.id==o.id), 1, data as Option);
      },
      error: (error) => {
        console.error(error);
      }
    })
  }
  eliminarAula(id: number) {
    this.aulaService.deleteAula(id).subscribe({
      next: (data) => {
        this.aulas = this.aulas.filter(a => a.id != id)
      },
      error: (error) => {
        alert("No se ha podido eliminar el aula")
        console.error(error);
      }
    });
  }
}
