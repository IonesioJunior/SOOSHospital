package Orgaos;

import java.io.Serializable;

public class Orgao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private String tipoSanguineo;
	
	/**
	 * Construtor da classe, que associa os valores recebidos aos atributos da mesma.
	 * @param nome nome do orgao
	 * @param tipoSanguineo tipo Sanguineo do orgao
	 */
	public Orgao(String nome, String tipoSanguineo) {
		this.nome= nome;
		this.tipoSanguineo = tipoSanguineo;
	}
	
	/**
	 * Retorna o nome do orgao
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Retorna o tipo Sanguineo do orgao
	 * @return tipoSanguineo
	 */
	public String getTipoSanguineo() {
		return tipoSanguineo;
	}
	
}
