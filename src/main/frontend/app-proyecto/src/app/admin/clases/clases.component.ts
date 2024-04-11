import { Component } from '@angular/core';
import {ClaseService} from "../../services/clase.service";
import {NgForOf} from "@angular/common";
import {CursoService} from "../../services/curso.service";
import {AsignaturaService} from "../../services/asignatura.service";
import {FormsModule} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {Option} from "../../interfaces/option";
import {MdbDropdownModule} from "mdb-angular-ui-kit/dropdown";
import {ModalComponent} from "../../util/modal/modal.component";
import {MdbModalModule, MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";

@Component({
  selector: 'app-clases',
  standalone: true,
  imports: [
    NgForOf,
    FormsModule,
    MdbModalModule,
    MdbDropdownModule
  ],
  templateUrl: './clases.component.html',
  styleUrl: './clases.component.css'
})
export class ClasesComponent {
  modalRef: MdbModalRef<ModalComponent> | null = null;
  clases:Option[]=[];
  cursos:Option[]=[];
  asignaturas:Option[]=[];
  cursoSeleccionado:number=-1;
  asignaturaSeleccionado:number=-1;

  claseAborrar:Option={id:0,
    nombre:""};
  constructor(private claseService: ClaseService,
              private cursoService:CursoService,
              private asignaturaService:AsignaturaService,
              private modalService: MdbModalService,
              private route:ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.asignaturaSeleccionado = params['asignaturaId']|| -1;
      this.cursoSeleccionado = params['cursoId']|| -1;
    });
    this.claseService.filterClase(this.cursoSeleccionado,this.asignaturaSeleccionado).subscribe({
      next: (data) => {
        this.clases = (data as Option[])
        this.cursoService.getCursos().subscribe(cursos=>this.cursos = (cursos as Option[]));
        this.asignaturaService.getAsignaturas().subscribe(asignaturas=>this.asignaturas = (asignaturas as Option[]));
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
  openModal(option:Option) {
    this.modalRef = this.modalService.open(ModalComponent, {
      data: { action: 'Eliminar',name:"asignatura",option:option },
    });
    this.modalRef.onClose.subscribe((o: Option) => {
      if (o!=null){
        this.eliminarClase(o.id)
      }
    });
  }
  filtrarClases(){
    this.claseService.filterClase(this.cursoSeleccionado,this.asignaturaSeleccionado).subscribe({
      next: (data) => {
        this.clases = (data as Option[])
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
  crearClase(){
    this.claseService.crearClase(this.cursoSeleccionado,this.asignaturaSeleccionado).subscribe({
      next: (data) => {
        this.clases.push(data as Option)
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
  borrar(clase:Option){
    this.claseAborrar=clase;
  }
}

