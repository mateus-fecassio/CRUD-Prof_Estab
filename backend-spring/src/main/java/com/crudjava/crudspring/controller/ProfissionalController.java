package com.crudjava.crudspring.controller;

import java.util.List;
import java.util.Optional;

import com.crudjava.crudspring.dto.ProfissionalDTO;
import com.crudjava.crudspring.model.Profissional;
import com.crudjava.crudspring.repository.ProfissionalRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

  private ProfissionalController(ProfissionalRepository profissionalRepository) {
    this.profissionalRepository = profissionalRepository;
  }

  private final ProfissionalRepository profissionalRepository;

  /**
   * Método para listar todos os profissionais cadastrados.
   * @return Lista com todo os profissionais cadastrados utilizando método do JpaRepository de listar tudo.
   */
  @GetMapping
  public List<Profissional> listar() {
    return profissionalRepository.findAll();
  }

  /**
   * Método para inserção de um novo profissional.
   * @param estabelecimentoDTO ,um objeto DTO que será manipulado, convertido para uma entidade e então armazenado no banco.
   * @return Resposta de sucesso na criação.
   */
  @PostMapping
  public ResponseEntity<ProfissionalDTO> criar(@RequestBody ProfissionalDTO profissionalDTO) {
    Profissional profissional = profissionalDTO.toEntity();
    profissionalRepository.save(profissional);
    return new ResponseEntity<>(profissional.toDTO(), HttpStatus.CREATED);
  }

  /**
   * Método para excluir um profissional dado um determinado id.
   * @param id do profissional a ser excluído.
   * @return Resposta sobre o sucesso ou não da exclusão.
   */
  @DeleteMapping(path = { "/{id}" })
  public ResponseEntity<Long> excluir(@PathVariable long id) {
    Optional<Profissional> profissional = profissionalRepository.findById(id);
    if (profissional.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    profissionalRepository.deleteById(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }

}
