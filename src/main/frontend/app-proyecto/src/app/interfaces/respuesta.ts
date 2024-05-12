import {Option} from "./option";

export interface Respuesta {
  pregunta:Option,
  respuesta:string;
  correcta:boolean;
}
