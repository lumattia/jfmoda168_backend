import {Component, inject} from '@angular/core';
import { Location } from '@angular/common';
import {
  NgbAccordionBody,
  NgbAccordionButton, NgbAccordionCollapse,
  NgbAccordionDirective,
  NgbAccordionHeader,
  NgbAccordionItem, NgbModal
} from "@ng-bootstrap/ng-bootstrap";
import {NgForOf, NgIf} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {TareaFase} from "../../../../../../../interfaces/tareaFase";
import {TareaService} from "../../../../../../../services/tarea.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Pregunta} from "../../../../../../../interfaces/pregunta";
import {Respuesta} from "../../../../../../../interfaces/respuesta";
import {ModalComponent} from "../../../../../../../util/modal/modal.component";

@Component({
  selector: 'app-fases-profesor',
  standalone: true,
  imports: [
    NgbAccordionDirective,
    NgbAccordionItem,
    NgbAccordionHeader,
    NgbAccordionButton,
    NgbAccordionCollapse,
    NgbAccordionBody,
    NgIf,
    NgForOf,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './fases-profesor.component.html',
  styleUrl: './fases-profesor.component.css'
})
export class FasesProfesorComponent {
  private modalService = inject(NgbModal);
  tarea:TareaFase=<TareaFase>{};
  show:number=0;
  constructor(route:ActivatedRoute,private tareaService:TareaService,private location: Location) {
    route.params.subscribe(p => {
      let id = Number(p['id'])||0;
      tareaService.getTarea(id).subscribe({
        next: (t) => {
          this.tarea = t as TareaFase;
        },
        error: (error) => {
          alert(error);
        }
      })
    })
  }
  nuevaRespuesta(pregunta:Pregunta){
    let r:Respuesta=<Respuesta>{};
    pregunta.respuestas.push(r)
  }
  nuevaPregunta(){
    let p:Pregunta=<Pregunta>{};
    p.respuestas=[];
    this.tarea.fases[this.show].preguntas.push(p)
  }
  openEliminarModal(i:number) {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.name = 'pregunta';
    modalRef.componentInstance.option = {nombre:"Pregunta "+(i+1)};
    modalRef.closed.subscribe(() => {
      this.tarea.fases[this.show].preguntas.splice(i,1);
    });
  }
  EliminarRespuesta(iPregunta:number,iRespuesta:number) {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.name = 'pregunta';
    modalRef.componentInstance.option = {nombre:"Respuesta "+(iRespuesta+1)+" de la pregunta "+(iPregunta+1)};
    modalRef.closed.subscribe(() => {
      this.tarea.fases[this.show].preguntas[iPregunta].respuestas.splice(iRespuesta,1);
    });
  }
  cancelar(){
    this.location.back()
  }
  guardar(){
    this.tareaService.saveTarea(this.tarea).subscribe({
      next: () => {
        this.location.back()
      },
      error: (error) => {
        alert(error);
      }
    })
  }
}
