package com.crudjava.crudspring.dto;

import com.crudjava.crudspring.model.Estabelecimento;

import lombok.Data;

@Data
public class EstabelecimentoDTO {
  private Long id;

  private String nome;

  private String endereco;

  private String telefone;

  public Estabelecimento toEntity() {
    Estabelecimento entity = new Estabelecimento();
    entity.setId(this.id);
    entity.setNome(this.nome);
    entity.setEndereco(this.endereco);
    entity.setTelefone(this.telefone);

    return entity;
  }
}
