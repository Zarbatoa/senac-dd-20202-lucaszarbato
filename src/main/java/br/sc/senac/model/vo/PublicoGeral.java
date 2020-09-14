package br.sc.senac.model.vo;

import java.time.LocalDate;

public class PublicoGeral extends Pessoa{

	private boolean voluntario;

	public PublicoGeral() {
	}

	public PublicoGeral(String nome, LocalDate dataNascimento, char sexo, String cpf, boolean voluntario) {
		super(nome, dataNascimento, sexo, cpf);
		this.voluntario = voluntario;
	}

	public boolean isVoluntario() {
		return voluntario;
	}

	public void setVoluntario(boolean voluntario) {
		this.voluntario = voluntario;
	}
	
	
}
