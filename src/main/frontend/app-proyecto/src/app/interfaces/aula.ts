import {Option} from "./option";
import {Tema} from "./tema";

export interface AulaForm {
  id:number,
  clase:Option,
  grupo:string,
  año:string,
}
export interface Aula {
  id:number,
  clase:Option,
  grupo:string,
  año:string,
  temas:Tema[]
}
