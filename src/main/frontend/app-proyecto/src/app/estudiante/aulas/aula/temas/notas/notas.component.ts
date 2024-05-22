import { Component } from '@angular/core';
import {EstudianteService} from "../../../../../services/estudiante.service";
import {ActivatedRoute} from "@angular/router";
import {PuntoTarea} from "../../../../../interfaces/tarea-estudiante";
import {KeyValuePipe, NgForOf, NgIf} from "@angular/common";
import {
  NgbAccordionBody,
  NgbAccordionButton,
  NgbAccordionCollapse,
  NgbAccordionDirective, NgbAccordionHeader, NgbAccordionItem
} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-notas',
  standalone: true,
  imports: [
    NgForOf,
    NgbAccordionBody,
    NgbAccordionButton,
    NgbAccordionCollapse,
    NgbAccordionDirective,
    NgbAccordionHeader,
    NgbAccordionItem,
    NgIf,
    KeyValuePipe
  ],
  templateUrl: './notas.component.html',
  styleUrl: './notas.component.css'
})
export class NotasComponent {
  puntos: { [temaNombre: string]: PuntoTarea[] }[]= [];
  constructor(estudianteService:EstudianteService,route:ActivatedRoute) {
    route.parent?.params.subscribe(p => {
      let idAula = Number(p['id'])||0;
      estudianteService.getPuntos(idAula).subscribe({
        next: (t) => {
          console.log(t)
          this.puntos = t;
        },
        error: (error) => {
          alert(error);
        }
      })
    })
  }

  protected readonly Object = Object;
}
