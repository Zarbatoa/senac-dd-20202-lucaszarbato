package br.sc.senac.view;

import java.time.LocalDate;
import java.util.List;

import br.sc.senac.model.dao.InstituicaoDAO;
import br.sc.senac.model.dao.PessoaDAO;
import br.sc.senac.model.dao.TipoPessoaDAO;
import br.sc.senac.model.dao.VacinaDAO;
import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;
import br.sc.senac.model.vo.Vacina;

public class Principal {

	public static void main(String[] args) {
		Instituicao ufsc = new Instituicao(1,"Universidade Federal de Santa Catarina");
		Instituicao senac = new Instituicao(1,"Serviço Nacional de Aprendizagem Comercial");
		
		Pessoa joao = new Pessoa(1,TipoPessoa.TIPO_PESQUISADOR,ufsc,"João", "Silva", LocalDate.of(1990, 2, 15), 'M', "11111111111");
		Pessoa maria = new Pessoa(2,TipoPessoa.TIPO_PUBLICO_GERAL, null, "Maria", "Pereira", LocalDate.of(1972, 10, 5),'F', "22222222222");
		Pessoa jose = new Pessoa(3,TipoPessoa.TIPO_VOLUNTARIO, null, "José", "Oswaldo", LocalDate.of(1988, 1, 10),'M', "33333333333");
		Pessoa vladmir = new Pessoa(4,TipoPessoa.TIPO_PESQUISADOR,senac,"Vladmir", "Oslov", LocalDate.of(1981, 5, 24), 'M', "44444444444");
		
		Vacina v0 = new Vacina(1,"Vacina Acreana","Brasil", Vacina.ESTAGIO_TESTES,LocalDate.of(2020, 4, 22),joao);
		Vacina v1 = new Vacina(1,"Vacina Cata Vento","Brasil", Vacina.ESTAGIO_INICIAL,LocalDate.of(2020, 6, 1),joao);
		Vacina v2 = new Vacina(1,"Vacina Orlova","Russia", Vacina.ESTAGIO_APLICACAO_EM_MASSA,LocalDate.of(2020, 5, 13),vladmir);
		
		InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
		instituicaoDAO.inserir(ufsc);
		instituicaoDAO.inserir(senac);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		// inserir pessoas
		pessoaDAO.inserir(joao);
		pessoaDAO.inserir(maria);
		pessoaDAO.inserir(jose);
		pessoaDAO.inserir(vladmir);
		System.out.println("=============================");
		
		// inserir vacina
		VacinaDAO vacinaDAO = new VacinaDAO();
		vacinaDAO.inserir(v0);
		vacinaDAO.inserir(v1);
		vacinaDAO.inserir(v2);
		
		// TipoPessoaDAO:
		TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO();
		TipoPessoa t0 = new TipoPessoa(1,"ENFERMEIRA");
		TipoPessoa t1 = new TipoPessoa(1,"MILITAR");
		TipoPessoa t2 = new TipoPessoa(1,"COBAIA");
		
		// inserir
		tipoPessoaDAO.inserir(t0);
		tipoPessoaDAO.inserir(t1);
		tipoPessoaDAO.inserir(t2);
		
		// select 1
		List<TipoPessoa> tipos1 = tipoPessoaDAO.pesquisarTodos();
		for (TipoPessoa tipoPessoa : tipos1) {
			System.out.println(tipoPessoa);
		}
		System.out.println("=============================");
		
		// alterar
		t1.setDescricao("PROFICIONAL_MILITAR");
		t2.setDescricao("COBAIA_ANIMAL");
		tipoPessoaDAO.alterar(t1);
		tipoPessoaDAO.alterar(t2);
		
		// select 2
		List<TipoPessoa> tipos2 = tipoPessoaDAO.pesquisarTodos();
		for (TipoPessoa tipoPessoa : tipos2) {
			System.out.println(tipoPessoa);
		}
		System.out.println("=============================");
		
		// excluir
		tipoPessoaDAO.excluir(t1.getId());
		
		// select 3
		List<TipoPessoa> tipos3 = tipoPessoaDAO.pesquisarTodos();
		for (TipoPessoa tipoPessoa : tipos3) {
			System.out.println(tipoPessoa);
		}
		System.out.println("=============================");
		
		// select 4
		System.out.println(tipoPessoaDAO.pesquisarPorId(t2.getId()));
		
	} 
	
}
