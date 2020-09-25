package br.sc.senac.model.vo;

import java.time.LocalDate;

public class Pesquisador extends Pessoa {

	private Instituicao instituicao;
	
	public Pesquisador() {
	}

	public Pesquisador(int id, String nome, LocalDate dataNascimento, char sexo, String cpf, Instituicao instituicao) {
		super(id, nome, dataNascimento, sexo, cpf);
		this.instituicao = instituicao;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

}
