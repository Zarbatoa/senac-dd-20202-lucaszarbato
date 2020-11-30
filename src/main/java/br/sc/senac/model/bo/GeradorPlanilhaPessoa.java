package br.sc.senac.model.bo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.sc.senac.model.vo.Pessoa;

public class GeradorPlanilhaPessoa {
	
	public void gerarPlanilhaPessoaTotalPorSexo(List<Pessoa> pessoas, String caminhoEscolhido) {
		String[] columns = { "Sexo", "Total de pessoas por sexo" }; //teria que fazer um método no  VO vacina? 

		HSSFWorkbook planilha = new HSSFWorkbook();

		// 1) Cria uma aba na planilha (nome é um parâmetro opcional)
		HSSFSheet abaPlanilha = planilha.createSheet("Pessoas");

		Row headerRow = abaPlanilha.createRow(0);

		// 2) Cria o cabeçalho a partir de um array columns
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		// 3) Cria as linhas com os produtos da lista
		int rowNum = 1;
		for (Pessoa pes : pessoas) {
			Row novaLinha = abaPlanilha.createRow(rowNum++);

			novaLinha.createCell(0).setCellValue(pes.getSexo());
			// fazer o total de pessoas por sexo provavemente terá de ser um DTO --> DAO 
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

	public void gerarPlanilhaPessoaTotalPorSexoPorPeriodo(List<Pessoa> pessoas, String caminhoEscolhido) {
		// TODO Auto-generated method stub
		
	}
	
	

}
