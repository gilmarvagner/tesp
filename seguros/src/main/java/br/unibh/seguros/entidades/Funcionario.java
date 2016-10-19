package br.unibh.seguros.entidades;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
@Entity
@Table(name="tb_funcionario")
@NamedQueries({
@NamedQuery(name="Funcionario.findByName", query = "select o from Funcionario o where o.nome like :nome")})

public class Funcionario extends Pessoa {
	
	
	public Funcionario(){
		
	}
	public Funcionario(Long id, String nome, String sexo, String cpf, String telefoneComercial,
			String telefoneResidencial, String telefoneCelular, String email, Date dataNascimento, Date dataCadastro) {
		super(id, nome, sexo, cpf, telefoneComercial, telefoneResidencial, telefoneCelular, email, dataNascimento,
				dataCadastro, null);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Funcionario(Long id, String nome, String sexo, String cpf, String telefoneComercial,
			String telefoneResidencial, String telefoneCelular, String email, java.util.Date dataNascimento,
			java.util.Date dataCadastro, Long version, Setor setor, String perfil, String login, String senha) {
		super(id, nome, sexo, cpf, telefoneComercial, telefoneResidencial, telefoneCelular, email, dataNascimento,
				dataCadastro, version);
		this.setor = setor;
		this.perfil = perfil;
		this.login = login;
		this.senha = senha;
	}



	@ManyToOne
	private Setor setor;
	
	
	@NotBlank
	@Size (max=30)
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Column (length=30, nullable=false)
	private String perfil;
	
	@NotBlank
	@Pattern(regexp="[A-z0-9]*",message="Não deve ter caracteres especiais e espaços")
	@Size(min=8,max=15)
	@Column (length=15, nullable=false)
	private String login;
	
	
	@Size(max=100)
	@Column (length=100, nullable=false)
	private String senha;
	

	
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String toString() {
		return "Funcionario [perfil=" + perfil + ", login=" + login + ", senha=" + senha + ", toString()="
				+ super.toString() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	

}
