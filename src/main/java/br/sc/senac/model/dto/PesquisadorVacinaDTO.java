package br.sc.senac.model.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.sc.senac.model.vo.Vacina;

/*
 * RELATORIO / OUTPUT DAS CONSULTAS:
    P.NOME,
    P.SOBRENOME,
    NUMERO_DE_VACINAS,
    V.PAIS_ORIGEM,
    V.ESTAGIO_PESQUISA
   
   FILTROS / INPUT DAS CONSULTAS
    P.SEXO,
    V.DATA_INICIO_PESQUISA
 * */

public class PesquisadorVacinaDTO {
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Vacina vacina;
	private String paisDeOrigem;
	private String nomePesquisador;
	private String instituicao;
	private LocalDate periodoPesquisa;
	private int valor;
	
	public PesquisadorVacinaDTO(Vacina vacina, String paisDeOrigem, String nomePesquisador, String instituicao,
			LocalDate periodoPesquisa, int valor) {
		super();
		this.vacina = vacina;
		this.paisDeOrigem = paisDeOrigem;
		this.nomePesquisador = nomePesquisador;
		this.instituicao = instituicao;
		this.periodoPesquisa = periodoPesquisa;
		this.valor = valor;
	}

	public PesquisadorVacinaDTO() {
		super();
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public String getPaisDeOrigem() {
		return paisDeOrigem;
	}

	public void setPaisDeOrigem(String paisDeOrigem) {
		this.paisDeOrigem = paisDeOrigem;
	}

	public String getNomePesquisador() {
		return nomePesquisador;
	}

	public void setNomePesquisador(String nomePesquisador) {
		this.nomePesquisador = nomePesquisador;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public LocalDate getPeriodoPesquisa() {
		return periodoPesquisa;
	}

	public void setPeriodoPesquisa(LocalDate periodoPesquisa) {
		this.periodoPesquisa = periodoPesquisa;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	
	
	
	
	
	
	


}
