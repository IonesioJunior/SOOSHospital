package Teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Orgaos.BancoOrgaos;

public class BancoOrgaosTest {
	
	private BancoOrgaos banco;
	
	@Before
	public void criaBancoOrgaos() {
		banco = new BancoOrgaos();
		banco.adicionaOrgao("coracao", "O+");
		banco.adicionaOrgao("figado", "AB-");
	}

	@Test
	public void testAdicionaOrgao() {
		try {
			assertEquals(2, banco.getQuantidadeBanco());
			banco.adicionaOrgao("rim", "A-");
			assertEquals(3, banco.getQuantidadeBanco());
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testRetiraOrgao() {
		try {
			assertEquals(2, banco.getQuantidadeBanco());
			banco.retiraOrgao("coracao", "O+");
			assertEquals(1, banco.getQuantidadeBanco());
		} catch(Exception e) {
			fail();
		}
		
		try {
			banco.retiraOrgao("coracao", "O-"); // nao existe coracao com esse tipo no banco
			fail();
		} catch(Exception e) {
			assertEquals("Orgao nao cadastrado.", e.getMessage());
		}
	}

	@Test
	public void testBuscaOrgaoNome() {
		try {
			assertEquals("O+", banco.buscaOrgaoNome("coracao"));
			assertEquals("AB-", banco.buscaOrgaoNome("figado"));
		} catch(Exception e) {
			fail();
		}
		
		try {
			assertEquals("A-", banco.buscaOrgaoNome("rim"));
			fail();
		} catch(Exception e) {
			assertEquals("Orgao nao cadastrado.", e.getMessage());
		}	
	}

	@Test
	public void testBuscaOrgaoTipoSanguineo() {
		try {
			assertEquals("coracao", banco.buscaOrgaoTipoSanguineo("O+"));
			assertEquals("figado", banco.buscaOrgaoTipoSanguineo("AB-"));
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testBuscaOrgao() {
		try {
			assertEquals(true, banco.buscaOrgao("coracao", "O+"));
			assertEquals(true, banco.buscaOrgao("figado", "AB-"));
			assertEquals(false, banco.buscaOrgao("rim", "A-"));
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testGetQuantidadeBanco() {
		try {
			assertEquals(2, banco.getQuantidadeBanco());
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testGetQuantidadeOrgao() {
		try {
			assertEquals(1, banco.getQuantidadeOrgao("coracao"));
			assertEquals(1, banco.getQuantidadeOrgao("figado"));
			banco.adicionaOrgao("coracao", "O-");
			assertEquals(2, banco.getQuantidadeOrgao("coracao"));
		} catch(Exception e) {
			fail();
		}
	}

}
