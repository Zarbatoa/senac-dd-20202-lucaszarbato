package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.sc.senac.model.vo.Nota;

public class NotaDAO implements BaseDAO<Nota>{

	public Nota inserir(Nota novaNota) {
		Connection conn = Banco.getConnection();
		
		String sql = " INSERT INTO NOTA (IDVACINA, IDPESSOA, VALOR) "
				+ " VALUES (?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conn, sql);
		
		try {
			query.setInt(1,novaNota.getVacina().getId());
			query.setInt(2,novaNota.getPessoa().getId());
			query.setDouble(3,novaNota.getValor());
			
			int codigoRetorno = query.executeUpdate();
			
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				if(resultado.next()) {
					int chaveGerada = resultado.getInt(1);
					
					novaNota.setId(chaveGerada);
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir nota.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return novaNota;
	}
	
	public boolean alterar(Nota nota) {
		Connection conn = Banco.getConnection();
		
		String sql = " UPDATE NOTA "
				+ " SET IDVACINA=?, IDPESSOA=?, VALOR=? "
				+ " WHERE IDNOTA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		boolean alterou = false;
		
		try {
			query.setInt(1,nota.getVacina().getId());
			query.setInt(2,nota.getPessoa().getId());
			query.setDouble(3,nota.getValor());
			query.setInt(4, nota.getId());
			
			int codigoRetorno = query.executeUpdate();
			alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar nota.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return alterou;
	}
	
	public boolean excluir(int id) {
		//TODO
		return false;
	}
	
	public Nota pesquisarPorId(int id) {
		//TODO
		return null;
	}
	
	public List<Nota> pesquisarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Nota construirDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
