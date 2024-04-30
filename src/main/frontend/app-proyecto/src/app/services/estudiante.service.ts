import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {EstudianteForm, EstudianteRow} from "../interfaces/estudiante";
import {Option} from "../interfaces/option";
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
  getEstudiante(id:number):Observable<EstudianteForm>{
    const url = `${ESTUDIANTEURL}/${id}`
    return this.http.get<EstudianteForm>(url);
  }
  buscarNotBlocked(searchTerm:string,page:number,pageSize:number,sortColumn:string,sortDirection:string):Observable<any>{
    const url = `${ESTUDIANTEURL}/notBlocked`
    let options={
      params:new HttpParams().set("buscar",searchTerm)
        .set("page",page-1)
        .set("size",pageSize)
        .set("sort",sortColumn+","+sortDirection)
    }
    return this.http.get<any>(url,options)
  }
  buscarEstudiante(searchTerm:string,page:number,pageSize:number,sortColumn:string,sortDirection:string):Observable<any>{
    let options={
      params:new HttpParams().set("buscar",searchTerm)
        .set("page",page-1)
        .set("size",pageSize)
        .set("sort",sortColumn+","+sortDirection)
    }
    return this.http.get<any>(ESTUDIANTEURL,options)
  }
  getAulas(id:number):Observable<Option[]>{
    const url = `${ESTUDIANTEURL}/getAulas/${id}`
    return this.http.get<Option[]>(url)
  }
  crearEstudiante(e:EstudianteForm):Observable<EstudianteRow>{
    return this.http.post<EstudianteRow>(ESTUDIANTEURL, e,HTTPOPTIONS)
  }

  actualizarEstudiante(e:EstudianteForm):Observable<EstudianteRow>{
    const url = `${ESTUDIANTEURL}/${e.id}`;
    return this.http.put<EstudianteRow>(url, e, HTTPOPTIONS);
  }

  deleteEstudiante(id:number){
    const url = `${ESTUDIANTEURL}/${id}`
    return this.http.delete(url, HTTPOPTIONS);
  }
}
