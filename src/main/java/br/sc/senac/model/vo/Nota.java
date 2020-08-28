package br.sc.senac.model.vo;

public class Nota {

	private Pessoa pessoa;
	private Vacina vacina;
	private double valor;
	
	public Nota() {
	}
	
	public Nota(Pessoa pessoa, Vacina vacina, double valor) {
		super();
		this.pessoa = pessoa;
		this.vacina = vacina;
		this.valor = valor;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
