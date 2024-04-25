import {Component, OnInit} from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {StorageService} from "./services/storage.service";
import {CommonModule} from "@angular/common";
import {LoginComponent} from "./login/login.component";
import {Usuario} from "./interfaces/usuario";
import {UsuarioService} from "./services/usuario.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, LoginComponent, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  title = 'app-proyecto';

  isLoggedIn  = false;
  usuario:Usuario;
  contrasenaAntigua: string="";
  nuevaContrasena: string="";
  confirmarContrasena: string="";
  constructor(private usuarioService:UsuarioService, private storageService: StorageService,private router: Router) {
    this.usuario=storageService.getUser().content;
  }
  logout() {
    this.storageService.clean();
    this.isLoggedIn = false;
    this.router.navigate(['/login']).then(
      () => {console.log('Logout OK, cargando login...')}
    )
  }
  abrirModal() {
    this.contrasenaAntigua = "";
    this.nuevaContrasena = "";
    this.confirmarContrasena = "";
  }
  cambiarContrasena(){
    if (this.nuevaContrasena !== this.confirmarContrasena) {
      return;
    }
    this.usuarioService.cambiarContraseña(this.contrasenaAntigua,this.nuevaContrasena).subscribe({
      next(){
        alert("Contraseña cambiada correctamente")
      },error(error){
        console.log(error)
        alert(error)
      }
    })
  }
  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
  }

}
