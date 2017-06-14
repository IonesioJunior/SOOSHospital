package Teste;

import static org.junit.Assert.*;


import org.junit.Test;

import Paciente.Paciente;

public class PacienteTest {

	@Test
	public void testAcessaInformacoes() {

		try {

			Paciente raquel = new Paciente("Raquel", "21/09/1997", 57.2, "feminino", "feminino", "A+", 1);
			assertEquals("feminino",raquel.AcessarInformacoes("sexo"));
			assertEquals("Raquel",raquel.AcessarInformacoes("nome"));
			assertEquals("1997-09-21",raquel.AcessarInformacoes("data"));
			assertEquals("feminino",raquel.AcessarInformacoes("genero"));
			assertEquals("A+",raquel.AcessarInformacoes("tiposanguineo"));
			assertEquals("Atributo invalido.",raquel.AcessarInformacoes("AtributoInvalido"));

		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testEquals() {

		try {
			Paciente raquel = new Paciente("Raquel", "21/09/1997", 57.2, "feminino", "feminino", "A+", 1);
			Paciente raiany = new Paciente("Raiany", "21/09/2000", 60.9, "feminino", "feminino", "A+", 1);
			Paciente ruth = new Paciente("Ruth", "28/12/1997", 60.9, "feminino", "feminino", "A+", 2);

			assertEquals(true,raquel.equals(raiany));
			assertEquals(false,raquel.equals(ruth));
		} catch (Exception e) {
			fail();
		}
	}
}
