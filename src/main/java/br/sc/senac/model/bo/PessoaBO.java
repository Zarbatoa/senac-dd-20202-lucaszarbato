package br.sc.senac.model.bo;

import java.util.List;
import br.sc.senac.model.Utils;
import br.sc.senac.model.dao.InstituicaoDAO;
import br.sc.senac.model.dao.PessoaDAO;
import br.sc.senac.model.exception.CpfJaCadastradoException;
import br.sc.senac.model.seletor.PessoaSeletor;
import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;

public class PessoaBO {

	private PessoaDAO pessoaDAO = new PessoaDAO();
	private InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
	
	public Pessoa salvar(Pessoa novaPessoa) throws CpfJaCadastradoException{
		Instituicao instiutuicaoDoBanco = null;
		if(instituicaoDAO.jaExisteNome(novaPessoa.getInstituicao())) {
			instiutuicaoDoBanco = instituicaoDAO.pesquisarPeloNome(novaPessoa.getInstituicao().getNome());
		} else {
			instiutuicaoDoBanco = instituicaoDAO.inserir(novaPessoa.getInstituicao());
		}
		novaPessoa.setInstituicao(instiutuicaoDoBanco);
		
		if(this.pessoaDAO.cpfJaCadastrado(novaPessoa)) {
			throw new CpfJaCadastradoException("O CPF informado (" + Utils.formatarCpf(novaPessoa.getCpf()) 
			+ ") já foi cadastrado.");
		}
		this.pessoaDAO.inserir(novaPessoa);
		
		return novaPessoa;
	}
	
	public boolean atualizar(Pessoa pessoa) throws CpfJaCadastradoException{

		if(this.pessoaDAO.cpfJaCadastrado(pessoa)) {
			throw new CpfJaCadastradoException("O CPF informado (" + pessoa.getCpf() 
			+ ") já foi cadastrado.");
		}
		return this.pessoaDAO.alterar(pessoa);
	}
	
	public Pessoa consultarPorCPF(String cpf) {
		return this.consultarPorCPF(cpf);
	}
	
	public boolean excluirPessoa(Pessoa pessoaQueSeraExcluida) {
		boolean excluiu = pessoaDAO.excluir(pessoaQueSeraExcluida.getId());
		
		return excluiu;
	}

	public List<Pessoa> pegarListaDePesquisadores() {
		return pessoaDAO.pesquisarTodosPesquisadores();
	}

	
	public List<Pessoa> listarPessoas(PessoaSeletor seletor) {
		return this.pessoaDAO.listarComSeletor(seletor);
	}

	public List<Pessoa> pegarListaDePessoas() {
		return this.pessoaDAO.pesquisarTodos();
	}
	
}
