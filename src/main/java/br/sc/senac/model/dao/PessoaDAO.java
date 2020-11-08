package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import br.sc.senac.model.vo.Pessoa;

public class PessoaDAO implements BaseDAO<Pessoa>{

	private static HashMap<String,Integer> tipos = new HashMap<String,Integer>();
	
	static {
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM TIPO ";
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		try {
			ResultSet conjuntoResultante = query.executeQuery();
			while(conjuntoResultante.next()) {
				String nomeTipo = conjuntoResultante.getString("DESCRICAO");
				Integer idTipo = conjuntoResultante.getInt("IDTIPO");
				tipos.put(nomeTipo, idTipo);
			}
			System.out.println("tipos construido com sucesso");
		}catch (SQLException e) {
			System.out.println("Erro ao consultar todas os tipos...\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conn);
		}
	}
	
	public int getIdTipo(String nomeTipo) {
		return tipos.get(nomeTipo);
	}
	
	public Pessoa inserir(Pessoa novaPessoa) {
		Connection conn = Banco.getConnection();
		
		String sql = " INSERT INTO PESSOA (IDTIPO, IDINSTITUICAO, NOME, DATA_NASCIMENTO, SEXO, CPF) "
				+ " VALUES (?,?,?,?,?,?)";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conn, sql);
		
		try {
			query.setInt(1, novaPessoa.getIdTipo());
			if (novaPessoa.getInstituicao() != null) {
				query.setInt(2, novaPessoa.getInstituicao().getId());
			} else {
				query.setString(2, null);
			}
			query.setString(3, novaPessoa.getNome());
			Date dataConvertidaSQL = java.sql.Date.valueOf(novaPessoa.getDataNascimento());
			query.setDate(4, dataConvertidaSQL);
			query.setString(5, novaPessoa.getSexo()+"");
			query.setString(6, novaPessoa.getCpf());
			
			int codigoRetorno = query.executeUpdate();
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				
				if(resultado.next()) {
					int chaveGerada = resultado.getInt(1);
					
					novaPessoa.setId(chaveGerada);
				}
			}
		} catch (SQLException e ) {
			System.out.println("Erro ao inserir pessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		
		return novaPessoa;
	}

	public boolean alterar(Pessoa pessoa) {
		Connection conn = Banco.getConnection();
		
		String sql = " UPDATE PESSOA "
				+ " SET IDTIPO=?, IDINSTITUICAO=?, NOME=?, DATA_NASCIMENTO=?, "
				+ "  SEXO=?, CPF=?"
				+ " WHERE IDPESSOA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		boolean alterou = false;
		
		try {
			query.setInt(1, pessoa.getIdTipo());
			if (pessoa.getInstituicao() != null) {
				query.setInt(2, pessoa.getInstituicao().getId());
			} else {
				query.setString(2, null);
			}
			query.setString(3, pessoa.getNome());
			Date dataConvertidaSQL = java.sql.Date.valueOf(pessoa.getDataNascimento());
			query.setDate(4, dataConvertidaSQL);
			query.setString(5, pessoa.getSexo() + "");
			query.setString(6, pessoa.getCpf());
			query.setInt(7, pessoa.getId());
			
			int codigoRetorno = query.executeUpdate();
			alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar pessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return alterou;
	}
	
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = " DELETE FROM PESSOA WHERE IDPESSOA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		boolean excluiu = false;
		
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pessoa (id: " + id + ") .\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return excluiu;
	}
	
	public Pessoa pesquisarPorId(int id) {
		//TODO
		return null;
	}
	
	public List<Pessoa> pesquisarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Pessoa construirDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
