package br.sc.senac.model.dto.informacaoRelatorio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.controller.ControllerRelatorio;
import br.sc.senac.model.dto.VacinaNotaPessoaDTO;
import br.sc.senac.model.seletor.RelatorioSeletor;

public class InfoRelatorioFaixasDeIdadeVacina extends AbstractInfoRelatorio {

	private DecimalFormat df = new DecimalFormat("0.00");
	
	@Override
	public String[] getNomesColunas() {
		return new String[] {
				"Faixas de Idade", "Número de Pessoas", "Média das Notas"
			};
	}

	@Override
	public List<String[]> getUltimosDadosConsultadosVisiveis(RelatorioSeletor seletor) {
		List<String[]> dadosVisiveis = new ArrayList<String[]>();
		ControllerRelatorio controller = new ControllerRelatorio();

		List<VacinaNotaPessoaDTO> listaVisivel = controller.consultarRelatorio(seletor);
		
		for (int i = 0; i < listaVisivel.size(); i++) {
			String[] dadoAtual = new String[3];
			VacinaNotaPessoaDTO dto = listaVisivel.get(i);

			dadoAtual[0] = dto.getFaixas();
			dadoAtual[1] = dto.getTotal() + "";
			dadoAtual[2] = df.format(dto.getMedia_nota());
			
			dadosVisiveis.add(dadoAtual);
		}
		return dadosVisiveis;
	}

	@Override
	public List<String[]> getUltimosDadosConsultadosCompletos(RelatorioSeletor seletor) {
		List<String[]> dadosCompletos = new ArrayList<String[]>();
		ControllerRelatorio controller = new ControllerRelatorio();

		int ultimaPagUsada = seletor.getPagina();
		seletor.setPagina(-1);
		List<VacinaNotaPessoaDTO> listaCompleta = controller.consultarRelatorio(seletor);
		seletor.setPagina(ultimaPagUsada);
		
		for (int i = 0; i < listaCompleta.size(); i++) {
			String[] dadoAtual = new String[3];
			VacinaNotaPessoaDTO dto = listaCompleta.get(i);

			dadoAtual[0] = dto.getFaixas();
			dadoAtual[1] = dto.getTotal() + "";
			dadoAtual[2] = df.format(dto.getMedia_nota());
			
			dadosCompletos.add(dadoAtual);
		}
		return dadosCompletos;
	}

	@Override
	public Object[][] getDefaultDataComHeaders() {
		return new Object[][] {
			getNomesColunas(),
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		};
	}

	@Override
	public List<Object[]> getDados(List<VacinaNotaPessoaDTO> dtos) {
		List<Object[]> dados = new ArrayList<Object[]>();
		
		for(VacinaNotaPessoaDTO dtoAtual : dtos) {
			Object[] dado = new Object[3];
			
			dado[0] = dtoAtual.getFaixas();
			dado[1] = dtoAtual.getTotal();
			dado[2] = df.format(dtoAtual.getMedia_nota());
			
			dados.add(dado);
		}
		
		return dados;
	}

}
