package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.vo.Pessoa;

public class PessoaDAO implements BaseDAO<Pessoa>{
	
	public Pessoa inserir(Pessoa novaPessoa) {
		Connection conn = Banco.getConnection();
		
		String sql = " INSERT INTO PESSOA (IDTIPO, IDINSTITUICAO, NOME, SOBRENOME, DATA_NASCIMENTO, SEXO, CPF) "
				+ " VALUES (?,?,?,?,?,?,?)";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conn, sql);
		
		try {
			query.setInt(1, novaPessoa.getTipo().getId());
			if (novaPessoa.getInstituicao() != null) {
				query.setInt(2, novaPessoa.getInstituicao().getId());
			} else {
				query.setString(2, null);
			}
			query.setString(3, novaPessoa.getNome());
			query.setString(4, novaPessoa.getSobrenome());
			Date dataConvertidaSQL = java.sql.Date.valueOf(novaPessoa.getDataNascimento());
			query.setDate(5, dataConvertidaSQL);
			query.setString(6, novaPessoa.getSexo()+"");
			query.setString(7, novaPessoa.getCpf());
			
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
				+ " SET IDTIPO=?, IDINSTITUICAO=?, NOME=?, SOBRENOME=?, "
				+ "  DATA_NASCIMENTO=?, SEXO=?, CPF=? "
				+ " WHERE IDPESSOA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		boolean alterou = false;
		
		try {
			query.setInt(1, pessoa.getTipo().getId());
			if (pessoa.getInstituicao() != null) {
				query.setInt(2, pessoa.getInstituicao().getId());
			} else {
				query.setString(2, null);
			}
			query.setString(3, pessoa.getNome());
			query.setString(4, pessoa.getSobrenome());
			Date dataConvertidaSQL = java.sql.Date.valueOf(pessoa.getDataNascimento());
			query.setDate(5, dataConvertidaSQL);
			query.setString(6, pessoa.getSexo() + "");
			query.setString(7, pessoa.getCpf());
			query.setInt(8, pessoa.getId());
			
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
		Connection conn = Banco.getConnection();
		
		String sql = " SELECT * FROM PESSOA WHERE IDPESSOA=? ";
		Pessoa pessoaBuscada = null;
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		try {
			query.setInt(1, id);
			ResultSet conjuntoResultante = query.executeQuery();
			
			if(conjuntoResultante.next()) {
				pessoaBuscada = construirDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por IDPESSOA (id: " + id + ") .\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return pessoaBuscada;
	}
	
	public List<Pessoa> pesquisarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA ";
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		List<Pessoa> pessoasBuscadas = new ArrayList<Pessoa>();
		
		try {
			ResultSet conjuntoResultante = query.executeQuery();
			while(conjuntoResultante.next()) {
				Pessoa pessoaBuscada = construirDoResultSet(conjuntoResultante);
				pessoasBuscadas.add(pessoaBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas as pessoas.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conn);
		}
		
		return pessoasBuscadas;
	}

	public boolean cpfJaCadastrado(Pessoa umaPessoa) {
		boolean jaCadastrado = false;

		Connection conexao = Banco.getConnection();
		String sql = "SELECT count(idpessoa) FROM PESSOA WHERE CPF = ?";
		
		if(umaPessoa.getId() > 0) {
			sql += " AND IDPESSOA <> ? ";
		}
		
		PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);
		
		try {
			consulta.setString(1, umaPessoa.getCpf());
			
			if(umaPessoa.getId() > 0) {
				consulta.setInt(2, umaPessoa.getId());
			}
	
			ResultSet conjuntoResultante = consulta.executeQuery();
			jaCadastrado = conjuntoResultante.next();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se CPF (" + umaPessoa.getCpf() + ") já foi usado .\nCausa: " + e.getMessage());
		}finally {
			Banco.closeStatement(consulta);
			Banco.closeConnection(conexao);
		}
		return jaCadastrado;
	}

	public Pessoa construirDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		Pessoa pessoaBuscada = new Pessoa();
		InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
		TipoPessoaDAO tipoDAO = new TipoPessoaDAO();
		
		pessoaBuscada.setId(conjuntoResultante.getInt("IDPESSOA"));
		
		int idTipo = conjuntoResultante.getInt("IDTIPO");
		pessoaBuscada.setTipo(tipoDAO.pesquisarPorId(idTipo));
		
		int idInstituicao = conjuntoResultante.getInt("IDINSTITUICAO");
		pessoaBuscada.setInstituicao(instituicaoDAO.pesquisarPorId(idInstituicao));
		
		pessoaBuscada.setNome(conjuntoResultante.getString("NOME"));
		pessoaBuscada.setSobrenome(conjuntoResultante.getString("SOBRENOME"));
		pessoaBuscada.setDataNascimento(conjuntoResultante.getDate("DATA_NASCIMENTO").toLocalDate());
		pessoaBuscada.setSexo(conjuntoResultante.getString("SEXO").charAt(0));
		pessoaBuscada.setCpf(conjuntoResultante.getString("CPF"));
		
		return pessoaBuscada;
	}
	
	
	
}
