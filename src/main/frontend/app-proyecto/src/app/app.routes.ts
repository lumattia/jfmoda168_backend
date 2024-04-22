import { Routes } from '@angular/router';
import {ClasesComponent} from "./admin/clases/clases.component";
import {LoginComponent} from "./login/login.component";
import {canActivateAdmin, canActivateEstudiante, canActivateProfesor} from "./security/authguard";
import {AdminComponent} from "./admin/admin.component";
import {CursosComponent} from "./admin/cursos/cursos.component";
import {AsignaturasComponent} from "./admin/asignaturas/asignaturas.component";
import {UsuariosComponent} from "./admin/usuarios/usuarios.component";
import {ProfesorFormComponent} from "./admin/usuarios/profesor/form/form.component";
import {EstudianteFormComponent} from "./admin/usuarios/estudiante/form/form.component";
import {ProfesorComponent} from "./profesor/profesor.component";
import {EstudianteComponent} from "./estudiante/estudiante.component";
import {ListAulasComponent} from "./profesor/list-aulas/list-aulas.component";
import {AulaComponent} from "./profesor/aula/aula.component";

export const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent, canActivate: [canActivateAdmin],children:[
      {path:'clases',component:ClasesComponent},
      {path:'clases/:asignaturaId',component:ClasesComponent},
      {path:'clases/:cursoId',component:ClasesComponent},
      {path:'cursos',component:CursosComponent},
      {path:'asignaturas',component:AsignaturasComponent},
      {path:'usuarios',component:UsuariosComponent},
      {path:'profesor/create',component:ProfesorFormComponent},
      {path:'profesor/edit/:id',component:ProfesorFormComponent},
      {path:'estudiante/create',component:EstudianteFormComponent},
      {path:'estudiante/edit/:id',component:EstudianteFormComponent},
      {path:'**',pathMatch:'full',redirectTo:'clases'}
      ]
  },
  {path:'profesor',component:ProfesorComponent, canActivate: [canActivateProfesor],children:[
      {path:'',component:ListAulasComponent},
      {path:':id',component:AulaComponent}
    ]
  },
  {path:'estudiante',component:EstudianteComponent, canActivate: [canActivateEstudiante],children:[

    ]
  },
  {path:'**',pathMatch:'full',redirectTo:'login'}
];
