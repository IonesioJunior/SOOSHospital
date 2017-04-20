package Factory;

import java.io.Serializable;

import Medicamentos.Medicamento;
import Medicamentos.MedicamentoReferencia;

public class MedicamentoFactory implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe
	 */
	public MedicamentoFactory() {
	}
	
	/**
	 * Cria Medicamentos
	 * @param nome associa ao nome
	 * @param tipo associa ao tipo
	 * @param preco associa ao preco
	 * @param quantidade associa a quantidade
	 * @param categorias associa as categorias
	 * @return medicamento criado
	 */
	public Medicamento criaMedicamento(String nome, String tipo, double preco, int quantidade, String categorias) {
		if(tipo.trim().equalsIgnoreCase("generico")) {
			return criaMedicamentoGenerico(nome, preco, quantidade, categorias);
		}
		if(tipo.trim().equalsIgnoreCase("Referencia")) {
			return criaMedicamentoReferencia(nome, preco, quantidade, categorias);
		}
		return null;
	}
	
	/**
	 * Cria medicamento do tipo generico
	 * @param nome associa ao nome
	 * @param preco associa ao preco
	 * @param quantidade associa ao preco
	 * @param categorias associa as categorias
	 * @return medicamento gerado
	 */
	private Medicamento criaMedicamentoGenerico(String nome, double preco, int quantidade, String categorias) {
		Medicamento novoGenerico = new Medicamento(nome, preco, quantidade, categorias);
		return novoGenerico;
	}
	
	/**
	 * Cria medicamento do tipo referencia
	 * @param nome associa ao nome
	 * @param preco associa ao preco
	 * @param quantidade associa ao preco
	 * @param categorias associa as categorias
	 * @return medicamento gerado
	 */
	private Medicamento criaMedicamentoReferencia(String nome, double preco, int quantidade, String categorias) {
		Medicamento novoReferencia = new MedicamentoReferencia(nome, preco, quantidade, categorias);
		return novoReferencia;
	}
}
