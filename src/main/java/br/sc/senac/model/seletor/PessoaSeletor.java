package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.TipoPessoa;

public class PessoaSeletor extends AbstractSeletor {
	
	//Atributos que servirão de filtros
	private String nome;
	private String sobrenome;
	private Character sexo;
	private String cpf;
	private LocalDate dataNascimento;
	private String nomeInstituicao; 
	private TipoPessoa tipo; 
	
	public PessoaSeletor() {
		super();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}
	

	public boolean temFiltro() {
		if (this.temFiltroDeNome()) {
			return true;
		}
		if (this.temFiltroDeSobrenome()) {
			return true;
		}
		if (this.temFiltroDeSexo()) {
			return true;
		}
		if (this.temFiltroDeCPF()) {
			return true;
		}
		if (this.temFiltroDeNomeInstituicao()) {
			return true;
		}  
		if (this.temFiltroDeTipo()) {
			return true; 
		}  
		if (this.temFiltroDeDataNascimento()) {
			return true;
		}

		return false;
	}
		
	public boolean temFiltroDeDataNascimento() {
		return this.dataNascimento != null;
	}

	public boolean temFiltroDeTipo() {
		return (this.tipo != null);
	}

	public boolean temFiltroDeNomeInstituicao() {
		return (this.nomeInstituicao != null) && (this.nomeInstituicao.trim().length()>0);
	}

	public boolean temFiltroDeCPF() {
		return (this.cpf != null) && (this.cpf.trim().length() > 0);
	}

	public boolean temFiltroDeSexo() {
		return this.sexo != null;
	}

	public boolean temFiltroDeSobrenome() {
		return (this.sobrenome != null) && (this.sobrenome.trim().length() > 0);
	}

	public boolean temFiltroDeNome() {
		return (this.nome != null) && (this.nome.trim().length() > 0);
	}

}
