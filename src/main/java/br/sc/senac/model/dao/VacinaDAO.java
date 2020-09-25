package br.sc.senac.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.sc.senac.model.vo.Vacina;

public class VacinaDAO implements BaseDAO<Vacina>{

	public Vacina inserir(Vacina novaVacina) {
		//TODO
		return null;
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
