import {Respuesta} from "./respuesta";
import {Option} from "./option";

export interface Pregunta {
  id:number,
  fase:Option,
  enunciado:string;
  respuestas:Respuesta[]
}
