import { Component } from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {NgbPagination} from "@ng-bootstrap/ng-bootstrap";
import {Aula} from "../../../../interfaces/aula";
import {TemaProfesorComponent} from "./tema/tema-profesor.component";
import {EstudianteService} from "../../../../services/estudiante.service";

@Component({
  selector: 'app-temas',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    ReactiveFormsModule,
    NgIf,
    NgbPagination,
    RouterLink,
    TemaProfesorComponent
  ],
  templateUrl: './temas-estudiante.component.html',
  styleUrl: './temas-estudiante.component.css'
})
export class TemasEstudianteComponent {
  aula: Aula=<Aula>{};
  constructor(estudianteService:EstudianteService,private route:ActivatedRoute) {
    this.route.params.subscribe(p => {
      let id = Number(p['id'])||0;
      estudianteService.getAula(id).subscribe({
        next: (a) => {
          this.aula = a;
        },
        error: (error) => {
          alert(error);
        }
      })
    })
  }
}
