package br.sc.senac.model.vo;

import java.time.LocalDate;

public class Pessoa {

	public static final String TIPO_PESQUISADOR = "PESQUISADOR";
	public static final String TIPO_PUBLICO_GERAL = "PUBLICO_GERAL";
	public static final String TIPO_VOLUNTARIO = "VOLUNTARIO";
	
	private int id;
	private int idTipo;
	private Instituicao instituicao;
	private String nome;
	private LocalDate dataNascimento;
	private char sexo;
	private String cpf;
	
	public Pessoa() {
	}

	public Pessoa(int id, int idTipo, Instituicao instituicao, String nome, LocalDate dataNascimento, char sexo, String cpf) {
		super();
		this.id = id;
		this.idTipo = idTipo;
		this.instituicao = instituicao;
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

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	
	@Override
	public String toString() {
		return "[id=" + this.id + ", idTipo=" + this.idTipo
				 + ", instituicao=" + this.instituicao + ", nome=" + this.nome
				 + ", data_nascimento=" + this.dataNascimento + ", sexo=" + this.sexo
				 + ", cpf=" + formatarCpf() + "]";
	}

	private String formatarCpf() {
		String formattedCpf = null;
		if (this.cpf != null) {
			formattedCpf = this.cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
		}
		return formattedCpf;
	}
	
}
