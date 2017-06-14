package Procedimentos;

import java.io.Serializable;
import java.time.LocalDate;

import Exceptions.ProcedimentoException;
import Paciente.Paciente;
/**
 * Classe responsavel pelas caracteristicas do procedimento de cirurgia bariatrica
 * */
public class CirurgiaBariatrica implements Iprocedimentos,Serializable{
	
	private static final long serialVersionUID = 1L;
	private final double PRECO = 7600;
	private String medico;
	private LocalDate data;
	
	/**
	 * Construtor da classe
	 * @param nome nome do medico
	 * @param data data do procedimento
	 */
	public CirurgiaBariatrica(String nome, LocalDate data){
		this.medico = nome;
		this.data = data;
	}
	/**
	 * Realiza uma cirurgia bariatrica, armazena no prontuario e retorna o valor do procedimento
	 * @param paciente que sera submetido ao procedimento
	 * @return valor referente ao procedimento
	 * @throws ProcedimentoException 
	 * */
	@Override
	public double realizarProcedimento(Paciente paciente) throws ProcedimentoException {
		double pesoAposProcedimento  = paciente.getPeso() * 0.85;
		paciente.setPeso(pesoAposProcedimento);
		return PRECO;
	}
	
	/**
	 * Retorna uma string com as informacoes do procedimento
	 */
	@Override
	public String toString() {
		String texto = "-->Cirurgia Bariatrica: " + System.getProperty("line.separator");
		texto +="......." +"Data: "  + data.getYear() + "-" + data.getMonthValue() + "-" + data.getDayOfMonth() + " Medico: " + medico + System.getProperty("line.separator");
		return texto;
	}
}
