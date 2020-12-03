package br.sc.senac.model.bo;

import java.util.List;

import br.sc.senac.model.dao.RelatorioDAO;
import br.sc.senac.model.dto.VacinaNotaPessoaDTO;
import br.sc.senac.model.exception.FiltroObrigatorioException;
import br.sc.senac.model.seletor.RelatorioSeletor;

public class RelatorioBO {

	private RelatorioDAO relatorioDAO = new RelatorioDAO();
	
	public List<VacinaNotaPessoaDTO> consultarRelatorioTotalVacinasPorPesquisador(RelatorioSeletor seletor) {
		return relatorioDAO.gerarRelatorioTotalVacinasPesquisadorDAO(seletor);
	}

	public List<VacinaNotaPessoaDTO> consultarRelatorioTotalVacinasPaisOrigem(RelatorioSeletor seletor) {
		return relatorioDAO.gerarRelatorioTotalVacinasPaisOrigemDAO(seletor);
	}

	public List<VacinaNotaPessoaDTO> consultarRelatorioTotalVacinasEstagioPesquisa(RelatorioSeletor seletor) {
		return relatorioDAO.gerarRelatorioTotalVacinasEstagioPesquisaDAO(seletor);
	}

	public List<VacinaNotaPessoaDTO> consultarRelatorioNumeroAvaliacoesPorVacina(RelatorioSeletor seletor) {
		return relatorioDAO.gerarRelatorioNumeroDeAvaliacoesVacinaDAO(seletor);
	}

	public List<VacinaNotaPessoaDTO> consultarRelatorioMediaAvaliacoesPorVacina(RelatorioSeletor seletor) {
		return relatorioDAO.gerarRelatorioMediaDeAvaliacoesVacinaDAO(seletor);
	}

	public List<VacinaNotaPessoaDTO> consultarRelatorioTotalPessoasPorTipo(RelatorioSeletor seletor) {
		return relatorioDAO.gerarRelatorioTotalPessoasPorTipoDAO(seletor);
	}

	public List<VacinaNotaPessoaDTO> consultarRelatorioFaixasDeIdadeVacina(RelatorioSeletor seletor) throws FiltroObrigatorioException {
		if(!seletor.temFiltroVacina()) {
			throw new FiltroObrigatorioException("O filtro por Vacina é obrigatório, por favor cadastre uma Vacina");
		}
		return relatorioDAO.gerarRelatorioFaixasDeIdadeVacinaDAO(seletor);
	}

}
