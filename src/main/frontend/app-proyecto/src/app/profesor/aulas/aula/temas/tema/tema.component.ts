import {Component, EventEmitter, inject, Input, Output} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {Option} from "../../../../../interfaces/option";
import {ModalComponent} from "../../../../../util/modal/modal.component";
import {FormModalComponent} from "../../../../../util/form-modal/form-modal.component";
import {Tema} from "../../../../../interfaces/tema";
import {TemaService} from "../../../../../services/tema.service";
import {TareaComponent} from "./tarea/tarea.component";
import {Tarea} from "../../../../../interfaces/tarea";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-tema',
  standalone: true,
    imports: [
        FormsModule,
        NgForOf,
        TareaComponent
    ],
  templateUrl: './tema.component.html',
  styleUrl: './tema.component.css'
})
export class TemaComponent {
    private modalService = inject(NgbModal);
    @Input() tema: Tema = <Tema>{};
    @Output() delete = new EventEmitter<Tema>;
    tareaACrear: string = "";

    constructor(private temaService: TemaService) {
    }
    openEliminarModal(option:Option) {
        const modalRef = this.modalService.open(ModalComponent);
        modalRef.componentInstance.name = 'tema';
        modalRef.componentInstance.option = option;
        modalRef.closed.subscribe((o: Option) => {
            this.eliminarTema(o.id)
        });
    }
    openEditarModal(o:Option) {
        const modalRef = this.modalService.open(FormModalComponent,{centered:true});
        modalRef.componentInstance.name = 'tema';
        modalRef.componentInstance.option =  {id: o.id, nombre: o.nombre};
        modalRef.closed.subscribe((o: Option) => {
            this.editarTema(o)
        });
    }
    editarTema(o: Option) {
        this.temaService.actualizarTema(o).subscribe({
            next: (data) => {
                this.tema.nombre = o.nombre;
            },
            error: (error) => {
                alert(error);
            }
        })
    }
    eliminarTema(id: number) {
        this.temaService.deleteTema(id).subscribe({
            next: (data) => {
                this.delete.emit(this.tema)
            },
            error: (error) => {
                alert("No se ha podido eliminar el tema")
                alert(error);
            }
        });
    }
    crearTarea() {
        this.temaService.crearTarea(this.tema.id,this.tareaACrear).subscribe({
            next: (data) => {
                this.tema.tareas.push(data as Tarea)
            },
            error: (error) => {
                alert(error);
            }
        });
    }
    deleteTarea(tarea:Option){
        this.tema.tareas = this.tema.tareas.filter(a => a.id != tarea.id)
    }
}
