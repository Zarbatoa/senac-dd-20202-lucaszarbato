package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.dto.VacinaNotaPessoaDTO;
import br.sc.senac.model.seletor.RelatorioSeletor;
import br.sc.senac.model.utilidades.Constantes;

//Não herda BaseDAO pq só faz relatórios
public class RelatorioDAO {
	/*
	TOTAL_VACINAS_POR_PESQUISADOR:
	POSSIVEIS FILTROS: VACINA.DATA_INICIO_PESQUISA E PESSOA.SEXO
		
		SELECT 
		    P.NOME AS NOME,
		    P.SOBRENOME AS SOBRENOME,
		    COUNT(V.IDVACINA) AS NUMERO_DE_VACINAS
		FROM
			PESSOA P LEFT JOIN
			VACINA V ON P.IDPESSOA = V.IDPESQUISADOR
		WHERE
			P.IDTIPO = 1
		GROUP BY
			P.IDPESSOA
		ORDER BY
			NUMERO_DE_VACINAS;
	*/
	//TODO QUERY 1 / RELATORIO 1
	public List<VacinaNotaPessoaDTO> gerarRelatorioTotalVacinasPesquisadorDAO(RelatorioSeletor seletor) {
		String sql = " SELECT "
				+ " P.NOME AS NOME, "
				+ " P.SOBRENOME AS SOBRENOME, "
				+ "	COUNT(V.IDVACINA) AS NUMERO_DE_VACINAS "
				+ "	 FROM "
				+ "	PESSOA P LEFT JOIN "
				+ " VACINA V ON P.IDPESSOA = V.IDPESQUISADOR ";

		if (seletor.temFiltro()) {
			sql = criarFiltrosGeral(seletor, sql)
				+ "	AND P.IDTIPO = 1 "
				+ "  GROUP BY "
				+ "	P.IDPESSOA "
				+ "	 ORDER BY "
				+ "	NUMERO_DE_VACINAS ";
		} else {
			sql += "  WHERE "
				+ "	P.IDTIPO = 1 "
				+ "  GROUP BY "
				+ "	P.IDPESSOA "
				+ "	 ORDER BY "
				+ "	NUMERO_DE_VACINAS ";
		}
		
		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		List<VacinaNotaPessoaDTO> dtos = new ArrayList<VacinaNotaPessoaDTO>();
		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				VacinaNotaPessoaDTO novoDto = new VacinaNotaPessoaDTO();
				novoDto.setNomePesquisador(result.getString("NOME"));
				novoDto.setSobrenomePesquisador(result.getString("SOBRENOME"));
				novoDto.setNumeroDeVacinas(result.getInt("NUMERO_DE_VACINAS"));
				dtos.add(novoDto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o relatório: "
					+ "\""+ Constantes.TOTAL_VACINAS_POR_PESQUISADOR +"\""
					+ " com filtros.\nCausa: " + e.getMessage());
		}
		return dtos;
	}

	
	/*
	TOTAL_VACINAS_POR_PAIS_ORIGEM:
	POSSIVEIS FILTROS: VACINA.DATA_INICIO_PESQUISA E PESSOA.SEXO
	
		SELECT 
			V.PAIS_ORIGEM AS PAIS_ORIGEM,
		    COUNT(V.IDVACINA) AS NUMERO_DE_VACINAS
		FROM
			PESSOA P INNER JOIN
			VACINA V ON P.IDPESSOA = V.IDPESQUISADOR
		#WHERE
		#	DATA_INICIO_PESQUISA >= '2020-06-23'
		GROUP BY
			PAIS_ORIGEM
		ORDER BY
			NUMERO_DE_VACINAS;
	*/
	//TODO QUERY 2 / RELATORIO 2
	public List<VacinaNotaPessoaDTO> gerarRelatorioTotalVacinasPaisOrigemDAO(RelatorioSeletor seletor) {
		String sql = " SELECT " + 
				" V.PAIS_ORIGEM AS PAIS_ORIGEM, " + 
				" COUNT(V.IDVACINA) AS NUMERO_DE_VACINAS " + 
				" FROM " + 
				" PESSOA P INNER JOIN " + 
				" VACINA V ON P.IDPESSOA = V.IDPESQUISADOR ";

		if (seletor.temFiltro()) {
			sql = criarFiltrosGeral(seletor, sql)
				+ "  GROUP BY "
				+ "	PAIS_ORIGEM "
				+ "	 ORDER BY "
				+ "	NUMERO_DE_VACINAS ";
		} else {
			sql += "  GROUP BY "
				+ " PAIS_ORIGEM "
				+ "	 ORDER BY "
				+ "	NUMERO_DE_VACINAS ";
		}
		
		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		List<VacinaNotaPessoaDTO> dtos = new ArrayList<VacinaNotaPessoaDTO>();
		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				VacinaNotaPessoaDTO novoDto = new VacinaNotaPessoaDTO();
				novoDto.setPaisOrigem(result.getString("PAIS_ORIGEM"));
				novoDto.setNumeroDeVacinas(result.getInt("NUMERO_DE_VACINAS"));
				dtos.add(novoDto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o relatório: "
					+ "\""+ Constantes.TOTAL_VACINAS_POR_PAIS_ORIGEM +"\""
					+ " com filtros.\nCausa: " + e.getMessage());
		}
		return dtos;
	}
	
	
	/*
	TOTAL_VACINAS_POR_ESTAGIO_PESQUISA:
	POSSIVEIS FILTROS: VACINA.DATA_INICIO_PESQUISA E PESSOA.SEXO
	
		SELECT
			V.ESTAGIO_PESQUISA AS ESTAGIO_PESQUISA,
		    COUNT(V.IDVACINA) AS NUMERO_DE_VACINAS
		FROM
			PESSOA P INNER JOIN
			VACINA V ON P.IDPESSOA = V.IDPESQUISADOR
		GROUP BY
			V.ESTAGIO_PESQUISA
		ORDER BY
			NUMERO_DE_VACINAS;
	*/
	//TODO QUERY 3 / RELATORIO 3
	public List<VacinaNotaPessoaDTO> gerarRelatorioTotalVacinasEstagioPesquisaDAO(RelatorioSeletor seletor) {
		String sql = " SELECT " + 
				" V.ESTAGIO_PESQUISA AS ESTAGIO_PESQUISA, " + 
				" COUNT(V.IDVACINA) AS NUMERO_DE_VACINAS " + 
				" FROM " + 
				" PESSOA P INNER JOIN " + 
				" VACINA V ON P.IDPESSOA = V.IDPESQUISADOR ";

		if (seletor.temFiltro()) {
			sql = criarFiltrosGeral(seletor, sql)
				+ "  GROUP BY "
				+ "	V.ESTAGIO_PESQUISA "
				+ "	 ORDER BY "
				+ "	NUMERO_DE_VACINAS ";
		} else {
			sql += "  GROUP BY "
				+ " V.ESTAGIO_PESQUISA "
				+ "	 ORDER BY "
				+ "	NUMERO_DE_VACINAS ";
		}
		
		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		List<VacinaNotaPessoaDTO> dtos = new ArrayList<VacinaNotaPessoaDTO>();
		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				VacinaNotaPessoaDTO novoDto = new VacinaNotaPessoaDTO();
				novoDto.setEstagioPesquisa(result.getInt("ESTAGIO_PESQUISA"));
				novoDto.setNumeroDeVacinas(result.getInt("NUMERO_DE_VACINAS"));
				dtos.add(novoDto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o relatório: "
					+ "\""+ Constantes.TOTAL_VACINAS_POR_ESTAGIO_PESQUISA +"\""
					+ " com filtros.\nCausa: " + e.getMessage());
		}
		return dtos;
	}
	
	
	/*
	NUMERO_DE_AVALIACOES_POR_VACINA:
	POSSIVEIS FILTROS: VACINA.DATA_INICIO_PESQUISA E PESSOA.SEXO
	
		SELECT
			V.NOME AS NOME_VACINA,
		    COUNT(N.IDVACINA) AS NUMERO_DE_AVALIACOES
		FROM
			PESSOA P INNER JOIN
			VACINA V ON P.IDPESSOA = V.IDPESQUISADOR
		    RIGHT JOIN NOTA N ON V.IDVACINA = N.IDVACINA
		GROUP BY V.NOME
		ORDER BY NUMERO_DE_AVALIACOES DESC;
	*/
	//TODO QUERY 4 / RELATORIO 4
	public List<VacinaNotaPessoaDTO> gerarRelatorioNumeroDeAvaliacoesVacinaDAO(RelatorioSeletor seletor) {
		String sql = " SELECT " + 
				" V.NOME AS NOME_VACINA, " + 
				" COUNT(N.IDVACINA) AS NUMERO_DE_AVALIACOES " + 
				" FROM " + 
				" PESSOA P INNER JOIN " + 
				" VACINA V ON P.IDPESSOA = V.IDPESQUISADOR " + 
				" RIGHT JOIN NOTA N ON V.IDVACINA = N.IDVACINA ";

		if (seletor.temFiltro()) {
			sql = criarFiltrosGeral(seletor, sql)
				+ "  GROUP BY "
				+ "	V.NOME "
				+ "	 ORDER BY "
				+ "	NUMERO_DE_AVALIACOES DESC ";
		} else {
			sql += "  GROUP BY "
				+ " V.NOME "
				+ "	 ORDER BY "
				+ "	NUMERO_DE_AVALIACOES DESC ";
		}
		
		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		List<VacinaNotaPessoaDTO> dtos = new ArrayList<VacinaNotaPessoaDTO>();
		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				VacinaNotaPessoaDTO novoDto = new VacinaNotaPessoaDTO();
				novoDto.setNomeVacina(result.getString("NOME_VACINA"));
				novoDto.setNumeroDeAvaliacoes(result.getInt("NUMERO_DE_AVALIACOES"));
				dtos.add(novoDto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o relatório: "
					+ "\""+ Constantes.NUMERO_DE_AVALIACOES_POR_VACINA +"\""
					+ " com filtros.\nCausa: " + e.getMessage());
		}
		return dtos;
	}
	
	
	/*
	MEDIA_DE_AVALIACOES_POR_VACINA:
	POSSIVEIS FILTROS: VACINA.DATA_INICIO_PESQUISA E PESSOA.SEXO
	
		SELECT
			V.NOME AS NOME_VACINA,
		    AVG(N.VALOR) AS MEDIA_DE_AVALIACOES
		FROM
			PESSOA P INNER JOIN
			VACINA V ON P.IDPESSOA = V.IDPESQUISADOR
		    RIGHT JOIN NOTA N ON V.IDVACINA = N.IDVACINA
		#WHERE
			#DATA_INICIO_PESQUISA >= '2020-06-23'
		    #P.SEXO='F'
		GROUP BY V.NOME
		ORDER BY MEDIA_DE_AVALIACOES DESC;
	*/
	//TODO QUERY 5 / RELATORIO 5
	public List<VacinaNotaPessoaDTO> gerarRelatorioMediaDeAvaliacoesVacinaDAO(RelatorioSeletor seletor) {
		String sql = " SELECT " + 
				" V.NOME AS NOME_VACINA, " + 
				" AVG(N.VALOR) AS MEDIA_DE_AVALIACOES " + 
				" FROM " + 
				" PESSOA P INNER JOIN " + 
				" VACINA V ON P.IDPESSOA = V.IDPESQUISADOR " + 
				" RIGHT JOIN NOTA N ON V.IDVACINA = N.IDVACINA ";

		if (seletor.temFiltro()) {
			sql = criarFiltrosGeral(seletor, sql)
				+ "  GROUP BY "
				+ "	V.NOME "
				+ "	 ORDER BY "
				+ "	MEDIA_DE_AVALIACOES DESC ";
		} else {
			sql += "  GROUP BY "
				+ " V.NOME "
				+ "	 ORDER BY "
				+ "	MEDIA_DE_AVALIACOES DESC ";
		}
		
		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		List<VacinaNotaPessoaDTO> dtos = new ArrayList<VacinaNotaPessoaDTO>();
		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				VacinaNotaPessoaDTO novoDto = new VacinaNotaPessoaDTO();
				novoDto.setNomeVacina(result.getString("NOME_VACINA"));
				novoDto.setMediaDeAvaliacoes(result.getDouble("MEDIA_DE_AVALIACOES"));
				dtos.add(novoDto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o relatório: "
					+ "\""+ Constantes.MEDIA_DE_AVALIACOES_POR_VACINA +"\""
					+ " com filtros.\nCausa: " + e.getMessage());
		}
		return dtos;
	}
	
	
	/*
	TODAL_DE_PESSOAS_POR_TIPO:
	POSSIVEIS FILTROS: PESSOA.SEXO
	
		SELECT 
			T.DESCRICAO AS DESCRICAO_TIPO_PESSOA,
		    COUNT(P.IDPESSOA) AS NUMERO_DE_PESSOAS
		FROM
			PESSOA P
		    RIGHT JOIN TIPO T ON
		    P.IDTIPO = T.IDTIPO
		GROUP BY
			T.DESCRICAO_TIPO_PESSOA;
	*/
	//TODO QUERY 6 / RELATORIO 6
	public List<VacinaNotaPessoaDTO> gerarRelatorioTotalPessoasPorTipoDAO(RelatorioSeletor seletor) {
		String sql = " SELECT " + 
				" T.DESCRICAO AS DESCRICAO_TIPO_PESSOA, " + 
				" COUNT(P.IDPESSOA) AS NUMERO_DE_PESSOAS " + 
				" FROM " + 
				" PESSOA P " + 
				" RIGHT JOIN TIPO T ON " + 
				" P.IDTIPO = T.IDTIPO ";

		if (seletor.temFiltro()) {
			sql = criarFiltrosGeral(seletor, sql)
				+ " GROUP BY "
				+ "	T.DESCRICAO ";
		} else {
			sql += " GROUP BY "
				+ "	T.DESCRICAO ";
		}
		
		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		List<VacinaNotaPessoaDTO> dtos = new ArrayList<VacinaNotaPessoaDTO>();
		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				VacinaNotaPessoaDTO novoDto = new VacinaNotaPessoaDTO();
				novoDto.setDescricaoTipoPessoa(result.getString("DESCRICAO_TIPO_PESSOA"));
				novoDto.setNumeroDePessoas(result.getInt("NUMERO_DE_PESSOAS"));
				dtos.add(novoDto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o relatório: "
					+ "\""+ Constantes.TOTAL_DE_PESSOAS_POR_TIPO +"\""
					+ " com filtros.\nCausa: " + e.getMessage());
		}
		return dtos;
	}
	
	
	
	/*
	NUMERO_DE_PESSOAS_E_MEDIA_DA_NOTA_POR_IDADE_DE_UMA_VACINA:
	FILTRO OBRIGATORIO: VACINA.IDVACINA
	
		SELECT 
			CASE 
				WHEN T.IDADE BETWEEN 1 AND 10 THEN 'De 1 a 10' 
				WHEN T.IDADE BETWEEN 11 AND 20 THEN 'De 11 a 20' 
				WHEN T.IDADE BETWEEN 21 AND 30 THEN 'De 21 a 30'
				WHEN T.IDADE BETWEEN 31 AND 40 THEN 'De 31 a 40'
				WHEN T.IDADE BETWEEN 41 AND 50 THEN 'De 41 a 50'
				WHEN T.IDADE BETWEEN 51 AND 60 THEN 'De 51 a 60'
				WHEN T.IDADE BETWEEN 61 AND 70 THEN 'De 61 a 70'
				WHEN T.IDADE BETWEEN 71 AND 80 THEN 'De 71 a 80'
				WHEN T.IDADE BETWEEN 81 AND 90 THEN 'De 81 a 90'
				WHEN T.IDADE > 90 THEN 'De 91 em diante'
			END AS FAIXAS,
			SUM(T.TOTAL) AS TOTAL,
		    AVG(T.VALOR) AS MEDIA_NOTA
		FROM 
			( 
				SELECT 
					YEAR(NOW()) - YEAR(P.DATA_NASCIMENTO) - (DAYOFYEAR(NOW()) < DAYOFYEAR(P.DATA_NASCIMENTO)) AS IDADE, COUNT(P.IDPESSOA) AS TOTAL, N.VALOR AS VALOR
				FROM
					PESSOA P INNER JOIN
		            NOTA N ON P.IDPESSOA = N.IDPESSOA
				WHERE
					N.IDVACINA = ?
				GROUP BY
					IDADE
			) AS T
		GROUP BY FAIXAS
		ORDER BY FAIXAS;
	 */
	//TODO QUERY 7 / RELATORIO 7
	public List<VacinaNotaPessoaDTO> gerarRelatorioFaixasDeIdadeVacinaDAO(RelatorioSeletor seletor) {
		String sql = " SELECT " + 
				"			CASE " + 
				"				WHEN T.IDADE BETWEEN 1 AND 10 THEN 'De 1 a 10' " + 
				"				WHEN T.IDADE BETWEEN 11 AND 20 THEN 'De 11 a 20' " + 
				"				WHEN T.IDADE BETWEEN 21 AND 30 THEN 'De 21 a 30' " + 
				"				WHEN T.IDADE BETWEEN 31 AND 40 THEN 'De 31 a 40' " + 
				"				WHEN T.IDADE BETWEEN 41 AND 50 THEN 'De 41 a 50' " + 
				"				WHEN T.IDADE BETWEEN 51 AND 60 THEN 'De 51 a 60' " + 
				"				WHEN T.IDADE BETWEEN 61 AND 70 THEN 'De 61 a 70' " + 
				"				WHEN T.IDADE BETWEEN 71 AND 80 THEN 'De 71 a 80' " + 
				"				WHEN T.IDADE BETWEEN 81 AND 90 THEN 'De 81 a 90' " + 
				"				WHEN T.IDADE > 90 THEN 'De 91 em diante' " + 
				"			END AS FAIXAS, " + 
				"			SUM(T.TOTAL) AS TOTAL, " + 
				"		    AVG(T.VALOR) AS MEDIA_NOTA " + 
				"		FROM " + 
				"			( " + 
				"				SELECT " + 
				"					YEAR(NOW()) - YEAR(P.DATA_NASCIMENTO) - (DAYOFYEAR(NOW()) < DAYOFYEAR(P.DATA_NASCIMENTO)) AS IDADE, COUNT(P.IDPESSOA) AS TOTAL, N.VALOR AS VALOR " + 
				"				FROM " + 
				"					PESSOA P INNER JOIN " + 
				"		            NOTA N ON P.IDPESSOA = N.IDPESSOA " + 
				"				WHERE " + 
				"					N.IDVACINA = " + seletor.getVacina().getId() + 
				"				GROUP BY " + 
				"					IDADE " + 
				"			) AS T " + 
				"		GROUP BY FAIXAS " + 
				"		ORDER BY FAIXAS ";
		
		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		List<VacinaNotaPessoaDTO> dtos = new ArrayList<VacinaNotaPessoaDTO>();
		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				VacinaNotaPessoaDTO novoDto = new VacinaNotaPessoaDTO();
				novoDto.setFaixas(result.getString("FAIXAS"));
				novoDto.setTotal(result.getInt("TOTAL"));
				novoDto.setMedia_nota(result.getDouble("MEDIA_NOTA"));
				dtos.add(novoDto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o relatório: "
					+ "\""+ Constantes.NUMERO_DE_PESSOAS_E_MEDIA_DA_NOTA_POR_IDADE_DE_UMA_VACINA +"\""
					+ " com filtros.\nCausa: " + e.getMessage());
		}
		return dtos;
	}
	
	
	
	
	
	
	
	
	private String criarFiltrosGeral(RelatorioSeletor seletor, String sql) {
		// Tem pelo menos UM filtro
		sql += " WHERE ";
		boolean primeiro = true;

		if (seletor.temFiltroSexo()) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.sexo = '" + seletor.getSexo() + "'";
			primeiro = false;
		}

		if (seletor.temFiltroDataInicioPesquisa()) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.data_inicio_pesquisa >= '" + seletor.getDataInicioPesquisa() + "'";
			primeiro = false;
		}
		
		return sql;
	}
}
