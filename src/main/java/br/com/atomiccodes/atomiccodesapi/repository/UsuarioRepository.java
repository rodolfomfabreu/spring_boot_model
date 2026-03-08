package br.com.atomiccodes.atomiccodesapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.atomiccodes.atomiccodesapi.controller.dto.SelectUsuarioDto;
import br.com.atomiccodes.atomiccodesapi.entities.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
	
	@Query("SELECT u FROM Usuarios u LEFT JOIN FETCH u.roles WHERE u.email = :email")
	Optional<Usuarios> findByEmail(@Param("email") String email);
	
	@Query("SELECT COUNT(u) > 0 FROM Usuarios u WHERE u.email = :email")
	boolean existsByEmail(@Param("email") String email);

	@Query("SELECT u FROM Usuarios u LEFT JOIN FETCH u.unidades WHERE u.userId = :user_id")
	Optional<Usuarios> findUnidadeByUserId(@Param("user_id") Long user_id);

	@Query("SELECT new br.com.atomiccodes.atomiccodesapi.controller.dto.SelectUsuarioDto(u.userId, u.nome, u.email, u.conselho, u.conselhoClasse, u.dtNascimento, u.cpf, u.dataCadastro) FROM Usuarios u")
	List<SelectUsuarioDto> findAllUsers();
}
