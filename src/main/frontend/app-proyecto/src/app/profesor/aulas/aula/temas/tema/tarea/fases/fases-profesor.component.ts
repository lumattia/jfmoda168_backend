import { Component } from '@angular/core';
import { Location } from '@angular/common';
import {
  NgbAccordionBody,
  NgbAccordionButton, NgbAccordionCollapse,
  NgbAccordionDirective,
  NgbAccordionHeader,
  NgbAccordionItem
} from "@ng-bootstrap/ng-bootstrap";
import {NgForOf, NgIf} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {TareaFase} from "../../../../../../../interfaces/tareaFase";
import {TareaService} from "../../../../../../../services/tarea.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Pregunta} from "../../../../../../../interfaces/pregunta";
import {Respuesta} from "../../../../../../../interfaces/respuesta";

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
    r.pregunta= {id:pregunta.id,nombre:""};
    pregunta.respuestas.push(r)
  }
  nuevaPregunta(){
    let p:Pregunta=<Pregunta>{};
    p.respuestas=[];
    p.fase= {id:this.tarea.fases[this.show].id,nombre:""};
    this.tarea.fases[this.show].preguntas.push(p)
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
