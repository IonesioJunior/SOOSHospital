package Paciente;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collections;

import Factory.PacienteFactory;
import Procedimentos.GerenciadorDeProcedimentos;
import Exceptions.ControllerException;
import Exceptions.DataInvalidaException;
import Exceptions.PacienteException;
import Exceptions.ProcedimentoException;

public class BancoPacientes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Paciente> pacientes;
	private PacienteFactory factoryPacientes;
	private GerenciadorDeProcedimentos gerenciaDeProcedimentos;
	
	/**
	 * Construtor de BancoPacientes, criando a colecao e instanciando o PacienteFactory.
	 */
	public BancoPacientes() {
		pacientes = new ArrayList<Paciente>();
		factoryPacientes = new PacienteFactory();
		gerenciaDeProcedimentos = new GerenciadorDeProcedimentos();
	}
	
	/**
	 * Cadastra o paciente e, se o mesmo estiver valido, o adiciona na colecao de pacientes.
	 * @param nome nome a ser associado ao paciente, passando por uma verificacao (se ja existe
	 * um com o mesmo nome)
	 * @param nascimento data de nascimento a ser associado ao paciente, tambem verificado
	 * @param peso peso a ser associado ao paciente	
	 * @param sexo sexo a ser associado ao paciente
	 * @param genero genero a ser associado ao paciente
	 * @param tipoSanguineo tipo sanguineo ao ser associado ao paciente
	 * @return id do paciente criado
	 * @throws PacienteException caso ja tenha um paciente na colecao
	 * @throws DataInvalidaException caso a data esteja invalida
	 */
	public String cadastrarPaciente(String nome,String nascimento,double peso,String sexo,String genero,String tipoSanguineo) throws PacienteException, DataInvalidaException{
		existePaciente(nome);
		Paciente paciente = factoryPacientes.criarPaciente(nome, nascimento, peso, sexo, genero,tipoSanguineo);
		pacientes.add(paciente);
		String idPaciente = String.format("%d", paciente.getID());
		return idPaciente;
	}
	
	/**
	 * Recupera informacoes do paciente com o id recebido
	 * @param id o id a ser pesquisado
	 * @param atributo atributo a ser retornado
	 * @return string com o atributo
	 * @throws PacienteException 
	 */
	public String getInfoPaciente(String id, String atributo) throws PacienteException {
		Paciente paciente = buscaPaciente(id);
		atributo = atributo.toLowerCase();
		switch(atributo){
			case "nome":
				return paciente.AcessarInformacoes(atributo);
			case "data":
				return paciente.AcessarInformacoes(atributo);
			case "sexo":
				return paciente.AcessarInformacoes(atributo);
			case "genero":
				return paciente.AcessarInformacoes(atributo);
			case "tiposanguineo":
				return paciente.AcessarInformacoes(atributo);
			case "idade":
				return paciente.AcessarInformacoes(atributo);
			case "peso":
				String peso = String.format("%.1f", paciente.getPeso());
				peso =  peso.replace(",", ".");
				return peso;
			default:
				return "Atributo invalido.";
		}
	}
	
	/**
	 * Verifica se existe um paciente com o nome recebido
	 * @param nome nome do paciente a ser verificado
	 * @throws PacienteException caso o paciente ja esteja cadastrado
	 */
	public void existePaciente(String nome) throws PacienteException{
		if(getPaciente(nome) != null){
			throw new PacienteException("Paciente ja cadastrado.");
		}
	}
	
	/**
	 * Recupera um paciente com o id recebido
	 * @param id id do paciente a ser recuperado
	 * @return paciente
	 * @throws PacienteException 
	 */
	public Paciente buscaPaciente(String id) throws PacienteException{
		verificaPacienteID(id);
		for (Paciente paciente : pacientes){
			String ID = String.format("%d",paciente.getID());
			if (ID.equals(id)){
				return paciente;
			}
		}
		return null;
	}
	
	/**
	 * Realiza um determinado procedimento em um paciente
	 * @param Procedimento a ser Realizado
	 * @param Id do paciente que sera realizado o procedimento
	 * @param custoMedicamentos Custo dos medicamentos ultilizados no procedimento
	 * @throws ProcedimentoException Caso nao exista esse procedimento
	 * @throws PacienteException caso o id seja invalido
	 * */
	public void realizaProcedimento(String procedimento,String idPaciente,double custoMedicamentos,String nomeDoMedico) throws ProcedimentoException, PacienteException{
		Paciente paciente = buscaPaciente(idPaciente);
		double custoProcedimento = gerenciaDeProcedimentos.realizarProcedimento(procedimento, paciente,nomeDoMedico);
		double custoTotal = custoMedicamentos + custoProcedimento;
		paciente.armazenarGastos(custoTotal);
		paciente.strategy();
	}
	/**
	 * Metodo que retorna a quantidade de procedimentos ao qual o paciente foi submetido
	 * @param id referente ao paciente
	 * @return Numero de procedimentos que o paciente foi submetido
	 * @throws PacienteException caso o paciente nao seja encontrado
	 * */
	public int QtdProcedimentosPaciente(String id) throws PacienteException{
		Paciente paciente = buscaPaciente(id);
		return paciente.getProcedimentosSize();
	}
	/**
	 * Retornar a despesa total de determinado paciente
	 * @param id do paciente a ser pesquisado
	 * @return Valor referente a despesa do paciente
	 * @throws PacienteException caso o paciente nao seja encontrado
	 * */
	public String despesaDoPaciente(String idPaciente) throws PacienteException{
		Paciente paciente = buscaPaciente(idPaciente);
		return paciente.getValorGasto();
	}
	/**
	 * Realiza um determinado procedimento em um paciente
	 * @param Procedimento a ser Realizado
	 * @param Id do paciente que sera realizado o procedimento
	 * @param custoMedicamentos Custo dos medicamentos ultilizados no procedimento
	 * @param Orgao a ser transplantado
	 * @throws ProcedimentoException Caso nao exista esse procedimento
	 * @throws PacienteException caso o id seja invalido
	 * */
	public void realizaProcedimento(String procedimento,String idPaciente,double custoMedicamentos,String nomeDoMedico,String orgao) throws ProcedimentoException, PacienteException{
		Paciente paciente = buscaPaciente(idPaciente);
		double custoProcedimento = gerenciaDeProcedimentos.realizarProcedimento(procedimento, paciente,nomeDoMedico,orgao);
		double custoTotal = custoMedicamentos + custoProcedimento;
		paciente.armazenarGastos(custoTotal);
		paciente.strategy();
	}
	/**
	 * Realiza o procedimento requisitado em determinado paciente
	 * @param procedimento Tipo de procedimento a ser realizado
	 * @param id do Paciente que sera submentido ao procedimento
	 * @param nome do medico que realizou o procedimento
	 * @throws PacienteException caso o Paciente nao exista 
	 * */
	public void realizaProcedimento(String procedimento,String idPaciente,String nomeDoMedico) throws PacienteException, ProcedimentoException{
		Paciente paciente = buscaPaciente(idPaciente);
		double custoProcedimento = gerenciaDeProcedimentos.realizarProcedimento(procedimento, paciente ,nomeDoMedico);
		paciente.armazenarGastos(custoProcedimento);
		paciente.strategy();
	}
	/**
	 * Metodo que retorna o tipo sanguineo de um paciente pelo id referente ao mesmo
	 * @param id do paciente a ser pesquisado
	 * @return TipoSanguineo do paciente
	 * @throws PacienteException caso o paciente nao seja encontrado
	 * */
	public String tipoSanguineoDoPaciente(String id) throws PacienteException{
		return buscaPaciente(id).AcessarInformacoes("tiposanguineo");
	}
	/**
	 * Recupera o ID de um paciente pelo nome
	 * @param Nome do paciente ao qual o id sera recuperado
	 * @return String com o id
	 * @throws PacienteException caso o Paciente nao seja encontrado
	 * */
	public String getPacienteID(String nome) throws PacienteException{
		Paciente paciente = getPaciente(nome);
		if(paciente == null){
			throw new PacienteException("Paciente nao cadastrado.");
		}
		String idString = String.format("%d", paciente.getID());
		return idString;
	}
	/**
	 * Recupera um paciente com o nome recebido
	 * @param nome nome do paciente a ser recuperado
	 * @return paciente
	 */
	public Paciente getPaciente(String nome){
		for (Paciente paciente : pacientes){
			if (paciente.AcessarInformacoes("nome").equals(nome)){
				return paciente;
			}
		}
		return null;
	}
	/**
	 * Recupera o prontuario do paciente com a posicao recebida
	 * @param posicao posicao no prontario
	 * @return info do prontuario
	 * @throws ControllerException
	 * @throws PacienteException
	 */
	public String getProntuario(int posicao) throws ControllerException, PacienteException {
		ordernaListaPacientes();
		Paciente paciente = getPaciente(posicao);
		int ID = paciente.getID();
		String IDString = String.format("%d", ID);
		return IDString;
	}
	
	/**
	 * Recupera o total de pontos do cartao fidelidade
	 * @throws PacienteException 
	 */
	public int getPontosFidelidade(String idPaciente) throws PacienteException{
		Paciente paciente = buscaPaciente(idPaciente);
		return paciente.getPontosCartaoFidelidade();
		
	}
	/**
	 * Recupera o paciente com a posicao recebida 
	 * @param posicao posicao do paciente na colecao
	 * @return o paciente
	 * @throws PacienteException caso nao exista paciente
	 */
	public Paciente getPaciente(int posicao) throws PacienteException{
		verificaTamanhoLista(posicao);
		return pacientes.get(posicao);
	}
	
	/**
	 * Adiciona um objeto paciente ao banco de pacientes
	 * @param paciente objeto do tipo paciente
	 * @throws PacienteException caso o paciente for invalido
	 */
	public void adicionaNoBancoDeDados(Paciente paciente) throws PacienteException{
		verificaPaciente(paciente);
		pacientes.add(paciente);
	}
	
	/**
	 * Ordena a lista do pacientes
	 */
	public void ordernaListaPacientes() {
		Collections.sort(pacientes);
	}
	
	/**
	 * Verifica paciente se e nulo
	 * @param paciente associa ao paciente
	 * @throws PacienteException
	 */
	private void verificaPaciente(Paciente paciente) throws PacienteException{
		if (paciente == null){
			throw new PacienteException("Paciente nulo");
		}
	}
	
	/**
	 * Verifica tamanho da lista de paciente
	 * @param posicao associa a posicao
	 * @throws PacienteException
	 */
	private void verificaTamanhoLista(int posicao) throws PacienteException{
		if (pacientes.size() < posicao){
			throw new PacienteException("Nao ha prontuarios suficientes (max = " + pacientes.size() + ").");
		}
	}
	
	/**
	 * Hashcode que funciona pelos mesmo atributos do equals
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pacientes == null) ? 0 : pacientes.hashCode());
		return result;
	}
	
	/**
	 * Equals que verifica se dois pacientes sao iguais se o objeto for o mesmo
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BancoPacientes other = (BancoPacientes) obj;
		if (pacientes == null) {
			if (other.pacientes != null)
				return false;
		} else if (!pacientes.equals(other.pacientes))
			return false;
		return true;
	}
	
	/**
	 * Retorna uma string com as informacoes dos pacientes
	 */
	@Override
	public String toString() {
		return "BancoPacientes [pacientes=" + pacientes + "]";
	}
	
	/**
	 * Verifica se a ID de um paciente e vazia
	 * */
	public  void verificaPacienteID(String id ) throws PacienteException{
		if (id.equals("") || id.equals(" ")){
			throw new PacienteException("ID do paciente nao pode ser vazio.");
		}
	}
	
	/**
	 * Registra as informacoes do paciente em um arquivo
	 * @param id do paciente em questao
	 * @throws PacienteException caso nao exista o paciente
	 * */
	public void exportaFichaPaciente(String idPaciente) throws PacienteException{
		Paciente paciente = buscaPaciente(idPaciente);
		paciente.registrarInformacoesEmArquivo();
	}
}
