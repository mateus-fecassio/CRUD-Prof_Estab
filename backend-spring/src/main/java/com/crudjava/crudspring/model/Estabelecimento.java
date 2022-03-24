package com.crudjava.crudspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.crudjava.crudspring.dto.EstabelecimentoDTO;

@Entity
public class Estabelecimento {

  //#region Atributos
  @Id //definição da chave primária a ser usada, com o tipo de geração incremental gerenciada pelo próprio SGBD 
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 50, nullable = false)
  private String nome;

  @Column(length = 50, nullable = false)
  private String endereco;

  @Column(length = 11, nullable = false)
  private String telefone;
  //#endregion


  //#region Getters and Setters
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
  //#endregion

  public EstabelecimentoDTO toDTO() {
    EstabelecimentoDTO dto = new EstabelecimentoDTO();
    dto.setId(this.id);
    dto.setNome(this.nome);
    dto.setEndereco(this.endereco);
    dto.setTelefone(this.telefone);

    return dto;
  }

}
