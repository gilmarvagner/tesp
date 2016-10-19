package br.unibh.seguros.entidades;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name="tb_tramitacao")

public class Tramitacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Proposta proposta;
	
	@NotNull
	@Column (name="etapa_processo", nullable=false)
	private EtapaProcesso EtapaProcesso;
	
	@NotNull
	@Column (name="data_hora", nullable=false)
	@Temporal(TemporalType.TIMESTAMP) 
	private Date dataHora;
	
	@NotNull
	@Column (name="tipo_decisao", nullable=false)
	private TipoDecisao tipoDecisao;
	
	@OneToOne
	@JoinColumn (name="usuario_decisao")
	private Funcionario usuarioDecisao;
	
	
	@Pattern(regexp="[A-z]*",message="Não deve ter caracteres especiais e espaços")
	@Size(max=4000)
	@Column (length = 4000)
	private String comentario;
	@Lob
	private File documento;
	@Version
	private Long version;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Proposta getProposta() {
		return proposta;
	}
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	public EtapaProcesso getEtapaProcesso() {
		return EtapaProcesso;
	}
	public void setEtapaProcesso(EtapaProcesso etapaProcesso) {
		EtapaProcesso = etapaProcesso;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public TipoDecisao getTipoDecisao() {
		return tipoDecisao;
	}
	public void setTipoDecisao(TipoDecisao tipoDecisao) {
		this.tipoDecisao = tipoDecisao;
	}
	public Funcionario getUsuarioDecisao() {
		return usuarioDecisao;
	}
	public void setUsuarioDecisao(Funcionario usuarioDecisao) {
		this.usuarioDecisao = usuarioDecisao;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public File getDocumento() {
		return documento;
	}
	public void setDocumento(File documento) {
		this.documento = documento;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Tramitacao [id=" + id + ", proposta=" + proposta + ", dataHora=" + dataHora + ", usuarioDecisao="
				+ usuarioDecisao + ", comentario=" + comentario + ", documento=" + documento + ", version=" + version
				+ "]";
	}
	public Tramitacao(Long id, Proposta proposta, br.unibh.seguros.entidades.EtapaProcesso etapaProcesso, Date dataHora,
			TipoDecisao tipoDecisao, Funcionario usuarioDecisao, String comentario, File documento, Long version) {
		super();
		this.id = id;
		this.proposta = proposta;
		EtapaProcesso = etapaProcesso;
		this.dataHora = dataHora;
		this.tipoDecisao = tipoDecisao;
		this.usuarioDecisao = usuarioDecisao;
		this.comentario = comentario;
		this.documento = documento;
		this.version = version;
	}
	
	
	public Tramitacao(){
		
	}
	
	
	
	}
	
	