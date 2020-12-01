package br.sc.senac.model.seletor;

public class AvaliacaoVacinaSeletor extends AbstractSeletor {
	
	//Atributos que servirão de filtros
	private String nomeVacina;
	private String nomePessoa;
	private double nota;
	
	public AvaliacaoVacinaSeletor() {
		super();
	}
	
	public boolean temFiltro() {
		if ((this.nomeVacina != null) && (this.nomeVacina.trim().length() > 0)) {
			return true;
		}
		if ((this.nomePessoa != null) && (this.nomePessoa.trim().length() > 0)) {
			return true;
		}
		if ((this.nota != 0) && (this.nota > 0)) {
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

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public double getNota() {
		return nota;
	}
	
	public void setNota(double nota) {
		this.nota = nota;
	}

}
