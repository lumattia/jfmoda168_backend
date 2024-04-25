import {Component, EventEmitter, inject, Input, Output} from '@angular/core';
import {Option} from "../../../../interfaces/option";
import {ModalComponent} from "../../../../util/modal/modal.component";
import {FormModalComponent} from "../../../../util/form-modal/form-modal.component";
import {TareaService} from "../../../../services/tarea.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-tarea',
  standalone: true,
  imports: [],
  templateUrl: './tarea.component.html',
  styleUrl: './tarea.component.css'
})
export class TareaComponent {
  private modalService = inject(NgbModal);
  @Input() tarea: Option = <Option>{};
  @Output() delete = new EventEmitter<Option>;
  constructor(private tareaService: TareaService) {
  }
  openEliminarModal(option:Option) {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.name = 'tarea';
    modalRef.componentInstance.option = option;
    modalRef.closed.subscribe((o: Option) => {
      this.eliminarTarea(o.id)
    });
  }
  openEditarModal(o:Option) {
    const modalRef = this.modalService.open(FormModalComponent,{centered:true});
    modalRef.componentInstance.name = 'tarea';
    modalRef.componentInstance.option =  {id: o.id, nombre: o.nombre};
    modalRef.closed.subscribe((o: Option) => {
      this.editarTarea(o)
    });
  }
  editarTarea(o:Option){
    this.tareaService.actualizarTarea(o).subscribe({
      next: (data) => {
        this.tarea=data as Option;
      },
      error: (error) => {
        alert(error.error);
      }
    })
  }
  eliminarTarea(id: number) {
    this.tareaService.deleteTarea(id).subscribe({
      next: (data) => {
        this.delete.emit(this.tarea)
      },
      error: (error) => {
        alert("No se ha podido eliminar el tema")
        console.error(error);
      }
    });
  }
}
