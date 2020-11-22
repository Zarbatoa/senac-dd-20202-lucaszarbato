package br.sc.senac.model.vo;

public class TipoPessoa {

	public static final TipoPessoa TIPO_PESQUISADOR = new TipoPessoa(1, "PESQUISADOR");
	public static final TipoPessoa TIPO_PUBLICO_GERAL = new TipoPessoa(2, "PUBLICO_GERAL");
	public static final TipoPessoa TIPO_VOLUNTARIO = new TipoPessoa(3, "VOLUNTARIO");
	
	private int id;
	private String descricao;
	
	public TipoPessoa() {
	}
	
	public TipoPessoa(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "[id=" + this.id + ", descricao=" + this.descricao + "]";
	}
}
