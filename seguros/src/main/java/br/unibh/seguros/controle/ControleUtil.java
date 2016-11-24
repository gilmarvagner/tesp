package br.unibh.seguros.controle;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class ControleUtil {
	@Inject
	protected Logger log;

	protected void mostrarAviso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage("messagePanel",
				new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, ""));
		log.warning("Warn: " + mensagem);
	}

	protected void mostrarMensagem(String mensagem) {
		FacesContext.getCurrentInstance().addMessage("messagePanel",
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
		log.info("Info: " + mensagem);
	}

	protected void mostrarErro(Exception e) {
		FacesContext.getCurrentInstance().addMessage("messagePanel",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: " + e.getMessage(), ""));
		log.severe("Erro: " + e.getMessage());
		e.printStackTrace();
	}

	protected boolean checkString(Throwable e, String str) {
		if (e.getMessage().contains(str)) {
			return true;

		} else if (e.getCause() != null) {
			return checkString(e.getCause(), str);
		}
		return false;
	}
}