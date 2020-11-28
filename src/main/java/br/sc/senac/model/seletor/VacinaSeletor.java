package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;

public class VacinaSeletor {
	
	//Atributos que servirão de filtros
	private String nomeVacina;
	private int estagioPesquisa;
	private String paisOrigem;
	private Pessoa nomePesquisador;
	private Instituicao instituicao;
	private LocalDate dataInicioPesquisa;
	
	//Atributos para possível paginação dos resultados (intervalo)
	private int limite;
	private int pagina;
	
	public VacinaSeletor() {
		//Default: traz os resultados sem limite e sem página
		this.limite = 0;
		this.pagina = -1;
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
		
	/**
	 * Verifica se os campos de paginacao estao preenchidos
	 *
	 * @return verdadeiro se os campos limite e pagina estao preenchidos
	 */
	public boolean temPaginacao() {
		return ((this.limite > 0) && (this.pagina > -1));
	}

	/**
	 * Calcula deslocamento (offset) a partir da pagina e do limite
	 *
	 * @return offset
	 */
	public int getOffset() {
		return (this.limite * (this.pagina - 1));
	}

	//getters e setters
	
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

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
}
