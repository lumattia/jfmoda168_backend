import {Clase} from "./clase";

export interface Curso {
    id:number;
    nombre:string;
    clases:Clase[];
}
