package com.crudjava.crudspring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import com.crudjava.crudspring.dto.ProfissionalDTO;

@Entity
public class Profissional {

  //#region Atributos
  @Id //definição da chave primária a ser usada, com o tipo de geração incremental gerenciada pelo próprio SGBD 
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 50, nullable = false)
  private String nome;

  @Column(length = 50, nullable = false)
  private String endereco;

  @Column(length = 12, nullable = false)
  private String celular;

  @Column(length = 11, nullable = false)
  private String telefone;

  @Column(length = 50, nullable = false)
  private String funcao;
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

  public Set<Estabelecimento> getEstabelecimentos() {
    return estabelecimentos;
  }

  public void setEstabelecimentos(Set<Estabelecimento> estabelecimentos) {
    this.estabelecimentos = estabelecimentos;
  }
  //#endregion


  @ManyToMany(fetch = FetchType.LAZY) //relação muitos para muitos, visto que um profissional pode estar associado a vários estabelecimentos.
  @JoinTable(name = "profissional_estabelecimento", joinColumns = { 
      @JoinColumn(name = "profissional_id") }, inverseJoinColumns = { @JoinColumn(name = "estabelecimento_id") }) //criação de uma tabela temporária para guardar essa relação
  private Set<Estabelecimento> estabelecimentos = new HashSet<>();

  public ProfissionalDTO toDTO() {
    ProfissionalDTO dto = new ProfissionalDTO();
    dto.setId(this.id);
    dto.setNome(this.nome);
    dto.setEndereco(this.endereco);
    dto.setCelular(this.celular);
    dto.setTelefone(this.telefone);
    dto.setFuncao(this.funcao);
    this.getEstabelecimentos().forEach(estabelecimento -> dto.getEstabelecimentos().add(estabelecimento.toDTO()));
    return dto;
  }

}
