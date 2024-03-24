import { Routes } from '@angular/router';
import {ClasesComponent} from "./admin/clases/clases.component";
import {LoginComponent} from "./login/login.component";
import {canActivate} from "./security/authguard";
import {AdminComponent} from "./admin/admin.component";
import {CursosComponent} from "./admin/cursos/cursos.component";
import {AsignaturasComponent} from "./admin/asignaturas/asignaturas.component";
import {UsuariosComponent} from "./admin/usuarios/usuarios.component";

export const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent, canActivate: [canActivate],children:[
      {path:'clases',component:ClasesComponent},
      {path:'cursos',component:CursosComponent},
      {path:'asignaturas',component:AsignaturasComponent},
      {path:'usuarios',component:UsuariosComponent},
      {path:'**',pathMatch:'full',redirectTo:'clases'}
      ]
  },

  {path:'**',pathMatch:'full',redirectTo:'login'}
];
