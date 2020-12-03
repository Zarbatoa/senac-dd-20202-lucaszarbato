package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import br.sc.senac.controller.ControllerRelatorio;
import br.sc.senac.controller.ControllerVacina;
import br.sc.senac.model.dto.VacinaNotaPessoaDTO;
import br.sc.senac.model.seletor.RelatorioSeletor;
import br.sc.senac.model.utilidades.Constantes;
import br.sc.senac.model.vo.Vacina;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaRelatorios extends JPanel {

	private JComboBox cbRelatorios;
	private JTable tableResultados;
	private DatePicker dpDataInicioPesquisa;
	private JComboBox cbVacinas;
	private JComboBox cbSexo;
	
	private JLabel lblPagAtual;
	
	private RelatorioSeletor ultimoSeletorUsado;
	private int paginaAtual = 1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelatorios frame = new TelaRelatorios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaRelatorios() throws ParseException {
		ControllerVacina controllerVacina = new ControllerVacina();

		setBounds(100, 100, 654, 452);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new MigLayout("", "[grow][27.00,grow][grow][][91.00][41.00][142.00,grow][42.00]", "[][][][][][][][][][]"));
		
		JLabel lbbRelatorios = new JLabel("Tela Relat\u00F3rios");
		lbbRelatorios.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(lbbRelatorios, "cell 3 0 3 1");
		
		cbRelatorios = new JComboBox();
		cbRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbRelatorios.getSelectedItem() == Constantes.NUMERO_DE_PESSOAS_E_MEDIA_DA_NOTA_POR_IDADE_DE_UMA_VACINA) {
					cbVacinas.setEnabled(true);
					cbSexo.setEnabled(false);
					dpDataInicioPesquisa.setEnabled(false);
				} else {
					cbVacinas.setEnabled(false);
					cbSexo.setEnabled(true);
					dpDataInicioPesquisa.setEnabled(true);
				}
					
			}
		});
		cbRelatorios.setModel(new DefaultComboBoxModel(Constantes.RELATORIO_VACINA_OPCOES));
		cbRelatorios.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(cbRelatorios, "cell 0 2 8 1,growx");
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblSexo, "cell 0 4,alignx trailing");
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		
		cbSexo = new JComboBox();
		cbSexo.setModel(new DefaultComboBoxModel(Constantes.OPCOES_SEXO_GERAL));
		this.add(cbSexo, "cell 1 4 2 1,growx");
		
		JLabel lblData = new JLabel("<html>Data<br /> In\u00EDcio Pesquisa</html>");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblData, "cell 5 4,alignx trailing");
		dpDataInicioPesquisa = new DatePicker(dateSettings);
		this.add(dpDataInicioPesquisa,"cell 6 4 2 1,growx");
		
		JLabel lblVacina = new JLabel("Vacina:");
		this.add(lblVacina, "cell 0 5,alignx trailing");
		
		List<Vacina> listaDeVacinas = controllerVacina.coletarTodasVacinas();
		cbVacinas = new JComboBox();
		cbVacinas.setEnabled(false);
		cbVacinas.setModel(new DefaultComboBoxModel(listaDeVacinas.toArray()));
		this.add(cbVacinas, "cell 1 5 2 1,growx");
		
		JButton btnConsultarRelatorio = new JButton("Consultar Relat\u00F3rio");
		btnConsultarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarRelatorio();
			}
		});
		btnConsultarRelatorio.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnConsultarRelatorio, "cell 2 6,alignx right");
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetarTodosOsCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnLimpar, "cell 4 6,alignx right");
		
		JButton btnGerarXls = new JButton("Gerar xls");
		btnGerarXls.setIcon(new ImageIcon("icones/iconeExcelmenor.png"));
		btnGerarXls.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnGerarXls, "cell 6 6");
		
		//método chamar xls no botão. Falta fazer o método de gerar relatório.
		btnGerarXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaArquivos = new JFileChooser();

				int opcaoSelecionada = janelaArquivos.showSaveDialog(null);

				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					//TODO quando eu for ver as telas de relatorio não se esquecer dessas variáveis
					@SuppressWarnings("unused")
					String caminho = janelaArquivos.getSelectedFile().getAbsolutePath();

					@SuppressWarnings("unused")
					ControllerVacina controller = new ControllerVacina();
					
					//TODO continuar implementacao proposta (onde esses argumentos serão declarados e instanciados?)
					String mensagem = ""; //controller.gerarRelatorioTotalVacinaPorPesquisador(vacinas, caminhoEscolhido, tipoRelatorio); //aqui está ligado ao método gerar relatório, que equivale a uma consulta de vacina

					JOptionPane.showMessageDialog(null, mensagem);
				}
			}
		});
		
		tableResultados = new JTable();
		tableResultados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		definirModeloPadraoTabela();
		this.add(tableResultados, "cell 0 8 8 1,grow");
		
		JButton btnPagAnterior = new JButton("< Anterior");
		btnPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnPagAnterior, "cell 2 9");
		
		btnPagAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				atualizarTabelaComUltimoSeletor();
			}
		});
		
		lblPagAtual = new JLabel("1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(lblPagAtual, "cell 4 9,alignx center");
		
		JButton btnPagProxima = new JButton("Pr\u00F3ximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnPagProxima, "cell 6 9");
		
		btnPagProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				atualizarTabelaComUltimoSeletor();
			}
		});
	}
	
	protected void consultarRelatorio() {
		paginaAtual = 1;
		lblPagAtual.setText(paginaAtual + "");

		ControllerRelatorio controlador = new ControllerRelatorio();
		RelatorioSeletor seletor = new RelatorioSeletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(Constantes.TAMANHO_PAGINA);
		
		// Preenche os campos de filtro da tela no seletor
		seletor.setTipoDeRelatorio((String)cbRelatorios.getSelectedItem());
		seletor.setSexo(cbSexo.getSelectedItem().toString().charAt(0));
		seletor.setDataInicioPesquisa(dpDataInicioPesquisa.getDate());
		if (seletor.getTipoDeRelatorio().equalsIgnoreCase(Constantes.NUMERO_DE_PESSOAS_E_MEDIA_DA_NOTA_POR_IDADE_DE_UMA_VACINA)) {
			seletor.setVacina((Vacina)cbVacinas.getSelectedItem());
		}
		
		// aqui é feita a consulta das pessoas e atualizada a tabela
		List<VacinaNotaPessoaDTO> dtos = controlador.consultarRelatorio(seletor);
		ultimoSeletorUsado = seletor;

		atualizarTabelaDtos(dtos);

	}

	protected void resetarTodosOsCampos() {
		cbSexo.setSelectedIndex(0);
		dpDataInicioPesquisa.clear();
		if(cbVacinas.getItemCount() != 0) {
			cbVacinas.setSelectedIndex(0);
		}
		definirModeloPadraoTabela();
		
	}

	protected void atualizarTabelaComUltimoSeletor() {
		if(ultimoSeletorUsado != null) {
			lblPagAtual.setText(paginaAtual + "");
			ultimoSeletorUsado.setPagina(paginaAtual);
			ultimoSeletorUsado.setLimite(Constantes.TAMANHO_PAGINA);
			ControllerRelatorio controlador = new ControllerRelatorio();
			List<VacinaNotaPessoaDTO> dtos = controlador.gerarUltimoRelatorio(ultimoSeletorUsado);
			atualizarTabelaDtos(dtos);
		}
	}
	
	private void atualizarTabelaDtos(List<VacinaNotaPessoaDTO> dtos) {
		String ultimoRelatorio = ultimoSeletorUsado.getTipoDeRelatorio();
		if(ultimoRelatorio.equalsIgnoreCase(Constantes.TOTAL_VACINAS_POR_PESQUISADOR)) {
			atualizarTabelaTotalVacinasPorPesquisador(dtos);
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.TOTAL_VACINAS_POR_PAIS_ORIGEM)) {
			atualizarTabelaTotalVacinasPaisOrigem(dtos);
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.TOTAL_VACINAS_POR_ESTAGIO_PESQUISA)) {
			atualizarTabelaTotalVacinasEstagioPesquisa(dtos);
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.NUMERO_DE_AVALIACOES_POR_VACINA)) {
			atualizarTabelaNumeroAvaliacoesPorVacina(dtos);
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.MEDIA_DE_AVALIACOES_POR_VACINA)) {
			atualizarTabelaMediaAvaliacoesPorVacina(dtos);
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.TOTAL_DE_PESSOAS_POR_TIPO)) {
			atualizarTabelaTotalPessoasPorTipo(dtos);
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.NUMERO_DE_PESSOAS_E_MEDIA_DA_NOTA_POR_IDADE_DE_UMA_VACINA)) {
			atualizarTabelaFaixasDeIdadeVacina(dtos);
		} else {
			System.out.println("atualizarTabelaDtos(List<VacinaNotaPessoaDTO>) -> Tipo de relatório não encontrado");
		}
	}

	private void atualizarTabelaFaixasDeIdadeVacina(List<VacinaNotaPessoaDTO> dtos) {
		this.definirModeloFaixasDeIdadeVacina();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		for (int i = 0; i < dtos.size(); i++) {
			VacinaNotaPessoaDTO dto = dtos.get(i);			
			modelo.setValueAt(dto.getFaixas(), i+1, 0);
			modelo.setValueAt(dto.getTotal(), i+1, 1);
			modelo.setValueAt(dto.getMedia_nota(), i+1, 2);
		}
	}

	private void definirModeloFaixasDeIdadeVacina() {
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					{"Faixas de Idade", "Número de Pessoas", "Média das Notas"},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {
					"Faixas de Idade", "Número de Pessoas", "Média das Notas"
				}
			)  {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	}

	private void atualizarTabelaTotalPessoasPorTipo(List<VacinaNotaPessoaDTO> dtos) {
		this.definirModeloTotalPessoasPorTipo();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		for (int i = 0; i < dtos.size(); i++) {
			VacinaNotaPessoaDTO dto = dtos.get(i);			
			modelo.setValueAt(dto.getDescricaoTipoPessoa(), i+1, 0);
			modelo.setValueAt(dto.getNumeroDePessoas(), i+1, 1);
		}
	}

	private void definirModeloTotalPessoasPorTipo() {
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					{"Categoria Pessoa", "Número de Pessoas"},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
					"categoriaPessoa", "numeroPessoas"
				}
			)  {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	}

	private void atualizarTabelaMediaAvaliacoesPorVacina(List<VacinaNotaPessoaDTO> dtos) {
		this.definirModeloMediaAvaliacoesPorVacina();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		for (int i = 0; i < dtos.size(); i++) {
			VacinaNotaPessoaDTO dto = dtos.get(i);			
			modelo.setValueAt(dto.getNomeVacina(), i+1, 0);
			modelo.setValueAt(dto.getMediaDeAvaliacoes(), i+1, 1);
		}
	}

	private void definirModeloMediaAvaliacoesPorVacina() {
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					{"Nome Vacina", "Média de Avaliações"},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
					"nomeVacina", "Média de Avaliações"
				}
			)  {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	}

	private void atualizarTabelaNumeroAvaliacoesPorVacina(List<VacinaNotaPessoaDTO> dtos) {
		this.definirModeloNumeroAvaliacoesPorVacina();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		for (int i = 0; i < dtos.size(); i++) {
			VacinaNotaPessoaDTO dto = dtos.get(i);			
			modelo.setValueAt(dto.getNomeVacina(), i+1, 0);
			modelo.setValueAt(dto.getNumeroDeAvaliacoes(), i+1, 1);
		}
	}

	private void definirModeloNumeroAvaliacoesPorVacina() {
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					{"Nome Vacina", "Número de Avaliações"},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
					"nomeVacina", "Número de Avaliações"
				}
			)  {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	}

	private void atualizarTabelaTotalVacinasEstagioPesquisa(List<VacinaNotaPessoaDTO> dtos) {
		this.definirModeloTotalVacinasEstagioPesquisa();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		for (int i = 0; i < dtos.size(); i++) {
			VacinaNotaPessoaDTO dto = dtos.get(i);			
			modelo.setValueAt(Vacina.getStringEstagioDePesquisa(dto.getEstagioPesquisa()), i+1, 0);
			modelo.setValueAt(dto.getNumeroDeVacinas(), i+1, 1);
		}
	}

	private void definirModeloTotalVacinasEstagioPesquisa() {
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					{"Estágio Pesquisa", "Número de Vacinas"},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
					"EstagioPesquisa", "Número de Vacinas"
				}
			)  {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	}

	private void atualizarTabelaTotalVacinasPaisOrigem(List<VacinaNotaPessoaDTO> dtos) {
		this.definirModeloTotalVacinasPaisOrigem();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		for (int i = 0; i < dtos.size(); i++) {
			VacinaNotaPessoaDTO dto = dtos.get(i);			
			modelo.setValueAt(dto.getPaisOrigem(), i+1, 0);
			modelo.setValueAt(dto.getNumeroDeVacinas(), i+1, 1);
		}
	}
	
	private void definirModeloTotalVacinasPaisOrigem() {
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					{"País Origem", "Número de Vacinas"},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
					"PaisOrigem", "Número de Vacinas"
				}
			)  {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	}

	private void atualizarTabelaTotalVacinasPorPesquisador(List<VacinaNotaPessoaDTO> dtos) {
		this.definirModeloTotalVacinasPorPesquisadorTabela();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		for (int i = 0; i < dtos.size(); i++) {
			VacinaNotaPessoaDTO dto = dtos.get(i);			
			modelo.setValueAt(dto.getNomePesquisador(), i+1, 0);
			modelo.setValueAt(dto.getSobrenomePesquisador(), i+1, 1);
			modelo.setValueAt(dto.getNumeroDeVacinas(), i+1, 2);
		}
	}

	private void definirModeloTotalVacinasPorPesquisadorTabela() {
		// nome, sobrenome, numero de vacinas
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					{"Nome", "Sobrenome", "Número de Vacinas"},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {
					"Nome", "Sobrenome", "Número de Vacinas"
				}
			)  {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	}

	private void definirModeloPadraoTabela() {
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					{"Consulte um Retório para gerar a tabela"},
					{null},
					{null},
					{null},
					{null},
					{null},
				},
				new String[] {
					"ColunaDescricao"
				}
			)  {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	}
	
}
