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
import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;

public class ControllerPessoa {
	/*1- Pessoas:
		* Nome, sobrenome (caso exista no modelo), sexo e CPF são obrigatórios       
		* Nome deve ter pelo menos 3 letras      
		* CPF deve ter exatamente 11 dígitos   
		* indicar o tipo de pessoa. Caso seja pesquisador, informar o nome da instituição onde ele trabalha
		* indicar se a pessoa é ou não voluntária
	* */
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
				throw new InstituicaoInvalidaException("O campo instituição deve ser preenchido para pessoas do tipo pesquisador.");
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

	public List<Pessoa> coletarListaDePesquisadores() {
		return pessoaBO.pegarListaDePesquisadores();
	}

}
 