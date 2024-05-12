import {Fase} from "./fase";

export interface TareaFase {
  id:number,
  nombre:string,
  fases:Fase[],
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
