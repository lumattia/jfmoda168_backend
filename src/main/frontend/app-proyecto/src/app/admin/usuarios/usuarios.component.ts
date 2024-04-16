import {Component} from '@angular/core';
import {ProfesorComponent} from "./profesor/profesor.component";
import {EstudianteComponent} from "./estudiante/estudiante.component";
import {FormsModule} from "@angular/forms";
import {UsuarioService} from "../../services/usuario.service";

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
  bloquearODesbloquear(u:any){
    this.usuarioService.cambiarEstado(u.id).subscribe({
      next: (b) => {
        u.blocked = b
      },
        error: (error) => {
        console.error(error);
      }
    })
  }
}


