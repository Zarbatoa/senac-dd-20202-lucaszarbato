package br.sc.senac.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.sc.senac.model.bo.RelatorioBO;
import br.sc.senac.model.dto.VacinaNotaPessoaDTO;
import br.sc.senac.model.exception.FiltroObrigatorioException;
import br.sc.senac.model.seletor.RelatorioSeletor;
import br.sc.senac.model.utilidades.Constantes;

public class ControllerRelatorio {
	
	private RelatorioBO relatorioBO = new RelatorioBO();
	
	public List<VacinaNotaPessoaDTO> gerarUltimoRelatorio(RelatorioSeletor ultimoSeletorUsado) {
		return consultarRelatorio(ultimoSeletorUsado);
	}

	public List<VacinaNotaPessoaDTO> consultarRelatorio(RelatorioSeletor seletor) {
		String tipoRelatorio = seletor.getTipoDeRelatorio();
		List<VacinaNotaPessoaDTO> dtos = new ArrayList<VacinaNotaPessoaDTO>();
		
		//tratar seletor (sexo)
		if (seletor.getSexo() != null) {
			if(seletor.getSexo() != Constantes.SEXO_MASCULINO.charAt(0)
					  && seletor.getSexo() != Constantes.SEXO_FEMININO.charAt(0)) {
				seletor.setSexo(null);
			}
		}
		
		if(tipoRelatorio.equalsIgnoreCase(Constantes.TOTAL_VACINAS_POR_PESQUISADOR)) {
			dtos = relatorioBO.consultarRelatorioTotalVacinasPorPesquisador(seletor);
		} else if(tipoRelatorio.equalsIgnoreCase(Constantes.TOTAL_VACINAS_POR_PAIS_ORIGEM)) {
			dtos = relatorioBO.consultarRelatorioTotalVacinasPaisOrigem(seletor);
		} else if(tipoRelatorio.equalsIgnoreCase(Constantes.TOTAL_VACINAS_POR_ESTAGIO_PESQUISA)) {
			dtos = relatorioBO.consultarRelatorioTotalVacinasEstagioPesquisa(seletor);
		} else if(tipoRelatorio.equalsIgnoreCase(Constantes.NUMERO_DE_AVALIACOES_POR_VACINA)) {
			dtos = relatorioBO.consultarRelatorioNumeroAvaliacoesPorVacina(seletor);
		} else if(tipoRelatorio.equalsIgnoreCase(Constantes.MEDIA_DE_AVALIACOES_POR_VACINA)) {
			dtos = relatorioBO.consultarRelatorioMediaAvaliacoesPorVacina(seletor);
		} else if(tipoRelatorio.equalsIgnoreCase(Constantes.TOTAL_DE_PESSOAS_POR_TIPO)) {
			dtos = relatorioBO.consultarRelatorioTotalPessoasPorTipo(seletor);
		} else if(tipoRelatorio.equalsIgnoreCase(Constantes.NUMERO_DE_PESSOAS_E_MEDIA_DA_NOTA_POR_IDADE_DE_UMA_VACINA)) {
			try {
				dtos = relatorioBO.consultarRelatorioFaixasDeIdadeVacina(seletor);
			} catch (FiltroObrigatorioException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else {
			System.out.println("consultarRelatorio(RelatorioSeletor) -> Tipo de relatório não encontrado");
		}
		
		return dtos;
	}

}
