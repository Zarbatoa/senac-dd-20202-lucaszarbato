package br.sc.senac.model.vo;

import java.time.LocalDate;

public class Pesquisador extends Pessoa {

	private Instituicao instituicao;
	
	public Pesquisador() {
	}

	public Pesquisador(String nome, LocalDate dataNascimento, char sexo, String cpf, Instituicao instituicao) {
		super(nome, dataNascimento, sexo, cpf);
		this.instituicao = instituicao;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

}
