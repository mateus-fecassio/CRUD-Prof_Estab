import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Estabelecimento } from 'src/app/models/estabelecimento';
import { Profissional } from 'src/app/models/profissional';

import { EstabelecimentoService } from '../../../services/estabelecimento.service';
import { ProfissionaisService } from '../../../services/profissionais.service';


@Component({
  selector: 'app-dialog-form-profissional',
  templateUrl: 'dialog-form-profissional.component.html',
  styleUrls: ['./dialog-form-profissional.component.scss']
})
export class DialogFormProfissional implements OnInit {
  form!: FormGroup;
  edicao: boolean = false;
  estabelecimentos: Estabelecimento[] = [];

  constructor(private _formBuilder: FormBuilder,
    private _profissionaisService: ProfissionaisService,
    private _estabelecimentoService: EstabelecimentoService,
    private _dialogRef: MatDialogRef<DialogFormProfissional>,
    @Inject(MAT_DIALOG_DATA) public data: Profissional) {

  }

  ngOnInit(): void {
    this.form = this._formBuilder.group({
      nome: ['', Validators.required],
      endereco: ['', Validators.required],
      celular: ['', Validators.required],
      telefone: ['', Validators.required],
      funcao: ['', Validators.required],
      estabelecimentos: [[], Validators.required],
    });

    this.listarEstabelecimentos();
    if (this.data) {
      this.edicao = true;
      this.form.patchValue({ ...this.data });
    }
  }

  listarEstabelecimentos(): void {
    this._estabelecimentoService.listar().subscribe(estabelecimentos => {
      this.estabelecimentos = estabelecimentos;
    });
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const profissional = { ...this.form.getRawValue() };
    if (this.edicao) {
      profissional.id = this.data.id;
    }
    this._profissionaisService.salvar(profissional).subscribe(response => {
      this._dialogRef.close(response);
    });
  }

  compararIds(o1: any, o2: any) {
    if (o1.id == o2.id && o1.id == o2.id)
      return true;
    else return false
  }

}
