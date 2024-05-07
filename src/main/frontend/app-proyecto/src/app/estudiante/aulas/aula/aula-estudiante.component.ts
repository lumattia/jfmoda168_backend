import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-aula-estudiante',
  standalone: true,
  imports: [
    RouterLink,
    RouterOutlet
  ],
  templateUrl: './aula-estudiante.component.html',
  styleUrl: './aula-estudiante.component.css'
})
export class AulaEstudianteComponent {
}
