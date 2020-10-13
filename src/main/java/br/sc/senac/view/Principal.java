package br.sc.senac.view;

import java.time.LocalDate;

import br.sc.senac.model.dao.InstituicaoDAO;
import br.sc.senac.model.vo.*;

public class Principal {

	public static void main(String[] args) {
		Instituicao ufsc = new Instituicao(1,"Universidade Federal de Santa Catarina");
		
		Pesquisador joao = new Pesquisador(1,"João da Silva", LocalDate.of(1990, 2, 15), 'M', "111.111.111-11",ufsc);
		PublicoGeral maria = new PublicoGeral(2,"Maria", LocalDate.of(1972, 10, 5),'F', "222.222.222-22",false);
		PublicoGeral jose = new PublicoGeral(3,"José", LocalDate.of(1988, 1, 10),'M', "333.333.333-33", true);
		
		Vacina v0 = new Vacina(1,"Brasil", Vacina.ESTAGIO_TESTES,LocalDate.of(2020, 4, 22),joao);
		
		
		InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
		instituicaoDAO.inserir(ufsc);
	}
	
}
