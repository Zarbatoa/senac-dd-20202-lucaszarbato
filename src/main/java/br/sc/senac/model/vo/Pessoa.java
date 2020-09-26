package br.sc.senac.model.vo;

import java.time.LocalDate;

public abstract class Pessoa {

	public static final String TIPO_PESQUISADOR = "PESQUISADOR";
	public static final String TIPO_PUBLICO_GERAL = "PUBLICO_GERAL";
	public static final String TIPO_VOLUNTARIO = "VOLUNTARIO";
	
	private int id;
	private String nome;
	private LocalDate dataNascimento;
	private char sexo;
	private String cpf;
	
	public Pessoa() {
	}

	public Pessoa(int id, String nome, LocalDate dataNascimento, char sexo, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
