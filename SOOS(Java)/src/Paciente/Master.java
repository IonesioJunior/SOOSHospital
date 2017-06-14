package Paciente;

import java.io.Serializable;

public class Master implements CartaoFidelidade,Serializable{

	private static final long serialVersionUID = 1L;
	int totalPontos;
	
	/**
	 * Construtor da classe
	 * @param totalPontos
	 */
	public Master(int totalPontos)	{	
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
	 * Aplica desconto de 15% ao preco do procedimento
	 */
	@Override
	public double aplicarDesconto(double preco) {
		preco = preco - (preco * 0.15);
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
