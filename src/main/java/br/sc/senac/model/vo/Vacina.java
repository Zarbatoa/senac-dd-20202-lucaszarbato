package br.sc.senac.model.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vacina {

	public static final int ESTAGIO_INICIAL = 1;
	public static final int ESTAGIO_TESTES = 2;
	public static final int ESTAGIO_APLICACAO_EM_MASSA = 3;
	
	private int id;
	private String nome;
	private String paisOrigem;
	private int estagioPesquisa;
	private LocalDate dataInicioPesquisa;
	private Pesquisador pesquisadorResponsavel;
	
	public Vacina() {
	}
	
	public Vacina(int id, String nome, String paisOrigem, int estagioPesquisa, LocalDate dataInicioPesquisa,
			Pesquisador pesquisadorResponsavel) {
		super();
		this.id = id;
		this.nome = nome;
		this.paisOrigem = paisOrigem;
		this.estagioPesquisa = estagioPesquisa;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public String toString() {
		String nomePesquisador = this.pesquisadorResponsavel != null ? this.pesquisadorResponsavel.getNome() : null;
		
		return "[id=" + this.getId() + ", nome=" + this.nome
				 + ", paisOrigem=" + this.paisOrigem + ", estagioPesquisa=" + this.estagioPesquisa
				 + ", dataInicioPesquisa=" + this.dataInicioPesquisa + ", pesquisadorResponsavel="
				 + nomePesquisador + "]";
	}
	
}
