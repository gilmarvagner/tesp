package br.unibh.seguros.entidades;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity @Inheritance(strategy=InheritanceType.JOINED)
@Table(name="tb_pessoa")



public abstract class Pessoa {
		
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	private Long id;
	
	@Column (length = 100, nullable=false)
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú .']*",message="Não pode ser nulo, vazio ou apenas caracteres de espaço. COD-01P")
	@Size(min=3,max=100)
	private String nome;
	
	@NotBlank
	@Pattern(regexp="[MF]{1}",message="Não pode ser nulo, vazio ou apenas caracteres de espaço, Permitido somente M: Masculino e F: Feminino. COD-02P")
	@Column (columnDefinition="CHAR(1)", nullable=false)
	private String sexo;
	
	@NotBlank
	@CPF
	@Column (columnDefinition="CHAR(11)", nullable=false, unique=true)
	private String cpf;
	
	@Pattern(regexp="\\(\\d{2}\\)\\d{0,1}\\d{4}-\\d{4}",message="Exemplo (XX)xxxx-xxxx. COD-04P")
	@Column (name="telefone_comercial", columnDefinition="CHAR(14)",nullable=false )
	private String telefoneComercial;
	
	@Pattern(regexp="\\(\\d{2}\\)\\d{0,1}\\d{4}-\\d{4}",message="Exemplo (XX)xxxx-xxxx. COD-05P")
	@Column (name="telefone_residencial", columnDefinition="CHAR(14)", nullable=false)
	private String telefoneResidencial;
	
	@Pattern(regexp="\\(\\d{2}\\)\\d{0,1}\\d{4}-\\d{4}",message="Exemplo (XX)xxxx-xxxx. COD-06P")
	@Column (name="telefone_celular", columnDefinition="CHAR(14)", nullable=false)
	private String telefoneCelular;
	
	@Email
	@Size(max=100)
	@Column (length=100)
	private String email;
	
	@NotNull
	@Past
	@Column (name="data_nascimento", nullable=false)
	@Temporal (TemporalType.DATE)
	private Date dataNascimento;
	
	@NotNull
	@Past
	@Column (name="data_cadastro", nullable=false)
	@Temporal (TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Version
	private Long version;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((telefoneCelular == null) ? 0 : telefoneCelular.hashCode());
		result = prime * result + ((telefoneComercial == null) ? 0 : telefoneComercial.hashCode());
		result = prime * result + ((telefoneResidencial == null) ? 0 : telefoneResidencial.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (telefoneCelular == null) {
			if (other.telefoneCelular != null)
				return false;
		} else if (!telefoneCelular.equals(other.telefoneCelular))
			return false;
		if (telefoneComercial == null) {
			if (other.telefoneComercial != null)
				return false;
		} else if (!telefoneComercial.equals(other.telefoneComercial))
			return false;
		if (telefoneResidencial == null) {
			if (other.telefoneResidencial != null)
				return false;
		} else if (!telefoneResidencial.equals(other.telefoneResidencial))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	public Pessoa(){
		
	}
	public Pessoa(Long id, String nome, String sexo, String cpf, String telefoneComercial, String telefoneResidencial,
			String telefoneCelular, String email, Date dataNascimento, Date dataCadastro, Long version) {
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.cpf = cpf;
		this.telefoneComercial = telefoneComercial;
		this.telefoneResidencial = telefoneResidencial;
		this.telefoneCelular = telefoneCelular;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
		this.version = version;
	}
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", cpf=" + cpf + ", telefoneComercial="
				+ telefoneComercial + ", telefoneResidencial=" + telefoneResidencial + ", telefoneCelular="
				+ telefoneCelular + ", email=" + email + ", dataNascimento=" + dataNascimento + ", dataCadastro="
				+ dataCadastro + ", version=" + version + "]";
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefoneComercial() {
		return telefoneComercial;
	}
	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}
	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}
	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
}
