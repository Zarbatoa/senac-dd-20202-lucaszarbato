package br.sc.senac.controller;

import java.time.LocalDate;
import java.util.List;

import br.sc.senac.model.bo.VacinaBO;
import br.sc.senac.model.exception.DataInicioPesquisaInvalidaException;
import br.sc.senac.model.exception.NomeInvalidoException;
import br.sc.senac.model.exception.PesquisadorInvalidoException;
import br.sc.senac.model.seletor.VacinaSeletor;
import br.sc.senac.model.utilidades.Constantes;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;

public class ControllerVacina {
	
	VacinaBO vacinaBO = new VacinaBO();
	
	public String salvar(Vacina novaVacina) {
		String mensagem = "";
		
		try {
			this.validarVacina(novaVacina);
			novaVacina = vacinaBO.salvar(novaVacina);
			mensagem = "Vacina salva com sucesso! Id gerado: " + novaVacina.getId();
		} catch(NomeInvalidoException
				| PesquisadorInvalidoException
				| DataInicioPesquisaInvalidaException e) {
			mensagem = e.getMessage();
		}
		return mensagem;
	}

	private void validarVacina(Vacina novaVacina)
			throws NomeInvalidoException, PesquisadorInvalidoException,
			DataInicioPesquisaInvalidaException{
		this.validarNome(novaVacina.getNome());
		this.validarPesquisador(novaVacina.getPesquisadorResponsavel());
		this.validarDataInicio(novaVacina.getDataInicioPesquisa());
	}

	private void validarDataInicio(LocalDate dataInicioPesquisa) throws DataInicioPesquisaInvalidaException {
		if(dataInicioPesquisa == null) {
			throw new DataInicioPesquisaInvalidaException("Data inicio pesquisa precisa ser preenchida");
		}
	}

	private void validarPesquisador(Pessoa pesquisadorResponsavel) throws PesquisadorInvalidoException {
		if(pesquisadorResponsavel == null) {
			throw new PesquisadorInvalidoException("O campo pesquisador responsável é obrigatório, é necessário haver um pesquisador cadastrado.");
		}
	}

	private void validarNome(String nome) throws NomeInvalidoException {
		if(nome == null || nome.isEmpty()) {
			throw new NomeInvalidoException("O campo nome é obrigatório, ele deve ser preenchido.");
		}
		
		if(nome.length() < 3) {
			throw new NomeInvalidoException("O campo nome deve ter pelo menos 3 letras.");
		}
	}

	public List<Vacina> coletarTodasVacinas() {
		return vacinaBO.pegarListaDeVacinas();
	}
	
	public List<Vacina> listarVacinas(VacinaSeletor seletor) {
		//tratar paisOrigem, pesquisadorResponsavel, estagioPesquisa
		if(seletor.getPaisOrigem() != null &&
				seletor.getPaisOrigem().equalsIgnoreCase(Constantes.OPCAO_PAISES_TODOS.getNome())) {
			seletor.setPaisOrigem(null);
		}
		
		//TODO testar possiveis erros com constante,
		// criar regra de negocio no BO para nao deixar cadastro com nome "Todos" ?
		if(seletor.getPesquisadorResponsavel() != null &&
				seletor.getPesquisadorResponsavel().getNome().equalsIgnoreCase(Constantes.OPCAO_PESQUISADOR_RESPONSAVEL_TODOS.getNome())) {
			seletor.setPesquisadorResponsavel(null);
		}
		
		if(seletor.getEstagioPesquisa() != null &&
				Vacina.getStringEstagioDePesquisa(seletor.getEstagioPesquisa()) == null) {
			seletor.setEstagioPesquisa(null);
		}
		
		return vacinaBO.listarVacinas(seletor);
	}
	
	public String excluir(List<Integer> idsASeremExcluidos) {
		return vacinaBO.excluirVacinas(idsASeremExcluidos);
	}
	
	public void gerarRelatorioTotalVacinaPorPesquisador(List<Vacina> vacinas, String caminhoEscolhido, String tipoRelatorio) {
		if(tipoRelatorio.equals(Constantes.TIPO_RELATORIO_XLS)){
			vacinaBO.gerarPlanilhaVacinaTotalPorPesquisador(vacinas, caminhoEscolhido);
		}
	}
	
	public void gerarRelatorioTotalVacinaPorPaisDeOrigem(List<Vacina> vacinas, String caminhoEscolhido, String tipoRelatorio) {
		if(tipoRelatorio.equals(Constantes.TIPO_RELATORIO_XLS)){
			vacinaBO.gerarPlanilhaVacinaTotalPorPaisDeOrigem(vacinas, caminhoEscolhido);
		}
	}
	
	public void gerarRelatorioTotalVacinaPorPaisDeOrigemPorPeriodo(List<Vacina> vacinas, String caminhoEscolhido, String tipoRelatorio) {
		if(tipoRelatorio.equals(Constantes.TIPO_RELATORIO_XLS)){
			vacinaBO.gerarPlanilhaVacinaTotalPorPaisDeOrigemPorPeriodo(vacinas, caminhoEscolhido);
		}
	}
	
	public void gerarRelatorioTotalVacinaPorEstagioDePesquisa(List<Vacina> vacinas, String caminhoEscolhido, String tipoRelatorio) {
		if(tipoRelatorio.equals(Constantes.TIPO_RELATORIO_XLS)){
			vacinaBO.gerarPlanilhaVacinaTotalPorEstagioDePesquisa(vacinas, caminhoEscolhido);
		}
	}
	
	public void gerarRelatorioTotalVacinaPorEstagioDePesquisaPorPeriodo(List<Vacina> vacinas, String caminhoEscolhido, String tipoRelatorio) {
		if(tipoRelatorio.equals(Constantes.TIPO_RELATORIO_XLS)){
			vacinaBO.gerarPlanilhaVacinaTotalPorEstagioDePesquisaPorPeriodo(vacinas, caminhoEscolhido);
		}
	}

}
