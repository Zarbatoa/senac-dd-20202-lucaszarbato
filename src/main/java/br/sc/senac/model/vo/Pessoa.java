package br.sc.senac.model.vo;

import java.time.LocalDate;

import br.sc.senac.model.Utils;

public class Pessoa {
	
	public static final String SEXO_MASCULINO = "Masculino";
	public static final String SEXO_FEMININO = "Feminino";
	
	private int id;
	private TipoPessoa tipo;
	private Instituicao instituicao;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private char sexo;
	private String cpf;
	
	public Pessoa() {
	}

	public Pessoa(int id, TipoPessoa tipo, Instituicao instituicao, String nome, String sobrenome, LocalDate dataNascimento, char sexo, String cpf) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.instituicao = instituicao;
		this.nome = nome;
		this.sobrenome = sobrenome;
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

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeCompleto() {
		return this.nome + " " + this.getSobrenome();
	}
	
	@Override
	public String toString() {
		return Utils.getNormalizedText(this.getNomeCompleto());
	}
	
	public String toStringVerboso() {
		return "[id=" + this.id + ", tipo=" + this.tipo.getDescricao()
				 + ", instituicao=" + this.instituicao + ", nome=" + this.nome + ", sobrenome=" + this.sobrenome
				 + ", data_nascimento=" + this.dataNascimento + ", sexo=" + this.sexo
				 + ", cpf=" + Utils.formatarCpf(this.cpf) + "]";
	}

}
