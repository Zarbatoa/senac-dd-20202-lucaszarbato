package br.sc.senac.model.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LancamentoNotaDTO {
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private String nomeVacina;
	private String nomePessoa;
	private String categoria;
	private char sexo;
	
	private LocalDate dataInicioPesquisa;
	private LocalDate dataFimPesquisa;
	
	public LancamentoNotaDTO(String nomeVacina, String nomePessoa, String categoria, char sexo,
			LocalDate dataInicioPesquisa, LocalDate dataFimPesquisa) {
		super();
		this.nomeVacina = nomeVacina;
		this.nomePessoa = nomePessoa;
		this.categoria = categoria;
		this.sexo = sexo;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.dataFimPesquisa = dataFimPesquisa;
	}
	
	public LancamentoNotaDTO() {
		super();
	}

	
	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public LocalDate getDataFimPesquisa() {
		return dataFimPesquisa;
	}

	public void setDataFimPesquisa(LocalDate dataFimPesquisa) {
		this.dataFimPesquisa = dataFimPesquisa;
	}
	
	
	
	
	
	
	
	

}
