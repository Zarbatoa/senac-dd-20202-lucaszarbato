package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
		//TODO
		return false;
	}
	
	public boolean excluir(int id) {
		//TODO
		return false;
	}
	
	public Vacina pesquisarPorId(int id) {
		//TODO
		return null;
	}

	public List<Vacina> pesquisarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vacina construirDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
