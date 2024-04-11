import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {EstudianteRow} from "../interfaces/estudiante";
const ESTUDIANTEURL="http://localhost:8080/v1/api/estudiantes"

const HTTPOPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class EstudianteService {

  constructor(private http:HttpClient) {
  }

  //Métodos (incluir tipos correctos en los argumentos)
  getEstudiantes():Observable<Object>{
    return this.http.get(ESTUDIANTEURL);
  }
  getEstudiante(id:number):Observable<Object>{
    const url = `${ESTUDIANTEURL}/${id}`
    return this.http.get<EstudianteRow>(url);
  }
  buscarEstudiante(searchTerm:string,page:number,pageSize:number,sortColumn:string,sortDirection:string){
    let options={
      params:new HttpParams().set("buscar",searchTerm)
        .set("page",page-1)
        .set("size",pageSize)
        .set("sort",sortColumn+","+sortDirection)
    }
    return this.http.get<EstudianteRow>(ESTUDIANTEURL,options)
  }
  crearEstudiante(nombre:any):Observable<Object>{
    return this.http.post<EstudianteRow>(ESTUDIANTEURL, {
      nombre
    },HTTPOPTIONS)
  }

  actualizarEstudiante(e:any):Observable<Object>{
    const url = `${ESTUDIANTEURL}/${e.id}`;
    return this.http.put<EstudianteRow>(url, e, HTTPOPTIONS);
  }

  deleteEstudiante(e:any):Observable<any>{
    const url = `${ESTUDIANTEURL}/${e}`
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
