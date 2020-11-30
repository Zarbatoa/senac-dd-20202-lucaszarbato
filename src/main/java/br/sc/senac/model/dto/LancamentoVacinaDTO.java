package br.sc.senac.model.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LancamentoVacinaDTO {
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private String nomeVacina;
	private String paisDeOrigem;
	private String nomePesquisador;
	private String instituicao;
	private LocalDate periodoPesquisa;
	private int valor;
	
	public LancamentoVacinaDTO(String nomeVacina, String paisDeOrigem, String nomePesquisador, String instituicao,
			LocalDate periodoPesquisa, int valor) {
		super();
		this.nomeVacina = nomeVacina;
		this.paisDeOrigem = paisDeOrigem;
		this.nomePesquisador = nomePesquisador;
		this.instituicao = instituicao;
		this.periodoPesquisa = periodoPesquisa;
		this.valor = valor;
	}

	public LancamentoVacinaDTO() {
		super();
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public String getPaisDeOrigem() {
		return paisDeOrigem;
	}

	public void setPaisDeOrigem(String paisDeOrigem) {
		this.paisDeOrigem = paisDeOrigem;
	}

	public String getNomePesquisador() {
		return nomePesquisador;
	}

	public void setNomePesquisador(String nomePesquisador) {
		this.nomePesquisador = nomePesquisador;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public LocalDate getPeriodoPesquisa() {
		return periodoPesquisa;
	}

	public void setPeriodoPesquisa(LocalDate periodoPesquisa) {
		this.periodoPesquisa = periodoPesquisa;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	
	
	
	
	
	
	


}
