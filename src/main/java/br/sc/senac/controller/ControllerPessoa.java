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
import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;
import br.sc.senac.model.vo.Vacina;
import br.sc.senac.view.TelaGerenciamentoDePessoas;


public class ControllerPessoa {
	/*1- Pessoas:
		* Nome, sobrenome (caso exista no modelo), sexo e CPF s�o obrigat�rios       
		* Nome deve ter pelo menos 3 letras      
		* CPF deve ter exatamente 11 d�gitos   
		* indicar o tipo de pessoa. Caso seja pesquisador, informar o nome da institui��o onde ele trabalha
		* indicar se a pessoa � ou n�o volunt�ria
	* */
	
	public static final String TIPO_RELATORIO_XLS = "xls"; 
	
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
		if(novaPessoa.getTipo() == TipoPessoa.TIPO_PESQUISADOR) {
			Instituicao instituicao = novaPessoa.getInstituicao();
			if(instituicao.getNome() == null || instituicao.getNome().isEmpty()) {
				throw new InstituicaoInvalidaException("O campo institui��o deve ser preenchido para pessoas do tipo pesquisador.");
			}
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
			throw new SobrenomeInvalidoException("O campo sobrenome � obrigat�rio, ele deve ser preenchido.");
		}
		
		if(sobrenome.length() < 3) {
			throw new SobrenomeInvalidoException("O campo sobrenome deve ter pelo menos 3 letras.");
		}
	}
	
	private void validarNome(String nome) throws NomeInvalidoException {
		if(nome == null || nome.isEmpty()) {
			throw new NomeInvalidoException("O campo nome � obrigat�rio, ele deve ser preenchido.");
		}
		
		if(nome.length() < 3) {
			throw new NomeInvalidoException("O campo nome deve ter pelo menos 3 letras.");
		}
	}
	
	public List<Pessoa> listarPessoas(PessoaSeletor seletor) {
		//tratar sexo, cpf e tipo
		if (seletor.getSexo() != null) {
			if(seletor.getSexo() != Pessoa.SEXO_MASCULINO.charAt(0)
					  && seletor.getSexo() != Pessoa.SEXO_FEMININO.charAt(0)) {
				seletor.setSexo(null);
			}
		}
		
		try {
			validarCpf(seletor.getCpf());
		} catch (CpfInvalidoException e) {
			seletor.setCpf(null);
		}
		
		if(seletor.getTipo() == TelaGerenciamentoDePessoas.OPCAO_CATEGORIA_TODAS) {
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
		if(tipoRelatorio.equals(TIPO_RELATORIO_XLS)){
			pessoaBO.gerarPlanilhaPessoaTotalPorSexo(pessoas, caminhoEscolhido);
		}
	}
	
	public void gerarRelatorioPessoaTotalPorSexoPorPeriodo(List<Pessoa> pessoas, String caminhoEscolhido, String tipoRelatorio) {
		if(tipoRelatorio.equals(TIPO_RELATORIO_XLS)){
			pessoaBO.gerarPlanilhaPessoaTotalPorSexoPorPeriodo(pessoas, caminhoEscolhido);
		}
	}

}
 