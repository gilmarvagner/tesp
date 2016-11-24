package br.unibh.seguros.controle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Proposta;
import br.unibh.seguros.negocio.ServicoProposta;

@ManagedBean(name = "propostamb")
@ViewScoped
public class ControleProposta extends ControleUtil {
	
	@Inject
	private ServicoProposta ejb;
	
	private Proposta proposta;
	
	private String nomeArg;
	
	private List<Proposta> lista;

	
	public void gerarCodigoSusep(){
		if (proposta != null){
			
			try {
				proposta.setCodigoSusep(ejb.criarCodigoSUSEP(proposta));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public List<Proposta> getLista() {
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
			if (proposta.getId() == null) {
				proposta = ejb.insert(proposta);
				
				mostrarMensagem("Inclusão realizada com sucesso!");
			} else {
				proposta = ejb.update(proposta);
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
		proposta = new Proposta();
		proposta.setCodigoSusep("AA");
	}

	public void cancelar() {
		proposta = null;
	}

	public void editar(Long id) {
		try {
			proposta = ejb.find(id);
			return;
		} catch (Exception e) {
			mostrarErro(e);
		}
		proposta = null;
	}

	public void excluir(Long id) {
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
			return;
		}
		proposta = null;
		mostrarMensagem("Exclusão realizada com sucesso!");
	}
}