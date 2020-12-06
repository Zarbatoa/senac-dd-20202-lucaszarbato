package br.sc.senac.model.bo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

	/*
	 * referencia pdf:
	 * https://www.baeldung.com/java-pdf-creation
	 * */
	
	public boolean gerarPDFDadosVisiveis(String selectedFilePath, PanelComDados panelSelecionado) {
		FileOutputStream fileOut = null;
		// modo paisagem
		Document document = new Document(PageSize.A4.rotate());
		boolean sucesso = false;
		
		String[] columns = panelSelecionado.getNomesColunas();
		List<String[]> dadosVisiveis = panelSelecionado.getDadosVisiveis();
		
		try {
			if(selectedFilePath.endsWith(".pdf")) {
				fileOut = new FileOutputStream(selectedFilePath);
			} else {
				fileOut = new FileOutputStream(selectedFilePath + ".pdf");
			}
			PdfWriter.getInstance( document, fileOut);
			
			document.open();
			
			// Criar uma tabela do pdf com o tamanho adequado
			PdfPTable table = new PdfPTable(columns.length);
			
			// Criar o cabeçalho
			for(int i = 0; i < columns.length; i++) {
				PdfPCell header = new PdfPCell();
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(columns[i]));
				table.addCell(header);
			}
			
			// Criar as outras linhas
			for (String[] linha : dadosVisiveis) {
				for(String dadoCelula : linha) {
					table.addCell(dadoCelula);
				}
			}
			// Fim
			document.add(table);
			sucesso = true;
		} catch(IOException
				| DocumentException e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			if(fileOut != null){
				document.close();
//				fileOut.close();
			}
		}
		return sucesso;
	}


	public boolean gerarPDFDadosCompletos(String selectedFilePath, PanelComDados panelSelecionado) {
		FileOutputStream fileOut = null;
		// modo paisagem
		Document document = new Document(PageSize.A4.rotate());
		boolean sucesso = false;
		
		String[] columns = panelSelecionado.getNomesColunas();
		List<String[]> dadosCompletos = panelSelecionado.getDadosCompletos();
		
		try {
			if(selectedFilePath.endsWith(".pdf")) {
				fileOut = new FileOutputStream(selectedFilePath);
			} else {
				fileOut = new FileOutputStream(selectedFilePath + ".pdf");
			}
			PdfWriter.getInstance( document, fileOut);
			
			document.open();
			
			// Criar uma tabela do pdf com o tamanho adequado
			PdfPTable table = new PdfPTable(columns.length);
			
			// Criar o cabeçalho
			for(int i = 0; i < columns.length; i++) {
				PdfPCell header = new PdfPCell();
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(columns[i]));
				table.addCell(header);
			}
			
			// Criar as outras linhas
			for (String[] linha : dadosCompletos) {
				for(String dadoCelula : linha) {
					table.addCell(dadoCelula);
				}
			}
			// Fim
			document.add(table);
			sucesso = true;
		} catch(IOException
				| DocumentException e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			if(fileOut != null){
				document.close();
//				fileOut.close();
			}
		}
		return sucesso;
	}

}
