package br.sc.senac.controller;

import java.util.List;

import br.sc.senac.model.bo.NotaBO;
import br.sc.senac.model.exception.PessoaInvalidaException;
import br.sc.senac.model.exception.VacinaInvalidaException;
import br.sc.senac.model.exception.ValorInvalidoException;
import br.sc.senac.model.seletor.AvaliacaoVacinaSeletor;
import br.sc.senac.model.seletor.PessoaSeletor;
import br.sc.senac.model.vo.Nota;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;

public class ControllerNota {

	private NotaBO notaBO = new NotaBO();
	
	public String salvar(Nota novaNota) {
		String mensagem = "";
		
		try {
			this.validarNota(novaNota);
			novaNota = notaBO.salvar(novaNota);
			mensagem = "Nota salva com sucesso! Id gerado: " + novaNota.getId();
		} catch(PessoaInvalidaException
				| VacinaInvalidaException
				| ValorInvalidoException e) {
			mensagem = e.getMessage();
		}
		
		return mensagem;
	}

	private void validarNota(Nota novaNota)
			throws PessoaInvalidaException, VacinaInvalidaException,
			ValorInvalidoException {
		this.validarPessoa(novaNota.getPessoa());
		this.validarVacina(novaNota.getVacina());
		this.validarValor(novaNota.getValor());
	}

	private void validarValor(Double valor) throws ValorInvalidoException {
		if (valor == null) {
			throw new ValorInvalidoException("Campo nota é obrigatório, preencha ele por favor.");
		}
	}

	private void validarVacina(Vacina vacina) throws VacinaInvalidaException {
		if (vacina == null) {
			throw new VacinaInvalidaException("Campo vacina é obrigatório, cadastre uma vacina antes.");
		}
	}

	private void validarPessoa(Pessoa pessoa) throws PessoaInvalidaException {
		if (pessoa == null) {
			throw new PessoaInvalidaException("Campo pessoa testada é obrigatório, cadastre uma pessoa antes.");
		}
	}
	
	public List<Nota> listarNotas(AvaliacaoVacinaSeletor seletor) {
		return notaBO.listarNotas(seletor);
	}

}
