package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.vo.TipoPessoa;

public class TipoPessoaDAO implements BaseDAO<TipoPessoa> {

	public TipoPessoa inserir(TipoPessoa novoTipoPessoa) {
		Connection conn = Banco.getConnection();
		
		String sql = " INSERT INTO TIPO (DESCRICAO) "
				+ " VALUES (?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conn, sql);
		
		try {
			query.setString(1,novoTipoPessoa.getDescricao());
			
			int codigoRetorno = query.executeUpdate();
			
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				if(resultado.next()) {
					int chaveGerada = resultado.getInt(1);
					
					novoTipoPessoa.setId(chaveGerada);
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir TipoPessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return novoTipoPessoa;
	}

	public boolean alterar(TipoPessoa tipoPessoaAlterada) {
		Connection conn = Banco.getConnection();
		
		String sql = " UPDATE TIPO "
				+ " SET DESCRICAO=? "
				+ " WHERE IDTIPO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		boolean alterou = false;
		
		try {
			query.setString(1, tipoPessoaAlterada.getDescricao());
			query.setInt(2, tipoPessoaAlterada.getId());
			
			int codigoRetorno = query.executeUpdate();
			alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar TipoPessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return alterou;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = " DELETE FROM TIPO WHERE IDTIPO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		boolean excluiu = false;
		
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir TipoPessoa (id: " + id + ") .\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return excluiu;
	}

	public List<TipoPessoa> pesquisarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM TIPO ";
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		List<TipoPessoa> tipoPessoaBuscadas = new ArrayList<TipoPessoa>();
		
		try {
			ResultSet conjuntoResultante = query.executeQuery();
			while(conjuntoResultante.next()) {
				TipoPessoa tipoPessoaBuscada = construirDoResultSet(conjuntoResultante);
				tipoPessoaBuscadas.add(tipoPessoaBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas os TiposPessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conn);
		}
		
		return tipoPessoaBuscadas;
	}

	public TipoPessoa pesquisarPorId(int id) {
		Connection conn = Banco.getConnection();
		
		String sql = " SELECT * FROM TIPO WHERE IDTIPO=? ";
		TipoPessoa tipoPessoaBuscada = null;
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		try {
			query.setInt(1, id);
			ResultSet conjuntoResultante = query.executeQuery();
			
			if(conjuntoResultante.next()) {
				tipoPessoaBuscada = construirDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar TipoPessoa por IDTIPO (id: " + id + ") .\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return tipoPessoaBuscada;
	}

	public TipoPessoa construirDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		TipoPessoa tipoPessoaBuscada = new TipoPessoa();
		tipoPessoaBuscada.setId(conjuntoResultante.getInt("IDTIPO"));
		tipoPessoaBuscada.setDescricao(conjuntoResultante.getString("DESCRICAO"));
		
		return tipoPessoaBuscada;
	}

}
