package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.seletor.VacinaSeletor;
import br.sc.senac.model.vo.Vacina;

public class VacinaDAO implements BaseDAO<Vacina>{

	public Vacina inserir(Vacina novaVacina) {
		Connection conn = Banco.getConnection();
		
		String sql = " INSERT INTO VACINA (IDPESQUISADOR, NOME, PAIS_ORIGEM, ESTAGIO_PESQUISA, DATA_INICIO_PESQUISA) "
				+ " VALUES (?,?,?,?,?)";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conn, sql);
		
		try {
			query.setInt(1, novaVacina.getPesquisadorResponsavel().getId());
			query.setString(2, novaVacina.getNome());
			query.setString(3, novaVacina.getPaisOrigem());
			query.setInt(4, novaVacina.getEstagioPesquisa());
			Date dataConvertidaSQL = java.sql.Date.valueOf(novaVacina.getDataInicioPesquisa());
			query.setDate(5, dataConvertidaSQL);
			
			int codigoRetorno = query.executeUpdate();
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				
				if(resultado.next()) {
					int chaveGerada = resultado.getInt(1);
					
					novaVacina.setId(chaveGerada);
				}
			}
		} catch (SQLException e ) {
			System.out.println("Erro ao inserir vacina.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return novaVacina;
	}
	
	public boolean alterar(Vacina vacina) {
		Connection conn = Banco.getConnection();
		
		String sql = " UPDATE VACINA "
				+ " SET IDPESQUISADOR=?, NOME=?, PAIS_ORIGEM=?, ESTAGIO_PESQUISA=?, DATA_INICIO_PESQUISA=? "
				+ " WHERE IDVACINA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		boolean alterou = false;
		
		try {
			query.setInt(1, vacina.getPesquisadorResponsavel().getId());
			query.setString(2, vacina.getNome());
			query.setString(3, vacina.getPaisOrigem());
			query.setInt(4, vacina.getEstagioPesquisa());
			Date dataConvertidaSQL = java.sql.Date.valueOf(vacina.getDataInicioPesquisa());
			query.setDate(5, dataConvertidaSQL);
			query.setInt(6, vacina.getId());
			
			int codigoRetorno = query.executeUpdate();
			alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar vacina.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return alterou;
	}
	
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = " DELETE FROM VACINA WHERE IDVACINA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		boolean excluiu = false;
		
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir vacina (id: " + id + ") .\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return excluiu;
	}
	
	public Vacina pesquisarPorId(int id) {
		Connection conn = Banco.getConnection();
		
		String sql = " SELECT * FROM VACINA WHERE IDVACINA=? ";
		Vacina vacinaBuscada = null;
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		try {
			query.setInt(1, id);
			ResultSet conjuntoResultante = query.executeQuery();
			
			if(conjuntoResultante.next()) {
				vacinaBuscada = construirDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacina por IDVACINA (id: " + id + ") .\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return vacinaBuscada;
	}

	public List<Vacina> pesquisarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM VACINA ";
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		List<Vacina> vacinasBuscadas = new ArrayList<Vacina>();
		
		try {
			ResultSet conjuntoResultante = query.executeQuery();
			while(conjuntoResultante.next()) {
				Vacina vacinaBuscada = construirDoResultSet(conjuntoResultante);
				vacinasBuscadas.add(vacinaBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas as vacinas.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conn);
		}
		
		return vacinasBuscadas;
	}

	//TODO verificar porquê conjuntoResultante.getDate("DATA_INICIO_PESQUISA")
	//  retorna a data com um dia a menos
	public Vacina construirDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		Vacina vacinaBuscada = new Vacina();
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		vacinaBuscada.setId(conjuntoResultante.getInt("IDVACINA"));
		
		int idPesquisador = conjuntoResultante.getInt("IDPESQUISADOR");
		vacinaBuscada.setPesquisadorResponsavel(pessoaDAO.pesquisarPorId(idPesquisador));
		
		vacinaBuscada.setNome(conjuntoResultante.getString("NOME"));
		vacinaBuscada.setPaisOrigem(conjuntoResultante.getString("PAIS_ORIGEM"));
		vacinaBuscada.setEstagioPesquisa(conjuntoResultante.getInt("ESTAGIO_PESQUISA"));
		vacinaBuscada.setDataInicioPesquisa(conjuntoResultante.getDate("DATA_INICIO_PESQUISA").toLocalDate());

		return vacinaBuscada;
	}
	
	// a partir daqui estão os métodos dos filtros
	
	public ArrayList<Vacina> listarComSeletor(VacinaSeletor seletor) {
		String sql = " SELECT * FROM VACINA v ";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Vacina> vacinas = new ArrayList<Vacina>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				Vacina v = construirDoResultSet(result); 
				vacinas.add(v);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacinas com filtros.\nCausa: " + e.getMessage());
		}
		return vacinas;
	}
	
	private String criarFiltros(VacinaSeletor seletor, String sql) {
		
		// Tem pelo menos UM filtro
		sql += " WHERE ";
		boolean primeiro = true;

		if (seletor.temFiltroDeNome()) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.nome LIKE '%" + seletor.getNomeVacina() + "%'";
			primeiro = false;
		}

		if (seletor.temFiltroDeEstagioDePesquisa()) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.estagio_pesquisa = '" + seletor.getEstagioPesquisa() + "'";
			primeiro = false;
		}
		
		if (seletor.temFiltroDePaisOrigem()) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.pais_origem LIKE '%" + seletor.getPaisOrigem() + "%'";
			primeiro = false;
		}
		
		if (seletor.temFiltroDePesquisadorResponsavel()) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.idpesquisador = '" + seletor.getPesquisadorResponsavel().getId() + "'";
			primeiro = false;
		}
			
		if (seletor.temFiltroDeDataInicioPesquisa()) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.data_inicio_pesquisa = '" + seletor.getDataInicioPesquisa() + "'";
			primeiro = false;
		}
		return sql;
	}
	
}
