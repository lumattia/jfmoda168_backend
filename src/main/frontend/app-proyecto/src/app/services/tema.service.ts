import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Option} from "../interfaces/option";

const TEMAURL="http://localhost:8080/v1/api/temas"
const HTTPOPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TemaService {

  constructor(private http:HttpClient) {
  }
  crearTarea(id:number,nombre:any):Observable<Object>{
    const url = `${TEMAURL}/${id}`;
    return this.http.post<Option>(url,
        {nombre}
    ,HTTPOPTIONS)  }
  actualizarTema(a:any):Observable<Object>{
    const url = `${TEMAURL}/${a.id}`;
    return this.http.put<Option>(url, a, HTTPOPTIONS);
  }
  deleteTema(a:any):Observable<any>{
    const url = `${TEMAURL}/${a}`
    return this.http.delete(url, HTTPOPTIONS);
  }
}
