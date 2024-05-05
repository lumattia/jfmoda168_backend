import {Fase} from "./fase";

export interface Tarea {
  id:number,
  nombre:string,
  facil:Fase[],
}
export interface TareaRow {
  id:number,
  nombre:string,
  propietarioNombreCompleto:string,
  temaNombre:string,
  aulaGrupoAnio:string
}
