package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.Vacina;

public class RelatorioSeletor extends AbstractSeletor{
	private Character sexo;
	private LocalDate dataInicioPesquisa;
	private Vacina vacina;
	
	public RelatorioSeletor() {
		super();
	}
	
	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	
	@Override
	public boolean temFiltro() {
		if(this.temFiltroSexo()) {
			return true;
		}
		
		if(this.temFiltroDataInicioPesquisa()) {
			return true;
		}
		
		if(this.temFiltroVacina()) {
			return true;
		}
		
		return false;
	}

	public boolean temFiltroVacina() {
		return this.vacina != null;
	}

	public boolean temFiltroDataInicioPesquisa() {
		return this.dataInicioPesquisa != null;
	}

	public boolean temFiltroSexo() {
		return this.sexo != null;
	}
	
}
