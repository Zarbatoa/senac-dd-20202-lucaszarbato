package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.Pessoa;

public class VacinaSeletor extends AbstractSeletor {
	
	//Atributos que servirão de filtros
	private String nomeVacina;
	private Integer estagioPesquisa;
	private String paisOrigem;
	
	private Pessoa pesquisadorResponsavel;
	private LocalDate dataInicioPesquisa;
	
	public VacinaSeletor() {
		super();
	}
	
	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public Integer getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(Integer estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}
	
	
	public boolean temFiltro() {
		if (this.temFiltroDeNome()) {
			return true;
		}
		if (this.temFiltroDeEstagioDePesquisa()) {
			return true;
		}
		if (this.temFiltroDePaisOrigem()) {
			return true;
		}
		if (this.temFiltroDePesquisadorResponsavel()){
			return true;
		}
		if (this.temFiltroDeDataInicioPesquisa()) {
			return true;
		}
		return false;
	}

	public boolean temFiltroDeDataInicioPesquisa() {
		return this.dataInicioPesquisa != null;
	}

	public boolean temFiltroDePesquisadorResponsavel() {
		return this.pesquisadorResponsavel != null;
	}

	public boolean temFiltroDePaisOrigem() {
		return (this.paisOrigem != null) && (this.paisOrigem.trim().length() > 0);
	}

	public boolean temFiltroDeEstagioDePesquisa() {
		return this.estagioPesquisa != null;
	}

	public boolean temFiltroDeNome() {
		return (this.nomeVacina != null) && (this.nomeVacina.trim().length() > 0);
	}
	
}
