import { Component } from '@angular/core';
import {MdbModalRef} from "mdb-angular-ui-kit/modal";
import {ModalComponent} from "../../util/modal/modal.component";
import {Aula} from "../../interfaces/aula";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-aula-edit-modal',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './aula-edit-modal.component.html',
  styleUrl: './aula-edit-modal.component.css'
})
export class AulaEditModalComponent {
  aula: Aula=<Aula>{};
  ano:string="";
  constructor(public modalRef: MdbModalRef<ModalComponent>) {

  }
  ngOnInit(){
    this.ano=this.aula.año;
  }
  close(): void {
    this.aula.año=this.ano
    this.modalRef.close(this.aula);
  }
}
