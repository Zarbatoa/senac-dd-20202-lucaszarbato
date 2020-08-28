package br.sc.senac.model.vo;

import java.time.LocalDate;

public class Pesquisador extends Pessoa {

	public Pesquisador() {
	}

	public Pesquisador(String nome, LocalDate dataNascimento, char sexo, String cpf) {
		super(nome, dataNascimento, sexo, cpf);
	}

}
