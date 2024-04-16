import {Component} from '@angular/core';
import {EstudianteForm} from "../../../../interfaces/estudiante";
import {FormsModule} from "@angular/forms";
import {EstudianteService} from "../../../../services/estudiante.service";
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
export class EstudianteFormComponent {
  estudiante: EstudianteForm={
    id:0,
    nombre:"",
    apellidos:"",
    email:"",
    aula:"",
    password:"123456"
  };
  constructor(private estudianteService: EstudianteService,private route:ActivatedRoute, private router: Router) {
    this.route.params.subscribe(p => {
      this.estudiante.id = Number(p['id'])||0;
      if (this.estudiante.id!=0){
        estudianteService.getEstudiante(this.estudiante.id).subscribe({
          next:(data)=>{
            /*el profesor que se obtiene desde el back tiene demasiados campos
           de las cuales hay algunas por ejemplo clase que está serializada
            y esto provoca un error*/
            let estudiante=data as EstudianteForm;
            this.estudiante.id=estudiante.id;
            this.estudiante.nombre=estudiante.nombre;
            this.estudiante.apellidos=estudiante.apellidos;
            this.estudiante.email=estudiante.email;
          },
          error:(error)=>{
            alert("Estudiante not found")
            console.log(error)
          }
        })
      }
    });
  }
  guardar() {
    console.log(this.estudiante)
    if (this.estudiante.id==0)
      this.estudianteService.crearEstudiante(this.estudiante).subscribe({
        next:(data)=>{
          this.volver()
        },
        error:(error)=>{
          alert("Error a crear el estudiante.")
          console.log(error)
        }
      })
    else{
      this.estudianteService.actualizarEstudiante(this.estudiante).subscribe({
        next:(data)=>{
          this.volver()
        },
        error:(error)=>{
          alert("Error a editar el estudiante.")
          console.log(error)
        }
      })
    }
  }
  volver() {
    this.router.navigate(['admin/usuarios'])
  }
}
