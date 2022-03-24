package com.crudjava.crudspring.repository;

import com.crudjava.crudspring.model.Estabelecimento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

}
