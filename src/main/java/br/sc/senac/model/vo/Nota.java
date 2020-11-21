package br.sc.senac.model.vo;

public class Nota {

	private int id;
	private Pessoa pessoa;
	private Vacina vacina;
	private double valor; // de 1 (péssima) a 5 (ótima)
	
	public Nota() {
	}
	
	public Nota(int id, Pessoa pessoa, Vacina vacina, double valor) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.vacina = vacina;
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
	
	
	@Override
	public String toString() {
		return "[id=" + this.id + ", pessoa=" + this.pessoa.getNomeCompleto() 
			+ ", vacina=" + vacina.getNome()
			+ ", nota=" + this.valor +"]";
	}
	
}
