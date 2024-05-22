import {Option} from "./option";

export interface TareaEstudiante {
  estudiante:Option;
  tarea:TareaDto;
  fase:number;
  basico:number;
  intermedio:number;
  avanzado:number;
}
export interface PuntoEstudiante {
  estudiante:Option;
  fase:number;
  basico:number;
  intermedio:number;
  avanzado:number;
}
export interface PuntoTarea {
  tarea:TareaDto;
  fase:number;
  basico:number;
  intermedio:number;
  avanzado:number;
}
export interface TareaDto {
  id:number;
  temaNombre:string;
  nombre:string;
}
