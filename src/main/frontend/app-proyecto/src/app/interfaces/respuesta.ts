import {Option} from "./option";

export interface Respuesta {
  id:number;
  pregunta:Option;
  respuesta:string;
  correcta:boolean;
}
