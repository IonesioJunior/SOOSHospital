package Teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Orgaos.Orgao;

public class OrgaoTest {
	private Orgao figado;
	private Orgao coracao;
	
	@Before
	public void criaOrgaos() {
		figado = new Orgao("figado", "A+");
		coracao = new Orgao("coracao", "B-");
	}

	@Test
	public void testGetNome() {
		try {
			assertEquals("figado", figado.getNome());
			assertEquals("coracao", coracao.getNome());
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testGetTipoSanguineo() {
		try {
			assertEquals("A+", figado.getTipoSanguineo());
			assertEquals("B-", coracao.getTipoSanguineo());
		} catch(Exception e) {
			fail();
		}
	}

}
