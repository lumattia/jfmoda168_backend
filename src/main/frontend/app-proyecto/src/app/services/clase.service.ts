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
  getClases():Observable<Option[]>{
    return this.http.get<Option[]>(CLASEURL);
  }
  getClase(id:number):Observable<Option>{
    const url = `${CLASEURL}/${id}`
    return this.http.get<Option>(url);
  }
  getProfesores(id:number,searchTerm:string):Observable<ProfesorRow[]>{
    const url = `${CLASEURL}/${id}/profesores`
    let options={
      params:new HttpParams().set("buscar",searchTerm)
    }
    return this.http.get<ProfesorRow[]>(url,options);
  }
  getAulas(idClase:number,searchTerm:string):Observable<Option[]>{
    const url = `${CLASEURL}/${idClase}/aulas`
    let options={
      params:new HttpParams().set("buscar",searchTerm)
    }
    return this.http.get<Option[]>(url,options);
  }
  filterClase(curso:number,asignatura:number):Observable<Option[]>{
    const url = `${CLASEURL}?curso=${curso}&asignatura=${asignatura}`
    return this.http.get<Option[]>(url);
  }
  filterTema(idsAulas:number[],idsProfesores:number[]):Observable<Option[]>{
    const url = `${CLASEURL}?aulas=${idsAulas}&profesores=${idsProfesores}`
    return this.http.get<Option[]>(url);
  }
  crearClase(curso:number,asignatura:number):Observable<Option>{
    return this.http.post<Option>(CLASEURL,
        {
          "curso":{"id":curso},
          "asignatura":{"id":asignatura},
        },HTTPOPTIONS)
  }
  addProf(id:number,ids:number[]):Observable<ProfesorRow[]>{
    const url = `${CLASEURL}/${id}/addProf`
    return this.http.post<ProfesorRow[]>(url,
      ids
      ,HTTPOPTIONS)  }
  deleteClase(c:number){
    const url = `${CLASEURL}/${c}`
    return this.http.delete(url, HTTPOPTIONS);
  }
  removeProfesor(idClase:number,idProfesor:number){
    const url = `${CLASEURL}/${idClase}/profesor/${idProfesor}`
    return this.http.delete(url, HTTPOPTIONS)
  }
  deleteAula(idClase:number,idAula:number){
    const url = `${CLASEURL}/${idClase}/aula/${idAula}`
    return this.http.delete(url, HTTPOPTIONS);
  }
  deleteTema(idClase:number,idTema:number){
    const url = `${CLASEURL}/${idClase}/tema/${idTema}`
    return this.http.delete(url, HTTPOPTIONS);
  }
}
