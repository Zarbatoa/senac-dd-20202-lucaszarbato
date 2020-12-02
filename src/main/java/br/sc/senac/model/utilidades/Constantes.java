package br.sc.senac.model.utilidades;

import java.util.List;

import br.sc.senac.model.vo.Pais;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;
import br.sc.senac.model.vo.Vacina;

/**
 * classe repositório de constantes do sistema
 * 
 * constantes da classe Banco ficam no Banco
 * */
public class Constantes {

	// BOs
	public static final String TIPO_RELATORIO_XLS = "xls";
	
	// VOs
	public static final String SEXO_MASCULINO = "Masculino";
	public static final String SEXO_FEMININO = "Feminino";
	
	public static final TipoPessoa TIPO_PESQUISADOR = new TipoPessoa(1, "PESQUISADOR");
	public static final TipoPessoa TIPO_PUBLICO_GERAL = new TipoPessoa(2, "PUBLICO_GERAL");
	public static final TipoPessoa TIPO_VOLUNTARIO = new TipoPessoa(3, "VOLUNTARIO");

	public static final int ESTAGIO_INICIAL = 1;
	public static final int ESTAGIO_TESTES = 2;
	public static final int ESTAGIO_APLICACAO_EM_MASSA = 3;
	public static final String TXT_ESTAGIO_INICIAL = "Estágio Inicial";
	public static final String TXT_ESTAGIO_TESTES = "Estágio de Testes";
	public static final String TXT_ESTAGIO_APLICACAO_EM_MASSA = "Estágio de Aplicação em Massa";
	
	// view
	public static final int TAMANHO_PAGINA = 5;
	
	public static final String OPCAO_TODOS = "Todos";
	public static final String OPCAO_TODAS = "Todas";
	public static final String[] OPCOES_SEXO_GERAL = {Constantes.OPCAO_TODOS, Constantes.SEXO_MASCULINO, Constantes.SEXO_FEMININO};
	public static final String[] OPCOES_SEXO_EDICAO_CADASTRO = {Constantes.SEXO_MASCULINO, Constantes.SEXO_FEMININO};
	
	public static final TipoPessoa OPCAO_CATEGORIA_TODAS = new TipoPessoa(-1, OPCAO_TODAS);
	public static final TipoPessoa[] OPCOES_TIPO_PESSOA_GERAL = {Constantes.OPCAO_CATEGORIA_TODAS ,Constantes.TIPO_PESQUISADOR, Constantes.TIPO_VOLUNTARIO, Constantes.TIPO_PUBLICO_GERAL};
	public static final TipoPessoa[] OPCOES_TIPO_PESSOA_EDICAO_CADASTRO = {Constantes.TIPO_PESQUISADOR, Constantes.TIPO_VOLUNTARIO, Constantes.TIPO_PUBLICO_GERAL};
	
	public static final Pais OPCAO_PAISES_TODOS = new Pais(null, OPCAO_TODOS);
	public static final List<Pais> OPCOES_PAISES_GERAL = Pais.createCountryListGeral();
	public static final Pais[] OPCOES_PAISES_EDICAO_CADASTRO = Pais.createCountryListEdicaoCadastro();
	
	public static final String OPCAO_ESTAGIO_VACINA_TODAS = OPCAO_TODAS;
	public static final String[] LISTA_ESTAGIOS_VACINA_GERAL = {OPCAO_ESTAGIO_VACINA_TODAS, TXT_ESTAGIO_INICIAL, TXT_ESTAGIO_TESTES, TXT_ESTAGIO_APLICACAO_EM_MASSA};
	public static final String[] LISTA_ESTAGIOS_VACINA_EDICAO_CADASTRO = {TXT_ESTAGIO_INICIAL, TXT_ESTAGIO_TESTES, TXT_ESTAGIO_APLICACAO_EM_MASSA};
	
	public static final Pessoa OPCAO_PESQUISADOR_RESPONSAVEL_TODOS = new Pessoa(-1, null, null, OPCAO_TODOS, null, null, ' ', null);
	public static final Pessoa OPCAO_PESSOA_TESTADA_TODAS = new Pessoa(-1, null, null, OPCAO_TODAS, null, null, ' ', null);
	public static final Vacina OPCAO_VACINA_TODAS = new Vacina(-1, OPCAO_TODAS, null, -1, null, null);
	
	//relatorios
	public static final String TOTAL_VACINAS_POR_PESQUISADOR = "Total de Vacinas por Pesquisador";
	public static final String TOTAL_VACINAS_POR_PAIS_ORIGEM = "Total de Vacinas por Pa\u00EDs de origem";
	public static final String TOTAL_VACINAS_POR_ESTAGIO_PESQUISA = "Total de Vacinas por Est\u00E1gio de Pesquisa";
	public static final String NUMERO_DE_AVALIACOES_POR_VACINA = "Número de Avaliações por Vacina";
	public static final String MEDIA_DE_AVALIACOES_POR_VACINA = "Média de Avaliações por Vacina";
	public static final String TODAL_DE_PESSOAS_POR_TIPO = "Todal de Pessoas por Categoria";
	public static final String NUMERO_DE_PESSOAS_E_MEDIA_DA_NOTA_POR_IDADE_DE_UMA_VACINA = "Número de pessoas e Média de nota de UMA Vacina por faixa de idade";
	public static final String[] RELATORIO_VACINA_OPCOES = {TOTAL_VACINAS_POR_PESQUISADOR, TOTAL_VACINAS_POR_PAIS_ORIGEM, TOTAL_VACINAS_POR_ESTAGIO_PESQUISA,
			NUMERO_DE_AVALIACOES_POR_VACINA, MEDIA_DE_AVALIACOES_POR_VACINA, TODAL_DE_PESSOAS_POR_TIPO,
			NUMERO_DE_PESSOAS_E_MEDIA_DA_NOTA_POR_IDADE_DE_UMA_VACINA};
}
