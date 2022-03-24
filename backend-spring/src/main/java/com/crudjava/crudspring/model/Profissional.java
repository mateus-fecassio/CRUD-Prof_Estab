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

import lombok.Data;

@Data
@Entity
public class Profissional {

  @Id
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

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "profissional_estabelecimento", joinColumns = {
      @JoinColumn(name = "profissional_id") }, inverseJoinColumns = { @JoinColumn(name = "estabelecimento_id") })
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
