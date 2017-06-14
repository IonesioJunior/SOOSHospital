package Orgaos;

import java.io.Serializable;
import java.util.ArrayList;


import Factory.OrgaoFactory;
import Exceptions.OrgaoException;

public class BancoOrgaos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Orgao> orgaos;
	private OrgaoFactory factoryOrgaos;
	
	/**
	 * Construtor de BancoOrgaos, criando o banco a ser usado e instanciando a factory.
	 */
	public BancoOrgaos() {
		orgaos = new ArrayList<Orgao>();
		factoryOrgaos = new OrgaoFactory();
	}
	
	/**
	 * Adiciona um novo orgao no banco.
	 * @param nome nome do orgao a ser criado na factory
	 * @param tipoSanguineo  tipo Sanguineo do orgao a ser criado na factory
	 */
	public void adicionaOrgao(String nome, String tipoSanguineo) {
		Orgao orgao = factoryOrgaos.criaOrgao(nome, tipoSanguineo);
		orgaos.add(orgao);
	}
	
	/**
	 * Remove um orgao do banco
	 * @param nome nome do orgao ser removido
	 * @param tipoSanguineo tipoSanguineo do orgao a ser removido
	 * @throws OrgaoException caso o orgao nao exista no banco
	 */
	public void retiraOrgao(String nome, String tipoSanguineo) throws OrgaoException {
		existeOrgao(nome);
		for(Orgao orgao: orgaos) {
			if(orgao.getNome().equals(nome) && orgao.getTipoSanguineo().equals(tipoSanguineo)) {
				orgaos.remove(orgao);
				return;
			}
		}
		throw new OrgaoException("Orgao nao cadastrado.");
	}
	
	/**
	 * Recupera os tipos sanguineos do orgao com o nome recebido
	 * @param nome nome do orgao a ser pesquisado
	 * @return String com os tipos Sanguineos do orgao
	 * @throws OrgaoException caso o objeto nao exista
	 */
	public String buscaOrgaoNome(String nome) throws OrgaoException {
		existeOrgao(nome);
		String listaTiposSanguineos = null;
		for(Orgao orgao: orgaos) {
			if(orgao.getNome().equals(nome)) {
				listaTiposSanguineos += orgao.getTipoSanguineo() + ",";
			}
		}
		return listaTiposSanguineos.substring(4, (listaTiposSanguineos.length()-1));
	}
	
	/**
	 * Recupera uma String com os orgaos que tenha o mesmo tipo Sanguineo recebido
	 * @param tipo tipo Sanguineo do orgao a ser recuperado
	 * @return String com o nome dos orgaos
	 * @throws OrgaoException caso nao haja nenhum orgao cadastrado
	 */
	public String buscaOrgaoTipoSanguineo(String tipo) throws OrgaoException {
		String listaOrgaos = "vazio";
		for(Orgao orgao: orgaos) {
			if(orgao.getTipoSanguineo().equals(tipo)) {
				if(!listaOrgaos.contains(orgao.getNome())) {
					listaOrgaos += orgao.getNome() + ",";
				}
			}
		}
		if(listaOrgaos.equals("vazio")) {
			throw new OrgaoException("Nao ha orgaos cadastrados para esse tipo sanguineo.");
		}
		return listaOrgaos.substring(5, (listaOrgaos.length()-1));
	}
	
	/**
	 * Verifica se existe um orgao que tenha o nome e tipoSanguineo recebido
	 * @param nome nomedo orgao a ser pesquisado
	 * @param tipoSanguineo tipoSanguineo a ser pesquisado
	 * @return boolean se existe ou nao.
	 * @throws OrgaoException 
	 */
	public boolean buscaOrgao(String nome, String tipoSanguineo) throws OrgaoException {
		for(Orgao orgao: orgaos) {
			if(orgao.getNome().equals(nome) && orgao.getTipoSanguineo().equals(tipoSanguineo)) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * Retorna a quantidade total de orgaos disponiveis no banco
	 * @return a quantidade de orgaos
	 */
	public int getQuantidadeBanco() {
		return orgaos.size();
	}
	
	/**
	 * Retorna a quantidade de orgaos que tem o mesmo nome recebido
	 * @param nome nome do orgao a ser pesquisado
	 * @return a quantidade do orgao;
	 * @throws OrgaoException caso nao exista o orgao
	 */
	public int getQuantidadeOrgao(String nome) throws OrgaoException {
		existeOrgao(nome);
		int quantidade = 0;
		for(Orgao orgao: orgaos) {
			if(orgao.getNome().equals(nome)) {
				quantidade += 1;
			}
		}
		return quantidade;
	}
	
	/**
	 * Verifica se o orgao com o nome recebido esta cadastrado
	 * @param nome nome do orgao a ser verificado
	 * @return true se estiver cadastrado
	 * @throws OrgaoException caso nao exista orgao com esse nome cadastrado
	 */
	private boolean existeOrgao(String nome) throws OrgaoException {
		for(Orgao orgao: orgaos) {
			if(orgao.getNome().equals(nome)) {
				return true;
			}
		}
		throw new OrgaoException("Orgao nao cadastrado.");
	}
		
}
