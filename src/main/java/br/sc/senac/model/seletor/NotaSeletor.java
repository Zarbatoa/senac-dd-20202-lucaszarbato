package br.sc.senac.model.seletor;

import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;

public class NotaSeletor extends AbstractSeletor {
	
	//Atributos que servirão de filtros
	private Vacina vacina;
	private Pessoa pessoa;
	private Double valorInicial;
	private Double valorFinal;
	
	public NotaSeletor() {
		super();
	}
	
	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Double getValorInicial() {
		return valorInicial;
	}
	
	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	
	public boolean temFiltro() {
		if (temFiltroDeVacina()) {
			return true;
		}
		if (temFiltroDePessoa()) {
			return true;
		}
		if (temFiltroDeValorInicial()) {
			return true; 
		}
		if (temFiltroDeValorFinal()) {
			return true;
		}
		return false;
	}

	public boolean temFiltroDeValorInicial() {
		return this.valorInicial != null;
	}

	public boolean temFiltroDeValorFinal() {
		return this.valorFinal != null;
	}
	
	public boolean temFiltroDePessoa() {
		return this.pessoa != null;
	}

	public boolean temFiltroDeVacina() {
		return this.vacina != null;
	}

}
