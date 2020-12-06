package br.sc.senac.model.bo;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.sc.senac.view.PanelComDados;


public class GeradorPlanilhas {
	
	public boolean gerarPlanilhaDadosVisiveis(String selectedFilePath, PanelComDados panelSelecionado) {
		boolean sucesso = false;
		String[] columns = panelSelecionado.getNomesColunas();

		HSSFWorkbook planilha = new HSSFWorkbook();

		// 1) Cria uma aba na planilha (nome é um parâmetro opcional)
		HSSFSheet abaPlanilha = planilha.createSheet();

		Row headerRow = abaPlanilha.createRow(0);

		// 2) Cria o cabeçalho a partir de um array columns
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		// 3) Cria as linhas com os produtos da lista
		int rowNum = 1;
		
		for (String[] linha : panelSelecionado.getDadosVisiveis()) {
			Row novaLinha = abaPlanilha.createRow(rowNum++);
			int colNum = 0;
			for(String dadoCelula : linha) {
				novaLinha.createCell(colNum++).setCellValue(dadoCelula);
			}
		}

		// 4) Ajusta o tamanho de todas as colunas conforme a largura do conteúdo
		//TODO É necessário isso?
		for (int i = 0; i < columns.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}

		//5) Escreve o arquivo em disco, no caminho informado
		FileOutputStream fileOut = null;
		try {
			if(selectedFilePath.endsWith(".xls")) {
				fileOut = new FileOutputStream(selectedFilePath);
			} else {
				fileOut = new FileOutputStream(selectedFilePath + ".xls");
			}
			planilha.write(fileOut);
			sucesso = true;
		} catch (IOException e) {
			e.printStackTrace();
			sucesso = false;
		}finally{
			if(fileOut != null){
				try {
					fileOut.close();
					planilha.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sucesso;
	}
	
	
	public boolean gerarPlanilhaDadosCompletos(String selectedFilePath, PanelComDados panelSelecionado) {
		boolean sucesso = false;
		String[] columns = panelSelecionado.getNomesColunas();

		HSSFWorkbook planilha = new HSSFWorkbook();

		// 1) Cria uma aba na planilha (nome é um parâmetro opcional)
		HSSFSheet abaPlanilha = planilha.createSheet();

		Row headerRow = abaPlanilha.createRow(0);

		// 2) Cria o cabeçalho a partir de um array columns
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		// 3) Cria as linhas com os produtos da lista
		int rowNum = 1;
		
		for (String[] linha : panelSelecionado.getDadosCompletos()) {
			Row novaLinha = abaPlanilha.createRow(rowNum++);
			int colNum = 0;
			for(String dadoCelula : linha) {
				novaLinha.createCell(colNum++).setCellValue(dadoCelula);
			}
		}

		// 4) Ajusta o tamanho de todas as colunas conforme a largura do conteúdo
		//TODO É necessário isso?
		for (int i = 0; i < columns.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}

		//5) Escreve o arquivo em disco, no caminho informado
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(selectedFilePath + ".xls");
			planilha.write(fileOut);
			sucesso = true;
		} catch (IOException e) {
			e.printStackTrace();
			sucesso = false;
		}finally{
			if(fileOut != null){
				try {
					fileOut.close();
					planilha.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sucesso;
	}

}
