package Teste;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import Funcionarios.BancoFuncionarios;
import Funcionarios.Diretor;
import Exceptions.DataInvalidaException;
import Exceptions.FuncionarioException;

public class BancoFuncionariosTest {

	private BancoFuncionarios banco;
	private Diretor hyldemaria;

	@Before
	public void criaBancoFuncionarios() throws FuncionarioException, DataInvalidaException {
		hyldemaria = new Diretor("Hyldemaria", "12345678", "123456", "21/06/1880", "diretor");
		banco = new BancoFuncionarios();
		banco.cadastrarFuncionarios("Raquel", "MEDICO", "21/09/1990");
	}

	@Test
	public void testCasdastrarFuncionarios() {

		try {
			assertEquals("12016002", banco.cadastrarFuncionarios("Raiany", "DIRETOR GERAL", "21/09/1990"));

		} catch (Exception e) {
			fail();
		}

	}

	@Test
	public void testGetInfo() {

		try {
			assertEquals("Raquel", banco.getInfoFuncionario("22016001", "nome"));
			assertEquals("MEDICO", banco.getInfoFuncionario("22016001", "cargo"));
			assertEquals("1990-09-21", banco.getInfoFuncionario("22016001", "data"));
			assertEquals("Atributo Invalido.", banco.getInfoFuncionario("22016001", "invalido"));
		} catch (Exception e) {
			fail();
		}

		try {
			banco.getInfoFuncionario("22016001", "senha");

		} catch (Exception e) {
			assertEquals("A senha do funcionario eh protegida.", e.getMessage());
		}

	}

	@Test
	public void testCriaPrimeiraConta() {

		try {
			assertEquals("12016002", banco.criarPrimeiraConta("Moabe", "07/09/1990"));
		} catch (Exception e) {
			fail();
		}

	}

	@Test
	public void testAtualizaInfo() {

		try {
			banco.atualizaInfoFuncionario("22016001", "nome", "Rufino");
			banco.atualizaInfoFuncionario("22016001", "data", "01/09/2010");
		} catch (Exception e) {
			fail();
		}

		try {
			banco.atualizaInfoFuncionario("22016004", "nome", "Rufino");

		} catch (Exception e) {
			assertEquals("Funcionario nao cadastrado.", e.getMessage());
		}

	}

	@Test
	public void testValidarUsuario() {

		try {
			banco.validarUsuario("22016001", "19902201");

		} catch (Exception e) {
			fail();
		}
		try {
			banco.validarUsuario("22016001", "123456");

		} catch (Exception e) {
			assertEquals("Senha incorreta.", e.getMessage());
		}

		try {
			banco.validarUsuario("22016005", "123456");

		} catch (Exception e) {
			assertEquals("Funcionario nao cadastrado.", e.getMessage());
		}

	}

}