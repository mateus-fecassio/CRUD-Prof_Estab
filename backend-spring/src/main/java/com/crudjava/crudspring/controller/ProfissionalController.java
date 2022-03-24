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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/profissionais")
@AllArgsConstructor // Instancia os atributos automaticamente, sem necessidade de criar um
                    // construtor
public class ProfissionalController {

  private final ProfissionalRepository profissionalRepository;

  @GetMapping
  public List<Profissional> listar() {
    return profissionalRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<ProfissionalDTO> criar(@RequestBody ProfissionalDTO profissionalDTO) {
    Profissional profissional = profissionalDTO.toEntity();
    profissionalRepository.save(profissional);
    return new ResponseEntity<>(profissional.toDTO(), HttpStatus.CREATED);
  }

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
