import {Clase} from "./clase";

export interface Asignatura {
    id:number;
    nombre:string;
    clases:Clase[];
}
