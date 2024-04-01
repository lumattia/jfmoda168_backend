import {Component, Input} from '@angular/core';
import {ProfesorComponent} from "../profesor/profesor.component";
import {ProfesorService} from "../../services/profesor.service";
import {Profesor} from "../../interfaces/profesor";
import {Estudiante} from "../../interfaces/estudiante";
import {EstudianteService} from "../../services/estudiante.service";
import {EstudianteComponent} from "../estudiante/estudiante.component";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [
    ProfesorComponent,
    EstudianteComponent,
    FormsModule
  ],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.css'
})
export class UsuariosComponent {
  searchTerm:string="";
  profesores: Profesor[] = [];
  estudiantes: Estudiante[] = [];
  constructor(private profesorService: ProfesorService,private estudianteService: EstudianteService) {
    this.profesorService.getProfesores().subscribe({
      next: (data) => {
        this.profesores = (data as Profesor[])
      },
      error: (error) => {
        console.error(error);
      }
    });
    this.estudianteService.getEstudiantes().subscribe({
      next: (data:any) => {
        this.estudiantes = (data.content as Estudiante[])
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}


