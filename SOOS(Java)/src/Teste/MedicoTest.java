package Teste;

import static org.junit.Assert.*;
import Funcionarios.Medico;
import org.junit.Test;

import Exceptions.FuncionarioException;


public class MedicoTest {


	@Test
	
	public void testeConstrutor(){
		try{
			Medico hyldemaria = new Medico("Hyldemaria", "12345678", "123456","21/06/1880","medico");

		}catch(Exception e){
			fail();
		}
		
		try{
			Medico hyldemaria = new Medico("Hyldemaria", "12345678", "","21/06/1880","medico");

		}catch(Exception e){
			assertEquals("Senha invalida.",e.getMessage());
		}
		
		try{
			Medico livia = new Medico("Livia", "", "123456","21/06/1880","medico");

		}catch(Exception e){
			
			assertEquals("Matricula Invalida!",e.getMessage());
		}
		
		try{
			Medico livia = new Medico("", "12345678", "123456","21/06/1880","medico");

		}catch(Exception e){
			
			assertEquals("Nome do funcionario nao pode ser vazio.",e.getMessage());
		}

	}
	
	@Test
	
	public void testEquals() throws FuncionarioException{
		
		Medico jailson = new Medico("Jailson", "12340987", "123456","21/06/1880","medico");
		Medico hyldemaria = new Medico("Hyldemaria", "12345678", "456789","21/06/1880","medico");
		Medico livia = new Medico("Livia", "12345678", "123456","21/06/1880","medico");
		assertEquals(true, hyldemaria.equals(livia));
		assertEquals(false, hyldemaria.equals(jailson));
	}


}