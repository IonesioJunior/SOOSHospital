package Teste;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import Paciente.BancoPacientes;
import Paciente.Paciente;
import Exceptions.DataInvalidaException;
import Exceptions.PacienteException;

public class BancoPacientesTest {

	private BancoPacientes banco;
	private Paciente raquel;

	@Before
	public void criaBancoDePacientes() throws PacienteException, DataInvalidaException {
		banco = new BancoPacientes();
		raquel = new Paciente("Raquel", "21/09/1997", 57.2, "feminino", "feminino", "A+", 1);

		banco.cadastrarPaciente("Raquel", "21/09/1997", 57.2, "feminino", "feminino", "A+");

	}

	@Test
	public void testCadastraPaciente() {

		try { // paciente ja cadastrado

			banco.cadastrarPaciente("Raquel", "21/09/1997", 57.2, "feminino", "feminino", "A+");

		} catch (Exception e) {
			assertEquals("Paciente ja cadastrado.", e.getMessage());
		}
	}

	@Test
	public void testGetInfoPaciente() {

		try {

			assertEquals("Raquel", banco.getInfoPaciente("1", "nome"));
			assertEquals("1997-09-21", banco.getInfoPaciente("1", "data"));
			assertEquals("feminino", banco.getInfoPaciente("1", "sexo"));
			assertEquals("feminino", banco.getInfoPaciente("1", "genero"));
			assertEquals("A+", banco.getInfoPaciente("1", "tiposanguineo"));
			assertEquals("57.2", banco.getInfoPaciente("1", "peso"));
			assertEquals("Atributo invalido.", banco.getInfoPaciente("1", "AtributoInvalido"));

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testExistePaciente() {

		try {
			assertEquals(raquel, banco.buscaPaciente("1"));
			assertEquals(null, banco.buscaPaciente("2"));
		} catch (Exception e) {

		}

	}

	@Test
	public void testGetPaciente() {

		try {
			assertEquals(raquel, banco.getPaciente("Raquel"));
			assertEquals(null, banco.getPaciente("Eronildo"));

		} catch (Exception e) {

		}

	}

	@Test
	public void testAdicionaNoBancoDeDados() {

		try {
			banco.adicionaNoBancoDeDados(raquel);

		} catch (Exception e) {

		}

	}
	

}