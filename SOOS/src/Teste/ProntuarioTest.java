package Teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import Paciente.Prontuario;

public class ProntuarioTest {

	private Prontuario raquelProntuario;

	@Before
	public void criaProntuario() {
		raquelProntuario = new Prontuario("Raquel", "21/09/1997", 57.2, "feminino", "feminino", "A+", 1);

	}

	@Test
	public void testAcessaInformacoes() {

		try {
			Double peso = 57.2;
			assertEquals("Raquel", raquelProntuario.getNome());
			assertEquals("1997-09-21",raquelProntuario.getData());
			assertEquals(peso,raquelProntuario.getPeso());
			assertEquals("feminino",raquelProntuario.getSexo());
			assertEquals("feminino",raquelProntuario.getGenero());
			assertEquals("A+",raquelProntuario.getTipoSanguineo());
			assertEquals(1,raquelProntuario.getID());
			assertEquals("18",raquelProntuario.getIdade());

		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testEquals() {

		try {
			Prontuario raianyProntuario = new Prontuario("Raiany", "21/09/2000", 60.9, "feminino", "feminino", "A+", 1);
			Prontuario ruthProntuario = new Prontuario("Ruth", "28/12/1997", 60.9, "feminino", "feminino", "A+", 2);

			assertEquals(true,raquelProntuario.equals(raianyProntuario));
			assertEquals(false,raquelProntuario.equals(ruthProntuario));
		} catch (Exception e) {
			fail();
		}
	}
}