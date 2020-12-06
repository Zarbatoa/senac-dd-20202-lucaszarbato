package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import br.sc.senac.controller.ControllerRelatorio;
import br.sc.senac.controller.ControllerVacina;
import br.sc.senac.model.dto.VacinaNotaPessoaDTO;
import br.sc.senac.model.dto.informacaoRelatorio.AbstractInfoRelatorio;
import br.sc.senac.model.dto.informacaoRelatorio.InfoMediaAvaliacoesPorVacina;
import br.sc.senac.model.dto.informacaoRelatorio.InfoModeloPadraoRelatorio;
import br.sc.senac.model.dto.informacaoRelatorio.InfoNumeroAvaliacoesPorVacina;
import br.sc.senac.model.dto.informacaoRelatorio.InfoRelatorioFaixasDeIdadeVacina;
import br.sc.senac.model.dto.informacaoRelatorio.InfoTotalPessoasPorTipo;
import br.sc.senac.model.dto.informacaoRelatorio.InfoTotalVacinasPorEstagioPesquisa;
import br.sc.senac.model.dto.informacaoRelatorio.InfoTotalVacinasPorPaisOrigem;
import br.sc.senac.model.dto.informacaoRelatorio.InfoTotalVacinasPorPesquisador;
import br.sc.senac.model.seletor.RelatorioSeletor;
import br.sc.senac.model.utilidades.Constantes;
import br.sc.senac.model.vo.Vacina;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaRelatorios extends PanelComDados {

	private JComboBox cbRelatorios;
	private JTable tableResultados;
	private DatePicker dpDataInicioPesquisa;
	private JComboBox cbVacinas;
	private JComboBox cbSexo;
	
	private JLabel lblPagAtual;
	
	private RelatorioSeletor ultimoSeletorUsado;
	private int paginaAtual = 1;
	
	private AbstractInfoRelatorio ultimoRelatorioConsultado;
	
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
		ultimoRelatorioConsultado = new InfoModeloPadraoRelatorio();
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

		atualizarInfoRelatorio(dtos);

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
			atualizarInfoRelatorio(dtos);
		}
	}
	
	private void atualizarInfoRelatorio(List<VacinaNotaPessoaDTO> dtos) {
		String ultimoRelatorio = ultimoSeletorUsado.getTipoDeRelatorio();
		if(ultimoRelatorio.equalsIgnoreCase(Constantes.TOTAL_VACINAS_POR_PESQUISADOR)) {
			ultimoRelatorioConsultado = new InfoTotalVacinasPorPesquisador();
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.TOTAL_VACINAS_POR_PAIS_ORIGEM)) {
			ultimoRelatorioConsultado = new InfoTotalVacinasPorPaisOrigem();
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.TOTAL_VACINAS_POR_ESTAGIO_PESQUISA)) {
			ultimoRelatorioConsultado = new InfoTotalVacinasPorEstagioPesquisa();
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.NUMERO_DE_AVALIACOES_POR_VACINA)) {
			ultimoRelatorioConsultado = new InfoNumeroAvaliacoesPorVacina();
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.MEDIA_DE_AVALIACOES_POR_VACINA)) {
			ultimoRelatorioConsultado = new InfoMediaAvaliacoesPorVacina();
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.TOTAL_DE_PESSOAS_POR_TIPO)) {
			ultimoRelatorioConsultado = new InfoTotalPessoasPorTipo();
		} else if(ultimoRelatorio.equalsIgnoreCase(Constantes.NUMERO_DE_PESSOAS_E_MEDIA_DA_NOTA_POR_IDADE_DE_UMA_VACINA)) {
			ultimoRelatorioConsultado = new InfoRelatorioFaixasDeIdadeVacina();
		} else {
			System.out.println("atualizarTabelaDtos(List<VacinaNotaPessoaDTO>) -> Tipo de relatório não encontrado");
		}
		
		atualizarDadosTabela(dtos);
	}

	private void atualizarDadosTabela(List<VacinaNotaPessoaDTO> dtos) {
		this.definirModeloTabela();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		List<Object[]> dados = ultimoRelatorioConsultado.getDados(dtos);
		
		for(int i = 0; i < dtos.size(); i++) {
			Object[] linhaAtual = dados.get(i);
			for(int j = 0; j < linhaAtual.length; j++) {
				Object valor = linhaAtual[j];
				modelo.setValueAt(valor, i+1, j);
			}
		}
		
	}
	
	private void definirModeloTabela() {
		tableResultados.setModel(new DefaultTableModel(
				ultimoRelatorioConsultado.getDefaultDataComHeaders(),
				ultimoRelatorioConsultado.getNomesColunas()
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
					"Consulte um Retório para gerar a tabela"
				}
			)  {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	}

	@Override
	public boolean hasDados() {
		return (ultimoSeletorUsado != null || 
				!(ultimoRelatorioConsultado instanceof InfoModeloPadraoRelatorio));
	}

	@Override
	public String[] getNomesColunas() {
		return ultimoRelatorioConsultado.getNomesColunas();
	}

	@Override
	public List<String[]> getDadosVisiveis() {
		return ultimoRelatorioConsultado.getUltimosDadosConsultadosVisiveis(ultimoSeletorUsado);
	}

	@Override
	public List<String[]> getDadosCompletos() {
		return ultimoRelatorioConsultado.getUltimosDadosConsultadosCompletos(ultimoSeletorUsado);
	}
	
}
