import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {StorageService} from "./services/storage.service";
import {CommonModule} from "@angular/common";
import {LoginComponent} from "./login/login.component";
import {Usuario} from "./interfaces/usuario";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  title = 'app-proyecto';

  isLoggedIn  = false;
  usuario:Usuario;
  constructor( private storageService: StorageService,private router: Router) {
    this.usuario=storageService.getUser();
  }
  logout() {
    this.storageService.clean();
    this.isLoggedIn = false;
    this.router.navigate(['/login']).then(
      () => {console.log('Logout OK, cargando login...')}
    )
  }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
  }


}
