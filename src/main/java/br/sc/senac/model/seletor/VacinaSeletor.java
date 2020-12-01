package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;

public class VacinaSeletor extends AbstractSeletor {
	
	//Atributos que servirão de filtros
	private String nomeVacina;
	private int estagioPesquisa;
	private String paisOrigem;
	
	private Pessoa nomePesquisador; // dúvida - é assim ou String???
	private Instituicao instituicao; // dúvida - é assim ou String???
	private LocalDate dataInicioPesquisa; // dúvida - é assim ou String???
	
	public VacinaSeletor() {
		super();
	}
	
	public boolean temFiltro() {
		if ((this.nomeVacina != null) && (this.nomeVacina.trim().length() > 0)) {
			return true;
		}
		if ((this.estagioPesquisa != 0) && (this.estagioPesquisa > 0)) {
			return true;
		}
		if ((this.paisOrigem != null) && (this.paisOrigem.trim().length() > 0)) {
			return true;
		}
		if ((this.nomePesquisador.getNomeCompleto() != null) && (this.nomePesquisador.getNomeCompleto().trim().length()>0)){
			return true;
		}
		if(((this.instituicao.getNome() != null)) && (this.instituicao.getNome().trim().length()>0)){
			return true;
		}
		if (this.dataInicioPesquisa != null) {
			return true;
		}
		return false;
	}
	
	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public int getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(int estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Pessoa getNomePesquisador() {
		return nomePesquisador;
	}

	public void setNomePesquisador(Pessoa nomePesquisador) {
		this.nomePesquisador = nomePesquisador;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

}
