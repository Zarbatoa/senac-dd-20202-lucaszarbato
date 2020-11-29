package br.sc.senac.model.vo;

import java.time.LocalDate;

public class Vacina {

	public static final int ESTAGIO_INICIAL = 1;
	public static final int ESTAGIO_TESTES = 2;
	public static final int ESTAGIO_APLICACAO_EM_MASSA = 3;
	public static final String TXT_ESTAGIO_INICIAL = "Estágio Inicial";
	public static final String TXT_ESTAGIO_TESTES = "Estágio de Testes";
	public static final String TXT_ESTAGIO_APLICACAO_EM_MASSA = "Estágio de Aplicação em Massa";
	
	private int id;
	private String nome;
	private String paisOrigem;
	private int estagioPesquisa;
	private LocalDate dataInicioPesquisa;
	private Pessoa pesquisadorResponsavel;
	
	public Vacina() {
	}
	
	public Vacina(int id, String nome, String paisOrigem, int estagioPesquisa, LocalDate dataInicioPesquisa,
			Pessoa pesquisadorResponsavel) {
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

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
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
		return "[id=" + this.getId()
					+ ", nome=" + this.nome
					+ ", paisOrigem=" + this.paisOrigem
					+ ", estagioPesquisa=" + getStringEstagioDePesquisa()
					+ ", dataInicioPesquisa=" + this.dataInicioPesquisa
					+ ", pesquisadorResponsavel=" + this.pesquisadorResponsavel + "]";
	}

	private String getStringEstagioDePesquisa() {
		String estagioPesquisa = null;
		
		switch (this.estagioPesquisa) {
			case Vacina.ESTAGIO_INICIAL:
				estagioPesquisa = TXT_ESTAGIO_INICIAL;
				break;
			case Vacina.ESTAGIO_TESTES:
				estagioPesquisa = TXT_ESTAGIO_TESTES;
				break;
			case Vacina.ESTAGIO_APLICACAO_EM_MASSA:
				estagioPesquisa = TXT_ESTAGIO_APLICACAO_EM_MASSA;
				break;
			default:
		}
		
		return estagioPesquisa;
	}

	public static int getIntEstagioDePesquisa(String nomeEstagio) {
		int estagioPesquisa = -1;
		
		if(nomeEstagio != null) {
			if(nomeEstagio.equalsIgnoreCase(TXT_ESTAGIO_INICIAL)) {
				estagioPesquisa = ESTAGIO_INICIAL;
			} else if(nomeEstagio.equalsIgnoreCase(TXT_ESTAGIO_TESTES)) {
				estagioPesquisa = ESTAGIO_TESTES;
			}  else if(nomeEstagio.equalsIgnoreCase(TXT_ESTAGIO_APLICACAO_EM_MASSA)) {
				estagioPesquisa = ESTAGIO_APLICACAO_EM_MASSA;
			}
		}
		
		return estagioPesquisa;
	}
	
	public static String[] getEstagiosDeVacina() {
		return new String[] {TXT_ESTAGIO_INICIAL, TXT_ESTAGIO_TESTES, TXT_ESTAGIO_APLICACAO_EM_MASSA};
	}
	
}
