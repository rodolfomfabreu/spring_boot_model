package br.com.atomiccodes.atomiccodesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.atomiccodes.atomiccodesapi.entities.Unidades;

public interface UnidadeRepository extends JpaRepository<Unidades, Long> {

}
