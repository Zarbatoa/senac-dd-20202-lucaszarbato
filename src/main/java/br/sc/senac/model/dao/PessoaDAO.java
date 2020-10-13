package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.sc.senac.model.exception.TipoDePessoaImprevistoException;
import br.sc.senac.model.vo.Pesquisador;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.PublicoGeral;

public class PessoaDAO implements BaseDAO<Pessoa>{

	public Pessoa inserir(Pessoa novaPessoa) {
		Connection conn = Banco.getConnection();
		
		String sql = " INSERT INTO PESSOA (IDTIPO, IDINSTITUICAO, NOME, DATA_NASCIMENTO, SEXO, CPF) "
				+ " VALUES (?,?,?,?,?,?)";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		try {
			query.setInt(1, pegarTipoDePessoa(novaPessoa));
			
			query.setInt(2, pegarInstituicaoDePesquisador(novaPessoa));
			query.setString(3, novaPessoa.getNome());
			
			Date dataConvertidaSQL = java.sql.Date.valueOf(novaPessoa.getDataNascimento());
			query.setDate(4, dataConvertidaSQL);
			query.setString(5, novaPessoa.getSexo()+"");
			query.setString(6, novaPessoa.getCpf());
		} catch (SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TipoDePessoaImprevistoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return novaPessoa;
	}

	public boolean alterar(Pessoa pessoa) {
		//TODO
		return false;
	}
	
	public boolean excluir(int id) {
		//TODO
		return false;
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
	
	
	private int pegarTipoDePessoa(Pessoa novaPessoa) throws TipoDePessoaImprevistoException {
		int tipoPessoa = 0;
		
		if (novaPessoa instanceof Pesquisador) {
			tipoPessoa = pegarIdDoTipoDePessoa(Pessoa.TIPO_PESQUISADOR);
		} else if (novaPessoa instanceof PublicoGeral) {
			PublicoGeral novoPublicoGeral = (PublicoGeral) novaPessoa;
			if(novoPublicoGeral.isVoluntario()) {
				tipoPessoa = pegarIdDoTipoDePessoa(Pessoa.TIPO_VOLUNTARIO);
			} else {
				tipoPessoa = pegarIdDoTipoDePessoa(Pessoa.TIPO_PUBLICO_GERAL);
			}
		} else {
			throw new TipoDePessoaImprevistoException("Erro no método pegarTipoDePessoa(Pessoa) de PessoaDAO.");
		}
		
		return tipoPessoa;
	}

	private int pegarIdDoTipoDePessoa(String tipoPesquisador) throws TipoDePessoaImprevistoException {
		int idTipo = 0;
		Connection conn = Banco.getConnection();
		String sql = " SELECT IDTIPO FROM TIPO WHERE DESCRICAO=? ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conn, sql);
		
		try {
			query.setString(1, tipoPesquisador);
			
			int codigoRetorno = query.executeUpdate();
			
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				
				idTipo = resultado.getInt(1);
			} else {
				throw new TipoDePessoaImprevistoException("Erro no método pegarIdDoTipoDePessoa(String) de PessoaDAO.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o id do tipo de pessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return idTipo;
	}
	

	private Integer pegarInstituicaoDePesquisador(Pessoa novaPessoa) {
		Integer idInstituicao = null;
		
		if (novaPessoa instanceof Pesquisador) {
			Pesquisador novoPesquisador = (Pesquisador) novaPessoa;
			if (novoPesquisador.getInstituicao() != null) {
				idInstituicao = novoPesquisador.getInstituicao().getId();
			}
		}
		
		return idInstituicao;
	}

	
}
