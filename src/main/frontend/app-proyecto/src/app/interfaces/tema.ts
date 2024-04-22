import {Option} from "./option";
import {Aula} from "./aula";

export interface Tema {
  id:number,
  nombre:string,
  tareas:Option[],
  aula:Aula
}
