package com.crudjava.crudspring.dto;

import com.crudjava.crudspring.model.Estabelecimento;


public class EstabelecimentoDTO {
  private Long id;

  private String nome;

  private String endereco;

  private String telefone;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public Estabelecimento toEntity() {
    Estabelecimento entity = new Estabelecimento();
    entity.setId(this.id);
    entity.setNome(this.nome);
    entity.setEndereco(this.endereco);
    entity.setTelefone(this.telefone);

    return entity;
  }
}
