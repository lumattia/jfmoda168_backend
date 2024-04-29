import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import {Option} from "../interfaces/option";
import {ProfesorRow} from "../interfaces/profesor";

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
  getProfesores(id:number,searchTerm:string):Observable<Array<ProfesorRow>>{
    const url = `${CLASEURL}/${id}/profesores`
    let options={
      params:new HttpParams().set("buscar",searchTerm)
    }
    return this.http.get<Array<ProfesorRow>>(url,options);
  }
  crearClase(curso:number,asignatura:number):Observable<Object>{
    return this.http.post<Option>(CLASEURL,
        {
          "curso":{"id":curso},
          "asignatura":{"id":asignatura},
        },HTTPOPTIONS)
  }
  addProf(id:number,ids:number[]):Observable<Object>{
    const url = `${CLASEURL}/${id}/addProf`
    return this.http.post<Option>(url,
      ids
      ,HTTPOPTIONS)  }
  deleteClase(c:any):Observable<any>{
    const url = `${CLASEURL}/${c}`
    return this.http.delete(url, HTTPOPTIONS);
  }
  removeProfesor(idClase:number,idProfesor:number):Observable<any>{
    const url = `${CLASEURL}/${idClase}/profesor/${idProfesor}`
    return this.http.delete(url, HTTPOPTIONS)
  }
}
