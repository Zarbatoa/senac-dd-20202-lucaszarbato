package br.sc.senac.model.utilidades;

import java.util.List;

import br.sc.senac.model.vo.Pais;
import br.sc.senac.model.vo.Pessoa;
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
	
	// view
	public static final int TAMANHO_PAGINA = 5;
	
	public static final String OPCAO_SEXO_TODOS = "Todos";
	public static final String[] OPCOES_SEXO_GERAL = {Constantes.OPCAO_SEXO_TODOS, Constantes.SEXO_MASCULINO, Constantes.SEXO_FEMININO};
	public static final String[] OPCOES_SEXO_EDICAO_CADASTRO = {Constantes.SEXO_MASCULINO, Constantes.SEXO_FEMININO};
	
	public static final TipoPessoa OPCAO_CATEGORIA_TODAS = new TipoPessoa(-1,"Todas");
	public static final TipoPessoa[] OPCOES_TIPO_PESSOA_GERAL = {Constantes.OPCAO_CATEGORIA_TODAS ,Constantes.TIPO_PESQUISADOR, Constantes.TIPO_VOLUNTARIO, Constantes.TIPO_PUBLICO_GERAL};
	public static final TipoPessoa[] OPCOES_TIPO_PESSOA_EDICAO_CADASTRO = {Constantes.TIPO_PESQUISADOR, Constantes.TIPO_VOLUNTARIO, Constantes.TIPO_PUBLICO_GERAL};
	
	public static final Pais OPCAO_PAISES_TODOS = new Pais(null,"Todos");
	public static final List<Pais> OPCOES_PAISES_GERAL = Pais.createCountryListGeral();
	public static final Pais[] OPCOES_PAISES_EDICAO_CADASTRO = Pais.createCountryListEdicaoCadastro();
	
	public static final String OPCAO_ESTAGIO_VACINA_TODAS = "Todas";
	public static final String[] LISTA_ESTAGIOS_VACINA_GERAL = {OPCAO_ESTAGIO_VACINA_TODAS, TXT_ESTAGIO_INICIAL, TXT_ESTAGIO_TESTES, TXT_ESTAGIO_APLICACAO_EM_MASSA};
	public static final String[] LISTA_ESTAGIOS_VACINA_EDICAO_CADASTRO = {TXT_ESTAGIO_INICIAL, TXT_ESTAGIO_TESTES, TXT_ESTAGIO_APLICACAO_EM_MASSA};
	
	public static final Pessoa OPCAO_PESQUISADOR_RESPONSAVEL_TODOS = new Pessoa(-1, null, null, "Todos", null, null, ' ', null);
}
