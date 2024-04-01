import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Usuario} from "../interfaces/usuario";
const USUARIOURL="http://localhost:8080/v1/api/usuarios"

const HTTPOPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  constructor(private http:HttpClient) {
  }
  actualizarUsuario(u:any):Observable<Object>{
    const url = `${USUARIOURL}/${u.id}`;
    return this.http.put<Usuario>(url, u, HTTPOPTIONS);
  }
}
