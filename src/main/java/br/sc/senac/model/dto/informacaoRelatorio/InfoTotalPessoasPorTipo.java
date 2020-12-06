package br.sc.senac.model.dto.informacaoRelatorio;

import java.util.ArrayList;
import java.util.List;

import br.sc.senac.controller.ControllerRelatorio;
import br.sc.senac.model.dto.VacinaNotaPessoaDTO;
import br.sc.senac.model.seletor.RelatorioSeletor;

public class InfoTotalPessoasPorTipo extends AbstractInfoRelatorio {

	@Override
	public String[] getNomesColunas() {
		return new String[] {
				"Categoria Pessoa", "Número de Pessoas"
			};
	}

	@Override
	public List<String[]> getUltimosDadosConsultadosVisiveis(RelatorioSeletor seletor) {
		List<String[]> dadosVisiveis = new ArrayList<String[]>();
		ControllerRelatorio controller = new ControllerRelatorio();

		List<VacinaNotaPessoaDTO> listaVisivel = controller.consultarRelatorio(seletor);
		
		for (int i = 0; i < listaVisivel.size(); i++) {
			String[] dadoAtual = new String[2];
			VacinaNotaPessoaDTO dto = listaVisivel.get(i);

			dadoAtual[0] = dto.getDescricaoTipoPessoa();
			dadoAtual[1] = dto.getNumeroDePessoas() + "";
			
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
			String[] dadoAtual = new String[2];
			VacinaNotaPessoaDTO dto = listaCompleta.get(i);

			dadoAtual[0] = dto.getDescricaoTipoPessoa();
			dadoAtual[1] = dto.getNumeroDePessoas() + "";
			
			dadosCompletos.add(dadoAtual);
		}
		return dadosCompletos;
	}

	@Override
	public Object[][] getDefaultDataComHeaders() {
		return new Object[][] {
			getNomesColunas(),
			{null, null},
			{null, null},
			{null, null},
			{null, null},
			{null, null},
		};
	}

	@Override
	public List<Object[]> getDados(List<VacinaNotaPessoaDTO> dtos) {
		List<Object[]> dados = new ArrayList<Object[]>();
		
		for(VacinaNotaPessoaDTO dtoAtual : dtos) {
			Object[] dado = new Object[2];
			
			dado[0] = dtoAtual.getDescricaoTipoPessoa();
			dado[1] = dtoAtual.getNumeroDePessoas();
			
			dados.add(dado);
		}
		
		return dados;
	}

}
