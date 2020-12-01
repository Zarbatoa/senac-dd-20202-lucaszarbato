package br.sc.senac.model.bo;

import java.util.List;

import br.sc.senac.model.dao.NotaDAO;
import br.sc.senac.model.exception.ValorInvalidoException;
import br.sc.senac.model.seletor.AvaliacaoVacinaSeletor;
import br.sc.senac.model.vo.Nota;

public class NotaBO {

	private NotaDAO notaDAO = new NotaDAO();
	
	public Nota salvar(Nota novaNota) throws ValorInvalidoException {
		if(novaNota.getValor() < 1 || novaNota.getValor() > 5) {
			throw new ValorInvalidoException("O valor de nota deve ser entre 1.0 e 5.0 !");
		}
		
		notaDAO.inserir(novaNota);
		
		return novaNota;
	}
	
	public List<Nota> listarNotas(AvaliacaoVacinaSeletor seletor) {
		return notaDAO.listarComSeletor(seletor);
	}
	
	public void gerarPlanilhaMediaNotaPorVacina(List<Nota> notas, String caminhoEscolhido) {
		GeradorPlanilhaAvaliacaoVacina gerador = new GeradorPlanilhaAvaliacaoVacina();
		gerador.gerarPlanilhaMediaNotaPorVacina(notas, caminhoEscolhido);
	}

}
