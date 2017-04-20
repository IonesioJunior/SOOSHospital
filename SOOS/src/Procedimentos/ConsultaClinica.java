package Procedimentos;

import java.io.Serializable;
import java.time.LocalDate;

import Exceptions.ProcedimentoException;
import Paciente.Paciente;
/**
 * Classe responsavel pelas caracteristicas do procedimento de consulta clinica
 * */
public class ConsultaClinica implements Iprocedimentos, Serializable{
	
	private static final long serialVersionUID = 1L;
	private final double PRECO = 350;
	private String medico;
	private LocalDate data;
	
	/**
	 * Construtor da classe
	 * @param nome nome do medico
	 * @param data data do procedimento
	 */
	public ConsultaClinica(String nome,LocalDate data){
		this.medico = nome;
		this.data = data;
	}
	
	/**
	 * Realiza uma consulta, armazena no prontuario e retorna o valor do procedimento
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
		String texto = "-->Consulta Clinica: " + System.getProperty("line.separator");
		texto +="......." +"Data: "  + data.getYear() + "-" + data.getMonthValue() + "-" + data.getDayOfMonth() + " Medico: " + medico + System.getProperty("line.separator");
		return texto;
	}

}
