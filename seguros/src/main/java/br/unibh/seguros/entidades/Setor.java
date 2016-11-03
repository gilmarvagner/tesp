package br.unibh.seguros.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
@Entity
@Table(name="tb_setor")
@NamedQueries({
@NamedQuery(name="Setor.findByName", query = "select o from Setor o where o.nome like :nome"),
@NamedQuery(name="Setor.findByIdComSetorSuperior", query = "select o from Setor o left join fetch o.setorSuperior where o.id = :id"),
@NamedQuery(name="Setor.findByNameComFuncionarios", query = "select o from Setor o join fetch o.funcionarios where o.nome like :nome")
})

public class Setor {
	
	public Setor(Long id, String nome, String sigla, Setor setorSuperior, Set<Funcionario> funcionarios) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.setorSuperior = setorSuperior;
		this.funcionarios = funcionarios;
	}
	
	public Setor(){
		
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	@Column (unique=true)
	private Long id;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú .']*",message="Apenas caracteres de A à Z maiúsculos ou minúsculos, com ou sem acentuação, além dos caracteres de espaço, ponto e aspas simples.")
	@Size(min=3,max=150) 
	@Column (length = 150, nullable=false)
	private String nome;
	
	@NotBlank
	@Pattern(regexp="[A-Z]*",message="Deverá ter apenas Letras maiúsculos sem espaços")
	@Size (min=2,max=10)
	@Column (length = 10, nullable=false)
	private String sigla;
	
	
	@OneToOne
	@JoinColumn (name="setor_superior")
	private Setor setorSuperior;
	
	@OneToMany(mappedBy="setor")
	private Set<Funcionario> funcionarios;
	
	@Version
	private Long version;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((funcionarios == null) ? 0 : funcionarios.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((setorSuperior == null) ? 0 : setorSuperior.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setor other = (Setor) obj;
		if (funcionarios == null) {
			if (other.funcionarios != null)
				return false;
		} else if (!funcionarios.equals(other.funcionarios))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (setorSuperior == null) {
			if (other.setorSuperior != null)
				return false;
		} else if (!setorSuperior.equals(other.setorSuperior))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Setor [id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", setorSuperior=" + setorSuperior
				+ ", funcionarios=" + funcionarios + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public Setor getSetorSuperior() {
		return setorSuperior;
	}
	public void setSetorSuperior(Setor setorSuperior) {
		this.setorSuperior = setorSuperior;
	}
	public Set<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(Set<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	

}
