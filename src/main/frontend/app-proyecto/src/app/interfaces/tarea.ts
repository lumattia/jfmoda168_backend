import {Fase} from "./fase";

export interface Tarea {
  id:number,
  nombre:string,
  facil:Fase[],
}
export interface TareaRow {
  id:number,
  nombre:string,
  propietarioNombre:string,
  temaNombre:string,
  aulaGrupoAnio:string
}
