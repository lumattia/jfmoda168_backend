import { Component } from '@angular/core';
import {MdbModalRef} from "mdb-angular-ui-kit/modal";
import {Option} from "../../interfaces/option";

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})
export class ModalComponent {
  action: string | null = null;
  name: string | null = null;
  option:Option|null=null;
  constructor(public modalRef: MdbModalRef<ModalComponent>) {}
  close(b:boolean): void {
    if (b){
      this.modalRef.close(this.option);
    }else{
      this.modalRef.close(null);
    }
  }
}
