package br.sc.senac.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.sc.senac.model.vo.Pessoa;

public class PessoaDAO implements BaseDAO<Pessoa>{

	public Pessoa inserir(Pessoa novaPessoa) {
		//TODO
		return null;
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
	
}
