package br.sc.senac.model.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.dao.VacinaDAO;
import br.sc.senac.model.exception.DataInicioPesquisaInvalidaException;
import br.sc.senac.model.seletor.VacinaSeletor;
import br.sc.senac.model.vo.Vacina;

public class VacinaBO {
	
	private VacinaDAO vacinaDAO = new VacinaDAO();

	public Vacina salvar(Vacina novaVacina) throws DataInicioPesquisaInvalidaException {
		if(novaVacina.getDataInicioPesquisa().isAfter(LocalDate.now())) {
			throw new DataInicioPesquisaInvalidaException("Data de inicio da pesquisa deve ser anterior a data atual.");
		}
		
		vacinaDAO.inserir(novaVacina);
		
		return novaVacina;
	}

	public List<Vacina> pegarListaDeVacinas() {
		return vacinaDAO.pesquisarTodos();
	}
	
	public List<Vacina> listarVacinas(VacinaSeletor seletor) {
		return vacinaDAO.listarComSeletor(seletor);
	}
	
	public void gerarPlanilhaVacinaTotalPorPesquisador(List<Vacina> vacinas, String caminhoEscolhido) {
		GeradorPlanilhaVacina gerador = new GeradorPlanilhaVacina();
		gerador.gerarPlanilhaVacinaTotalPorPesquisador(vacinas, caminhoEscolhido);
	}
	
	public void gerarPlanilhaVacinaTotalPorPaisDeOrigem(List<Vacina> vacinas, String caminhoEscolhido) {
		GeradorPlanilhaVacina gerador = new GeradorPlanilhaVacina();
		gerador.gerarPlanilhaVacinaTotalPorPaisOrigem(vacinas, caminhoEscolhido);
	}
	
	public void gerarPlanilhaVacinaTotalPorPaisDeOrigemPorPeriodo(List<Vacina> vacinas, String caminhoEscolhido) {
		GeradorPlanilhaVacina gerador = new GeradorPlanilhaVacina();
		gerador.gerarPlanilhaVacinaTotalPorPaisDeOrigemPorPeriodo(vacinas, caminhoEscolhido);
	}
	
	public void gerarPlanilhaVacinaTotalPorEstagioDePesquisa(List<Vacina> vacinas, String caminhoEscolhido) {
		GeradorPlanilhaVacina gerador = new GeradorPlanilhaVacina();
		gerador.gerarPlanilhaVacinaTotalPorEstagioDePesquisa(vacinas, caminhoEscolhido);
	}
	
	public void gerarPlanilhaVacinaTotalPorEstagioDePesquisaPorPeriodo(List<Vacina> vacinas, String caminhoEscolhido) {
		GeradorPlanilhaVacina gerador = new GeradorPlanilhaVacina();
		gerador.gerarPlanilhaVacinaTotalPorEstagioDePesquisaPorPeriodo(vacinas, caminhoEscolhido);
	}

	public String excluirVacinas(List<Integer> idsASeremExcluidos) {
		StringBuffer mensagem = new StringBuffer();
		StringBuffer msgVacinasExcluidasComSucesso = new StringBuffer();
		StringBuffer msgVacinasExcluidasSemSucesso = new StringBuffer();
		List<Vacina> vacinasExcluidas = new ArrayList<Vacina>();
		List<Boolean> statusVacinasExcluidas = new ArrayList<Boolean>();
		boolean nenhumaVacinaExcluida = true;
		
		for(Integer idASerExcluido : idsASeremExcluidos) {
			Vacina vacinaExcluida = vacinaDAO.pesquisarPorId(idASerExcluido);
			if (vacinaExcluida != null) {
				statusVacinasExcluidas.add(vacinaDAO.excluir(idASerExcluido));
				vacinasExcluidas.add(vacinaExcluida);
			}
		}
		
		for(int i = 0; i < statusVacinasExcluidas.size(); i++) {
			Boolean foiExcluida = statusVacinasExcluidas.get(i);
			Vacina vacinaExcluida = vacinasExcluidas.get(i);
			if(foiExcluida) {
				if(msgVacinasExcluidasComSucesso.length() > 0) {
					msgVacinasExcluidasComSucesso.append(", ");
				}
				msgVacinasExcluidasComSucesso.append(
						"(" + vacinaExcluida.getId() + " - " + vacinaExcluida.toString() + ")"
						);
				nenhumaVacinaExcluida = false;
			} else {
				if(msgVacinasExcluidasSemSucesso.length() > 0) {
					msgVacinasExcluidasSemSucesso.append(", ");
				}
				msgVacinasExcluidasSemSucesso.append(
						"(" + vacinaExcluida.getId() + " - " + vacinaExcluida.toString() + ")"
						);
			}
		}
		
		if(nenhumaVacinaExcluida) {
			mensagem.append("Nenhuma vacina excluída.");
		} else {
			mensagem.append("Vacina(s) ");
			mensagem.append(msgVacinasExcluidasComSucesso);
			mensagem.append(" excluída(s) com sucesso!\n");
			if(msgVacinasExcluidasSemSucesso.length() > 0) {
				mensagem.append("Não foi possível excluir a(s) pessoa(s) ");
				mensagem.append(msgVacinasExcluidasSemSucesso);
				mensagem.append('!');
			}
		}
		
		return mensagem.toString();
	}

	public boolean atualizar(Vacina vacinaAtualizada) throws DataInicioPesquisaInvalidaException {
		
		if(vacinaAtualizada.getDataInicioPesquisa().isAfter(LocalDate.now())) {
			throw new DataInicioPesquisaInvalidaException("Data de inicio da pesquisa deve ser anterior a data atual.");
		}
		
		return this.vacinaDAO.alterar(vacinaAtualizada);
	}

}
