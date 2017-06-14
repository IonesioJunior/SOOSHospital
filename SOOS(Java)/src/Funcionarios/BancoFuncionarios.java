package Funcionarios;

import java.io.Serializable;
import java.util.HashMap;


import Factory.FuncionarioFactory;
import Exceptions.ControllerException;
import Exceptions.DataInvalidaException;
import Exceptions.FuncionarioException;

public class BancoFuncionarios implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private HashMap<String,Funcionario> listaFuncionarios;
	private FuncionarioFactory factoryFuncionarios;
	
	/**
	 * Construtor da classe, criando a colecao e instanciando a factory
	 */
	public BancoFuncionarios() {
		listaFuncionarios = new HashMap<String,Funcionario>();
		factoryFuncionarios = new FuncionarioFactory();
	}
	
	/**
	 * Cadastra Funcionarios
	 * @param nome associa ao nome
	 * @param cargo associa ao cargo
	 * @param nascimento associa a data
	 * @return a matricula do funcionario
	 * @throws DataInvalidaException
	 * @throws FuncionarioException
	 */
	public String cadastrarFuncionarios(String nome,String cargo,String nascimento) throws DataInvalidaException, FuncionarioException{
		Funcionario funcionario = factoryFuncionarios.criaUsuario(nome, cargo, nascimento);
		verificaFuncionario(funcionario);
		listaFuncionarios.put(funcionario.getMatricula(), funcionario);
		return funcionario.getMatricula();
	}
	
	/**
	 * Recupera informacoes do usuario
	 * @param matricula associa a matricula do funcionario
	 * @param atributo associa ao atributo
	 * @return a informacao referente ao que foi pedido
	 * @throws ControllerException
	 */
	public String getInfoFuncionario(String matricula, String atributo) throws ControllerException{
		Funcionario user = verificarUsuario(matricula);
		atributo = atributo.toLowerCase();
		switch (atributo){
			case "nome":
				return user.getNome();
			case "cargo":
				return user.getCargo();
			case "data":
				return user.getData();
			case "senha":
				throw new ControllerException("A senha do funcionario eh protegida.");
			default:
				return "Atributo Invalido.";
		}
	 }
	/**
	 * Cria primeira conta
	 * @param nome associa ao nome
	 * @param data associa a data de nascimento
	 * @return matricula do funcionario
	 * @throws DataInvalidaException
	 * @throws FuncionarioException
	 */
	public String criarPrimeiraConta(String nome,String data) throws DataInvalidaException, FuncionarioException{
		String cargo = "Diretor Geral";
		Funcionario funcionario = factoryFuncionarios.criaUsuario(nome, cargo, data);
		listaFuncionarios.put(funcionario.getMatricula(), funcionario);
		return funcionario.getMatricula();
	}
	
	/**
	 * Atualiza informacoes de um funcionario
	 * @param matricula associa a matricula na busca ao usuario
	 * @param atributo associa ao atributo que sera atualizado
	 * @param valor associa um novo valor a ser atribuido
	 * @throws ControllerException
	 * */
	public void atualizaInfoFuncionario(String matricula,String atributo , String valor) throws ControllerException{
		Funcionario user = verificarUsuario(matricula);
		atributo = atributo.toLowerCase();
		switch (atributo){
			case "nome":
				user.setNome(valor);
				break;
			case "data":
				user.setData(valor);
				break;
		}
	}
	
	/**
	 * Verifica se usuario e senha sao corretos
	 * @param matricula associada a matricula do usuario que pretende se  logar
	 * @param senha associada a senha do usuario que pretende se logar
	 * @throws ControllerException
	 * */
	public void validarUsuario(String matricula,String senha) throws ControllerException{
		Funcionario user = getFuncionario(matricula);
		if (user == null){
			throw new ControllerException("Funcionario nao cadastrado.");
		}
		else if (!(user.getSenha().equals(senha))){
			throw new ControllerException("Senha incorreta.");
		}
	}
	
	/**
	 * Recupera o Funcionario
	 * @param matricula associa a matricula do Funcionario
	 * @return
	 */
	public Funcionario getFuncionario(String matricula){
		return listaFuncionarios.get(matricula);
	}
	
	/**
	 * Exclui funcionario
	 * @param matricula associa a matricula
	 * @throws ControllerException
	 */
	public void excluiFuncionario(String matricula) throws ControllerException{
		verificarUsuario(matricula);
		listaFuncionarios.remove(matricula);
	}
	
	/**
	 * Verifica se funcionario e nulo
	 * @param funcionario associa ao funcionario
	 * @throws FuncionarioException
	 */
	private void verificaFuncionario(Funcionario funcionario) throws FuncionarioException{
		if (funcionario == null){
			throw new FuncionarioException("Funcionario nulo");
		}
	}
	
	/**
	 * Verifica se o Objeto funcionario e um objeto valido
	 * @throws ControllerException
	 * */
	private Funcionario verificarUsuario(String matricula) throws ControllerException{
		if (getFuncionario(matricula)  == null){
			throw new ControllerException("Funcionario nao cadastrado.");
		}
		else{
			return getFuncionario(matricula);
		}
	}
	
	
}
