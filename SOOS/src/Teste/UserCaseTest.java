package Teste;

import java.util.ArrayList;


import Sistema.Facade;
import easyaccept.EasyAcceptFacade;

public class UserCaseTest {

	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("Usercase/usecase_1.txt");
		list.add("Usercase/usecase_2.txt");
		list.add("Usercase/usecase_3.txt");
		list.add("Usercase/usecase_4.txt");
		list.add("Usercase/usecase_5.txt");
		list.add("Usercase/usecase_6.txt");
		list.add("Usercase/usecase_7.txt");
		
		Facade fachada = new Facade();
		 

			
		
		EasyAcceptFacade testefachada = new EasyAcceptFacade(fachada , list);
		
		testefachada.executeTests();
		
		System.out.println(testefachada.getCompleteResults());
		
		
	}

}
