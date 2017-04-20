package Funcionarios;

import java.util.HashSet;


import Exceptions.FuncionarioException;

public class Medico extends Funcionario {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de medico
	 * @param nome associa ao nome
	 * @param matricula associa a matricula
	 * @param senha associa a senha
	 * @param data associa a data
	 * @param cargo associa ao cargo
	 * @throws FuncionarioException
	 */
	public Medico(String nome, String matricula, String senha, String data,String cargo) throws FuncionarioException {
		super(nome,matricula,senha,data,cargo);
		definirPermissoes();
	}
	
	/**
	 * Define permissoes relacionadas ao medico
	 */
	@Override
	public void definirPermissoes() {
		HashSet<Permissoes> permissoesConcedidas = this.getPermissoes();
		permissoesConcedidas.add(Permissoes.CADASTRAORGAO);
		permissoesConcedidas.add(Permissoes.REALIZAPROCEDIMENTO);
		this.setPermissoes(permissoesConcedidas);	
	}

}
