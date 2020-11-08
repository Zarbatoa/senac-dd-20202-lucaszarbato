package br.sc.senac.view;

import java.time.LocalDate;
import java.util.List;

import br.sc.senac.model.dao.InstituicaoDAO;
import br.sc.senac.model.dao.PessoaDAO;
import br.sc.senac.model.vo.*;

public class Principal {

	public static void main(String[] args) {
		Instituicao ufsc = new Instituicao(1,"Universidade Federal de Santa Catarina");
		Instituicao senac = new Instituicao(1,"Serviço Nacional de Aprendizagem Comercial");
		
		Pesquisador joao = new Pesquisador(1,-1,ufsc,"João da Silva", LocalDate.of(1990, 2, 15), 'M', "11111111111");
		PublicoGeral maria = new PublicoGeral(2,-1,"Maria", LocalDate.of(1972, 10, 5),'F', "22222222222",false);
		PublicoGeral jose = new PublicoGeral(3,-1,"José", LocalDate.of(1988, 1, 10),'M', "33333333333", true);
		
		Vacina v0 = new Vacina(1,"Brasil", Vacina.ESTAGIO_TESTES,LocalDate.of(2020, 4, 22),joao);
		
		InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
		instituicaoDAO.inserir(ufsc);
		instituicaoDAO.inserir(senac);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		joao.setIdTipo(pessoaDAO.getIdTipo(Pessoa.TIPO_PESQUISADOR));
		maria.setIdTipo(pessoaDAO.getIdTipo(Pessoa.TIPO_PUBLICO_GERAL));
		jose.setIdTipo(pessoaDAO.getIdTipo(Pessoa.TIPO_VOLUNTARIO));
		
		
		pessoaDAO.inserir(joao);
		pessoaDAO.inserir(maria);
		pessoaDAO.inserir(jose);
		
		
//		joao.setNome("Rosesclaudio");
//		joao.setDataNascimento(LocalDate.of(1991, 12, 3));
//		joao.setSexo('F');
//		joao.setCpf("12345678912");
		joao.setInstituicao(senac);
		
		pessoaDAO.alterar(joao);
		
		pessoaDAO.excluir(joao.getId());

	} 
	
}
