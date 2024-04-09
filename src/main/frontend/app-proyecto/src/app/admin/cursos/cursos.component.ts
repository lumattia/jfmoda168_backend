import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {CursoService} from "../../services/curso.service";
import {Curso} from "../../interfaces/curso";
import {RouterLink} from "@angular/router";
import {Asignatura} from "../../interfaces/asignatura";

@Component({
  selector: 'app-cursos',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    RouterLink
  ],
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.css'
})
export class CursosComponent {
  searchTerm:string="";
  cursos: Curso[] = [];
    nombreCurso:string="";
    cursoABorrar:Curso={
        id:0,
        nombre:"",
        clases:[]
    }
    existeCurso:boolean=false;
    constructor(private cursoService: CursoService) {
        this.cursoService.getCursos().subscribe({
            next: (data) => {
                this.cursos = (data as Curso[])
            },
            error: (error) => {
                console.error(error);
            }
        });
    }
  buscar(){
    this.cursoService.buscarAsignatura(this.searchTerm).subscribe({
      next: (data:any) => {
        this.cursos = (data as Asignatura[])
      },
      error: (error) => {
        console.error(error);
      }
    })
  }
    crearCurso() {
        this.existeCurso=this.cursos.filter(c=>c.nombre==this.nombreCurso).length==1
        if (!this.existeCurso){
            this.cursoService.crearCurso(this.nombreCurso).subscribe({
                next: (data) => {
                    this.cursos.push(data as Curso)
                },
                error: (error) => {
                    console.error(error);
                }
            });
            // Establecer un temporizador para cambiar existeCurso a false después de 2 segundos
        }else{
            setTimeout(() => {
                this.existeCurso = false;
            }, 1500);
        }
    }
    borrar(curso:Curso){
        this.cursoABorrar=curso;
    }
    eliminarCurso(id: number) {
        this.cursoService.deleteCurso(id).subscribe({
            next: (data) => {
                this.cursos = this.cursos.filter(c => c.id != id)
            },
            error: (error) => {
                console.error(error);
            }
        });
    }
}
