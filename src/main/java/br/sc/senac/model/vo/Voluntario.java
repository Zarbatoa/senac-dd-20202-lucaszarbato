package br.sc.senac.model.vo;

import java.time.LocalDate;

public class Voluntario extends Pessoa{

	private boolean publicoGeral;

	public Voluntario() {
	}

	public Voluntario(String nome, LocalDate dataNascimento, char sexo, String cpf, boolean publicoGeral) {
		super(nome, dataNascimento, sexo, cpf);
		this.publicoGeral = publicoGeral;
	}

	public boolean isPublicoGeral() {
		return publicoGeral;
	}

	public void setPublicoGeral(boolean publicoGeral) {
		this.publicoGeral = publicoGeral;
	}
	
	
}
