import {Option} from "./option";
import {Tema} from "./tema";
import {ProfesorRow} from "./profesor";
import {EstudianteRow} from "./estudiante";

export interface AulaForm {
  id:number,
  clase:Option,
  grupo:string,
  anio:string,
}
export interface Aula {
  id:number,
  clase:Option,
  grupo:string,
  anio:string,
  temas:Tema[],
  propietario:number,
  profesores:ProfesorRow[],
  estudiantes:EstudianteRow[],
}
