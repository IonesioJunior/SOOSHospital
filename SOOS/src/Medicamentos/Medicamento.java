package Medicamentos;

import java.io.Serializable;

public class Medicamento implements Comparable<Medicamento>,Serializable { 
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private double preco;
	private int quantidade;
	private String listaCategorias;
	/**
	 * Construtor de medicamento
	 * @param nome associa ao nome
	 * @param preco associa ao preco
	 * @param quantidade associa a quantidade
	 * @param categorias associa as categorias
	 */
	public Medicamento(String nome, double preco, int quantidade, String categorias) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		associaCategoria(categorias);
	}
	/**
	 * Verifica se o medicamento contem a categoria
	 * @param categoria a ser pesquisada
	 * @return
	 */
	public boolean contemCategoria(String categoria) {
		if(listaCategorias.contains(categoria)) {
			return true;
		}
		return false;
	}
	/**
	 * Adiciona categorias
	 * @param tipos associa aos tipos de categorias
	 */
	private void associaCategoria(String tipos) {
		String[] categoriasSeparadas = tipos.split(",");
		for(TiposMedicamento tipo: TiposMedicamento.values()) {
			for(String categoria: categoriasSeparadas) {
				if(tipo.name().equalsIgnoreCase(categoria)) {
					listaCategorias += tipo.toString().toLowerCase() + ",";
				}
			}
		}
	}
	
	/**
	 * Adiciona quantidade de medicamento
	 * @param quantidade associa a quantidade
	 */
	public void adicionaQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}
	
	public String getTipo() {
		return "Generico";
	}
	
	public String getNome() {
		return nome;
	}
	
	public double getPrecoBase() {
		return preco;
	}
	
	public double getPreco() {
		return preco * 0.6; 
		}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getCategorias() {
		return listaCategorias.substring(4, (listaCategorias.length()-1));
	}
	
	/**
	 * Retorna uma string com as informacoes do medicamento
	 */
	@Override
	public String toString() {
		String texto = String.format("Medicamento Generico: %s - Preco: R$ %.2f - Disponivel: %d - Categorias: %s", getNome(), getPreco(), getQuantidade(), getCategorias());
		return texto;
	}

	/**
	 * Compara o medicamento pelo preco
	 */
	@Override
	public int compareTo(Medicamento medicamento) {
		if(this.preco < medicamento.getPreco()) {
			return 1;
		}
		if(this.preco > medicamento.getPreco()) {
			return -1;
		}
		return 0;
	}
	
	
}
