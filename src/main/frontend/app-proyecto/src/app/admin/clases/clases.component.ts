import { Component } from '@angular/core';
import {Clase} from "../../interfaces/clase";
import {ClaseService} from "../../services/clase.service";
import {NgForOf} from "@angular/common";
import {Curso} from "../../interfaces/curso";
import {Asignatura} from "../../interfaces/asignatura";
import {CursoService} from "../../services/curso.service";
import {AsignaturaService} from "../../services/asignatura.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-clases',
  standalone: true,
  imports: [
    NgForOf,
    FormsModule
  ],
  templateUrl: './clases.component.html',
  styleUrl: './clases.component.css'
})
export class ClasesComponent {
  clases:Clase[]=[];
  cursos:Curso[]=[];
  asignaturas:Asignatura[]=[];
  cursoSeleccionado:number=-1;
  asignaturaSeleccionado:number=-1;

  claseAborrar:Clase={id:0,
    nombre:"",
    profesores:[]};
  constructor(private claseService: ClaseService,
              private cursoService:CursoService,
              private asignaturaService:AsignaturaService) {
    this.claseService.getClases().subscribe({
      next: (data) => {
        this.clases = (data as Clase[])
        this.cursoService.getCursos().subscribe(cursos=>this.cursos = (cursos as Curso[]));
        this.asignaturaService.getAsignaturas().subscribe(asignaturas=>this.asignaturas = (asignaturas as Asignatura[]));
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  filtrarClases(){
    this.claseService.filterClase(this.cursoSeleccionado,this.asignaturaSeleccionado).subscribe({
      next: (data) => {
        this.clases = (data as Clase[])
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
  crearClase(){
    this.claseService.crearClase(this.cursoSeleccionado,this.asignaturaSeleccionado).subscribe({
      next: (data) => {
        this.clases.push(data as Clase)
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
  eliminarClase(id:number){
    this.claseService.deleteClase(id).subscribe({
      next: (data) => {
        this.clases=this.clases.filter(c=>c.id!=id)
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
  borrar(clase:Clase){
    this.claseAborrar=clase;
  }
}

