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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/estabelecimentos")
@AllArgsConstructor
public class EstabelecimentoController {
  private final EstabelecimentoRepository estabelecimentoRepository;

  @GetMapping
  public List<Estabelecimento> listar() {
    return estabelecimentoRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<EstabelecimentoDTO> criar(@RequestBody EstabelecimentoDTO estabelecimentoDTO) {
    Estabelecimento estabelecimento = estabelecimentoDTO.toEntity();
    estabelecimentoRepository.save(estabelecimento);
    return new ResponseEntity<>(estabelecimento.toDTO(), HttpStatus.CREATED);
  }

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
