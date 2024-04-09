import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Asignatura} from "../interfaces/asignatura";
import {Curso} from "../interfaces/curso";

const ASIGNATURAURL="http://localhost:8080/v1/api/asignaturas"

const HTTPOPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AsignaturaService {

  constructor(private http:HttpClient) {
  }

  //Métodos (incluir tipos correctos en los argumentos)
  getAsignaturas():Observable<Object>{
    return this.http.get(ASIGNATURAURL);
  }
  getAsignatura(id:number):Observable<Object>{
    const url = `${ASIGNATURAURL}/${id}`
    return this.http.get<Asignatura>(url);
  }
  buscarAsignatura(searchTerm:string){
    let options={
      params:new HttpParams().set("buscar",searchTerm)
    }
    return this.http.get<Asignatura>(ASIGNATURAURL,options)
  }
  crearAsignatura(nombre:any):Observable<Object>{
    return this.http.post<Curso>(ASIGNATURAURL, {
      nombre
    },HTTPOPTIONS)  }

  actualizarAsignatura(a:any):Observable<Object>{
    const url = `${ASIGNATURAURL}/${a.id}`;
    return this.http.put<Asignatura>(url, a, HTTPOPTIONS);
  }

  deleteAsignatura(a:any):Observable<any>{
    const url = `${ASIGNATURAURL}/${a}`
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
