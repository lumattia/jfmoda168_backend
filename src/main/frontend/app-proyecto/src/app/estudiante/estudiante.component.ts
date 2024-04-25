import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {Option} from "../interfaces/option";
import {EstudianteService} from "../services/estudiante.service";
import {StorageService} from "../services/storage.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-estudiante',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    RouterLink
  ],
  templateUrl: './estudiante.component.html',
  styleUrl: './estudiante.component.css'
})
export class EstudianteComponent {
  aulas:Option[]=[];
  constructor(private estudianteService:EstudianteService,private storageService: StorageService) {
    var id=storageService.getUser().content.id
    estudianteService.getAulas(id).subscribe({
      next: (a) => {
        this.aulas = a as Option[]
      },
      error: (error) => {
        alert(error);
      }
    })
  }
}
