package br.sc.senac.model.bo;

import br.sc.senac.model.dao.InstituicaoDAO;
import br.sc.senac.model.dao.PessoaDAO;
import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;

public class PessoaBO {

	private PessoaDAO pessoaDAO = new PessoaDAO();
	private InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
	
	public Pessoa salvar(Pessoa novaPessoa) {
		// faltam mais verificações
		Instituicao instiutuicaoDoBanco = null;
		if(instituicaoDAO.jaExisteNome(novaPessoa.getInstituicao())) {
			instiutuicaoDoBanco = instituicaoDAO.pesquisarPeloNome(novaPessoa.getInstituicao().getNome());
		} else {
			instiutuicaoDoBanco = instituicaoDAO.inserir(novaPessoa.getInstituicao());
		}
		novaPessoa.setInstituicao(instiutuicaoDoBanco);
		this.pessoaDAO.inserir(novaPessoa);
		return novaPessoa;
	}

}
