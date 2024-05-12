import {Component, Input} from '@angular/core';
import {TareaDetail} from "../../../../../../interfaces/tareaFase";

@Component({
  selector: 'app-tarea',
  standalone: true,
  imports: [],
  templateUrl: './tarea-estudiante.component.html',
  styleUrl: './tarea-estudiante.component.css'
})
export class TareaEstudianteComponent {
  @Input() tarea: TareaDetail = <TareaDetail>{};
  constructor() {
  }
}
