package br.unibh.seguros.entidades;

import java.util.Set;

public class Setor {
	
	public Setor(Long id, String nome, String sigla, Setor setorSuperior, Set<Funcionario> funcionarios) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.setorSuperior = setorSuperior;
		this.funcionarios = funcionarios;
	}
	private Long id;
	private String nome;
	private String sigla;
	private Setor setorSuperior;
	private Set<Funcionario> funcionarios;
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

}
