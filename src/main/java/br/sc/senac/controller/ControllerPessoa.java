package br.sc.senac.controller;

import java.time.LocalDate;
import java.util.List;

import br.sc.senac.model.bo.PessoaBO;
import br.sc.senac.model.exception.CpfInvalidoException;
import br.sc.senac.model.exception.CpfJaCadastradoException;
import br.sc.senac.model.exception.DataNascimentoInvalidaException;
import br.sc.senac.model.exception.InstituicaoInvalidaException;
import br.sc.senac.model.exception.NomeInvalidoException;
import br.sc.senac.model.exception.SobrenomeInvalidoException;
import br.sc.senac.model.seletor.PessoaSeletor;
import br.sc.senac.model.utilidades.Constantes;
import br.sc.senac.model.utilidades.StatusMensagem;
import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;


public class ControllerPessoa {

	PessoaBO pessoaBO = new PessoaBO();
	
	public String salvar(Pessoa novaPessoa) {
		String mensagem = "";
		
		try {
			this.validarPessoa(novaPessoa);
			novaPessoa = pessoaBO.salvar(novaPessoa);
			mensagem = "Pessoa salva com sucesso! Id gerado: " + novaPessoa.getId();
		} catch(CpfInvalidoException
				| SobrenomeInvalidoException
				| NomeInvalidoException
				| CpfJaCadastradoException
				| InstituicaoInvalidaException
				| DataNascimentoInvalidaException e) {
			mensagem = e.getMessage();
		}
		
		return mensagem;
	}

	private void validarPessoa(Pessoa novaPessoa)
			throws CpfInvalidoException, SobrenomeInvalidoException,
			NomeInvalidoException, InstituicaoInvalidaException,
			CpfJaCadastradoException, DataNascimentoInvalidaException  {
		this.validarNome(novaPessoa.getNome());
		this.validarSobrenome(novaPessoa.getSobrenome());
		this.validarCpf(novaPessoa.getCpf());
		this.validarInstituicao(novaPessoa);
		this.validarDataNascimento(novaPessoa.getDataNascimento());
	}

	private void validarDataNascimento(LocalDate dataNascimento) throws DataNascimentoInvalidaException {
		if(dataNascimento == null) {
			throw new DataNascimentoInvalidaException("Data nascimento precisa ser preenchida");
		}
	}

	private void validarInstituicao(Pessoa novaPessoa) throws InstituicaoInvalidaException {
		if(novaPessoa.getTipo() == Constantes.TIPO_PESQUISADOR) {
			Instituicao instituicao = novaPessoa.getInstituicao();
			if(instituicao.getNome() == null || instituicao.getNome().isEmpty()) {
				throw new InstituicaoInvalidaException("O campo instituição deve ser preenchido para pessoas do tipo pesquisador.");
			}
		} else {
			novaPessoa.setInstituicao(null);
		}
	}

	private void validarCpf(String cpf) throws CpfInvalidoException {
		if(cpf == null || cpf.isEmpty() ||
				cpf.length() != 11) {
			throw new CpfInvalidoException("O Campo CPF deve ser preenchido completamente!");
		}
	}

	private void validarSobrenome(String sobrenome) throws SobrenomeInvalidoException {
		if(sobrenome == null || sobrenome.isEmpty()) {
			throw new SobrenomeInvalidoException("O campo sobrenome é obrigatório, ele deve ser preenchido.");
		}
		
		if(sobrenome.length() < 3) {
			throw new SobrenomeInvalidoException("O campo sobrenome deve ter pelo menos 3 letras.");
		}
	}
	
	private void validarNome(String nome) throws NomeInvalidoException {
		if(nome == null || nome.isEmpty()) {
			throw new NomeInvalidoException("O campo nome é obrigatório, ele deve ser preenchido.");
		}
		
		if(nome.length() < 3) {
			throw new NomeInvalidoException("O campo nome deve ter pelo menos 3 letras.");
		}
	}
	
	public List<Pessoa> listarPessoas(PessoaSeletor seletor) {
		//tratar sexo, cpf e tipo
		if (seletor.getSexo() != null) {
			if(seletor.getSexo() != Constantes.SEXO_MASCULINO.charAt(0)
					  && seletor.getSexo() != Constantes.SEXO_FEMININO.charAt(0)) {
				seletor.setSexo(null);
			}
		}
		
		try {
			validarCpf(seletor.getCpf());
		} catch (CpfInvalidoException e) {
			seletor.setCpf(null);
		}
		
		if(seletor.getTipo() == Constantes.OPCAO_CATEGORIA_TODAS) {
			seletor.setTipo(null);
		}
		
		return this.pessoaBO.listarPessoas(seletor);
	}
	

	public List<Pessoa> coletarListaDePesquisadores() {
		return pessoaBO.pegarListaDePesquisadores();
	}

	public List<Pessoa> coletarTodasPessoas() {
		return pessoaBO.pegarListaDePessoas();
	}

	public String excluir(List<Integer> idsASeremExcluidos) {
		return pessoaBO.excluirPessoas(idsASeremExcluidos);
	}
	
	public void gerarRelatorioPessoaTotalPorSexo(List<Pessoa> pessoas, String caminhoEscolhido, String tipoRelatorio) {
		if(tipoRelatorio.equals(Constantes.TIPO_RELATORIO_XLS)){
			pessoaBO.gerarPlanilhaPessoaTotalPorSexo(pessoas, caminhoEscolhido);
		}
	}
	
	public void gerarRelatorioPessoaTotalPorSexoPorPeriodo(List<Pessoa> pessoas, String caminhoEscolhido, String tipoRelatorio) {
		if(tipoRelatorio.equals(Constantes.TIPO_RELATORIO_XLS)){
			pessoaBO.gerarPlanilhaPessoaTotalPorSexoPorPeriodo(pessoas, caminhoEscolhido);
		}
	}

	public StatusMensagem atualizar(Pessoa pessoaAlterada) {
		StatusMensagem statusMensagem = new StatusMensagem();
		
		try {
			this.validarPessoa(pessoaAlterada);
			statusMensagem.setStatus(pessoaBO.atualizar(pessoaAlterada));
			if (statusMensagem.getStatus()) {
				statusMensagem.setMensagem("Pessoa atualizada com sucesso!");
			} else {
				statusMensagem.setMensagem("Não foi possível atualizar esta pessoa!");
			}
		} catch(CpfInvalidoException
				| SobrenomeInvalidoException
				| NomeInvalidoException
				| CpfJaCadastradoException
				| InstituicaoInvalidaException
				| DataNascimentoInvalidaException e) {
			statusMensagem.setMensagem(e.getMessage());
			statusMensagem.setStatus(false);
		}
		
		return statusMensagem;
	}

}
 