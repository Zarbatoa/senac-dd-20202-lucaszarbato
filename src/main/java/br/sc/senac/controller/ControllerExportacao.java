package br.sc.senac.controller;

import java.io.File;

import javax.swing.JOptionPane;

import br.sc.senac.model.bo.GeradorPlanilhas;
import br.sc.senac.view.PanelComDados;

public class ControllerExportacao {

	GeradorPlanilhas geradorPlanilhas = new GeradorPlanilhas();
	
	public void exportarParaXLS(String selectedFilePath, PanelComDados panelSelecionado) {
		String message = "";
		if(geradorPlanilhas.gerarPlanilhaDadosVisiveis(selectedFilePath, panelSelecionado)) {
			message = "Planilha gerada com sucesso";
		} else {
			message = "N�o foi poss�vel gerar a planilha";
		}
		
		JOptionPane.showMessageDialog(null, message);
	}

}
