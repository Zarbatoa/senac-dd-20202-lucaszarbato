package br.sc.senac.model.vo;

public class Nota {

	private int id;
	private Pessoa pessoa;
	private int idVacina;
	private double valor;
	
	public Nota() {
	}
	
	public Nota(int id, Pessoa pessoa, int idVacina, double valor) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.idVacina = idVacina;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getVacina() {
		return idVacina;
	}

	public void setVacina(int idVacina) {
		this.idVacina = idVacina;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
