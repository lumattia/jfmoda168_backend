import {Fase} from "./fase";

export interface Tarea {
  id:number,
  nombre:string,
  facil:Fase,
  intermedio:Fase,
  Dificil:Fase,
}
