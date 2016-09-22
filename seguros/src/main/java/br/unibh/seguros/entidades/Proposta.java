package br.unibh.seguros.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
@Entity
@Table(name="tb_proposta")

public class Proposta {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	private Long id;
	
	@NotBlank
	@Column (nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Pattern(regexp="[A-Z]")
	@Size(max=1,min=1)
	@Column (columnDefinition="CHAR(1)", nullable=false)
	private String classe;
	
	@NotBlank
	@Size (max=15)
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Column (name="codigo_susep", columnDefinition="CHAR(15)", unique=true)
	private String codigoSusep;
	
	@NotBlank
	@DecimalMin("0.01")
	@Column (name="valor_segurado", columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal valorSegurado;
	
	@NotBlank
	@DecimalMin("0.01")
	@Column (name="valor_franquia", columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal valorFranquia;
	
	
	@NotBlank
	@Column (name="data_inicio_vigencia", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioVigencia;
	
	
	@NotBlank
	@Column (name="data_termino_vigencia", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTerminoVigencia;
	
	@NotBlank
	@Column (name="carencia_em_meses", nullable=false)
	private int carenciaEmMeses;
	
	@NotBlank
	@DecimalMin("0.01")
	@Column (name="valor_premio", columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal valorPremio;
	
	@NotBlank
	@Column (name="dia_pagamento", nullable=false)
	private int diaPagamento;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=50)
	@Column (name="banco_pagamento", length = 50, nullable=false)
	private String bancoPagamento;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=15)
	@Column (length = 15, nullable=false)
	private String agencia;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=15)
	@Column (length = 15, nullable=false)
	private String conta;
	
	@ManyToOne
	private Segurado segurado;
	
	@OneToOne
	private Veiculo veiculo;
	
	@OneToOne
	private Questionario questionario;
	
	@OneToMany(mappedBy="proposta")
	private Set<Tramitacao> tramitacoes;
	
	@Version
	private Long version;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getCodigoSusep() {
		return codigoSusep;
	}
	public void setCodigoSusep(String codigoSusep) {
		this.codigoSusep = codigoSusep;
	}
	public BigDecimal getValorSegurado() {
		return valorSegurado;
	}
	public void setValorSegurado(BigDecimal valorSegurado) {
		this.valorSegurado = valorSegurado;
	}
	public BigDecimal getValorFranquia() {
		return valorFranquia;
	}
	public void setValorFranquia(BigDecimal valorFranquia) {
		this.valorFranquia = valorFranquia;
	}
	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}
	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}
	public Date getDataTerminoVigencia() {
		return dataTerminoVigencia;
	}
	public void setDataTerminoVigencia(Date dataTerminoVigencia) {
		this.dataTerminoVigencia = dataTerminoVigencia;
	}
	public int getCarenciaEmMeses() {
		return carenciaEmMeses;
	}
	public void setCarenciaEmMeses(int carenciaEmMeses) {
		this.carenciaEmMeses = carenciaEmMeses;
	}
	public BigDecimal getValorPremio() {
		return valorPremio;
	}
	public void setValorPremio(BigDecimal valorPremio) {
		this.valorPremio = valorPremio;
	}
	public int getDiaPagamento() {
		return diaPagamento;
	}
	public void setDiaPagamento(int diaPagamento) {
		this.diaPagamento = diaPagamento;
	}
	public String getBancoPagamento() {
		return bancoPagamento;
	}
	public void setBancoPagamento(String bancoPagamento) {
		this.bancoPagamento = bancoPagamento;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public Segurado getSegurado() {
		return segurado;
	}
	public void setSegurado(Segurado segurado) {
		this.segurado = segurado;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Questionario getQuestionario() {
		return questionario;
	}
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	public Set<Tramitacao> getTramitacoes() {
		return tramitacoes;
	}
	public void setTramitacoes(Set<Tramitacao> tramitacoes) {
		this.tramitacoes = tramitacoes;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Proposta [id=" + id + ", data=" + data + ", classe=" + classe + ", codigoSusep=" + codigoSusep
				+ ", valorSegurado=" + valorSegurado + ", valorFranquia=" + valorFranquia + ", dataInicioVigencia="
				+ dataInicioVigencia + ", dataTerminoVigencia=" + dataTerminoVigencia + ", carenciaEmMeses="
				+ carenciaEmMeses + ", valorPremio=" + valorPremio + ", diaPagamento=" + diaPagamento
				+ ", bancoPagamento=" + bancoPagamento + ", agencia=" + agencia + ", conta=" + conta + ", segurado="
				+ segurado + ", veiculo=" + veiculo + ", questionario=" + questionario + ", tramitacoes=" + tramitacoes
				+ ", version=" + version + "]";
	}
	public Proposta(Long id, Date data, String classe, String codigoSusep, BigDecimal valorSegurado,
			BigDecimal valorFranquia, Date dataInicioVigencia, Date dataTerminoVigencia, int carenciaEmMeses,
			BigDecimal valorPremio, int diaPagamento, String bancoPagamento, String agencia, String conta,
			Segurado segurado, Veiculo veiculo, Questionario questionario, Set<Tramitacao> tramitacoes, Long version) {
		super();
		this.id = id;
		this.data = data;
		this.classe = classe;
		this.codigoSusep = codigoSusep;
		this.valorSegurado = valorSegurado;
		this.valorFranquia = valorFranquia;
		this.dataInicioVigencia = dataInicioVigencia;
		this.dataTerminoVigencia = dataTerminoVigencia;
		this.carenciaEmMeses = carenciaEmMeses;
		this.valorPremio = valorPremio;
		this.diaPagamento = diaPagamento;
		this.bancoPagamento = bancoPagamento;
		this.agencia = agencia;
		this.conta = conta;
		this.segurado = segurado;
		this.veiculo = veiculo;
		this.questionario = questionario;
		this.tramitacoes = tramitacoes;
		this.version = version;
	}
	
	
	
}
