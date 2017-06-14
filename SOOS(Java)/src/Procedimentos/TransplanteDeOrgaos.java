package Procedimentos;

import java.io.Serializable;
import java.time.LocalDate;

import Exceptions.ProcedimentoException;
import Paciente.Paciente;
/**
 * Classe responsavel pelas caracteristicas do procedimento de transplante de orgaos
 * */
public class TransplanteDeOrgaos implements Iprocedimentos,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final double PRECO = 12500;
	private String medico;
	private LocalDate data;
	private String orgao;
	
	/**
	 * Construtor da classe
	 * @param nome nome do medico
	 * @param data data do procedimento
	 */
	public TransplanteDeOrgaos(String nome,LocalDate data,String orgao){
		this.medico = nome;
		this.data = data;
		this.orgao = orgao;
	}
	/**
	 * Realiza um procedimento de transplante de orgaos, armazena no prontuario e retorna o valor do procedimento
	 * @param paciente que sera submetido ao procedimento
	 * @return valor referente ao procedimento
	 * @throws ProcedimentoException 
	 * */
	@Override
	public double realizarProcedimento(Paciente paciente) throws ProcedimentoException {
		return PRECO;
	}
	
	/**
	 * Retorna uma string com as informacoes do procedimento
	 */
	@Override
	public String toString() {
		String texto = "-->Transplante de Orgaos: " + System.getProperty("line.separator");
		texto +="......." +"Data: "  + data.getYear() + "-" + data.getMonthValue() + "-" + data.getDayOfMonth() + " Medico: " + medico + System.getProperty("line.separator");
		texto += "Orgao Transplantado: " + orgao  + System.getProperty("line.separator");
		return texto;
	}
}
