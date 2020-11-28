package br.sc.senac.model.seletor;

import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;

public class AvaliacaoVacinaSeletor {
	
	//Atributos que servirão de filtros
	private Vacina nomeVacina;
	private Pessoa nomePessoa;
	private double nota;
	
	//Atributos para possível paginação dos resultados (intervalo)
	private int limite;
	private int pagina;
	
	public AvaliacaoVacinaSeletor() {
		//Default: traz os resultados sem limite e sem página
		this.limite = 0;
		this.pagina = -1;
	}
	
	public boolean temFiltro() {
		if ((this.nomeVacina.getNome() != null) && (this.nomeVacina.getNome().trim().length() > 0)) {
			return true;
		}
		if ((this.nomePessoa.getNomeCompleto() != null) && (this.nomePessoa.getNomeCompleto().trim().length() > 0)) {
			return true;
		}
		if ((this.nota != 0) && (this.nota > 0)) {
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
	
	public Vacina getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(Vacina nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public Pessoa getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(Pessoa nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
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
