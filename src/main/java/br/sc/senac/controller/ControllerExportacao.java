package br.sc.senac.controller;

import javax.swing.JOptionPane;

import br.sc.senac.model.bo.GeradorPlanilhas;
import br.sc.senac.view.PanelComDados;

public class ControllerExportacao {

	GeradorPlanilhas geradorPlanilhas = new GeradorPlanilhas();
	
	public void exportarVisivelParaXLS(String selectedFilePath, PanelComDados panelSelecionado) {
		String message = "";
		if(geradorPlanilhas.gerarPlanilhaDadosVisiveis(selectedFilePath, panelSelecionado)) {
			message = "Planilha gerada com sucesso";
		} else {
			message = "Não foi possível gerar a planilha";
		}
		
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void exportarCompletoParaXLS(String selectedFilePath, PanelComDados panelSelecionado) {
		String message = "";
		if(geradorPlanilhas.gerarPlanilhaDadosCompletos(selectedFilePath, panelSelecionado)) {
			message = "Planilha gerada com sucesso";
		} else {
			message = "Não foi possível gerar a planilha";
		}
		
		JOptionPane.showMessageDialog(null, message);
	}

}
