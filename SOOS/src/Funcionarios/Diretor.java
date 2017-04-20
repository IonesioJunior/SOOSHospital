package Funcionarios;

import java.util.HashSet;


import Exceptions.FuncionarioException;

public class Diretor extends Funcionario {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor de diretor
	 * @param nome associa ao nome
	 * @param matricula associa a matricula
	 * @param senha associa a senha
	 * @param data associa a data
	 * @param cargo associa ao cargo
	 * @throws FuncionarioException
	 */
	public Diretor(String nome, String matricula, String senha, String data,String cargo) throws FuncionarioException {
		super(nome, matricula, senha, data,cargo);
		definirPermissoes();
	}
	/**
	 * Na especificacao do problema o diretor tem todas as permissoes possiveis
	 * */
	public void definirPermissoes(){
		HashSet<Permissoes> permissoesConcedidas = this.getPermissoes();
		permissoesConcedidas.add(Permissoes.ATUALIZADADOS);
		permissoesConcedidas.add(Permissoes.CADASTRAFUNCIONARIO);
		permissoesConcedidas.add(Permissoes.CADASTRAMEDICAMENTO);
		permissoesConcedidas.add(Permissoes.CADASTRAPACIENTE);
		permissoesConcedidas.add(Permissoes.EXCLUI);
		this.setPermissoes(permissoesConcedidas);
	}
}
