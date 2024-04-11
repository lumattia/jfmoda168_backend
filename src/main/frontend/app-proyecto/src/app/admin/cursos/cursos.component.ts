import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {CursoService} from "../../services/curso.service";
import {RouterLink} from "@angular/router";
import {Option} from "../../interfaces/option";
import {MdbDropdownModule} from "mdb-angular-ui-kit/dropdown";
import {ModalComponent} from "../../util/modal/modal.component";
import {MdbModalModule, MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";

@Component({
  selector: 'app-cursos',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    RouterLink,
    MdbModalModule,
    MdbDropdownModule
  ],
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.css'
})
export class CursosComponent {
  modalRef: MdbModalRef<ModalComponent> | null = null;
  searchTerm:string="";
  cursos: Option[] = [];
    nombreCurso:string="";
    cursoABorrar:Option={
        id:0,
        nombre:""
    }
    existeCurso:boolean=false;
  constructor(private modalService: MdbModalService,private cursoService: CursoService) {
    this.buscar();
  }
  openModal(option:Option) {
    this.modalRef = this.modalService.open(ModalComponent, {
      data: { action: 'Eliminar',name:"curso",option:option },
    });
    this.modalRef.onClose.subscribe((o: Option) => {
      if (o!=null){
        this.eliminarCurso(o.id)
      }
    });
  }
  buscar(){
    this.cursoService.buscarAsignatura(this.searchTerm).subscribe({
      next: (data:any) => {
        this.cursos = (data as Option[])
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
                    this.cursos.push(data as Option)
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
    borrar(curso:Option){
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
