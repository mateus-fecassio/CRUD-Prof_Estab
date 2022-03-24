import { DialogFormProfissional } from '../dialog-form-profissional/dialog-form-profissional.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';

import { ProfissionaisService } from './../../../services/profissionais.service';
import { Profissional } from 'src/app/models/profissional';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.scss']
})
export class ListaComponent implements OnInit {
  displayedColumns: string[] = ['nome', 'endereco', 'celular', 'telefone', 'funcao', 'acoes'];
  dataSource = new MatTableDataSource([]);
  profissionais: Profissional[] = [];

  constructor(private _profissionaisService: ProfissionaisService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.listarProfissionais();
  }

  listarProfissionais(): void {
    this._profissionaisService.listar().subscribe(profissionais => {
      this.profissionais = profissionais;
      this.dataSource = new MatTableDataSource(profissionais);
    });
  }

  novo(): void {
    const dialogRef = this.dialog.open(DialogFormProfissional);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.listarProfissionais();
      }
    });
  }

  editar(profissional: Profissional): void {
    const dialogRef = this.dialog.open(DialogFormProfissional, {
      data: profissional
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.listarProfissionais();
      }
    });
  }

  excluir(profissional: Profissional): void {
    this._profissionaisService.excluir(profissional.id).subscribe(response => {
      if (response) {
        this.listarProfissionais();
      }
    });
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
