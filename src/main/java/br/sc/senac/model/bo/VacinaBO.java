package br.sc.senac.model.bo;

import java.time.LocalDate;
import java.util.List;

import br.sc.senac.model.dao.VacinaDAO;
import br.sc.senac.model.exception.DataInicioPesquisaInvalidaException;
import br.sc.senac.model.seletor.AvaliacaoVacinaSeletor;
import br.sc.senac.model.seletor.VacinaSeletor;
import br.sc.senac.model.vo.Nota;
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

}
