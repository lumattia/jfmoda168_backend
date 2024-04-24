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
      .pipe(catchError(this.handleError));
  }
  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }
}
