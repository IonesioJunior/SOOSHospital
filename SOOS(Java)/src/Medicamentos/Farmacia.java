package Medicamentos;

import java.io.Serializable;
import java.util.Collections;

import java.util.Comparator;
import java.util.LinkedList;

import Factory.MedicamentoFactory;
import Exceptions.ControllerException;
import Exceptions.MedicamentoException;

public class Farmacia implements Serializable{

	private static final long serialVersionUID = 1L;
	private LinkedList<Medicamento> listaMedicamentos;
	private MedicamentoFactory factoryMedicamentos;
	/**
	 * Construtor da classe, criando a colecao de medicamentos e instanciando a factory
	 */
	public Farmacia() {
		listaMedicamentos = new LinkedList<Medicamento>();
		factoryMedicamentos = new MedicamentoFactory();
	}
	
	/**
	 * Cadastra Medicamentos
	 * @param nome associa ao nome
	 * @param tipo associa ao tipo
	 * @param preco associa ao preco
	 * @param quantidade associa a quantidade
	 * @param categorias associa as categorias
	 * @return nome do medicamento
	 * @throws MedicamentoException
	 */
	public String cadastrarMedicamento(String nome, String tipo, double preco, int quantidade, String categorias) throws MedicamentoException {
		Medicamento remedio = factoryMedicamentos.criaMedicamento(nome, tipo, preco, quantidade, categorias);
		listaMedicamentos.add(remedio);
		return remedio.getNome();
	}
	
	
	/**
	 * Recupera o Medicamento
	 * @param nome associa ao nome do medicamento
	 * @return
	 */
	public Medicamento getMedicamento(String nome) {
		for(Medicamento medicamento: listaMedicamentos) {
			if(medicamento.getNome().equalsIgnoreCase(nome)) {
				return medicamento;
			}	
		}
		return null;
	}
	
	/**
	 * Recupera informacoes do Medicamentos
	 * @param atributo associa ao atributo
	 * @param nome associa ao nome 
	 * @return a informacao pedida
	 * @throws ControllerException
	 */
	public String getInfoMedicamento(String atributo, String nome) throws ControllerException {
		Medicamento remedio = verificarMedicamento(nome);
		atributo = atributo.toLowerCase();
		switch (atributo) {
			case "tipo":
				return remedio.getTipo(); // chamada polimorfica
			case "preco":
				String valor = String.format("%.1f", remedio.getPreco());
				valor =  valor.replace(",", ".");
				return valor;
			case "quantidade":
				return String.valueOf(remedio.getQuantidade());
			case "categorias":
				return remedio.getCategorias();
			default:
				return "Atributo Invalido.";
		}
	}
	/**
	 * Atualiza preco do medicamento
	 * @param nome associa ao nome do medicamento
	 * @param novoPreco associa ao novo preco
	 * @throws ControllerException
	 */
	public void atualizaPrecoMedicamento(String nome, double novoPreco) throws ControllerException {
		Medicamento remedio = verificarMedicamento(nome);
		remedio.setPreco(novoPreco);
	}
	/**
	 * atualiza a quantidade de medicamento
	 * @param nome associa ao nome do medicamento
	 * @param novaQuant associa a nova quantidade
	 * @throws ControllerException
	 */
	public void atualizaQuantMedicamento(String nome, int novaQuant) throws ControllerException {
		Medicamento remedio = verificarMedicamento(nome);
		remedio.setQuantidade(novaQuant);
	}
	
	/**
	 * Consulta Medicamento pelo nome
	 * @param nome associa ao nome
	 * @return	informacoes do medicamento caso exista
	 * @throws ControllerException
	 */
	public String consultaMedNome(String nome) throws ControllerException {
		Medicamento remedio = getMedicamento(nome);
		if(remedio == null) {
			throw new ControllerException("Medicamento nao cadastrado.");
		}
		return remedio.toString();
	}
	/**
	 * Consulta Medicamento pelo nome
	 * @param nome associa ao nome
	 * @throws ControllerException
	 */
	public void consultaMedPorNome(String nome) throws ControllerException {
		Medicamento remedio = getMedicamento(nome);
		if(remedio == null) {
			throw new ControllerException("Medicamento nao cadastrado.");
		}
	}
	/**
	 * Adiciona medicamento no banco de dados
	 * @param remedio associa ao medicamento
	 * @throws MedicamentoException
	 */
	public void adicionaNoBancoDeDados(Medicamento remedio) throws MedicamentoException {
		verificaMedicamento(remedio);
		listaMedicamentos.add(remedio);
	}
	/**
	 * Verifica se o medicamento e nulo
	 * @param remedio associa ao medicamento
	 * @throws MedicamentoException
	 */
	private void verificaMedicamento(Medicamento remedio) throws MedicamentoException {
		if(remedio == null) {
			throw new MedicamentoException("Medicamento nulo");
		}
	}
	
	/**
	 * Verifica se as categorias existem
	 * @param tipos associa as categorias
	 * @throws MedicamentoException
	 */
	private void verificaCategoria(String tipos) throws MedicamentoException {
		String[] categoriasSeparadas = tipos.split(",");
		boolean contem = false;
		for(TiposMedicamento tipo: TiposMedicamento.values()) {
			for(String categoria: categoriasSeparadas) {
				if(tipo.name().equalsIgnoreCase(categoria)) {
					contem = true;
				}
			}
		}
		if(!contem) {
			throw new MedicamentoException("Categoria invalida.");
		}
		
	}
	
	/**
	 * Busca medicamento por categoria
	 * @param categoria associa a categoria dele
	 * @return
	 * @throws MedicamentoException
	 */
	public String buscaMedicamentoCategoria(String categoria) throws MedicamentoException {
		verificaCategoria(categoria);
		String remedios = "";
		sortedMedicamentosPreco();
		for(Medicamento medicamento: listaMedicamentos) {
			if(medicamento.contemCategoria(categoria)) {
				remedios += medicamento.getNome() + ",";
			}
		}
		if(remedios == "") {
			throw new MedicamentoException("Nao ha remedios cadastrados nessa categoria.");
		}
		 return remedios.substring(0, (remedios.length()-1));
	}
	
	/**
	 * Organiza os medicamentos pelo nome
	 */
	private void sortedMedicamentosNome(){
		Collections.sort(listaMedicamentos, new Comparator<Medicamento>() {
			public int compare(Medicamento o1, Medicamento o2) {
				return o1.getNome().compareTo(o2.getNome());
			}
		});
	}
	
	/**
	 * Organiza medicamentos
	 */
	public void sortedMedicamentos() {
		Collections.sort(listaMedicamentos);
	}
	
	/**
	 * Organiza pelo preco
	 */
	public void sortedMedicamentosPreco() {
		Collections.sort(listaMedicamentos, new Comparator<Medicamento>() {
			
			public int compare(Medicamento o1, Medicamento o2) {
				if (o1.getPreco() > o2.getPreco()) {
					return 1;
				} else if (o1.getPreco() < o2.getPreco()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
	}
	
	/**
	 * Ordenar a lista de Medicamentos de acordo com o parametro recebido
	 * @param ordenacao ordenacao desejada (alfabetica ou preco)
	 * @return nomes dos medicamentos ordenados
	 */
	public String getListaOrdenada(String ordenacao){
		if(ordenacao.equalsIgnoreCase("alfabetica")){
			sortedMedicamentosNome();
		} else	{
			sortedMedicamentosPreco();	
		}
		String nomedosmedicamentos = "";
		for (Medicamento medicamento : listaMedicamentos){
				nomedosmedicamentos += medicamento.getNome() + ",";
		}
		nomedosmedicamentos = (String) nomedosmedicamentos.subSequence(0, nomedosmedicamentos.length() - 1);
		return nomedosmedicamentos;
	}
	

	/**
	 * Verifica se o Objeto Medicamento e um objeto valido
	 * @throws ControllerException
	 * */
	private Medicamento verificarMedicamento(String nome) throws ControllerException {
		if(nome.equals("") || nome.equals(" ")){
			throw new ControllerException("Nome do medicamento nao pode ser vazio.");
		}
		else if(getMedicamento(nome) == null) {
			throw new ControllerException("Medicamento nao cadastrado.");
		}
		return getMedicamento(nome);
	}
	/**
	 * Verifica se os medicamentos pedidos existem no estoque
	 * @param medicamentos String com os medicamentos a serem avaliados
	 * @return preco total dos medicamentos  pedidos
	 * @throws ControllerException
	 * */
	public double verificaListaDeMedicamentos(String medicamentos) throws ControllerException{
		String[] listademedicamentos = medicamentos.split(",");
		double preco = 0;
		for (String medicamento : listademedicamentos){
			verificarMedicamento(medicamento);
			consultaMedPorNome(medicamento);
			preco += getMedicamento(medicamento).getPreco();
		}
		return preco;
	}
}
