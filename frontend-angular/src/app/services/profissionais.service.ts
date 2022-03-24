import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Profissional } from '../models/profissional';

@Injectable({
  providedIn: 'root'
})
export class ProfissionaisService {

  constructor(private _httpClient: HttpClient) { }

  // SERVIÇOS DESENVOLVIDOS
  listar(): Observable<any> {
    return this._httpClient.get(`${environment.apiUrl}/profissionais`);
  }

  salvar(profissional: Profissional): Observable<any> {
    return this._httpClient.post(`${environment.apiUrl}/profissionais`, profissional);
  }

  excluir(id: number): Observable<any> {
    return this._httpClient.delete(`${environment.apiUrl}/profissionais/${id}`);
  }

}
