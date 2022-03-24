package com.crudjava.crudspring.controller;

import java.util.List;
import java.util.Optional;

import com.crudjava.crudspring.dto.EstabelecimentoDTO;
import com.crudjava.crudspring.model.Estabelecimento;
import com.crudjava.crudspring.repository.EstabelecimentoRepository;

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
@RequestMapping("/api/estabelecimentos")
public class EstabelecimentoController {
  private EstabelecimentoController(EstabelecimentoRepository estabelecimentoRepository) {
    this.estabelecimentoRepository = estabelecimentoRepository;
  }

  private final EstabelecimentoRepository estabelecimentoRepository;
  

  /**
   * Método para listar todos os estabelecimentos cadastrados.
   * @return Lista com todo os estabelecimentos cadastrados utilizando método do JpaRepository de listar tudo.
   */
  @GetMapping
  public List<Estabelecimento> listar() {
    return estabelecimentoRepository.findAll();
  }
  
  /**
   * Método para inserção de um novo estabelecimento.
   * @param estabelecimentoDTO ,um objeto DTO que será manipulado, convertido para uma entidade e então armazenado no banco.
   * @return Resposta de sucesso na criação.
   */
  @PostMapping
  public ResponseEntity<EstabelecimentoDTO> criar(@RequestBody EstabelecimentoDTO estabelecimentoDTO) {
    Estabelecimento estabelecimento = estabelecimentoDTO.toEntity();
    estabelecimentoRepository.save(estabelecimento);
    return new ResponseEntity<>(estabelecimento.toDTO(), HttpStatus.CREATED);
  }

  /**
   * Método para excluir um estabelecimento dado um determinado id.
   * @param id do estabelecimento a ser excluído.
   * @return Resposta sobre o sucesso ou não da exclusão.
   */
  @DeleteMapping(path = { "/{id}" })
  public ResponseEntity<Long> excluir(@PathVariable long id) {
    Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
    if (estabelecimento.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    estabelecimentoRepository.deleteById(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
