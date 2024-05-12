import {Pregunta} from "./pregunta";
import {Option} from "./option";

export interface Fase {
  id:number,
  tarea:Option,
  nivel:string,
  preguntas:Pregunta[]
}
