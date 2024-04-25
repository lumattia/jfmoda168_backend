import { Component } from '@angular/core';
import {AulaService} from "../../services/aula.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {Aula} from "../../interfaces/aula";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {NgbPagination} from "@ng-bootstrap/ng-bootstrap";
import {Tema} from "../../interfaces/tema";
import {TemaComponent} from "./tema/tema.component";

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
  templateUrl: './aula.component.html',
  styleUrl: './aula.component.css'
})
export class AulaComponent {
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
          console.error(error);
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
          alert(error.error);
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
