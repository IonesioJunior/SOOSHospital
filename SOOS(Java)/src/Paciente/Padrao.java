package Paciente;

import java.io.Serializable;

public class Padrao implements CartaoFidelidade, Serializable{
	
	private static final long serialVersionUID = 1L;
		int totalPontos;
	
	/**
	 * Construtor da classe
	 * @param totalPontos
	 */
	public Padrao(int totalPontos) {	
		this.totalPontos = totalPontos;
	}
	
	/**
	 * Adiciona pontos ao cartao
	 */
	@Override
	public void adicionarPontos(int pontos) {
		totalPontos += pontos;
	}
	
	/**
	 * Aplica desconto de 0% ao procedimento
	 */
	@Override
	public double aplicarDesconto(double preco) {
		return preco;	
	}
	
	/**
	 * Retorna os pontos do cartao
	 */
	@Override
	public int getPontosCartao() {
		return totalPontos;
	}
	
}
