import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Option} from "../interfaces/option";
import {TareaFase, TareaDetail} from "../interfaces/tareaFase";
const TAREASURL="http://localhost:8080/v1/api/tareas"
const HTTPOPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class TareaService {
  constructor(private http:HttpClient) {
  }
  getTarea(id:number):Observable<TareaFase>{
    const url = `${TAREASURL}/${id}`;
    return this.http.get<TareaFase>(url, HTTPOPTIONS);
  }
  cambiarNombre(a:Option):Observable<TareaDetail>{
    const url = `${TAREASURL}/${a.id}/cambiarNombre`;
    return this.http.put<TareaDetail>(url, a, HTTPOPTIONS);
  }
  saveTarea(a:TareaFase){
    const url = `${TAREASURL}/${a.id}`;
    return this.http.put(url, a, HTTPOPTIONS);
  }
  cambiarEstado(id:number):Observable<boolean>{
    const url = `${TAREASURL}/${id}/cambiarEstado`;
    return this.http.put<boolean>(url, HTTPOPTIONS);
  }
  deleteTarea(id:number){
    const url = `${TAREASURL}/${id}`
    return this.http.delete(url, HTTPOPTIONS);
  }
}
