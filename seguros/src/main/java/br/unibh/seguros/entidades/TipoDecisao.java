package br.unibh.seguros.entidades;

public enum TipoDecisao {
	
	
	MANUAL(1), AUTOMATICA(2);
	public int valor;
	TipoDecisao(int valor) {
	this.valor = valor;
	}
	public int getValor() {
	return valor;
	}
	}