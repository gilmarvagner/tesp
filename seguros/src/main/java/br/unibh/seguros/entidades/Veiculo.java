package br.unibh.seguros.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
@Entity
@Table(name="tb_veiculo")

public class Veiculo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	private Long id;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=50)
	@Column (length = 50, nullable=false)
	private String marca;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=150)
	@Column (length = 150, nullable=false)
	private String descricao;
	
	@NotBlank
	@Min(value=1950, message ="Só é permitido acima de 1950")
	@Column (name="ano_fabricacao", nullable=false )
	private int anoFabricacao;
	
	@NotBlank
	@Min(value=1950, message ="Só é permitido acima de 1950")
	@Column (name="ano_modelo", nullable=false)
	private int anoModelo;
	
	@NotBlank
	@Pattern(regexp="[A-Z]{3}-\\d{4}",message="Exemplo modelo de placa AAA-9999")
	@Column (columnDefinition="CHAR(8)", nullable=false)
	private String placa;
	
	@NotBlank
	@Pattern(regexp="[A-z ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=50)
	@Column (length = 50, nullable=false)
	private String chassi;
	
	@NotBlank
	@Column (name="tipo_combustivel", nullable=false)
	private TipoCombustivel tipoCombustivel;
	
	@NotBlank
	@Column (name="zero_km", nullable=false)
	private Boolean zeoKm;
	
	@NotBlank
	@Column (name="veiculo_alienado", nullable=false)
	private Boolean veiculoAlienado;
	
	@Version
	private Long version;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public int getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public TipoCombustivel getTipoCombustivel() {
		return tipoCombustivel;
	}
	public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}
	public Boolean getZeoKm() {
		return zeoKm;
	}
	public void setZeoKm(Boolean zeoKm) {
		this.zeoKm = zeoKm;
	}
	public Boolean getVeiculoAlienado() {
		return veiculoAlienado;
	}
	public void setVeiculoAlienado(Boolean veiculoAlienado) {
		this.veiculoAlienado = veiculoAlienado;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", marca=" + marca + ", descricao=" + descricao + ", anoFabricacao="
				+ anoFabricacao + ", anoModelo=" + anoModelo + ", placa=" + placa + ", chassi=" + chassi
				+ ", tipoCombustivel=" + tipoCombustivel + ", zeoKm=" + zeoKm + ", veiculoAlienado=" + veiculoAlienado
				+ ", version=" + version + "]";
	}
	public Veiculo(Long id, String marca, String descricao, int anoFabricacao, int anoModelo, String placa,
			String chassi, TipoCombustivel tipoCombustivel, Boolean zeoKm, Boolean veiculoAlienado, Long version) {
		super();
		this.id = id;
		this.marca = marca;
		this.descricao = descricao;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		this.placa = placa;
		this.chassi = chassi;
		this.tipoCombustivel = tipoCombustivel;
		this.zeoKm = zeoKm;
		this.veiculoAlienado = veiculoAlienado;
		this.version = version;
	}

}
