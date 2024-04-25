import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Option} from "../interfaces/option";

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
  buscarAsignatura(searchTerm:string){
    let options={
      params:new HttpParams().set("buscar",searchTerm)
    }
    return this.http.get<Option>(ASIGNATURAURL,options)
  }
  crearAsignatura(nombre:any):Observable<Object>{
    return this.http.post<Option>(ASIGNATURAURL, {
      nombre
    },HTTPOPTIONS)  }
  actualizarAsignatura(a:any):Observable<Object>{
    const url = `${ASIGNATURAURL}/${a.id}`;
    return this.http.put<Option>(url, a, HTTPOPTIONS);
  }
  deleteAsignatura(a:any):Observable<any>{
    const url = `${ASIGNATURAURL}/${a}`
    return this.http.delete(url, HTTPOPTIONS);
  }
}
