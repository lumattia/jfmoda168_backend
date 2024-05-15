import {Component} from '@angular/core';
import {CommonModule, Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {FaseService} from "../../../../../../../services/fase.service";
import {FaseComponent} from "./fase/fase.component";


@Component({
  selector: 'app-fases-estudiante',
  standalone: true,
  imports: [CommonModule, FaseComponent
  ],
  templateUrl: './fases-estudiante.component.html',
  styleUrl: './fases-estudiante.component.css'
})
export class FasesEstudianteComponent {
  idTarea:number=0;
  nivelMax:number=1;
  nivel:number=1;
  constructor(route:ActivatedRoute,
    private faseService:FaseService,private location: Location) {
    route.params.subscribe(p => {
      this.idTarea = Number(p['id'])||0;
      faseService.getNivelMax(this.idTarea).subscribe({
        next: (n) => {
          this.nivelMax = n;
          this.nivel=this.nivelMax;
        },
        error: (error) => {
          alert(error);
        }
      })
    })
  }
  cancelar(){
    this.location.back()
  }
}
