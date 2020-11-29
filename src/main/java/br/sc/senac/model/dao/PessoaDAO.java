package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.seletor.PessoaSeletor;
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
		String sql = " SELECT * FROM PESSOA WHERE CPF = ? ";
		
		PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);
		
		try {
			consulta.setString(1, umaPessoa.getCpf());
	
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

	public List<Pessoa> pesquisarTodosPesquisadores() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA "
				+ " WHERE IDTIPO=1 ";
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		List<Pessoa> pessoasBuscadas = new ArrayList<Pessoa>();
		
		try {
			ResultSet conjuntoResultante = query.executeQuery();
			while(conjuntoResultante.next()) {
				Pessoa pessoaBuscada = construirDoResultSet(conjuntoResultante);
				pessoasBuscadas.add(pessoaBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas os pesquisadores.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conn);
		}
		
		return pessoasBuscadas;
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
	
	// a partir daqui estão os métodos dos filtros
	public ArrayList<Pessoa> listarComSeletor(PessoaSeletor seletor) {
		String sql = " SELECT * FROM PESSOA p";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				Pessoa p = construirDoResultSet(result); 
				pessoas.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoas com filtros.\nCausa: " + e.getMessage());
		}
		return pessoas;
	}
	
	private String criarFiltros(PessoaSeletor seletor, String sql) {
		
		// Tem pelo menos UM filtro
		sql += " WHERE ";
		boolean primeiro = true;

		if ((seletor.getNome() != null) && (seletor.getNome().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.nome LIKE '%" + seletor.getNome() + "%'";
			primeiro = false;
		}

		if ((seletor.getSobrenome() != null) && (seletor.getSobrenome().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.sobrenome LIKE '%" + seletor.getSobrenome() + "%";
			primeiro = false;
		}
		
		if ((seletor.getSexo() != 0) && (seletor.getSexo() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.sexo = '" + seletor.getSexo() + "'";
			primeiro = false;
		}
		
		if ((seletor.getCpf() != null) && (seletor.getCpf().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.cpf = '" + seletor.getCpf() + "'";
			primeiro = false;
		}
		
		// dúvidas de como fazer isso no banco de dados...ou senão tiramos por instituição e tipo - não sei se ainda está correto
		if ((seletor.getNomeInstituicao() != null) && (seletor.getNomeInstituicao().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.idinstituicao = '" + seletor.getNomeInstituicao() + "'"; // dúvida pois o nome da instituição veio do DAO INSTITUIÇÃO
			primeiro = false;
		}
		
		if ((seletor.getTipo().getDescricao() != null) && (seletor.getTipo().getDescricao().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.idtipo = '" + seletor.getTipo() + "'"; // dúvida pois o nome da instituição veio do DAO TIPO
			primeiro = false;
		}
		
		if (seletor.getDataNascimento() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.data_nascimento = '" + seletor.getDataNascimento() + "'";
			primeiro = false;
		}
		return sql;
	}
}
