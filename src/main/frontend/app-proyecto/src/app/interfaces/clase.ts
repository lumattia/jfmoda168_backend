import {Asignatura} from "./asignatura";
import {Profesor} from "./profesor";
import {Curso} from "./curso";

export interface Clase {
  id:number;
  nombre:string;
  profesores:string[];
}
