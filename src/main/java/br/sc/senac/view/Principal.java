package br.sc.senac.view;

import java.time.LocalDate;

import br.sc.senac.model.vo.*;

public class Principal {

	public static void main(String[] args) {
		
		Pesquisador joao = new Pesquisador("João da Silva", LocalDate.of(1990, 2, 15), 'M', "111.111.111-11");
		Voluntario maria = new Voluntario("Maria", LocalDate.of(1972, 10, 5),'F', "222.222.222-22",false);
		Voluntario jose = new Voluntario("José", LocalDate.of(1988, 1, 10),'M', "333.333.333-33", true);
		
		Vacina v0 = new Vacina("Brasil", Vacina.ESTAGIO_TESTES,LocalDate.of(2020, 4, 22),joao);
		
	}
	
}
