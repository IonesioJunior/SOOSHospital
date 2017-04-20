package Sistema;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Exceptions.BancoOrgaosException;

import Exceptions.ControllerException;
import Exceptions.OrgaoException;

public class Facade {
	Controller control;
	
	/**
	 * Construtor da classe, iniciando o sistema
	 */
	public Facade(){
		iniciaSistema();
	}
	
	// Caso 1
	
	/**
	 * Inicia o Sistema
	 * @throws FileNotFoundException 
	 * */
	public void iniciaSistema(){
		try{
			FileInputStream Arquivo = new FileInputStream("SOOS.dat");
			ObjectInputStream stream  = new ObjectInputStream(Arquivo);
			this.control = (Controller) stream.readObject();
			stream.close();
		}catch(Exception e){
			control = new Controller();
		}
	}
	
	/**
	 * Libera Sistema
	 * @param chave associa a chave
	 * @param nome associa ao nome
	 * @param nascimento associa a data de nascimento
	 * @return
	 * @throws Exception
	 */
	public String liberaSistema(String chave, String nome, String nascimento) throws Exception {
		try {
			String mensagem = control.liberaSistema(chave, nome, nascimento);
			return mensagem;
		} catch (Exception e) {
			throw new Exception("Erro ao liberar o sistema. " + e.getMessage());
		}
	}
	
	/**
	 * Faz login
	 * @param matricula associa a matricula
	 * @param senha associa a senha
	 * @throws Exception
	 */
	public void login(String matricula, String senha) throws Exception {
		try {
			control.realizarLogin(matricula, senha);
		} catch (Exception e) {
			throw new Exception("Nao foi possivel realizar o login. " + e.getMessage());
		}
	}
	
	/**
	 * Recupera informacao do usuario
	 * @param matricula associa a matricula
	 * @param atributo associa ao atributo
	 * @return o atributo desejado
	 * @throws Exception
	 */
	public String getInfoFuncionario(String matricula, String atributo) throws Exception {
		try {
			return control.getInfoFuncionario(matricula, atributo);
		} catch (Exception e) {
			throw new Exception("Erro na consulta de funcionario. " + e.getMessage());
		}
	}
	
	/**
	 * Cadastra Funcionario
	 * @param nome associa ao nome
	 * @param cargo associa ao cargo
	 * @param nascimento associa a data de nascimento
	 * @return
	 * @throws Exception
	 */
	public String cadastraFuncionario(String nome, String cargo, String nascimento) throws Exception {
		try {
			return control.cadastrarFuncionarios(nome, cargo, nascimento);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de funcionario. " + e.getMessage());
		}
	}
	
	/**
	 * Atualiza Informacoes do Funcionario
	 * @param matricula associa a matricula
	 * @param atributo associa ao atributo
	 * @param novoValor associa ao novo valor do atributo
	 * @throws Exception
	 */
	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor) throws Exception {
		try {
			control.atualizaInfoFuncionario(matricula, atributo, novoValor);
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}
	
	/**
	 * Atualiza Informacoes do Funcionario
	 * @param atributo associa ao atributo
	 * @param novoValor associa ao novo valor do atributo
	 * @throws Exception
	 */
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws Exception {
		try {
			control.atualizaFuncionario(atributo, novoValor);
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar funcionario. " + e.getMessage());}
		}
	
	// Caso  2 , 3 e 4
	/**
	 * Cadastra Paciente
	 * @param nome associa ao nome
	 * @param nascimento associa a data de nascimento
	 * @param peso associa ao peso
	 * @param sexo associa ao sexo
	 * @param genero associa ao genero
	 * @param tipoSanguineo associa ao tipo sanguineo
	 * @return
	 * @throws Exception
	 */
	public String cadastraPaciente(String nome, String nascimento, double peso, String sexo, String genero,
			String tipoSanguineo) throws Exception {
		try {
			return control.cadastraPaciente(nome, nascimento, peso, sexo, genero, tipoSanguineo);
		} catch (Exception e) {
			throw new Exception("Nao foi possivel cadastrar o paciente. " + e.getMessage());
		}
	}
	
	/**
	 * Atualiza senha do usuario
	 * @param senhaAntiga associa a senha antiga
	 * @param senhaNova associa a senha nova
	 * @throws Exception
	 */
	public void atualizaSenha(String senhaAntiga, String senhaNova) throws Exception {
		try {
			control.atualizaSenha(senhaAntiga, senhaNova);
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}
	
	/**
	 * Recupera informacoes do paciente
	 * @param id associa ao id 
	 * @param atributo associa ao atributo
	 * @return informacao deseja pelo usuario
	 * @throws Exception
	 */
	public String getInfoPaciente(String id, String atributo) throws Exception {
		try {
			return control.getInfoPaciente(id, atributo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * Recupera prontuario
	 * @param posicao associa a posicao do prontuario
	 * @return o prontuario desejado
	 * @throws Exception
	 */
	public String getProntuario(String posicao) throws Exception {
		try {
			return control.getProntuario(posicao);
		} catch (Exception e) {
			throw new Exception("Erro ao consultar prontuario. " + e.getMessage());
		}
	}
	
	/**
	 * Cadastra Medicamentos
	 * @param nome associa ao nome
	 * @param tipo associa ao tipo
	 * @param preco associa ao preco
	 * @param quantidade associa a quantidade
	 * @param categorias associa as categorias
	 * @return nome do medicamento
	 * @throws Exception
	 */
	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws Exception {
		try {
			return control.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de medicamento. " + e.getMessage());
		}
	}
	
	/**
	 * Recupera informacoes do medicamento
	 * @param atributo associa ao atributo
	 * @param nome associa ao nome
	 * @return a informacao desejada
	 * @throws Exception
	 */
	public String getInfoMedicamento(String atributo, String nome) throws Exception {
		try {
			return control.getInfoMedicamento(atributo, nome);
		} catch (Exception e) {
			throw new Exception("Erro ao consultar medicamento. " + e.getMessage());
		}
	}
	
	/**
	 * Atualiza Medicamentos
	 * @param nome associa ao nome do medicamento
	 * @param atributo associa ao atributo
	 * @param novoValor associa ao novo valor do atributo
	 * @throws Exception
	 */
	public void atualizaMedicamento(String nome, String atributo, String novoValor) throws Exception {
		try {
			control.atualizaMedicamento(nome, atributo, novoValor);
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar medicamento. " + e.getMessage());
		}
	}
	
	/**
	 * Consulta Medicamentos que contem categoria
	 * @param categoria associa a categoria
	 * @return Retorna todos os medicamentos que contem a categoria
	 * @throws Exception
	 */
	public String consultaMedCategoria(String categoria) throws Exception {
		try {
			return control.consultaMedCategoria(categoria);
		} catch (Exception e) {
			throw new Exception("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}
	
	/**
	 * Consulta Medicamento pelo nome
	 * @param nome associa ao nome
	 * @return informacoes do medicamento
	 * @throws Exception
	 */
	public String consultaMedNome(String nome) throws Exception {
		try {
			return control.consultaMedNome(nome);
		} catch (Exception e) {
			throw new Exception("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}
	
	/**
	 * Sai do sistema
	 * @throws Exception
	 */
	public void logout() throws Exception {
		try {
			control.logout();
		} catch (Exception e) {
			throw new Exception("Nao foi possivel realizar o logout. " + e.getMessage());
		}
	}
	
	/**
	 * Exclui funcionario
	 * @param matricula associa a matricula
	 * @param senha associa a senha
	 * @throws Exception
	 */
	public void excluiFuncionario(String matricula, String senha) throws Exception {
		try {
			control.excluirFuncionario(matricula, senha);
		} catch (Exception e) {
			throw new Exception("Erro ao excluir funcionario. " + e.getMessage());
		}
	}
	
	/**
	 * Fecha o sistema
	 * @throws Exception
	 */
	public void fechaSistema() throws Exception {
		try {
			control.verificaLogin();
			FileOutputStream arquivo = new FileOutputStream("SOOS.dat");
			ObjectOutputStream stream  = new ObjectOutputStream(arquivo);
			stream.writeObject(control);
			stream.close();
		} catch (Exception e) {
			throw new Exception("Nao foi possivel fechar o sistema. " + e.getMessage());
		}
	}
	
	/**
	 * Recupera todos os medicamentos da farmacia
	 * @param ordenacao qual tipo de ordenacao o usuario deseja
	 * @return medicamentos ordenados
	 * @throws Exception
	 */
	public String getEstoqueFarmacia(String ordenacao) throws Exception {
		try {
			return control.getEstoqueFarmacia(ordenacao);
		} catch (ControllerException e) {
			throw new Exception("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}
	
	// Caso 5
	
	
	/**
	 * Cadastra um orgao no banco de Orgaos
	 * @param nome nome do orgao a ser cadastrado
	 * @param tipo tipoSanguineo do orgao
	 * @throws ControllerException caso algum dado esteja invalido
	 */
	public void cadastraOrgao(String nome, String tipo) throws BancoOrgaosException {
		try {
			control.cadastraOrgao(nome, tipo);
		} catch(ControllerException e) {
			throw new BancoOrgaosException(e.getMessage());
		}
	}
	
	/**
	 * Busca um orgao no banco de Orgaos pelo tipoSanguineo
	 * @param tipoSanguineo tipoSanguineo a ser pesquisado
	 * @return lista com os orgaos que possuem o tipoSanguineo
	 * @throws ControllerException caso algum dado esteja invalido
	 */
	public String buscaOrgPorSangue(String tipoSanguineo) throws BancoOrgaosException {
		try {
			return control.buscaOrgaoPorSangue(tipoSanguineo);
		} catch (ControllerException e) {
			throw new BancoOrgaosException(e.getMessage());
		}
	}
	
	/**
	 * Busca um orgao no banco de Orgaos pelo nome
	 * @param nome nome do orgao a ser pesquisado
	 * @return lista com os orgaos que possuem o tipoSanguineo
	 * @throws ControllerException caso algum dado esteja invalido
	 */
	public String buscaOrgPorNome(String nome) throws BancoOrgaosException{
		try {
			return control.buscaOrgaoPorNome(nome);
		} catch (ControllerException e) {
			throw new BancoOrgaosException(e.getMessage());
		}
	}
	
	/**
	 * Busca um orgao que contem o nome e o tipo sanguineo recebidos
	 * @param nome nome do orgao a ser pesquisado
	 * @param tipoSanguineo tipo sanguineo do orgao a ser pesquisado
	 * @return boolean se existe
	 * @throws ControllerException caso algum dado esteja invalido
	 */
	public boolean buscaOrgao(String nome, String tipoSanguineo) throws BancoOrgaosException {
		try {
			return control.buscaOrgao(nome, tipoSanguineo);
		} catch (ControllerException e) {
			throw new BancoOrgaosException(e.getMessage());
		}
	}
	
	/**
	 * Retorna a quantidade de orgaos que tem o nome recebido
	 * @param nome nome do orgao a ser pesquisado
	 * @return int quantidade de orgaos com o nome no banco
	 * @throws ControllerException caso algum dado esteja invalido
	 */
	public int qtdOrgaos(String nome) throws BancoOrgaosException {
		try {
			return control.qntOrgaos(nome);
		} catch (ControllerException e) {
			throw new BancoOrgaosException(e.getMessage());
		}
	}
	
	/**
	 * Retorna a quantidade de orgaos cadastradosno banco de orgaos
	 */
	public int totalOrgaosDisponiveis() {
		return control.totalOrgaosDisponiveis();
	}
	
	/**
	 * Remove um orgao do banco de Orgaos
	 * @param nome nome do orgao ser removido
	 * @param tipoSanguineo tipoSanguineo do orgao a ser removido
	 * @throws OrgaoException caso o orgao nao exista no banco
	 */
	public void retiraOrgao(String nome, String tipoSanguineo) throws Exception {
		try {
			control.retiraOrgao(nome, tipoSanguineo);
		} catch (ControllerException e) {
			throw new Exception("Erro na retirada de orgaos. " + e.getMessage());
		}
	}
	
	// Caso 6
	
	/**
	 * Recupera o ID de um paciente pelo nome
	 * @param Nome do paciente ao qual o id sera recuperado
	 * @return String com o id
	 * @throws Exception
	 * */
	public String getPacienteID(String nome) throws Exception{
		try{
			return control.getPacienteID(nome);
		}catch(ControllerException e){
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * Realiza um determinado procedimento em um paciente
	 * @param Procedimento a ser Realizado
	 * @param Id do paciente que sera realizado o procedimento
	 * @param medicamentos ultilizados no procedimento
	 * @throws ControllerException
	 * */
	public void realizaProcedimento(String Procedimento , String idPaciente, String medicamentos) throws Exception{
		try{
			control.realizaProcedimento(Procedimento ,idPaciente ,medicamentos);
		}catch(ControllerException e){
			throw new Exception("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}
	
	/**
	 * Realiza um procedimento de transplante de orgaos em determinado paciente
	 * @param Procedimento a ser Realizado
	 * @param Id do paciente que sera realizado o procedimento
	 * @param Orgao a ser transplantado
	 * @param medicamentos ultilizados no procedimento
	 * @throws Exception
	 * */
	public void realizaProcedimento(String Procedimento , String idPaciente,String orgao, String medicamentos) throws Exception{
		try{
			control.realizaProcedimento(Procedimento ,idPaciente,orgao ,medicamentos);
		}catch(ControllerException e){
			throw new Exception("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}
	
	/**
	 * Retorna o numero total de procedimentos ao qual um paciente foi submetido
	 * */
	public int getTotalProcedimento(String id) throws Exception{
		try{
			return control.getTotalProcedimento(id);
		}catch(ControllerException e){
				throw new Exception(e.getMessage());
		}
	}
	
	// Caso 7 
	/**
	 * Retorna o numero total de pontos fidelidade
	 * @return
	 * @throws Exception 
	 */
	public int getPontosFidelidade(String idPaciente) throws Exception{
		try{
			
			return control.getPontosFidelidade(idPaciente);
		}catch(ControllerException e){
				throw new Exception(e.getMessage());
		}
	}

	/**
	 * Realiza um procedimento de transplante de orgaos em determinado paciente
	 * @param Procedimento a ser Realizado
	 * @param Id do paciente que sera realizado o procedimento
	 * @throws Exception
	 * */
	public void realizaProcedimento(String Procedimento , String idPaciente) throws Exception{
		try{
			control.realizaProcedimento(Procedimento ,idPaciente);
		}catch(ControllerException e){
			throw new Exception("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}
	
	/**
	 * Retorna o numero total de pontos fidelidade
	 * @return 
	 * @throws Exception 
	 */
	public String getGastosPaciente(String idPaciente) throws Exception {
		try{
			return control.getGastos(idPaciente);
		}catch(ControllerException e){
			throw new Exception("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}
	
	/**
	 * Registra as informacoes do paciente em um arquivo
	 * @param id do paciente em questao
	 * @throws Exception caso nao exista o paciente
	 * */
	public void exportaFichaPaciente(String idPaciente) throws Exception{
		try{
			control.exportaFichaPaciente(idPaciente);
		}catch(ControllerException e){
			throw new Exception(e.getMessage());
		}
	}
	
}
