package br.sc.senac.view;

import java.time.LocalDate;
import java.util.List;

import br.sc.senac.model.dao.InstituicaoDAO;
import br.sc.senac.model.dao.NotaDAO;
import br.sc.senac.model.dao.PessoaDAO;
import br.sc.senac.model.dao.VacinaDAO;
import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Nota;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;
import br.sc.senac.model.vo.Vacina;

public class Principal {

	/*
	 * 1- Pessoas:
			* Nome, sobrenome (caso exista no modelo), sexo e CPF são obrigatórios       
			* Nome deve ter pelo menos 3 letras      
			* CPF deve ter exatamente 11 dígitos   
			* indicar o tipo de pessoa. Caso seja pesquisador, informar o nome da instituição onde ele trabalha
			* indicar se a pessoa é ou não voluntária
			 
		2- Vacinas
			* país de origem, estágio da pesquisa (1-inicial, 2-testes, 3-aplicação em massa), data de início da pesquisa, nome do pesquisador responsável
			* nome do país e do pesquisador deve possuir no mínimo 3 letras
			* converter data no formado "dd/MM/YYYY" para LocalDate
			* todos os campos são obrigatórios

		3- Ao clicar em "Salvar/Cadastrar" em cada uma das telas deve:
			* Chamar respectivo controller, passando os dados da tela em um objeto de entidade
			* O controller deve devolver uma String contendo a mensagem de sucesso/erro/validação
			* Mostrar a mensagem devolvida pelo controller em um JOptionPane, chamado pela tela original criada.
	 * */
	
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
		
		//inserir notas
		NotaDAO notaDAO = new NotaDAO();
		Nota n0 = new Nota(1,joao,v0,3);
		Nota n1 = new Nota(2,joao,v1,2);
		Nota n2 = new Nota(3,joao,v2,4);
		
		Nota n3 = new Nota(4,maria,v0,1);
		Nota n4 = new Nota(5,maria,v1,2);
		Nota n5 = new Nota(6,maria,v2,5);
		
		Nota n6 = new Nota(7,jose,v0,4);
		Nota n7 = new Nota(8,jose,v1,3);
		Nota n8 = new Nota(9,jose,v2,3);
		
		Nota n9 = new Nota(10,vladmir,v0,2);
		Nota n10 = new Nota(11,vladmir,v1,1);
		Nota n11 = new Nota(12,vladmir,v2,5);
		
		notaDAO.inserir(n0);
		notaDAO.inserir(n1);
		notaDAO.inserir(n2);
		
		notaDAO.inserir(n3);
		notaDAO.inserir(n4);
		notaDAO.inserir(n5);
		
		notaDAO.inserir(n6);
		notaDAO.inserir(n7);
		notaDAO.inserir(n8);
		
		notaDAO.inserir(n9);
		notaDAO.inserir(n10);
		notaDAO.inserir(n11);
		
		// select 1
		List<Nota> notas1 = notaDAO.pesquisarTodos();
		for (Nota nota : notas1) {
			System.out.println(nota);
		}
		System.out.println("=============================");
		
		// alterar
		n6.setVacina(v2);
		n7.setValor(1);
		n8.setVacina(v0);
		
		notaDAO.alterar(n6);
		notaDAO.alterar(n7);
		notaDAO.alterar(n8);
		
		n0.setPessoa(maria);
		n1.setPessoa(maria);
		n2.setPessoa(maria);
		n3.setPessoa(joao);
		n4.setPessoa(joao);
		n5.setPessoa(joao);
		
		notaDAO.alterar(n0);
		notaDAO.alterar(n1);
		notaDAO.alterar(n2);
		notaDAO.alterar(n3);
		notaDAO.alterar(n4);
		notaDAO.alterar(n5);
		
		// select 2
		List<Nota> notas2 = notaDAO.pesquisarTodos();
		for (Nota nota : notas2) {
			System.out.println(nota);
		}
		System.out.println("=============================");
		
		// excluir
		notaDAO.excluir(n0.getId());
		notaDAO.excluir(n4.getId());
		notaDAO.excluir(n8.getId());
		notaDAO.excluir(n9.getId());
		notaDAO.excluir(n9.getId());
		
		// select 3
		List<Nota> notas3 = notaDAO.pesquisarTodos();
		for (Nota nota : notas3) {
			System.out.println(nota);
		}
		System.out.println("=============================");
		
		// select 4 por id
		System.out.println(notaDAO.pesquisarPorId(n11.getId()));
		
	} 
	
}
