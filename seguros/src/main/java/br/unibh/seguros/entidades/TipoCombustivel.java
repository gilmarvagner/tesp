package br.unibh.seguros.entidades;

public enum TipoCombustivel {
	GASOLINA(1), DIESEL(2), ETANOL(3), FLEX(4);
	public int valor;

	TipoCombustivel(int valor) {
		this.valor = valor;
	}

public int getValor() {

	return valor;
	}
	}