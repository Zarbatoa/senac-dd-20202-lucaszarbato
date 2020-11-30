package br.sc.senac.model.bo;

import java.util.ArrayList;
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

	public String excluirPessoas(List<Integer> idsASeremExcluidos) {
		StringBuffer mensagem = new StringBuffer();
		StringBuffer msgPessoasExcluidasComSucesso = new StringBuffer();
		StringBuffer msgPessoasExcluidasSemSucesso = new StringBuffer();
		List<Pessoa> pessoasExcluidas = new ArrayList<Pessoa>();
		List<Boolean> statusPessoasExcluidas = new ArrayList<Boolean>();
		boolean nenhumaPessoaExcluida = true;
		
		for(Integer idASerExcluido : idsASeremExcluidos) {
			Pessoa pessoaExcluida = pessoaDAO.pesquisarPorId(idASerExcluido);
			if (pessoaExcluida != null) {
				statusPessoasExcluidas.add(pessoaDAO.excluir(idASerExcluido));
				pessoasExcluidas.add(pessoaExcluida);
			}
		}
		
		for(int i = 0; i < statusPessoasExcluidas.size(); i++) {
			Boolean foiExcluida = statusPessoasExcluidas.get(i);
			Pessoa pessoaExcluida = pessoasExcluidas.get(i);
			if(foiExcluida) {
				if(msgPessoasExcluidasComSucesso.length() > 0) {
					msgPessoasExcluidasComSucesso.append(", ");
				}
				msgPessoasExcluidasComSucesso.append(
						"(" + pessoaExcluida.getId() + " - " + pessoaExcluida.toString() + ")"
						);
				nenhumaPessoaExcluida = false;
			} else {
				if(msgPessoasExcluidasSemSucesso.length() > 0) {
					msgPessoasExcluidasSemSucesso.append(", ");
				}
				msgPessoasExcluidasSemSucesso.append(
						"(" + pessoaExcluida.getId() + " - " + pessoaExcluida.toString() + ")"
						);
			}
		}
		
		if(nenhumaPessoaExcluida) {
			mensagem.append("Nenhuma pessoa excluída.");
		} else {
			mensagem.append("Pessoa(s) ");
			mensagem.append(msgPessoasExcluidasComSucesso);
			mensagem.append(" excluída(s) com sucesso!\n");
			if(msgPessoasExcluidasSemSucesso.length() > 0) {
				mensagem.append("Não foi possível excluir a(s) pessoa(s) ");
				mensagem.append(msgPessoasExcluidasSemSucesso);
				mensagem.append('!');
			}
		}
		
		return mensagem.toString();
	}
	
	public void gerarPlanilhaPessoaTotalPorSexoPorPeriodo(List<Pessoa> pessoas, String caminhoEscolhido) {
		GeradorPlanilhaPessoa gerador = new GeradorPlanilhaPessoa();
		gerador.gerarPlanilhaPessoaTotalPorSexoPorPeriodo(pessoas, caminhoEscolhido);
	}
	
	public void gerarPlanilhaPessoaTotalPorSexo(List<Pessoa> pessoas, String caminhoEscolhido) {
		GeradorPlanilhaPessoa gerador = new GeradorPlanilhaPessoa();
		gerador.gerarPlanilhaPessoaTotalPorSexo(pessoas, caminhoEscolhido);
	}
	
	
}
