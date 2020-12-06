package br.sc.senac.model.dto.informacaoRelatorio;

import java.util.List;

import br.sc.senac.model.dto.VacinaNotaPessoaDTO;
import br.sc.senac.model.seletor.RelatorioSeletor;

public abstract class AbstractInfoRelatorio {
	
	public abstract String[] getNomesColunas();
	public abstract List<String[]> getUltimosDadosConsultadosVisiveis(RelatorioSeletor seletor);
	public abstract List<String[]> getUltimosDadosConsultadosCompletos(RelatorioSeletor seletor);

	public abstract Object[][] getDefaultDataComHeaders();
	
	public abstract List<Object[]> getDados(List<VacinaNotaPessoaDTO> dtos);
}
