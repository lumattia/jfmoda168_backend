import { Routes } from '@angular/router';
import {ClasesComponent} from "./admin/clases/clases.component";
import {LoginComponent} from "./login/login.component";
import {canActivateAdmin, canActivateEstudiante, canActivateProfesor} from "./security/authguard";
import {AdminComponent} from "./admin/admin.component";
import {CursosComponent} from "./admin/cursos/cursos.component";
import {AsignaturasComponent} from "./admin/asignaturas/asignaturas.component";
import {UsuariosAdminComponent} from "./admin/usuarios/usuarios-admin.component";
import {ProfesorFormComponent} from "./admin/usuarios/profesor/form/form.component";
import {EstudianteFormComponent} from "./admin/usuarios/estudiante/form/form.component";
import {ProfesorComponent} from "./profesor/profesor.component";
import {EstudianteComponent} from "./estudiante/estudiante.component";
import {AulasComponent} from "./profesor/aulas/aulas.component";
import {AulaComponent} from "./profesor/aulas/aula/aula.component";
import {UsuariosProfesorComponent} from "./profesor/aulas/aula/usuarios/usuarios-profesor.component";
import {TemasComponent} from "./profesor/aulas/aula/temas/temas.component";
import {ClaseComponent} from "./admin/clases/clase/clase.component";
import {ProfesorClaseComponent} from "./admin/clases/clase/profesor/profesor-clase.component";
import {TareasComponent} from "./admin/clases/clase/tareas/tareas.component";

export const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent, canActivate: [canActivateAdmin],children:[
      {path:'clases',component:ClasesComponent},
      {path:'clases/:id',component:ClaseComponent,children:[
          {path:'',component:TareasComponent},
          {path:'profesores',component:ProfesorClaseComponent},
          {path:'**',pathMatch:'full',redirectTo:''}
        ]},
      {path:'cursos',component:CursosComponent},
      {path:'asignaturas',component:AsignaturasComponent},
      {path:'usuarios',component:UsuariosAdminComponent},
      {path:'profesor/create',component:ProfesorFormComponent},
      {path:'profesor/edit/:id',component:ProfesorFormComponent},
      {path:'estudiante/create',component:EstudianteFormComponent},
      {path:'estudiante/edit/:id',component:EstudianteFormComponent},
      {path:'**',pathMatch:'full',redirectTo:'clases'}
      ]
  },
  {path:'profesor',component:ProfesorComponent, canActivate: [canActivateProfesor],children:[
      {path:'',component:AulasComponent},
      {path:'aula/:id',component:AulaComponent,children:[
          {path:'',component:TemasComponent},
          {path:'usuarios',component:UsuariosProfesorComponent},
          {path:'**',pathMatch:'full',redirectTo:''}
        ]},
      {path:'**',pathMatch:'full',redirectTo:''}
    ]
  },
  {path:'estudiante',component:EstudianteComponent, canActivate: [canActivateEstudiante],children:[

    ]
  },
  {path:'**',pathMatch:'full',redirectTo:'login'}
];
