package br.unibh.seguros.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;

import br.unibh.seguros.entidades.Desconto;
import br.unibh.seguros.negocio.ServicoDesconto;

public class ConversorDesconto implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		try {
			ServicoDesconto ss = (ServicoDesconto) new InitialContext().lookup("java:global/seguros/ServicoDesconto");
			if (value != null)
				return ss.find(new Long(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		if (value != null && value instanceof Desconto) {
			Desconto s = (Desconto) value;
			if (s.getId() != null)
				return s.getId().toString();
		}
		return null;
	}
}