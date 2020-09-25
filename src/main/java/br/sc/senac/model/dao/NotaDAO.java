package br.sc.senac.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.sc.senac.model.vo.Nota;

public class NotaDAO implements BaseDAO<Nota>{

	public Nota inserir(Nota novaNota) {
		//TODO
		return null;
	}
	
	public boolean alterar(Nota nota) {
		//TODO
		return false;
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
