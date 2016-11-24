package br.unibh.seguros.controle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Segurado;
import br.unibh.seguros.negocio.ServicoSegurado;

@ManagedBean(name = "seguradomb")
@ViewScoped
public class ControladorSegurado extends ControleUtil {
	@Inject
	private ServicoSegurado ejb;
	private Segurado segurado;
	private String nomeArg;
	private List<Segurado> lista;

	public Segurado getSegurado() {
		return segurado;
	}

	public void setSegurado(Segurado segurado) {
		this.segurado = segurado;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public List<Segurado> getLista() {
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
			if (segurado.getId() == null) {
				segurado = ejb.insert(segurado);
				
				mostrarMensagem("Inclusão realizada com sucesso!");
			} else {
				segurado = ejb.update(segurado);
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
		segurado = new Segurado();
	}

	public void cancelar() {
		segurado = null;
	}

	public void editar(Long id) {
		try {
			segurado = ejb.find(id);
			return;
		} catch (Exception e) {
			mostrarErro(e);
		}
		segurado = null;
	}

	public void excluir(Long id) {
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
			return;
		}
		segurado = null;
		mostrarMensagem("Exclusão realizada com sucesso!");
	}
}