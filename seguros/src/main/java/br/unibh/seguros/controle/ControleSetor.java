package br.unibh.seguros.controle;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import br.unibh.seguros.entidades.Setor;
import br.unibh.seguros.negocio.ServicoSetor;

@ManagedBean(name = "setormb")
@ViewScoped
public class ControleSetor extends ControleUtil {
	@Inject
	private ServicoSetor ejb;
	private Setor setor;
	private String nomeArg;
	private List<Setor> lista;

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public List<Setor> getLista() {
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
			if (setor.getId() == null) {
				setor = ejb.insert(setor);
				
				mostrarMensagem("Inclusão realizada com sucesso!");
			} else {
				setor = ejb.update(setor);
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
		setor = new Setor();
	}

	public void cancelar() {
		setor = null;
	}

	public void editar(Long id) {
		try {
			setor = ejb.find(id);
			return;
		} catch (Exception e) {
			mostrarErro(e);
		}
		setor = null;
	}

	public void excluir(Long id) {
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
			return;
		}
		setor = null;
		mostrarMensagem("Exclusão realizada com sucesso!");
	}
}