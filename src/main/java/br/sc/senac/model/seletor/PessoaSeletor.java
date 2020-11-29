package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.TipoPessoa;

public class PessoaSeletor {
	
	//Atributos que servirão de filtros
	private String nome;
	private String sobrenome;
	private char sexo;
	private String cpf;
	private LocalDate dataNascimento;
	private String instituicao; 
	private TipoPessoa tipo; 
	
	//Atributos para possível paginação dos resultados (intervalo)
	private int limite;
	private int pagina;
	
	public PessoaSeletor() {
		//Default: traz os resultados sem limite e sem página
		this.limite = 0;
		this.pagina = -1;
	}
	
	public boolean temFiltro() {
		if ((this.nome != null) && (this.nome.trim().length() > 0)) {
			return true;
		}
		if ((this.sobrenome != null) && (this.sobrenome.trim().length() > 0)) {
			return true;
		}
		if ((this.sexo != 0) && (this.sexo > 0)) {
			return true; // não sei se está correto
		}
		if ((this.cpf != null) && (this.cpf.trim().length() > 0)) {
			return true;
		}
		if ((this.instituicao != null) && (this.instituicao.trim().length()>0)) {
			return true;
		}  
		if ((this.tipo.getDescricao() != null) && (this.tipo.getDescricao().trim().length()>0)) {
			return true; 
		}  
		if (this.dataNascimento != null) {
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
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

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
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
	
}
