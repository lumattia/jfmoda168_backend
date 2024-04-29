import { Component } from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {NgbPagination} from "@ng-bootstrap/ng-bootstrap";
import {Aula} from "../../../../interfaces/aula";
import {AulaService} from "../../../../services/aula.service";
import {TemaComponent} from "./tema/tema.component";
import {Tema} from "../../../../interfaces/tema";

@Component({
  selector: 'app-aula',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    ReactiveFormsModule,
    NgIf,
    NgbPagination,
    RouterLink,
    TemaComponent
  ],
  templateUrl: './temas.component.html',
  styleUrl: './temas.component.css'
})
export class TemasComponent {
  aula: Aula=<Aula>{};
  temaACrear:string="";
  constructor(private aulaService:AulaService,private route:ActivatedRoute) {
    this.route.params.subscribe(p => {
      let id = Number(p['id'])||0;
      aulaService.getAula(id).subscribe({
        next: (a) => {
          this.aula = a;
          this.aula.temas.sort((t1,t2)=>t1.nombre.localeCompare(t2.nombre))
        },
        error: (error) => {
          alert(error);
        }
      })
    })
  }

  crearTema() {
    if (this.existe(this.temaACrear)){
      this.aulaService.crearTema(this.aula.id,this.temaACrear).subscribe({
        next: (data) => {
          this.aula.temas.push(data as Tema)
        },
        error: (error) => {
          alert(error);
        }
      });
    }else{
      alert("¡El tema ya existe!")
    }
  }

  existe(nombre:string):boolean{
    return this.aula.temas.filter(a=>a.nombre==nombre).length==0;
  }
  getYear() {
    return this.aula.año;
  }
  deleteTema(tema:Tema){
    this.aula.temas = this.aula.temas.filter(a => a.id != tema.id)
  }
}
