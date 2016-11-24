package br.unibh.seguros.controle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Funcionario;
import br.unibh.seguros.negocio.ServicoFuncionario;

@ManagedBean(name = "funcionariomb")
@ViewScoped
public class ControleFuncionario extends ControleUtil {
	@Inject
	private ServicoFuncionario ejb;
	private Funcionario funcionario;
	private String nomeArg;
	private List<Funcionario> lista;

	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public List<Funcionario> getLista() {
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
			if (funcionario.getId() == null) {
				funcionario = ejb.insert(funcionario);
				mostrarMensagem("Inclusão realizada com sucesso!");
			} else {
				funcionario = ejb.update(funcionario);
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
		funcionario = new Funcionario();
	}

	public void cancelar() {
		funcionario = null;
	}

	public void editar(Long id) {
		try {
			funcionario = ejb.find(id);
			return;
		} catch (Exception e) {
			mostrarErro(e);
		}
		funcionario = null;
	}

	public void excluir(Long id) {
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
			return;
		}
		funcionario = null;
		mostrarMensagem("Exclusão realizada com sucesso!");
	}
}