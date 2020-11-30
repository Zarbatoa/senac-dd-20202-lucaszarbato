package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.TipoPessoa;

public class PessoaSeletor {
	
	//Atributos que servirão de filtros
	private String nome;
	private String sobrenome;
	private Character sexo;
	private String cpf;
	private LocalDate dataNascimento;
	private String nomeInstituicao; 
	private TipoPessoa tipo; 
	
	//Atributos para possível paginação dos resultados (intervalo)
	private int limite;
	private int pagina;
	
	public PessoaSeletor() {
		//Default: traz os resultados sem limite e sem página
		this.limite = 0;
		this.pagina = -1;
	}
	
	//getters e setters
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
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
	
	

	public boolean temFiltro() {
		if (this.temFiltroDeNome()) {
			return true;
		}
		if (this.temFiltroDeSobrenome()) {
			return true;
		}
		if (this.temFiltroDeSexo()) {
			return true;
		}
		if (this.temFiltroDeCPF()) {
			return true;
		}
		if (this.temFiltroDeNomeInstituicao()) {
			return true;
		}  
		if (this.temFiltroDeTipo()) {
			return true; 
		}  
		if (this.temFiltroDeDataNascimento()) {
			return true;
		}

		return false;
	}
		
	public boolean temFiltroDeDataNascimento() {
		return this.dataNascimento != null;
	}

	public boolean temFiltroDeTipo() {
		return (this.tipo != null);
	}

	public boolean temFiltroDeNomeInstituicao() {
		return (this.nomeInstituicao != null) && (this.nomeInstituicao.trim().length()>0);
	}

	public boolean temFiltroDeCPF() {
		return (this.cpf != null) && (this.cpf.trim().length() > 0);
	}

	public boolean temFiltroDeSexo() {
		// (this.sexo != 0) && (this.sexo > 0);
		return this.sexo != null;
	}

	public boolean temFiltroDeSobrenome() {
		return (this.sobrenome != null) && (this.sobrenome.trim().length() > 0);
	}

	public boolean temFiltroDeNome() {
		return (this.nome != null) && (this.nome.trim().length() > 0);
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

}
