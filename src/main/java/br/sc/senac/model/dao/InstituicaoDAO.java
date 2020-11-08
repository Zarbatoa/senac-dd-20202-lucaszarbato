package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.vo.Instituicao;

public class InstituicaoDAO implements BaseDAO<Instituicao>{

	public Instituicao inserir(Instituicao novaInstituicao) {
		Connection conn = Banco.getConnection();
		
		String sql = " INSERT INTO INSTITUICAO (NOME) "
				+ " VALUES (?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conn, sql);
		
		try {
			query.setString(1,novaInstituicao.getNome());
			
			int codigoRetorno = query.executeUpdate();
			
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				if(resultado.next()) {
					int chaveGerada = resultado.getInt(1);
					
					novaInstituicao.setId(chaveGerada);
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir instituição.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return novaInstituicao;
	}

	public boolean alterar(Instituicao instituicaoAlterada) {
		Connection conn = Banco.getConnection();
		
		String sql = " UPDATE INSTITUICAO "
				+ " SET NOME=? "
				+ " WHERE IDINSTITUICAO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		boolean alterou = false;
		
		try {
			query.setString(1, instituicaoAlterada.getNome());
			query.setInt(2, instituicaoAlterada.getId());
			
			int codigoRetorno = query.executeUpdate();
			alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar instituição.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return alterou;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = " DELETE FROM INSTITUICAO WHERE IDINSTITUICAO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		boolean excluiu = false;
		
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir instituição (id: " + id + ") .\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return excluiu;
	}

	public List<Instituicao> pesquisarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM INSTITUICAO ";
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		List<Instituicao> instituicoesBuscadas = new ArrayList<Instituicao>();
		
		try {
			ResultSet conjuntoResultante = query.executeQuery();
			while(conjuntoResultante.next()) {
				Instituicao instituicaoBuscada = construirDoResultSet(conjuntoResultante);
				instituicoesBuscadas.add(instituicaoBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas as instituições.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conn);
		}
		
		return instituicoesBuscadas;
	}

	public Instituicao pesquisarPorId(int id) {
		Connection conn = Banco.getConnection();
		
		String sql = " SELECT * FROM INSTITUICAO WHERE IDINSTITUICAO=? ";
		Instituicao instituicaoBuscada = null;
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		try {
			query.setInt(1, id);
			ResultSet conjuntoResultante = query.executeQuery();
			
			if(conjuntoResultante.next()) {
				instituicaoBuscada = construirDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar instituição por IDINSTITUICAO (id: " + id + ") .\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return instituicaoBuscada;
	}
	
	public Instituicao pesquisarPeloNome(String nome) {
		Connection conn = Banco.getConnection();
		
		String sql = " SELECT * FROM INSTITUICAO WHERE NOME=? ";
		Instituicao instituicaoBuscada = null;
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		try {
			query.setString(1, nome);
			ResultSet conjuntoResultante = query.executeQuery();
			
			if(conjuntoResultante.next()) {
				instituicaoBuscada = construirDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar instituição por NOME (NOME: " + nome + ") .\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return instituicaoBuscada;
	}

	public Instituicao construirDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		Instituicao instituicaoBuscada = new Instituicao();
		instituicaoBuscada.setId(conjuntoResultante.getInt("IDINSTITUICAO"));
		instituicaoBuscada.setNome(conjuntoResultante.getString("NOME"));
		
		return instituicaoBuscada;
	}

}
