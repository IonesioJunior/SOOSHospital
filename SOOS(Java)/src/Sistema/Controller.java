package Sistema;



import Funcionarios.BancoFuncionarios;

import Funcionarios.Funcionario;
import Funcionarios.Permissoes;
import Medicamentos.Farmacia;
import Orgaos.BancoOrgaos;
import Paciente.BancoPacientes;


import java.io.Serializable;

import Exceptions.ControllerException;
import Exceptions.DataInvalidaException;
import Exceptions.FuncionarioException;
import Exceptions.MedicamentoException;
import Exceptions.OrgaoException;
import Exceptions.PacienteException;
import Exceptions.ProcedimentoException;



public class Controller implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean sistemaLiberado;
	private Funcionario usuarioLogado;
	private BancoPacientes bancoPacientes;
	private BancoFuncionarios bancoFuncionarios;
	private Farmacia farmacia;
	private Util util;
	private BancoOrgaos bancoOrgaos;
	
	/**
	 * Construtor da classe, atribuindo os valores necessarios aos atributos e instanciando os bancos
	 */
	public Controller(){
		sistemaLiberado = false;
		util = new Util();
		bancoPacientes = new BancoPacientes();
		bancoFuncionarios = new BancoFuncionarios();
		farmacia = new Farmacia();
		bancoOrgaos = new BancoOrgaos();
	}
	
	// Casos 1,2,3 e 4
	
	/**
	 * Libera Sistema
	 * @param chave associa a chave
	 * @param nome associa ao nome
	 * @param data associa a data de Nascimento
	 * @return matricula da primeira conta
	 * @throws DataInvalidaException
	 * @throws FuncionarioException
	 * @throws ControllerException
	 */
	public String liberaSistema(String chave,String nome , String data) throws DataInvalidaException, FuncionarioException, ControllerException{
		verificaSistema();
		util.verificaChave(chave);
		String matricula = bancoFuncionarios.criarPrimeiraConta(nome, data);
		sistemaLiberado = true;
		return matricula;
	}
	
	/**
	 * Realiza Login do Funcionario
	 * @param matricula associa a matricula dele
	 * @param senha associa a senha dele
	 * @throws ControllerException
	 */
	public void realizarLogin(String matricula,String senha) throws ControllerException{
		bancoFuncionarios.validarUsuario(matricula,senha);
		verificaLogin();
		usuarioLogado = bancoFuncionarios.getFuncionario(matricula);
	}
	
	/**
	 * Recupera informacoes do usuario
	 * @param matricula associa a matricula do funcionario
	 * @param atributo associa ao atributo
	 * @return a informacao referente ao que foi pedido
	 * @throws ControllerException
	 */
	public String getInfoFuncionario(String matricula, String atributo) throws ControllerException{
		util.verificaMatricula(matricula);
		return bancoFuncionarios.getInfoFuncionario(matricula, atributo);
	}
	
	/**
	 * Recupera informacoes do Medicamentos
	 * @param atributo associa ao atributo
	 * @param nome associa ao nome 
	 * @return a informacao pedida
	 * @throws ControllerException
	 */
	public String getInfoMedicamento(String atributo, String nome) throws ControllerException {
		return farmacia.getInfoMedicamento(atributo, nome);
	}
	
	/**
	 * Consulta ao tipo de Medicamento
	 * @param categoria associa a categoria
	 * @return 
	 * @throws MedicamentoException
	 */
	public String consultaMedCategoria(String categoria) throws MedicamentoException {
		return farmacia.buscaMedicamentoCategoria(categoria);
	}
	
	/**
	 * Consulta Medicamento pelo nome
	 * @param nome associa ao nome
	 * @return	informacoes do medicamento caso exista
	 * @throws ControllerException
	 */
	public String consultaMedNome(String nome) throws ControllerException {
		return farmacia.consultaMedNome(nome);
	}
	
	/**
	 * Cadastra Funcionarios
	 * @param nome associa ao nome
	 * @param cargo associa ao cargo
	 * @param nascimento associa a data de nascimento
	 * @return matricula gerada pelo cadastro
	 * @throws DataInvalidaException
	 * @throws FuncionarioException
	 * @throws ControllerException
	 */
	public String cadastrarFuncionarios(String nome,String cargo ,String nascimento) throws DataInvalidaException, FuncionarioException, ControllerException{
		if(usuarioLogado.verificaPermissao(Permissoes.CADASTRAFUNCIONARIO)){
			verificaCargo(cargo);
			return bancoFuncionarios.cadastrarFuncionarios(nome, cargo, nascimento);
		}else{
			throw new ControllerException("O funcionario " + usuarioLogado.getNome() + " nao tem permissao para cadastrar funcionarios.");
		}
	}
	/**
	 * Cadastra Medicamentos
	 * @param nome associa ao nome
	 * @param tipo associa ao tipo
	 * @param preco associa do preco
	 * @param quantidade associa a quantidade
	 * @param categorias associa a categorias
	 * @return	nome do medicamento
	 * @throws ControllerException
	 * @throws MedicamentoException
	 */
	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias) throws ControllerException, MedicamentoException {
		if(usuarioLogado.verificaPermissao(Permissoes.CADASTRAMEDICAMENTO)) {
			util.nomeMedicamento(nome);
			util.precoMedicamento(preco);
			util.quantidadeMedicamento(quantidade);
			return farmacia.cadastrarMedicamento(nome, tipo, preco, quantidade, categorias);
		} else {
			throw new ControllerException("O funcionario " + usuarioLogado.getNome() + " nao tem permissao para cadastrar medicamentos.");
		}
	}
	/**
	 * Verifica se ja Existe alguem logado
	 * @throws ControllerException
	 * */
	public void verificaLogin() throws ControllerException{
		if (usuarioLogado != null){
			throw new ControllerException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + "." );
		}
	}
	/**
	 * Realiza o logout do sistema
	 * @throws ControllerException
	 * */
	public void logout() throws ControllerException{
		if (usuarioLogado == null){
			throw new ControllerException("Nao ha um funcionario logado.");
		}
		usuarioLogado = null;
	}
	/**
	 * Exclui um funcionario caso exista
	 * @param matricula associa a matricula do usuario a ser excluido
	 * @param senha associa a senha do diretor
	 * @throws ControllerException
	 * */
	public void excluirFuncionario(String matricula,String senha) throws ControllerException{
		util.verificaMatricula(matricula);
		if(usuarioLogado.verificaPermissao(Permissoes.EXCLUI)){
			usuarioLogado.confirmarSenha(senha);
			bancoFuncionarios.excluiFuncionario(matricula);
		}else{
				throw new ControllerException("O funcionario "+ usuarioLogado.getNome() + " nao tem permissao para excluir funcionarios.");
			}
	}
	/**
	 * Atualiza informacoes de um funcionario
	 * @param matricula associa a matricula na busca ao usuario
	 * @param atributo associa ao atributo que sera atualizado
	 * @param valor associa um novo valor a ser atribuido
	 * @throws ControllerException
	 * */
	public void atualizaInfoFuncionario(String matricula,String atributo , String valor) throws ControllerException{
		atributo = atributo.toLowerCase();
		switch (atributo){
			case "nome":
				util.verificaNome(valor);
				break;
			case "data":
				util.verificaData(valor);
				break;
			case "":
				throw new ControllerException("Atributo invalido!");
		}
		bancoFuncionarios.atualizaInfoFuncionario(matricula, atributo, valor);
	}
	/**
	 * Atualiza informacoes de um Medicamento
	 * @param nome associa ao nome do medicamento a ser atualizado
	 * @param atributo associa ao atributo a ser atualizado
	 * @param novoValor define um novo valor para esse atributo
	 * @throws ControllerException
	 * */
	public void atualizaMedicamento(String nome, String atributo, String novoValor) throws ControllerException {
		atributo = atributo.toLowerCase();
		switch (atributo) {
			case "nome":
				throw new ControllerException("Nome do medicamento nao pode ser alterado.");
			case "tipo":
				throw new ControllerException("Tipo do medicamento nao pode ser alterado.");
			case "preco":
				double valor = Double.valueOf(novoValor);
				util.verificaPreco(valor);
				farmacia.atualizaPrecoMedicamento(nome, valor);
				break;
			case "quantidade":
				int quantidade = Integer.valueOf(novoValor);
				util.verificaQuantidade(quantidade);
				farmacia.atualizaQuantMedicamento(nome, quantidade);
				break;
			default:
				throw new ControllerException("Atributo invalido.");
		}
	}
	
	/**
	 * Atualiza a Senha de um usuario
	 * @param senhaAntiga associa a antiga senha do usuario para confirmacao
	 * @param senhaNova associa a nova senha que sera definida
	 * @throws ControllerException
	 * */
	public void atualizaSenha(String senhaAntiga , String senhaNova) throws ControllerException{
		usuarioLogado.confirmarSenha(senhaAntiga);
		util.verificaSenha(senhaNova);
		usuarioLogado.setSenha(senhaNova);
	}
	
	/**
	 * Atualiza um atributo do usuario logado
	 * @param atributo associa ao atributo que sera atualizado
	 * @param valor associa ao valor que sera definido para o atributo
	 * @throws ControllerException
	 * */
	public void atualizaFuncionario(String atributo , String valor) throws ControllerException{
		atributo = atributo.toLowerCase();
		switch(atributo){
			case "nome":
				util.verificaNome(valor);
				usuarioLogado.setNome(valor);
				break;
			case "data":
				util.verificaData(valor);
				usuarioLogado.setData(valor);
				break;
		}
	}
	/**
	 * Cadastra um novo Paciente
	 * @param nome sera usado como nome do novo paciente
	 * @param nascimento sera usado como data de nascimento do novo paciente
	 * @param peso sera usado como peso do novo paciente
	 * @param sexo sera usado como sexo do novo paciente
	 * @param genero sera usado como genero do novo paciente
	 * @param tipoSanguineo sera usado como tipo sanguineo do novo paciente
	 * @return o ID do novo paciente gerado
	 * @throws ControllerException
	 * */
	public String cadastraPaciente(String nome, String nascimento, double peso, String sexo, String genero, String tipoSanguineo) throws ControllerException{
		if(usuarioLogado.verificaPermissao(Permissoes.CADASTRAPACIENTE)){
			util.Nomepaciente(nome);
			util.data(nascimento);
			util.peso(peso);
			util.tipoSanguineo(tipoSanguineo);
			return bancoPacientes.cadastrarPaciente(nome, nascimento, peso, sexo, genero, tipoSanguineo);
		}else{
			throw new ControllerException("O funcionario " + usuarioLogado.getNome() +" nao tem permissao para cadastrar pacientes.");
		}
	}
	/**
	 *  Pega informacoes de um paciente
	 *  @param id sera usado para buscar o paciente em questao
	 *  @param atributo associado ao atributo que sera retornado
	 *  @return String do atributo
	 *  @throws ControllerException
	 *  */
	public String getInfoPaciente(String id,String atributo) throws ControllerException{
		return bancoPacientes.getInfoPaciente(id, atributo);
	}
	
	/**
	 *  Pega a ID do paciente com determinado prontuario
	 *  @param posicao associa a posicao que esse prontuario esta armazenado
	 *  @throws ControllerException
	 *  */
	public String getProntuario(String posicao) throws ControllerException{
		util.verificaID(posicao);
		int index = Integer.parseInt(posicao);
		return bancoPacientes.getProntuario(index);
	}
	
	/**
	 * Retorna o estoque de medicamentos
	 * @param ordenacao define o tipo de ordenacao que sera retornada
	 * @throws ControlerException
	 * */
	public String getEstoqueFarmacia(String ordenacao) throws ControllerException{
		util.verificaOrdenacao(ordenacao);
		return farmacia.getListaOrdenada(ordenacao);
	}
	
	// Caso 5
	
	
	/**
	 * Cadastra um orgao no banco de Orgaos
	 * @param nome nome do orgao a ser cadastrado
	 * @param tipo tipoSanguineo do orgao
	 * @throws ControllerException caso algum dado esteja invalido
	 */
	public void cadastraOrgao(String nome, String tipo) throws ControllerException {
		if(usuarioLogado.verificaPermissao(Permissoes.CADASTRAORGAO)) {
			util.nomeOrgao(nome);
			util.tipoSanguineo(tipo);
			bancoOrgaos.adicionaOrgao(nome, tipo);
		} else {
			throw new ControllerException("O funcionario " + usuarioLogado.getNome() +" nao tem permissao para cadastrar orgaos.");
		}	
	}
	
	/**
	 * Busca um orgao no banco de Orgaos pelo tipoSanguineo
	 * @param tipoSanguineo tipoSanguineo a ser pesquisado
	 * @return lista com os orgaos que possuem o tipoSanguineo
	 * @throws ControllerException caso algum dado esteja invalido
	 */
	public String buscaOrgaoPorSangue(String tipoSanguineo) throws ControllerException {
		util.tipoSanguineo(tipoSanguineo);
		return bancoOrgaos.buscaOrgaoTipoSanguineo(tipoSanguineo);
	}
	
	/**
	 * Busca um orgao no banco de Orgaos pelo nome
	 * @param nome nome do orgao a ser pesquisado
	 * @return lista com os orgaos que possuem o tipoSanguineo
	 * @throws ControllerException caso algum dado esteja invalido
	 */
	public String buscaOrgaoPorNome(String nome) throws ControllerException {
		util.nomeOrgao(nome);
		return bancoOrgaos.buscaOrgaoNome(nome);
	}
	
	/**
	 * Busca um orgao que contem o nome e o tipo sanguineo recebidos
	 * @param nome nome do orgao a ser pesquisado
	 * @param tipoSanguineo tipo sanguineo do orgao a ser pesquisado
	 * @return boolean se existe
	 * @throws ControllerException caso algum dado esteja invalido
	 */
	public boolean buscaOrgao(String nome, String tipoSanguineo) throws ControllerException {
		util.tipoSanguineo(tipoSanguineo);
		return bancoOrgaos.buscaOrgao(nome, tipoSanguineo);
	}
	
	/**
	 * Retorna a quantidade de orgaos que tem o nome recebido
	 * @param nome nome do orgao a ser pesquisado
	 * @return int quantidade de orgaos com o nome no banco
	 * @throws ControllerException caso algum dado esteja invalido
	 */
	public int qntOrgaos(String nome) throws ControllerException {
		util.nomeOrgao(nome);
		return bancoOrgaos.getQuantidadeOrgao(nome);
	}
	
	/**
	 * Retorna a quantidade de orgaos cadastradosno banco de orgaos
	 */
	public int totalOrgaosDisponiveis() {
		return bancoOrgaos.getQuantidadeBanco();
	}
	
	/**
	 * Remove um orgao do banco de Orgaos
	 * @param nome nome do orgao ser removido
	 * @param tipoSanguineo tipoSanguineo do orgao a ser removido
	 * @throws OrgaoException caso o orgao nao exista no banco
	 */
	public void retiraOrgao(String nome, String tipoSanguineo) throws ControllerException {
		util.nomeOrgao(nome);
		util.tipoSanguineo(tipoSanguineo);
		bancoOrgaos.retiraOrgao(nome, tipoSanguineo);
	}
	
	

	// Caso 6
	
	/**
	 * Recupera o ID de um paciente pelo nome
	 * @param Nome do paciente ao qual o id sera recuperado
	 * @return String com o id
	 * @throws ControllerException caso o Paciente nao seja encontrado
	 * */
	public String getPacienteID(String nome) throws ControllerException {
		return bancoPacientes.getPacienteID(nome);
	}
	/**
	 * Realiza um determinado procedimento em um paciente
	 * @param Procedimento a ser Realizado
	 * @param Id do paciente que sera realizado o procedimento
	 * @param medicamentos ultilizados no procedimento
	 * @throws ControllerException
	 * */
	public void realizaProcedimento(String procedimento, String idPaciente, String medicamentos) throws ControllerException {
		if(usuarioLogado.verificaPermissao(Permissoes.REALIZAPROCEDIMENTO)){
		bancoPacientes.verificaPacienteID(idPaciente);
		double precoMedicamentos = farmacia.verificaListaDeMedicamentos(medicamentos);
		bancoPacientes.realizaProcedimento(procedimento, idPaciente, precoMedicamentos,usuarioLogado.getNome());
		}else{
			throw new ProcedimentoException("O funcionario " + usuarioLogado.getNome() + " nao tem permissao para realizar procedimentos.");
		}
	}
	
	/**
	 * Retorna o numero total de procedimentos ao qual um paciente foi submetido
	 * */
	public int getTotalProcedimento(String id) throws PacienteException {
		return bancoPacientes.QtdProcedimentosPaciente(id);
	}
	/**
	 * Realiza um procedimento de transplante de orgaos em determinado paciente
	 * @param Procedimento a ser Realizado
	 * @param Id do paciente que sera realizado o procedimento
	 * @param Orgao a ser transplantado
	 * @param medicamentos ultilizados no procedimento
	 * @throws ControllerException
	 * */
	public void realizaProcedimento(String procedimento, String idPaciente, String orgao, String medicamentos) throws ControllerException {
		if(usuarioLogado.verificaPermissao(Permissoes.REALIZAPROCEDIMENTO)){
			util.verificaNomeOrgao(orgao);
			util.verificaProcedimento(procedimento);
			bancoPacientes.verificaPacienteID(idPaciente);
			if (bancoOrgaos.buscaOrgao(orgao,bancoPacientes.tipoSanguineoDoPaciente(idPaciente))){
				double precoMedicamentos = farmacia.verificaListaDeMedicamentos(medicamentos);
				bancoPacientes.realizaProcedimento(procedimento, idPaciente, precoMedicamentos,usuarioLogado.getNome(),orgao);
			}else{
				throw new OrgaoException("Banco nao possui o orgao especificado.");
			}
		}else{
			throw new ProcedimentoException("O funcionario " + usuarioLogado.getNome() + " nao tem permissao para realizar procedimentos.");
		}
	}
	/**
	 * Realiza um procedimento
	 * @throws PacienteException 
	 * @throws ProcedimentoException 
	 */
	public void realizaProcedimento(String procedimento, String idPaciente) throws PacienteException, ProcedimentoException{
		if(usuarioLogado.verificaPermissao(Permissoes.REALIZAPROCEDIMENTO)){
			util.verificaProcedimento(procedimento);
			bancoPacientes.verificaPacienteID(idPaciente);
			bancoPacientes.realizaProcedimento(procedimento, idPaciente,usuarioLogado.getNome());
			}
		else{
			throw new ProcedimentoException("O funcionario " + usuarioLogado.getNome() + " nao tem permissao para realizar procedimentos.");
		}
	}


	/**
	 * Recupera os pontos do cartao fidelidade
	 * @param posicao associa a posicao do paciente
	 * @return
	 * @throws PacienteException
	 */
	public int getPontosFidelidade(String idPaciente) throws PacienteException{
		return bancoPacientes.getPontosFidelidade(idPaciente);
	}
	
	/**
	 * Recupera todos os gastos do paciente
	 * @throws PacienteException 
	 */
	public String getGastos(String idPaciente) throws PacienteException{
		return bancoPacientes.despesaDoPaciente(idPaciente);
	}
	/**
	 * Registra as informacoes do paciente em um arquivo
	 * @param id do paciente em questao
	 * @throws PacienteException caso nao exista o paciente
	 * */
	public void exportaFichaPaciente(String idPaciente) throws PacienteException{
		bancoPacientes.exportaFichaPaciente(idPaciente);
	}
	
	
	// Metodos de Verificacao
	
	/**
	 * Verifica se o sistema ja foi liberado anteriormente
	 * @throws ControllerException
	 * */
	private void verificaSistema() throws ControllerException{
		if (sistemaLiberado == true){
			throw new ControllerException("Sistema liberado anteriormente.");
		}
	}
	/**
	 * Verifica se o cargo eh valido para criacao de um novo funcionario
	 * @throws ControllerException
	 * */
	private void verificaCargo(String cargo) throws ControllerException{
		if (cargo.equalsIgnoreCase("diretor geral")){
			throw new ControllerException("Nao eh possivel criar mais de um Diretor Geral.");
		}
		else if (cargo.equals("")){
			throw new ControllerException("Nome do cargo nao pode ser vazio.");
		}
		else if(!cargo.equalsIgnoreCase("diretor geral") && !cargo.equalsIgnoreCase("medico") && !cargo.equalsIgnoreCase("tecnico administrativo")){
			throw new ControllerException("Cargo invalido.");
		}
	}
}

