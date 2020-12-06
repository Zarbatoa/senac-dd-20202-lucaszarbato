package br.sc.senac.model.dto.informacaoRelatorio;

import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.dto.VacinaNotaPessoaDTO;
import br.sc.senac.model.seletor.RelatorioSeletor;

public class InfoModeloPadraoRelatorio extends AbstractInfoRelatorio {

	@Override
	public String[] getNomesColunas() {
		return new String[] {
				"Consulte um Retório para gerar a tabela"
			};
	}

	@Override
	public List<String[]> getUltimosDadosConsultadosVisiveis(RelatorioSeletor seletor) {
		return new ArrayList<String[]>();
	}

	@Override
	public List<String[]> getUltimosDadosConsultadosCompletos(RelatorioSeletor seletor) {
		return new ArrayList<String[]>();
	}

	@Override
	public Object[][] getDefaultDataComHeaders() {
		return new Object[][] {
			{"Consulte um Retório para gerar a tabela"},
			{null},
			{null},
			{null},
			{null},
			{null},
		};
	}

	@Override
	public List<Object[]> getDados(List<VacinaNotaPessoaDTO> dtos) {
		return new ArrayList<Object[]>();
	}

}
