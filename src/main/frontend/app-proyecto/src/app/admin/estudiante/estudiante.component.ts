import {Component, Input} from '@angular/core';
import {NgForOf} from "@angular/common";
import {Estudiante} from "../../interfaces/estudiante";
import {EstudianteService} from "../../services/estudiante.service";

@Component({
  selector: 'app-estudiante',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './estudiante.component.html',
  styleUrl: './estudiante.component.css'
})
export class EstudianteComponent {
  @Input() estudiantes: Estudiante[] = [];
  nombreEstudiante: string = "";
  estudianteABorrar: Estudiante = {
    id: 0,
    nombre: "",
    apellido1: "",
    apellido2: "",
    email: "",
    roles: [],
    blocked: false,
    aula: ""
  }
  existeEstudiante: boolean = false;

  constructor(private estudianteService: EstudianteService) {
  }

  crearEstudiante() {
    this.existeEstudiante = this.estudiantes.filter(c => c.nombre == this.nombreEstudiante).length == 1
    if (!this.existeEstudiante) {
      this.estudianteService.crearEstudiante(this.nombreEstudiante).subscribe({
        next: (data) => {
          this.estudiantes.push(data as Estudiante)
        },
        error: (error) => {
          console.error(error);
        }
      });
      // Establecer un temporizador para cambiar existeEstudiante a false después de 2 segundos
    } else {
      setTimeout(() => {
        this.existeEstudiante = false;
      }, 1500);
    }
  }

  borrar(estudiante: Estudiante) {
    this.estudianteABorrar = estudiante;
  }

  eliminarEstudiante(id: number) {
    this.estudianteService.deleteEstudiante(id).subscribe({
      next: (data) => {
        this.estudiantes = this.estudiantes.filter(c => c.id != id)
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}
