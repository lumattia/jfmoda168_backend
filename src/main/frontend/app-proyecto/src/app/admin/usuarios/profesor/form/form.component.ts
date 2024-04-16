import {Component} from '@angular/core';
import {ProfesorForm} from "../../../../interfaces/profesor";
import {FormsModule} from "@angular/forms";
import {ProfesorService} from "../../../../services/profesor.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class ProfesorFormComponent {
  profesor: ProfesorForm={
    id:0,
    nombre:"",
    apellidos:"",
    email:"",
    password:"123456"/*contraseña por defecto*/
  };
  constructor(private profesorService: ProfesorService,private route:ActivatedRoute, private router: Router) {
    this.route.params.subscribe(p => {
      this.profesor.id = Number(p['id'])||0;
      if (this.profesor.id!=0){
        profesorService.getProfesor(this.profesor.id).subscribe({
          next:(data)=>{
            /*el profesor que se obtiene desde el back tiene demasiados campos
            de las cuales hay algunas por ejemplo clase que está serializada
             y esto provoca un error*/
            let profesor=data as ProfesorForm;
            this.profesor.id=profesor.id;
            this.profesor.nombre=profesor.nombre;
            this.profesor.apellidos=profesor.apellidos;
            this.profesor.email=profesor.email;
          },
          error:(error)=>{
            alert("ProfesorForm not found")
            console.log(error)
          }
        })
      }
    });
  }
  guardar() {
    if (this.profesor.id==0)
      this.profesorService.crearProfesor(this.profesor).subscribe({
        next:(data)=>{
          this.volver()
        },
        error:(error)=>{
          alert("Error a crear el profesor.")
          console.log(error)
        }
      })
    else{
      this.profesorService.actualizarProfesor(this.profesor).subscribe({
        next:(data)=>{
          this.volver()
        },
        error:(error)=>{
          alert("Error a editar el profesor.")
          console.log(error)
        }
      })
    }
  }
  volver() {
    this.router.navigate(['admin/usuarios'])
  }
}
