import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {FaseEstudiante} from "../interfaces/fase-estudiante";

const FASEURL="http://localhost:8080/v1/api/fases"
const HTTPOPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class FaseService {

  constructor(private http:HttpClient) { }
  getNivelMax(idTarea:number):Observable<number>{
    const url = `${FASEURL}/${idTarea}`;
    return this.http.get<number>(url, HTTPOPTIONS);
  }
  getFase(idTarea:number,idFase:number):Observable<FaseEstudiante>{
    const url = `${FASEURL}/${idTarea}/${idFase}`;
    return this.http.get<FaseEstudiante>(url, HTTPOPTIONS);
  }
}
