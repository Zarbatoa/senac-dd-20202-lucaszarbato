package br.sc.senac.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.sc.senac.model.seletor.RelatorioAvaliacaoVacinaSeletor;
import br.sc.senac.model.vo.Nota;

public class RelatorioNotaDTO {
	
	private Nota construirDoResultSet(ResultSet result) {
		// TODO Auto-generated method stub
		return null;
	} // será que consigo fazer somente desse método???
	
	
	//aqui iniciam os métodos de filtros
	public ArrayList<Nota> listarComSeletor(RelatorioAvaliacaoVacinaSeletor seletor) {
		String sql = " SELECT * FROM NOTA n ";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Nota> notas = new ArrayList<Nota>();
		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				Nota n = construirDoResultSet(result); 
				notas.add(n);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoas com filtros.\nCausa: " + e.getMessage());
		}
		return notas;
	}
	
	private String criarFiltros(RelatorioAvaliacaoVacinaSeletor seletor, String sql) {

		// Tem pelo menos UM filtro
		sql += " WHERE ";
		boolean primeiro = true;

		if ((seletor.getDataInicio() != null) && (seletor.getDataFim()!= null)) {
			// Regra composta, olha as 3 opções de preenchimento do período

			// Todo o período preenchido (início e fim)
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.dataCadastro BETWEEN" + seletor.getDataInicio() + " AND " + seletor.getDataFim(); //arrumar variável para ficar igual ao sql
			primeiro = false;
		} else if (seletor.getDataInicio() != null) {
			// só início
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.dataCadastro >= " + seletor.getDataInicio(); //arrumar variável para ficar igual ao sql
			primeiro = false;
		} else if (seletor.getDataFim() != null) {
			// só o fim
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.dataCadastro <= " + seletor.getDataFim(); //arrumar variável para ficar igual ao sql
			primeiro = false;
		}
		return sql;
	}


}
