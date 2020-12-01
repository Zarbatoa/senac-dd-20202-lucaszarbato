package br.sc.senac.model.vo;

import java.time.LocalDate;

import br.sc.senac.model.utilidades.Constantes;

public class Vacina {

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
		return this.getNome();
	}
	
	public String toStringVerboso() {
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
			case Constantes.ESTAGIO_INICIAL:
				estagioPesquisa = Constantes.TXT_ESTAGIO_INICIAL;
				break;
			case Constantes.ESTAGIO_TESTES:
				estagioPesquisa = Constantes.TXT_ESTAGIO_TESTES;
				break;
			case Constantes.ESTAGIO_APLICACAO_EM_MASSA:
				estagioPesquisa = Constantes.TXT_ESTAGIO_APLICACAO_EM_MASSA;
				break;
			default:
		}
		
		return estagioPesquisa;
	}

	//TODO verificar se isto deveria estar aqui ou em Constantes.
	public static int getIntEstagioDePesquisa(String nomeEstagio) {
		int estagioPesquisa = -1;
		
		if(nomeEstagio != null) {
			if(nomeEstagio.equalsIgnoreCase(Constantes.TXT_ESTAGIO_INICIAL)) {
				estagioPesquisa = Constantes.ESTAGIO_INICIAL;
			} else if(nomeEstagio.equalsIgnoreCase(Constantes.TXT_ESTAGIO_TESTES)) {
				estagioPesquisa = Constantes.ESTAGIO_TESTES;
			}  else if(nomeEstagio.equalsIgnoreCase(Constantes.TXT_ESTAGIO_APLICACAO_EM_MASSA)) {
				estagioPesquisa = Constantes.ESTAGIO_APLICACAO_EM_MASSA;
			}
		}
		
		return estagioPesquisa;
	}
	
}
