package br.unibh.seguros.controle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Tramitacao;
import br.unibh.seguros.negocio.ServicoTramitacao;


@ManagedBean(name = "tramitacaomb")
@ViewScoped

public class ControleTramitacao extends ControleUtil {
	@Inject
	private ServicoTramitacao ejb;
	private Tramitacao tramitacao;
	private String nomeArg;
	private List<Tramitacao> lista;

	
	public Tramitacao getTramitacao() {
		return tramitacao;
	}

	public void setTramitacao(Tramitacao tramitacao) {
		this.tramitacao = tramitacao;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public List<Tramitacao> getLista() {
		return lista;
	}

	@PostConstruct
	public void inicializaLista() {
		try {
			lista = ejb.findAll();
		} catch (Exception e) {
			mostrarErro(e);
		}
	}

	public void gravar() {

		try {
			if (tramitacao.getId() == null) {
				tramitacao = ejb.insert(tramitacao);
				mostrarMensagem("Inclusão realizada com sucesso!");
			} else {
				tramitacao = ejb.update(tramitacao);
				mostrarMensagem("Atualização realizada com sucesso!");
			}
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
		}
	}

	public void pesquisar() {
		try {
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
		}
	}

	public void novo() {
		tramitacao = new Tramitacao();
	}

	public void cancelar() {
		tramitacao = null;
	}

	public void editar(Long id) {
		try {
			tramitacao = ejb.find(id);
			return;
		} catch (Exception e) {
			mostrarErro(e);
		}
		tramitacao = null;
	}

	public void excluir(Long id) {
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
			return;
		}
		tramitacao = null;
		mostrarMensagem("Exclusão realizada com sucesso!");
	}
}