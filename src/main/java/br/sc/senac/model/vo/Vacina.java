package br.sc.senac.model.vo;

import java.time.LocalDate;
import java.util.List;

public class Vacina {

	private String paisOrigem;
	private int estagioPesquisa;
	private LocalDate dataInicioPesquisa;
	private Pesquisador pesquisadorResponsavel;
	private List<Nota> notas;
	
	public Vacina() {
	}
	
	public Vacina(String paisOrigem, int estagioPesquisa, LocalDate dataInicioPesquisa,
			Pesquisador pesquisadorResponsavel, List<Nota> notas) {
		super();
		this.paisOrigem = paisOrigem;
		this.estagioPesquisa = estagioPesquisa;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.notas = notas;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public int getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(int estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public Pesquisador getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pesquisador pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	
}
