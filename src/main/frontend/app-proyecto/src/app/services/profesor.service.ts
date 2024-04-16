import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {ProfesorForm, ProfesorRow} from "../interfaces/profesor";
import {Option} from "../interfaces/option";
const PROFESORURL="http://localhost:8080/v1/api/profesores"

const HTTPOPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ProfesorService {
  constructor(private http:HttpClient) {
  }

  //Métodos (incluir tipos correctos en los argumentos)
  getProfesor(id:number):Observable<Object>{
    const url = `${PROFESORURL}/${id}`
    return this.http.get<ProfesorRow>(url);
  }
  buscarProfesor(searchTerm:string,page:number,pageSize:number,sortColumn:string,sortDirection:string){
    let options={
      params:new HttpParams().set("buscar",searchTerm)
      .set("page",page-1)
      .set("size",pageSize)
      .set("sort",sortColumn+","+sortDirection)
    }
    return this.http.get<ProfesorRow>(PROFESORURL,options)
  }
  getAulas(id:number):Observable<Object>{
    const url = `${PROFESORURL}/getAulas/${id}`
    return this.http.get<Array<Option>>(url)
  }
  getClases(id:number):Observable<Object>{
    const url = `${PROFESORURL}/getClases/${id}`
    return this.http.get<Array<Option>>(url)
  }
  crearProfesor(p:ProfesorForm):Observable<Object>{
    return this.http.post<ProfesorRow>(PROFESORURL, p,HTTPOPTIONS)
  }

  actualizarProfesor(p:any):Observable<Object>{
    const url = `${PROFESORURL}/${p.id}`;
    return this.http.put<ProfesorRow>(url, p, HTTPOPTIONS);
  }

  deleteProfesor(id:number):Observable<any>{
    const url = `${PROFESORURL}/${id}`
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

