import { Component } from '@angular/core';
import {MdbModalRef} from "mdb-angular-ui-kit/modal";
import {Option} from "../../interfaces/option";
import {ModalComponent} from "../modal/modal.component";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './form-modal.component.html',
  styleUrl: './form-modal.component.html'
})
export class FormModalComponent {
  name: string | null = null;
  option:Option= {
    id:0,
    nombre:""
  };
  constructor(public modalRef: MdbModalRef<ModalComponent>) {}
  close(): void {
      this.modalRef.close(this.option);
  }
}
