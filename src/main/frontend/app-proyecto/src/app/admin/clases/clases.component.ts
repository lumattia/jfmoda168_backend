import { Component } from '@angular/core';
import {Clase} from "../../interfaces/clase";
import {ClaseService} from "../../services/clase.service";
import {Router} from "@angular/router";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-clases',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './clases.component.html',
  styleUrl: './clases.component.css'
})
export class ClasesComponent {
  clases:Clase[]=[];
  errorDB: boolean = false;

  constructor(private claseService: ClaseService,private _router: Router) {
    this.claseService.getClases().subscribe({
      next: (data) => {
        console.log(data)
        this.clases = (data as Clase[])
      },
      error: (error) => {
        console.error(error);
        this.errorDB = true
      }
    });
  }
}
