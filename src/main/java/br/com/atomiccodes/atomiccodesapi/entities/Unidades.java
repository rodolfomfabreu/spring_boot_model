package br.com.atomiccodes.atomiccodesapi.entities;

import java.time.Instant;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidades")
public class Unidades {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unidade_id")
	private Long unidadeId;
	@CreationTimestamp
	private Instant createdAt;
	private String nome;
	private boolean ativo;
	private String tipo;
	@ManyToOne
	@JoinColumn(name = "owner_id")
	@JsonIgnoreProperties({"unidades", "unidadesOwner", "password", "roles"})
	private Usuarios owner;
	private String email;
	private String endereco;
	private String municipio;
	private String cnpj;
	private String uf;
	@Column(name = "codigo_ibge")
	private String codigoIbge;
	@Column(name = "codigo_cnes")
	private String codigoCnes;
	@ManyToMany(mappedBy = "unidades") 
    @JsonIgnoreProperties("unidades")
    private Set<Usuarios> usuariosComAcesso;

	public Long getUnidadeId() {
		return unidadeId;
	}
	public void setUnidadeId(Long unidadeId) {
		this.unidadeId = unidadeId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Instant getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public boolean getAtivo() {
		return this.ativo;
	}


	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuarios getOwner() {
		return this.owner;
	}

	public void setOwner(Usuarios owner) {
		this.owner = owner;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getCodigoCnes() {
		return codigoCnes;
	}

	public void setCodigoCnes(String codigoCnes) {
		this.codigoCnes = codigoCnes;
	}
}
