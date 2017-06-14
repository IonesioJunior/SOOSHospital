package Factory;

import java.io.Serializable;

import Orgaos.Orgao;

/**
 * Classe responsavel por criar objetos do tipo Orgao.
 *
 */
public class OrgaoFactory implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe
	 */
	public OrgaoFactory() {
		
	}
	
	/**
	 * Cria um novo objeto do tipo Orgao e o retorna
	 * @param nome nome do orgao a ser criado
	 * @param tipoSanguineo tipo sanguineo do orgao a ser criado
	 * @return o objeto do tipo Orgao
	 */
	public Orgao criaOrgao(String nome, String tipoSanguineo) {
		Orgao orgao = new Orgao(nome, tipoSanguineo);
		return orgao;
	}
	
}
