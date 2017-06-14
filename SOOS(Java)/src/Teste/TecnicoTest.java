package Teste;

import static org.junit.Assert.*;



import org.junit.Test;

import Funcionarios.Tecnico;
import Exceptions.FuncionarioException;


public class TecnicoTest {


	@Test
	
	public void testeConstrutor(){
		try{
			Tecnico hyldemaria = new Tecnico("Hyldemaria", "12345678", "123456","21/06/1880","Tecnico Administrativo");

		}catch(Exception e){
			fail();
		}
		
		try{
			Tecnico hyldemaria = new Tecnico("Hyldemaria", "12345678", "","21/06/1880","Tecnico Administrativo");

		}catch(Exception e){
			assertEquals("Senha invalida.",e.getMessage());
		}
		
		try{
			Tecnico livia = new Tecnico("Livia", "", "123456","21/06/1880","Tecnico Administrativo");

		}catch(Exception e){
			
			assertEquals("Matricula Invalida!",e.getMessage());
		}
		
		try{
			Tecnico livia = new Tecnico("", "12345678", "123456","21/06/1880","Tecnico Administrativo");

		}catch(Exception e){
			
			assertEquals("Nome do funcionario nao pode ser vazio.",e.getMessage());
		}

	}
	
	@Test
	
	public void testEquals() throws FuncionarioException{
		
		Tecnico jailson = new Tecnico("Jailson", "12340987", "123456","21/06/1880","Tecnico Administrativo");
		Tecnico hyldemaria = new Tecnico("Hyldemaria", "12345678", "456789","21/06/1880","Tecnico Administrativo");
		Tecnico livia = new Tecnico("Livia", "12345678", "123456","21/06/1880","Tecnico Administrativo");
		assertEquals(true, hyldemaria.equals(livia));
		assertEquals(false, hyldemaria.equals(jailson));
	}


}