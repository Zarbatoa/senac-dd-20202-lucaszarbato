package br.sc.senac.view;

import java.time.LocalDate;

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

	public static void main(String[] args) {
		Instituicao ufsc = new Instituicao(1,"Universidade Federal de Santa Catarina");
		Instituicao senac = new Instituicao(1,"Serviço Nacional de Aprendizagem Comercial");
		
		Pessoa joao = new Pessoa(1,TipoPessoa.TIPO_PESQUISADOR,ufsc,"João", "Silva", LocalDate.of(1990, 2, 15), 'M', "11111111111");
		Pessoa maria = new Pessoa(2,TipoPessoa.TIPO_PUBLICO_GERAL, null, "Maria", "Pereira", LocalDate.of(1972, 10, 5),'F', "22222222222");
		Pessoa jose = new Pessoa(3,TipoPessoa.TIPO_VOLUNTARIO, null, "José", "Oswaldo", LocalDate.of(1988, 1, 10),'M', "33333333333");
		
		Vacina v0 = new Vacina(1,"Vacina acreana","Brasil", Vacina.ESTAGIO_TESTES,LocalDate.of(2020, 4, 22),joao);
		
		InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
		instituicaoDAO.inserir(ufsc);
		instituicaoDAO.inserir(senac);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
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
