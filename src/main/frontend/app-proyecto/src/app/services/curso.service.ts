import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Curso} from "../interfaces/curso";
const CURSOSURL="http://localhost:8080/v1/api/cursos"

const HTTPOPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  constructor(private http:HttpClient) {
  }

  //Métodos (incluir tipos correctos en los argumentos)
  getCursos():Observable<Object>{
    return this.http.get(CURSOSURL);
  }
  getCurso(id:number):Observable<Object>{
    const url = `${CURSOSURL}/${id}`
    return this.http.get<Curso>(url);
  }
  crearCurso(nombre:any):Observable<Object>{
    return this.http.post<Curso>(CURSOSURL, {
      nombre
    },HTTPOPTIONS)
  }

  actualizarAsignatura(c:any):Observable<Object>{
    const url = `${CURSOSURL}/${c.id}`;
    return this.http.put<Curso>(url, c, HTTPOPTIONS);
  }

  deleteCurso(c:any):Observable<any>{
    const url = `${CURSOSURL}/${c}`
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
