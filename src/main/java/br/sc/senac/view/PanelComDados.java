package br.sc.senac.view;

import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class PanelComDados extends JPanel {
	
	// acho que este atributo vai ser util, somentente p relatorios
	protected boolean dadosPreenchidos;
	
	public boolean getDadosPreenchidos() {
		return dadosPreenchidos;
	}

	public void setDadosPreenchidos(boolean dadosPreenchidos) {
		this.dadosPreenchidos = dadosPreenchidos;
	}

	/**
	 * @return retorna se este Panel já preencheu os dados alguma vez
	 *   (no nosso caso seria se as JTables estão preenchidas e não foram limpas)
	 * */
	public boolean hasDados() {
		return dadosPreenchidos;
	}
	
	/**
	 * @return retorna um array de nomes da tabela de dados
	 * */
	public abstract String[] getNomesColunas();
	
	/**
	 * @return retorna os dados, que estão mostrando na JTable
	 *  (incluindo a primeira linha com as descrições), ou seja,
	 *  os dados com paginação
	 * */
	public abstract List<String[]> getDadosVisiveis();
	
	/**
	 * @return retorna todos os dados, sem os limites da paginação
	 *  (incluindo a primeira linha com as descrições)
	 * */
	public abstract List<String[]> getDadosCompletos();
}
