package br.sc.senac.model.bo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.sc.senac.model.vo.Vacina;

/**
 * Classe utilitária para gerar planilhas Excel através da API Apache POI.
 * 
 * @author Vilmar César Pereira Júnior
 *
 **/

public class GeradorPlanilhaVacina {
	
	// isso serve para a criação de qualquer planilha relacionada a vacina
	
	public void gerarPlanilhaVacinaTotalPorPesquisador(List<Vacina> vacinas, String caminhoEscolhido) {
		String[] columns = { "Nome Vacina","País de Origem", "Pesquisador Responsável", "Instituição", "Total de vacina por pesquisador" }; //teria que fazer um método no  VO vacina? 

		HSSFWorkbook planilha = new HSSFWorkbook();

		// 1) Cria uma aba na planilha (nome é um parâmetro opcional)
		HSSFSheet abaPlanilha = planilha.createSheet("Vacinas");

		Row headerRow = abaPlanilha.createRow(0);

		// 2) Cria o cabeçalho a partir de um array columns
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		// 3) Cria as linhas com os produtos da lista
		int rowNum = 1;
		for (Vacina vac : vacinas) {
			Row novaLinha = abaPlanilha.createRow(rowNum++);

			novaLinha.createCell(0).setCellValue(vac.getNome());
			novaLinha.createCell(3).setCellValue(vac.getPaisOrigem());
			novaLinha.createCell(1).setCellValue(vac.getPesquisadorResponsavel().getNomeCompleto());
			novaLinha.createCell(2).setCellValue(vac.getPesquisadorResponsavel().getInstituicao().getNome());
			// fazer o total de pesquisador por vacina. provavemente terá de ser um DTO --> DAO ???
		}

		// 4) Ajusta o tamanho de todas as colunas conforme a largura do
		// conteúdo
		for (int i = 0; i < columns.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}

		//5) Escreve o arquivo em disco, no caminho informado
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(caminhoEscolhido + ".xls");
			planilha.write(fileOut);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	}
	
	public void gerarPlanilhaVacinaTotalPorPaisOrigem(List<Vacina> vacinas, String caminhoEscolhido) {
		String[] columns = { "Nome Vacina","País de Origem", "Pesquisador Responsável", "Instituição", "Total de vacina por país de Origem" }; //teria que fazer um método no  VO vacina? 

		HSSFWorkbook planilha = new HSSFWorkbook();

		// 1) Cria uma aba na planilha (nome é um parâmetro opcional)
		HSSFSheet abaPlanilha = planilha.createSheet("Vacinas");

		Row headerRow = abaPlanilha.createRow(0);

		// 2) Cria o cabeçalho a partir de um array columns
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		// 3) Cria as linhas com os produtos da lista
		int rowNum = 1;
		for (Vacina vac : vacinas) {
			Row novaLinha = abaPlanilha.createRow(rowNum++);

			novaLinha.createCell(0).setCellValue(vac.getNome());
			novaLinha.createCell(3).setCellValue(vac.getPaisOrigem());
			novaLinha.createCell(1).setCellValue(vac.getPesquisadorResponsavel().getNomeCompleto());
			novaLinha.createCell(2).setCellValue(vac.getPesquisadorResponsavel().getInstituicao().getNome());
			// fazer o total de pesquisador por país origem. provavemente terá de ser um DTO 
		}

		// 4) Ajusta o tamanho de todas as colunas conforme a largura do
		// conteúdo
		for (int i = 0; i < columns.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}

		//5) Escreve o arquivo em disco, no caminho informado
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(caminhoEscolhido + ".xls");
			planilha.write(fileOut);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	}

	public void gerarPlanilhaVacinaTotalPorPaisDeOrigemPorPeriodo(List<Vacina> vacinas, String caminhoEscolhido) {
		// TODO Auto-generated method stub
		
	}

	public void gerarPlanilhaVacinaTotalPorEstagioDePesquisa(List<Vacina> vacinas, String caminhoEscolhido) {
		// TODO Auto-generated method stub
		
	}

	public void gerarPlanilhaVacinaTotalPorEstagioDePesquisaPorPeriodo(List<Vacina> vacinas, String caminhoEscolhido) {
		// TODO Auto-generated method stub
		
	}
	
	

}
