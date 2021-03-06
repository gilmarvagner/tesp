package br.unibh.seguros.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;

import br.unibh.seguros.entidades.Tramitacao;
import br.unibh.seguros.negocio.ServicoTramitacao;

public class ConversorTramitacao implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		try {
			ServicoTramitacao ss = (ServicoTramitacao) new InitialContext().lookup("java:global/seguros-0.0.1-SNAPSHOT/ServicoTramitacao");
			if (value != null)
				return ss.find(new Long(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		if (value != null && value instanceof Tramitacao) {
			Tramitacao s = (Tramitacao) value;
			if (s.getId() != null)
				return s.getId().toString();
		}
		return null;
	}
}