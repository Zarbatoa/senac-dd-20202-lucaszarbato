package br.sc.senac.view;

import java.time.LocalDate;
import java.util.List;

import br.sc.senac.model.dao.InstituicaoDAO;
import br.sc.senac.model.dao.NotaDAO;
import br.sc.senac.model.dao.PessoaDAO;
import br.sc.senac.model.dao.VacinaDAO;
import br.sc.senac.model.vo.*;

public class Principal {

	public static void main(String[] args) {
		Instituicao ufsc = new Instituicao(1,"Universidade Federal de Santa Catarina");
		Instituicao senac = new Instituicao(1,"Serviço Nacional de Aprendizagem Comercial");
		
		Pesquisador joao = new Pesquisador(1,-1,ufsc,"João da Silva", LocalDate.of(1990, 2, 15), 'M', "11111111111");
		PublicoGeral maria = new PublicoGeral(2,-1,"Maria", LocalDate.of(1972, 10, 5),'F', "22222222222",false);
		PublicoGeral jose = new PublicoGeral(3,-1,"José", LocalDate.of(1988, 1, 10),'M', "33333333333", true);
		
		Vacina v0 = new Vacina(1,"Vacina acreana","Brasil", Vacina.ESTAGIO_TESTES,LocalDate.of(2020, 4, 22),joao);
		
		InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
		instituicaoDAO.inserir(ufsc);
		instituicaoDAO.inserir(senac);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		joao.setIdTipo(pessoaDAO.getIdTipo(Pessoa.TIPO_PESQUISADOR));
		maria.setIdTipo(pessoaDAO.getIdTipo(Pessoa.TIPO_PUBLICO_GERAL));
		jose.setIdTipo(pessoaDAO.getIdTipo(Pessoa.TIPO_VOLUNTARIO));
		
		// inserir pessoas
		pessoaDAO.inserir(joao);
		pessoaDAO.inserir(maria);
		pessoaDAO.inserir(jose);
		System.out.println("=============================");
		
		// inserir vacina
		VacinaDAO vacinaDAO = new VacinaDAO();
		vacinaDAO.inserir(v0);
		
		// inserir nota
		NotaDAO notaDAO = new NotaDAO();
		Nota n0 = new Nota(-1, maria, v0, 4);
		Nota n1 = new Nota(-1, maria, v0, 1);
		notaDAO.inserir(n0);
		notaDAO.inserir(n1);
		
		// select 1
		System.out.println(n0);
		System.out.println(n1);
		
		// alterar nota
		n0.setPessoa(jose);
		n0.setValor(2);
		notaDAO.alterar(n0);
		
		// excluir
		notaDAO.excluir(n0.getId());
		
	} 
	
}
