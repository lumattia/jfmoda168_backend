import {Usuario} from "./usuario";
import {Clase} from "./clase";

export interface Profesor extends Usuario{
  clases:Clase[];
}
