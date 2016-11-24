package br.unibh.seguros.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_desconto")
@NamedQueries({
@NamedQuery(name="Desconto.findByName", query = "select o from Desconto o where o.classe like :classe"),
@NamedQuery(name="Desconto.findByClasse", query = "select o from Desconto o where o.classe = :classe and o.dataFim is null"),
@NamedQuery(name="Desconto.findAllByClasse", query = "select o from Desconto o where o.classe = :classe")
})
public class Desconto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	private Long id;

	@NotBlank
	@Pattern(regexp="[A-Z]")
	@Column (columnDefinition="CHAR(1)", nullable=false)
	private String classe ;
	
	
	@NotNull
	@Past
	@Temporal (TemporalType.DATE)
	@Column (name="data_inicio", nullable=false)
	private Date dataInicio;
	
	
	
	@Future
	@Temporal (TemporalType.DATE)
	@Column (name="data_fim")
	private Date dataFim;
	
	@NotNull
	@DecimalMin("0.01")
	@DecimalMax("100.00")
	@Column (name="percentual_desconto", columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal percentualDesconto;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}
	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	@Override
	public String toString() {
		return "Desconto [id=" + id + ", classe=" + classe + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
				+ ", percentualDesconto=" + percentualDesconto + "]";
	}
	public Desconto(){
		
	}
	public Desconto(Long id, String classe, Date dataInicio, Date dataFim, BigDecimal percentualDesconto) {
		super();
		this.id = id;
		this.classe = classe;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.percentualDesconto = percentualDesconto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classe == null) ? 0 : classe.hashCode());
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((percentualDesconto == null) ? 0 : percentualDesconto.hashCode());
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
		Desconto other = (Desconto) obj;
		if (classe == null) {
			if (other.classe != null)
				return false;
		} else if (!classe.equals(other.classe))
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (percentualDesconto == null) {
			if (other.percentualDesconto != null)
				return false;
		} else if (!percentualDesconto.equals(other.percentualDesconto))
			return false;
		return true;
	}
	
	
	
}
