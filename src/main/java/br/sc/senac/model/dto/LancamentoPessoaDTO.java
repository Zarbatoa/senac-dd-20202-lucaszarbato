package br.sc.senac.model.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LancamentoPessoaDTO {
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private char sexo;
	private String instituicao;
	private String categoria;
	private int faixaIdade;
	private int valor;
	
	private LocalDate dataInicioPesquisa;
	private LocalDate dataFinalPesquisa;
	
	public LancamentoPessoaDTO(char sexo, String instituicao, String categoria, int faixaIdade, int valor,
			LocalDate dataInicioPesquisa, LocalDate dataFinalPesquisa) {
		super();
		this.sexo = sexo;
		this.instituicao = instituicao;
		this.categoria = categoria;
		this.faixaIdade = faixaIdade;
		this.valor = valor;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.dataFinalPesquisa = dataFinalPesquisa;
	}
	
	public LancamentoPessoaDTO() {
		super();

	}
	
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getFaixaIdade() {
		return faixaIdade;
	}
	public void setFaixaIdade(int faixaIdade) {
		this.faixaIdade = faixaIdade;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}
	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}
	public LocalDate getDataFinalPesquisa() {
		return dataFinalPesquisa;
	}
	public void setDataFinalPesquisa(LocalDate dataFinalPesquisa) {
		this.dataFinalPesquisa = dataFinalPesquisa;
	}
	
	

	

}
