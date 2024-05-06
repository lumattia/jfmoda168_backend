import {Fase} from "./fase";

export interface Tarea {
  id:number,
  nombre:string,
  facil:Fase[],
}
export interface TareaDetail {
  id:number,
  nombre:string,
  visible:boolean,
}
export interface TareaRow {
  id:number,
  nombre:string,
  propietario:string,
  temaNombre:string,
  aulaGrupoAnio:string,
}
