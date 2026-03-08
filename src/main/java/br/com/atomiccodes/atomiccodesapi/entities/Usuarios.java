package br.com.atomiccodes.atomiccodesapi.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.atomiccodes.atomiccodesapi.controller.dto.LoginRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	private Instant dataCadastro;
	@Column(unique = true)
	private String email;
	@JsonIgnore
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Roles> roles;
	
	private String nome;
	private String conselho;
	@Column(name = "dt_nascimento")
	private LocalDate dtNascimento;
	@Column(name = "conselho_classe")
	private String conselhoClasse;
	private String cpf;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "usuarios_unidades",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "unidade_id")
	)
	@JsonIgnoreProperties("usuariosUnidades")
	private Set<Unidades> unidades;
	
	@OneToMany(mappedBy = "owner")
	@JsonIgnoreProperties("owner")
	private Set<Unidades> unidadesOwner;

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Instant getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Roles> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConselho() {
		return this.conselho;
	}

	public void setConselho(String conselho) {
		this.conselho = conselho;
	}

	public LocalDate getDtNascimento() {
		return this.dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getConselhoClasse() {
		return this.conselhoClasse;
	}

	public void setConselhoClasse(String conselhoClasse) {
		this.conselhoClasse = conselhoClasse;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<Unidades> getUnidades() {
		return this.unidades;
	}

	public void setUnidades(Set<Unidades> unidades) {
		this.unidades = unidades;
	}
	
	public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(loginRequest.password(), this.password);
	}
}
