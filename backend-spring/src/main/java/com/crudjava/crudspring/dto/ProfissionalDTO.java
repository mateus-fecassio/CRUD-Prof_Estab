package com.crudjava.crudspring.dto;

import java.util.HashSet;
import java.util.Set;

import com.crudjava.crudspring.model.Profissional;

public class ProfissionalDTO {

  private Long id;

  private String nome;

  private String endereco;

  private String celular;

  private String telefone;

  private String funcao;

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

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getFuncao() {
    return funcao;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }

  public Set<EstabelecimentoDTO> getEstabelecimentos() {
    return estabelecimentos;
  }

  public void setEstabelecimentos(Set<EstabelecimentoDTO> estabelecimentos) {
    this.estabelecimentos = estabelecimentos;
  }

  private Set<EstabelecimentoDTO> estabelecimentos = new HashSet<>();

  public Profissional toEntity() {
    Profissional entity = new Profissional();
    entity.setId(this.id);
    entity.setNome(this.nome);
    entity.setEndereco(this.endereco);
    entity.setCelular(this.celular);
    entity.setTelefone(this.telefone);
    entity.setFuncao(this.funcao);
    this.getEstabelecimentos()
        .forEach(estabelecimentoDTO -> entity.getEstabelecimentos().add(estabelecimentoDTO.toEntity()));
    return entity;
  }

}