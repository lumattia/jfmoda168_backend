import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Option} from "../interfaces/option";
import {Aula, AulaForm} from "../interfaces/aula";

const AULAURL="http://localhost:8080/v1/api/aulas"

const HTTPOPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AulaService {

  constructor(private http:HttpClient) {
  }

  //Métodos (incluir tipos correctos en los argumentos)
  getAulas():Observable<Object>{
    return this.http.get(AULAURL);
  }
  getAula(id:number):Observable<Aula>{
    const url = `${AULAURL}/${id}`
    return this.http.get<Aula>(url);
  }
  crearAula(aula:AulaForm):Observable<Object>{
    return this.http.post<Option>(AULAURL,
      aula
    ,HTTPOPTIONS)  }
  crearTema(id:number,nombre:string):Observable<Object>{
    const url = `${AULAURL}/${id}`
    return this.http.post<Option>(url,
      {nombre}
      ,HTTPOPTIONS)  }
  actualizarAula(a:any):Observable<Object>{
    const url = `${AULAURL}/${a.id}`;
    return this.http.put<Option>(url, a, HTTPOPTIONS);
  }
  deleteAula(a:any):Observable<any>{
    const url = `${AULAURL}/${a}`
    return this.http.delete(url, HTTPOPTIONS)
  }
}
