package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.Pessoa;

public class RelatorioAvaliacaoVacinaSeletor {
	
	//Atributos que servirão de filtros
		private Pessoa nome;
		
		//Filtragem de datas por período (início, fim)
		private LocalDate dataInicio;
		private LocalDate dataFim;
		
		//Atributos para possível paginação dos resultados (intervalo)
		private int limite;
		private int pagina;
		
		public RelatorioAvaliacaoVacinaSeletor() {
			//Default: traz os resultados sem limite e sem página
			this.limite = 0;
			this.pagina = -1;
		}
		
		public boolean temFiltro() {
			if ((this.nome.getNomeCompleto() != null) && (this.nome.getNomeCompleto().trim().length() > 0)) {
				return true;
			}
			if (this.dataInicio != null) {
				return true;
			}
			if (this.dataFim != null) {
				return true;
			}
			return false;
		}
			
		/**
		 * Verifica se os campos de paginacao estao preenchidos
		 *
		 * @return verdadeiro se os campos limite e pagina estao preenchidos
		 */
		public boolean temPaginacao() {
			return ((this.limite > 0) && (this.pagina > -1));
		}

		/**
		 * Calcula deslocamento (offset) a partir da pagina e do limite
		 *
		 * @return offset
		 */
		public int getOffset() {
			return (this.limite * (this.pagina - 1));
		}

		//getters e setters
		
		public Pessoa getNome() {
			return nome;
		}

		public void setNome(Pessoa nome) {
			this.nome = nome;
		}

		public LocalDate getDataInicio() {
			return dataInicio;
		}

		public void setDataInicio(LocalDate dataInicio) {
			this.dataInicio = dataInicio;
		}

		public LocalDate getDataFim() {
			return dataFim;
		}

		public void setDataFim(LocalDate dataFim) {
			this.dataFim = dataFim;
		}

		public int getLimite() {
			return limite;
		}

		public void setLimite(int limite) {
			this.limite = limite;
		}

		public int getPagina() {
			return pagina;
		}

		public void setPagina(int pagina) {
			this.pagina = pagina;
		}
		
}
