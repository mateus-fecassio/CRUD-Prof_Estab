import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Estabelecimento } from 'src/app/models/estabelecimento';

import { EstabelecimentoService } from '../../../services/estabelecimento.service';


@Component({
  selector: 'app-dialog-form-estabelecimento',
  templateUrl: './dialog-form-estabelecimento.component.html',
  styleUrls: ['./dialog-form-estabelecimento.component.scss']
})
export class DialogFormEstabelecimentoComponent implements OnInit {

  form!: FormGroup;
  edicao: boolean = false;

  constructor(private _formBuilder: FormBuilder,
    private _estabelecimentoService: EstabelecimentoService,
    private _dialogRef: MatDialogRef<DialogFormEstabelecimentoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Estabelecimento) {

  }

  ngOnInit(): void {
    this.form = this._formBuilder.group({
      nome: ['', Validators.required],
      endereco: ['', Validators.required],
      telefone: ['', Validators.required],
    });

    if (this.data) {
      this.edicao = true;
      this.form.patchValue({
        nome: this.data.nome,
        endereco: this.data.endereco,
        telefone: this.data.telefone
      });
      // this.form.patchValue({ ...this.data });
    }
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const estabelecimento = { ...this.form.getRawValue() };
    if (this.edicao) {
      estabelecimento.id = this.data.id;
    }
    this._estabelecimentoService.salvar(estabelecimento).subscribe(response => {
      this._dialogRef.close(response);
    });
  }

}
