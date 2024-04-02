import {Component} from '@angular/core';
import {ProfesorComponent} from "../profesor/profesor.component";
import {EstudianteComponent} from "../estudiante/estudiante.component";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [
    ProfesorComponent,
    EstudianteComponent,
    FormsModule
  ],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.css'
})
export class UsuariosComponent {
  searchTerm:string="";
  constructor() {
  }
}


