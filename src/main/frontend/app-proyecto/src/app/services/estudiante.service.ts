import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {EstudianteRow} from "../interfaces/estudiante";
import {Option} from "../interfaces/option";
import {ProfesorRow} from "../interfaces/profesor";
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
  buscarNotBlocked(searchTerm:string,page:number,pageSize:number,sortColumn:string,sortDirection:string)
  {
    const url = `${ESTUDIANTEURL}/notBlocked`
    let options={
      params:new HttpParams().set("buscar",searchTerm)
        .set("page",page-1)
        .set("size",pageSize)
        .set("sort",sortColumn+","+sortDirection)
    }
    return this.http.get<ProfesorRow>(url,options)
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
  getAulas(id:number):Observable<Object>{
    const url = `${ESTUDIANTEURL}/getAulas/${id}`
    return this.http.get<Array<Option>>(url)
  }
  crearEstudiante(e:any):Observable<Object>{
    return this.http.post<EstudianteRow>(ESTUDIANTEURL, e,HTTPOPTIONS)
  }

  actualizarEstudiante(e:any):Observable<Object>{
    const url = `${ESTUDIANTEURL}/${e.id}`;
    return this.http.put<EstudianteRow>(url, e, HTTPOPTIONS);
  }

  deleteEstudiante(e:any):Observable<any>{
    const url = `${ESTUDIANTEURL}/${e}`
    return this.http.delete(url, HTTPOPTIONS);
  }
}
