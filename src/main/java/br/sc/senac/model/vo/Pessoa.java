package br.sc.senac.model.vo;

import java.time.LocalDate;

public abstract class Pessoa {

	private String nome;
	private LocalDate dataNascimento;
	private char sexo;
	private String cpf;
	
	public Pessoa() {
	}

	public Pessoa(String nome, LocalDate dataNascimento, char sexo, String cpf) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
