package br.com.atomiccodes.atomiccodesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.atomiccodes.atomiccodesapi.entities.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
	Roles findByNome(String nome);
}
