package com.crudjava.crudspring.dto;

import java.util.HashSet;
import java.util.Set;

import com.crudjava.crudspring.model.Profissional;

import lombok.Data;

@Data
public class ProfissionalDTO {

  private Long id;

  private String nome;

  private String endereco;

  private String celular;

  private String telefone;

  private String funcao;

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