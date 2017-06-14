package Teste;

import static org.junit.Assert.*;


import org.junit.Test;
import Funcionarios.Diretor;
import Exceptions.FuncionarioException;

public class DiretorTest {


	@Test
	
	public void testeConstrutor(){
		try{
			Diretor hyldemaria = new Diretor("Hyldemaria", "12345678", "123456","21/06/1880","diretor");

		}catch(Exception e){
			fail();
		}
		
		try{
			Diretor hyldemaria = new Diretor("Hyldemaria", "12345678", "","21/06/1880","diretor");

		}catch(Exception e){
			assertEquals("Senha invalida.",e.getMessage());
		}
		
		try{
			Diretor livia = new Diretor("Livia", "", "123456","21/06/1880","diretor");

		}catch(Exception e){
			
			assertEquals("Matricula Invalida!",e.getMessage());
		}
		
		try{
			Diretor livia = new Diretor("", "12345678", "123456","21/06/1880","diretor");

		}catch(Exception e){
			
			assertEquals("Nome do funcionario nao pode ser vazio.",e.getMessage());
		}

	}
	
	@Test
	
	public void testEquals() throws FuncionarioException{
		
		Diretor jailson = new Diretor("Jailson", "12340987", "123456","21/06/1880","diretor");
		Diretor hyldemaria = new Diretor("Hyldemaria", "12345678", "456789","21/06/1880","diretor");
		Diretor livia = new Diretor("Livia", "12345678", "123456","21/06/1880","diretor");
		assertEquals(true, hyldemaria.equals(livia));
		assertEquals(false, hyldemaria.equals(jailson));
	}


}