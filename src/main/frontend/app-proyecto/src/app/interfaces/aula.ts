import {Option} from "./option";
import {Tema} from "./tema";
import {ProfesorRow} from "./profesor";
import {EstudianteRow} from "./estudiante";

export interface AulaForm {
  id:number,
  clase:Option,
  grupo:string,
  año:string,
}
export interface Aula {
  id:number,
  clase:Option,
  grupo:string,
  año:string,
  temas:Tema[],
  propietario:number,
  profesores:ProfesorRow[],
  estudiantes:EstudianteRow[],
}
