package br.sc.senac.model.dto;

/**
 * INPUTS / FILTROS: (SELETOR)
	PESSOA.SEXO, VACINA.DATA_INICIO_PESQUISA, VACINA.IDVACINA

	OUTPUTS / CAMPOS SELECT: (DTO)
	* query 1:
		NOME, SOBRENOME, NUMERO_DE_VACINAS
	* query 2:
		PAIS_ORIGEM, NUMERO_DE_VACINAS
	* query 3:
		ESTAGIO_PESQUISA, NUMERO_DE_VACINAS
	* query 4:
		NOME_VACINA, NUMERO_DE_AVALIACOES
	* query 5:
		NOME_VACINA, MEDIA_DE_AVALIACOES
	* query 6:
		DESCRICAO_TIPO_PESSOA, NUMERO_DE_PESSOAS
	* query 7:
		(FILTRO OBRIGATORIO: VACINA.IDVACINA)
		FAIXAS, TOTAL, MEDIA_NOTA
 * */
public class VacinaNotaPessoaDTO {
	private String nomePesquisador;
	private String sobrenomePesquisador;
	private Integer numeroDeVacinas;

	private String paisOrigem;

	private Integer estagioPesquisa;

	private String nomeVacina;
	private Integer numeroDeAvaliacoes;
	
	private Double  mediaDeAvaliacoes;
	
	private String descricaoTipoPessoa;
	private Integer numeroDePessoas;
	
	private String faixas;
	private Integer total;
	private Double media_nota;
	
	public VacinaNotaPessoaDTO() {
	}

	
	public String getNomePesquisador() {
		return nomePesquisador;
	}

	public void setNomePesquisador(String nomePesquisador) {
		this.nomePesquisador = nomePesquisador;
	}

	public String getSobrenomePesquisador() {
		return sobrenomePesquisador;
	}

	public void setSobrenomePesquisador(String sobrenomePesquisador) {
		this.sobrenomePesquisador = sobrenomePesquisador;
	}

	public Integer getNumeroDeVacinas() {
		return numeroDeVacinas;
	}

	public void setNumeroDeVacinas(Integer numeroDeVacinas) {
		this.numeroDeVacinas = numeroDeVacinas;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Integer getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(Integer estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public Integer getNumeroDeAvaliacoes() {
		return numeroDeAvaliacoes;
	}

	public void setNumeroDeAvaliacoes(Integer numeroDeAvaliacoes) {
		this.numeroDeAvaliacoes = numeroDeAvaliacoes;
	}

	public Double getMediaDeAvaliacoes() {
		return mediaDeAvaliacoes;
	}

	public void setMediaDeAvaliacoes(Double mediaDeAvaliacoes) {
		this.mediaDeAvaliacoes = mediaDeAvaliacoes;
	}

	public String getDescricaoTipoPessoa() {
		return descricaoTipoPessoa;
	}

	public void setDescricaoTipoPessoa(String descricaoTipoPessoa) {
		this.descricaoTipoPessoa = descricaoTipoPessoa;
	}

	public Integer getNumeroDePessoas() {
		return numeroDePessoas;
	}

	public void setNumeroDePessoas(Integer numeroDePessoas) {
		this.numeroDePessoas = numeroDePessoas;
	}

	public String getFaixas() {
		return faixas;
	}

	public void setFaixas(String faixas) {
		this.faixas = faixas;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Double getMedia_nota() {
		return media_nota;
	}

	public void setMedia_nota(Double media_nota) {
		this.media_nota = media_nota;
	}
}
