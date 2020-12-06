package br.sc.senac.model.dto.informacaoRelatorio;

import java.util.ArrayList;
import java.util.List;

import br.sc.senac.controller.ControllerRelatorio;
import br.sc.senac.model.dto.VacinaNotaPessoaDTO;
import br.sc.senac.model.seletor.RelatorioSeletor;
//TODO lembrar de refatorar, nome lista e getDefHeaders
public class InfoTotalVacinasPorPesquisador extends AbstractInfoRelatorio {

	@Override
	public String[] getNomesColunas() {
		return new String[] {
				"Nome", "Sobrenome", "Número de Vacinas"
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

			dadoAtual[0] = dto.getNomePesquisador();
			dadoAtual[1] = dto.getSobrenomePesquisador();
			dadoAtual[2] = dto.getNumeroDeVacinas() + "";
			
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

			dadoAtual[0] = dto.getNomePesquisador();
			dadoAtual[1] = dto.getSobrenomePesquisador();
			dadoAtual[2] = dto.getNumeroDeVacinas() + "";
			
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
			
			dado[0] = dtoAtual.getNomePesquisador();
			dado[1] = dtoAtual.getSobrenomePesquisador();
			dado[2] = dtoAtual.getNumeroDeVacinas();
			
			dados.add(dado);
		}
		
		return dados;
	}

}
