import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Option} from "../interfaces/option";
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
  actualizarTarea(a:Option):Observable<Option>{
    const url = `${TAREASURL}/${a.id}`;
    return this.http.put<Option>(url, a, HTTPOPTIONS);
  }
  deleteTarea(id:number){
    const url = `${TAREASURL}/${id}`
    return this.http.delete(url, HTTPOPTIONS);
  }
}
