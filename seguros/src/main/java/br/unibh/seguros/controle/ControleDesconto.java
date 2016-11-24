package br.unibh.seguros.controle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Desconto;
import br.unibh.seguros.negocio.ServicoDesconto;


@ManagedBean(name = "descontomb")
@ViewScoped
public class ControleDesconto extends ControleUtil {
	@Inject
	private ServicoDesconto ejb;
	private Desconto desconto;
	private String nomeArg;
	private List<Desconto> lista;

	public Desconto getDesconto() {
		return desconto;
	}

	public void setDesconto(Desconto Desconto) {
		this.desconto = Desconto;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public List<Desconto> getLista() {
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
			if (desconto.getId() == null) {
				desconto = ejb.insert(desconto);
				
				mostrarMensagem("Inclusão realizada com sucesso!");
			} else {
				desconto = ejb.update(desconto);
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
		desconto = new Desconto();
	}

	public void cancelar() {
		desconto = null;
	}

	public void editar(Long id) {
		try {
			desconto = ejb.find(id);
			return;
		} catch (Exception e) {
			mostrarErro(e);
		}
		desconto = null;
	}

	public void excluir(Long id) {
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
			return;
		}
		desconto = null;
		mostrarMensagem("Exclusão realizada com sucesso!");
	}
}