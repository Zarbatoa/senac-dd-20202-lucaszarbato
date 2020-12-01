package br.sc.senac.model.utilidades;

import br.sc.senac.model.vo.TipoPessoa;

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
	public static final String[] LISTA_ESTAGIOS_VACINA = {TXT_ESTAGIO_INICIAL, TXT_ESTAGIO_TESTES, TXT_ESTAGIO_APLICACAO_EM_MASSA};
	
	// view
	public static final String OPCAO_SEXO_TODOS = "Todos";
	public static final TipoPessoa OPCAO_CATEGORIA_TODAS = new TipoPessoa(-1,"Todas");
	public static final int TAMANHO_PAGINA = 5;
	
	public static final String[] OPCOES_SEXO_GERAL = {Constantes.OPCAO_SEXO_TODOS, Constantes.SEXO_MASCULINO, Constantes.SEXO_FEMININO};
	public static final TipoPessoa[] OPCOES_TIPO_PESSOA_GERAL = {Constantes.OPCAO_CATEGORIA_TODAS ,Constantes.TIPO_PESQUISADOR, Constantes.TIPO_VOLUNTARIO, Constantes.TIPO_PUBLICO_GERAL};
	public static final String[] OPCOES_SEXO_EDICAO_CADASTRO = {Constantes.SEXO_MASCULINO, Constantes.SEXO_FEMININO};
	public static final TipoPessoa[] OPCOES_TIPO_PESSOA_EDICAO_CADASTRO = {Constantes.TIPO_PESQUISADOR, Constantes.TIPO_VOLUNTARIO, Constantes.TIPO_PUBLICO_GERAL};
	
}
