package Factory;
import java.io.Serializable;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import Exceptions.DataInvalidaException;
import Exceptions.FuncionarioException;
import Funcionarios.Diretor;
import Funcionarios.Funcionario;
import Funcionarios.Medico;
import Funcionarios.Tecnico;

public class FuncionarioFactory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int numeroDeContas;
	private final String prefixoDiretor = "1";
	private final String prefixoMedico = "2";
	private final String prefixoTecnico = "3";
	
	/**
	 * Construtor da classe, que comeca a contabilizar o numero da conta
	 */
	public FuncionarioFactory(){
		numeroDeContas = 1;
	}
	
	/**
	 * Cria Usuario
	 * @param nome associa ao nome
	 * @param cargo associa ao cargo
	 * @param data associa a data de nascimento
	 * @return retorna o funcionario criado
	 * @throws DataInvalidaException
	 * @throws FuncionarioException
	 */
	public Funcionario criaUsuario(String nome, String cargo, String data) throws DataInvalidaException, FuncionarioException{
		
		String matricula = gerarMatricula(cargo);
		LocalDate dataFormatada = dataFormatChanges(data);
		DateTimeFormatter formatadorAno =  DateTimeFormatter.ofPattern("yyyy");
		String ano = dataFormatada.format(formatadorAno);
		
		String senha = gerarSenha(matricula,ano);
		

		if(cargo.toUpperCase().equalsIgnoreCase("DIRETOR GERAL")){
			Funcionario usuario = new Diretor(nome, matricula, senha, data,cargo);
			numeroDeContas++;
			return usuario;
		}
		
		if(cargo.toUpperCase().equalsIgnoreCase("MEDICO")){
			Funcionario usuario = new Medico(nome, matricula, senha, data,cargo);
			numeroDeContas++;
			return usuario;
		}
		
		if(cargo.toUpperCase().equalsIgnoreCase("TECNICO ADMINISTRATIVO")){
			Funcionario usuario = new Tecnico(nome, matricula, senha, data,cargo);
			numeroDeContas++;
			return usuario;
		}
		
		
		return null;
	}
	/**
	 * Gera a matricula do funcionario
	 * @param cargo associa ao cargo
	 * @return matricula do funcionario
	 */
	private String gerarMatricula(String cargo){
		cargo = cargo.toLowerCase();
		String matricula;
		switch(cargo){
			case "diretor geral":
			matricula = "1" + LocalDate.now().getYear() + String.format("%03d", numeroDeContas);
			return matricula;
		case  "medico":
			matricula = "2" + LocalDate.now().getYear() + String.format("%03d", numeroDeContas);
			return matricula;
		case "tecnico administrativo":
			matricula = "3" + LocalDate.now().getYear() + String.format("%03d", numeroDeContas);
			return matricula;
		}
	return "Cargo Invalido.";
	}
	
	/**
	 * Gera a senha
	 * @param matricula associa a matricula
	 * @param ano associa ao ano atual
	 * @return senha gerada
	 */
	private String gerarSenha(String matricula, String ano){
		String senha = ano + matricula.subSequence(0,4);
		return senha;
	}
	
	/**
	 * Formata a data
	 * @param data associa a data de nascimento
	 * @return data formatada
	 * @throws DataInvalidaException
	 */
	private LocalDate dataFormatChanges(String data) throws DataInvalidaException { 

		String[] newDate = data.split("/");
	
		LocalDate hoje = LocalDate.now();
		int anoAtual = hoje.getYear();

		int dia = Integer.parseInt(newDate[0]);
		int mes = Integer.parseInt(newDate[1]);
		int ano = Integer.parseInt(newDate[2]);
	
		if( (dia < 1 || dia > 31)  || (mes < 1 || mes > 12) || (ano > anoAtual) ){
			throw new DataInvalidaException("Data invalida.");
		
		}
		LocalDate date;
		date = LocalDate.of(ano, mes, dia);
	
		return date;

	}
}
