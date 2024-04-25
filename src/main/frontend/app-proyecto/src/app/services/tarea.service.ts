import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
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
  actualizarTarea(a:any):Observable<Object>{
    const url = `${TAREASURL}/${a.id}`;
    return this.http.put<Option>(url, a, HTTPOPTIONS);
  }
  deleteTarea(a:any):Observable<any>{
    const url = `${TAREASURL}/${a}`
    return this.http.delete(url, HTTPOPTIONS);
  }
}
