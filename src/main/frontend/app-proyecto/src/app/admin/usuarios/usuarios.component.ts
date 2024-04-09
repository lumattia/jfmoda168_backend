import {Component} from '@angular/core';
import {ProfesorComponent} from "../profesor/profesor.component";
import {EstudianteComponent} from "../estudiante/estudiante.component";
import {FormsModule} from "@angular/forms";
import {UsuarioService} from "../../services/usuario.service";
import {Profesor} from "../../interfaces/profesor";

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
  constructor(private usuarioService:UsuarioService) {
  }
  bloquearODesbloquear(profesor:Profesor){
    console.log("A")
    this.usuarioService.cambiarEstado(profesor.id).subscribe({
      next: (b) => {
        profesor.blocked = b
      },
        error: (error) => {
        console.error(error);
      }
    })
  }
}


