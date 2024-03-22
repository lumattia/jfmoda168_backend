import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import {Clase} from "../interfaces/clase";

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
    return this.http.get<Clase>(url);
  }
  crearClase(c:any):Observable<Object>{
    return this.http.post<Clase>(CLASEURL,c,HTTPOPTIONS)
  }

  actualizarUsuario(c:any):Observable<Object>{
    const url = `${CLASEURL}/${c.id}`;
    return this.http.put<Clase>(url, c, HTTPOPTIONS);
  }

  deleteClase(c:any):Observable<any>{
    const url = `${CLASEURL}/${c}`
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
