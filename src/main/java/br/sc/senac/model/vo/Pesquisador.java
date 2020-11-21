package br.sc.senac.model.vo;

import java.time.LocalDate;

public class Pesquisador extends Pessoa {

	public Pesquisador() {
	}

	public Pesquisador(int id, int idTipo,  Instituicao instituicao, String nome, String sobrenome, LocalDate dataNascimento, char sexo, String cpf) {
		super(id, idTipo, instituicao, nome, sobrenome, dataNascimento, sexo, cpf);
	}

}
