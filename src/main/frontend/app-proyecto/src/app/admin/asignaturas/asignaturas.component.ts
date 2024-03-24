import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {AsignaturaService} from "../../services/asignatura.service";
import {Asignatura} from "../../interfaces/asignatura";

@Component({
    selector: 'app-asignaturas',
    standalone: true,
    imports: [
        FormsModule,
        NgForOf,
        NgIf
    ],
    templateUrl: './asignaturas.component.html',
    styleUrl: './asignaturas.component.css'
})
export class AsignaturasComponent {
    asignaturas: Asignatura[] = [];
    nombreAsignatura:string="";
    asignaturaABorrar:Asignatura={
        id:0,
        nombre:"",
        clases:[]
    }
    existeAsignatura:boolean=false;
    constructor(private asignaturaService: AsignaturaService) {
        this.asignaturaService.getAsignaturas().subscribe({
            next: (data) => {
                this.asignaturas = (data as Asignatura[])
            },
            error: (error) => {
                console.error(error);
            }
        });
    }
    crearAsignatura() {
        this.existeAsignatura=this.asignaturas.filter(a=>a.nombre==this.nombreAsignatura).length==1
        if (!this.existeAsignatura){
            this.asignaturaService.crearAsignatura(this.nombreAsignatura).subscribe({
                next: (data) => {
                    this.asignaturas.push(data as Asignatura)
                },
                error: (error) => {
                    console.error(error);
                }
            });
            // Establecer un temporizador para cambiar existeAsignatura a false después de 2 segundos
        }else{
            setTimeout(() => {
                this.existeAsignatura = false;
            }, 1500);
        }
    }
    borrar(asignatura:Asignatura){
        this.asignaturaABorrar=asignatura;
    }
    eliminarAsignatura(id: number) {
        this.asignaturaService.deleteAsignatura(id).subscribe({
            next: (data) => {
                this.asignaturas = this.asignaturas.filter(a => a.id != id)
            },
            error: (error) => {
                console.error(error);
            }
        });
    }
}
