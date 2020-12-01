package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.Pessoa;

public class RelatorioAvaliacaoVacinaSeletor extends AbstractSeletor {
	
	//Atributos que servirão de filtros
	private Pessoa nome;
		
	//Filtragem de datas por período (início, fim)
	private LocalDate dataInicio;
	private LocalDate dataFim;
			
	public RelatorioAvaliacaoVacinaSeletor() {
		super();
	}
		
	public boolean temFiltro() {
		if ((this.nome.getNomeCompleto() != null) && (this.nome.getNomeCompleto().trim().length() > 0)) {
			return true;
		}
		if (this.dataInicio != null) {
			return true;
		}
		if (this.dataFim != null) {
			return true;
		}
		
		return false;
	}
		
	public Pessoa getNome() {
		return nome;
	}

	public void setNome(Pessoa nome) {
		this.nome = nome;
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
