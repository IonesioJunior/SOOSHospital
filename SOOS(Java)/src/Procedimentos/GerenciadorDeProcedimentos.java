package Procedimentos;


import java.io.Serializable;
import java.time.LocalDate;

import Exceptions.ProcedimentoException;
import Paciente.Paciente;
/**
 * Classe responsavel por gerenciar os procedimentos
 **/
public class GerenciadorDeProcedimentos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Iprocedimentos tipoDeProcedimento;
	
	/**
	 * Realiza o procedimento requisitado em determinado paciente
	 * @param procedimento Tipo de procedimento a ser realizado
	 * @param paciente Paciente que sera submentido ao procedimento
	 * @param nome do medico que realizou o procedimento
	 * @return custoDoProcedimento valor cobrado pelo procedimento requisitado
	 * @throws ProcedimentoException 
	 * */
	public double realizarProcedimento(String procedimento , Paciente paciente,String nome) throws ProcedimentoException{
		definirProcedimento(procedimento,nome);
		paciente.registrarProcedimento(tipoDeProcedimento);
		double custoDoProcedimento = tipoDeProcedimento.realizarProcedimento(paciente);
		return custoDoProcedimento;
	}
	/**
	 * Realiza o procedimento requisitado em determinado paciente
	 * @param procedimento Tipo de procedimento a ser realizado
	 * @param paciente Paciente que sera submentido ao procedimento
	 * @param nome do medico que realizou o procedimento
	 * @param orgao transplantado no procedimento
	 * @return custoDoProcedimento valor cobrado pelo procedimento requisitado
	 * @throws ProcedimentoException 
	 * */
	public double realizarProcedimento(String procedimento , Paciente paciente,String nome,String orgao) throws ProcedimentoException{
		definirProcedimento(procedimento,nome,orgao);
		paciente.registrarProcedimento(tipoDeProcedimento);
		double custoDoProcedimento = tipoDeProcedimento.realizarProcedimento(paciente);
		return custoDoProcedimento;
	}
	
	/**
	 * Define qual o procedimento sera realizado
	 * @param procedimento String indicando o procedimento
	 * @param nome Do Medico que realiza o procedimento
	 * */
	private void definirProcedimento(String procedimento,String nome){
		procedimento  = procedimento.toLowerCase();
		switch (procedimento){
			case "consulta clinica":
				tipoDeProcedimento = new ConsultaClinica(nome,LocalDate.now());
				break;
			case "cirurgia bariatrica":
				tipoDeProcedimento = new CirurgiaBariatrica(nome,LocalDate.now());
				break;
			case "redesignacao sexual":
				tipoDeProcedimento = new RedesignacaoSexual(nome,LocalDate.now());
				break;
		}
	}
	
	/**
	 * Define qual o procedimento sera realizado
	 * @param procedimento String indicando o procedimento
	 * @param nomeDoMedico que realiza o procedimento
	 * @param orgao a ser transplantado
	 * */
	private void definirProcedimento(String procedimento, String nome,String orgao){
		procedimento  = procedimento.toLowerCase();
		switch(procedimento){
			case "transplante de orgaos":
				tipoDeProcedimento = new TransplanteDeOrgaos(nome,LocalDate.now(),orgao);
				break;
		}
	}
	
}	
