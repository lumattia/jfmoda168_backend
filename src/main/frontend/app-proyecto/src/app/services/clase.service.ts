import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import {Option} from "../interfaces/option";

//ENDPOINTS
const CLASEURL="http://localhost:8080/v1/api/clases"

const HTTPOPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ClaseService {

  constructor(private http:HttpClient) {
  }

  //Métodos (incluir tipos correctos en los argumentos)
  getClases():Observable<Object>{
    return this.http.get(CLASEURL);
  }
  getClase(id:number):Observable<Object>{
    const url = `${CLASEURL}/${id}`
    return this.http.get<Option>(url);
  }
  filterClase(curso:number,asignatura:number):Observable<Object>{
    const url = `${CLASEURL}?curso=${curso}&asignatura=${asignatura}`
    return this.http.get<Option>(url);
  }
  crearClase(curso:number,asignatura:number):Observable<Object>{
    return this.http.post<Option>(CLASEURL,
        {
          "curso":{"id":curso},
          "asignatura":{"id":asignatura},
        },HTTPOPTIONS)
  }
  deleteClase(c:any):Observable<any>{
    const url = `${CLASEURL}/${c}`
    return this.http.delete(url, HTTPOPTIONS);
  }
}
