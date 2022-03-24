import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Estabelecimento } from '../models/estabelecimento';

@Injectable({
  providedIn: 'root'
})
export class EstabelecimentoService {

  constructor(private _httpClient: HttpClient) { }

  // SERVIÇOS DESENVOLVIDOS
  listar(): Observable<any> {
    return this._httpClient.get(`${environment.apiUrl}/estabelecimentos`);
  }

  salvar(estabelecimento: Estabelecimento): Observable<any> {
    return this._httpClient.post(`${environment.apiUrl}/estabelecimentos`, estabelecimento);
  }

  excluir(id: number): Observable<any> {
    return this._httpClient.delete(`${environment.apiUrl}/estabelecimentos/${id}`);
  }
}
