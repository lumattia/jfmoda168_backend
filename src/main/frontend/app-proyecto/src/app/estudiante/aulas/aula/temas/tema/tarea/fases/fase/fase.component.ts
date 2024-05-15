import {Component, Input} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {
    NgbAccordionBody,
    NgbAccordionButton,
    NgbAccordionCollapse,
    NgbAccordionDirective, NgbAccordionHeader, NgbAccordionItem
} from "@ng-bootstrap/ng-bootstrap";
import {FaseService} from "../../../../../../../../services/fase.service";
import {FaseEstudiante} from "../../../../../../../../interfaces/fase-estudiante";

@Component({
  selector: 'app-fase',
  standalone: true,
    imports: [
        FormsModule,
        NgForOf,
        NgbAccordionBody,
        NgbAccordionButton,
        NgbAccordionCollapse,
        NgbAccordionDirective,
        NgbAccordionHeader,
        NgbAccordionItem
    ],
  templateUrl: './fase.component.html',
  styleUrl: './fase.component.css'
})
export class FaseComponent {
  @Input() nivel:number=0;
  @Input() idTarea:number=0;
  fase:FaseEstudiante=<FaseEstudiante>{}
  constructor(private faseService:FaseService) {

  }
  ngOnInit(){
    this.faseService.getFase(this.idTarea,this.nivel).subscribe({
      next: (data) => {
        console.log(data);
        this.fase = data;
      },
      error: (error) => {
        alert(error);
      }
    })
  }
}
