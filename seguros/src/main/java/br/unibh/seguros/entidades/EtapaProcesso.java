package br.unibh.seguros.entidades;

public enum EtapaProcesso {

	
	CRIADA(1), APROVADA(2), REPROVADA(3), EMITIDA(4),
	CANCELADA(5);
	public int valor;
	EtapaProcesso(int valor) {
	this.valor = valor;
	}
	public int getValor() {
	return valor;
	}
	
}
