package br.sc.senac.model.seletor;

import java.time.LocalDate;

public class RelatorioPessoaSeletor extends AbstractSeletor {
	
	//Filtragem de datas por período (início, fim)
	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	public RelatorioPessoaSeletor() {
		super();
	}
	
	public boolean temFiltro() {
		if (this.dataInicio != null) {
			return true;
		}
		if (this.dataFim != null) {
			return true;
		}
		return false;
	}
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
}
