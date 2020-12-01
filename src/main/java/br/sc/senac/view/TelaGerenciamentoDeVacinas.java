package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import br.sc.senac.controller.ControllerPessoa;
import br.sc.senac.controller.ControllerVacina;
import br.sc.senac.model.seletor.VacinaSeletor;
import br.sc.senac.model.utilidades.Constantes;
import br.sc.senac.model.utilidades.StatusMensagem;
import br.sc.senac.model.utilidades.Utils;
import br.sc.senac.model.vo.Pais;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaGerenciamentoDeVacinas extends JFrame {

	private JPanel contentPane;
	private JTextField tfNomeVacina;
	private JComboBox cbEstagioPesquisa;
	private JTable tableResultados;
	private DatePicker dpDataInicioPesquisa;
	private JLabel lblPagAtual;
	
	private JComboBox cbPaisOrigem;
	private JComboBox cbPesquisador;
	private JButton btnVoltar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnConsultar;
	private JButton btnPagAnterior;
	private JButton btnPagProxima;
	private JButton btnPegarRegistro;
	
	private List<Pessoa> listaDePesquisadoresGeral;
	private List<Pessoa> listaDePesquisadoresParaEdicao;
	private HashMap<String, Integer> mapaPaisesParaIndicesEdicao;
	private VacinaSeletor ultimoSeletorUsado;
	private int paginaAtual = 1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciamentoDeVacinas frame = new TelaGerenciamentoDeVacinas();
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
	public TelaGerenciamentoDeVacinas() throws ParseException {
		ultimoSeletorUsado = null;
		ControllerPessoa controllerPessoa = new ControllerPessoa();
		mapaPaisesParaIndicesEdicao = new HashMap<String, Integer>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][grow][24.00][105.00][47.00][grow][][]", "[][][][][][][][][][][][][]"));
		
		JLabel lblGerenciamentoVacinas = new JLabel("Gerenciamento de Vacinas");
		lblGerenciamentoVacinas.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblGerenciamentoVacinas, "cell 3 0 3 1,alignx center");
		
		JLabel lblNomeVacina = new JLabel("Nome Vacina:");
		lblNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNomeVacina, "cell 0 2,alignx trailing");
		
		tfNomeVacina = new JTextField();
		tfNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfNomeVacina, "cell 1 2 3 1,growx");
		tfNomeVacina.setColumns(10);
		
		JLabel lblEstagioPesquisa = new JLabel("Estágio da Pesquisa:");
		lblEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblEstagioPesquisa, "cell 5 2,alignx trailing");
		
		cbEstagioPesquisa = new JComboBox();
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(Constantes.LISTA_ESTAGIOS_VACINA_GERAL));
		cbEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbEstagioPesquisa, "cell 6 2 2 1,growx");
		
		JLabel lblPaisDeOrigem = new JLabel("Pa\u00EDs de Origem:");
		lblPaisDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPaisDeOrigem, "cell 0 4,alignx trailing");
		
		cbPaisOrigem = new JComboBox();
		cbPaisOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbPaisOrigem.setModel(new DefaultComboBoxModel(Constantes.OPCOES_PAISES_GERAL.toArray()));
		contentPane.add(cbPaisOrigem, "cell 1 4 3 1,growx");
		
		JLabel lblPesquisador = new JLabel("Pesquisador:");
		lblPesquisador.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPesquisador, "cell 5 4,alignx trailing");

		listaDePesquisadoresParaEdicao = controllerPessoa.coletarListaDePesquisadores();
		preencherListaPesquisadoresGeral(listaDePesquisadoresParaEdicao);
		preencherMapaDePaisesParaIndiceEdicao();
		cbPesquisador = new JComboBox();
		cbPesquisador.setModel(new DefaultComboBoxModel(listaDePesquisadoresGeral.toArray()));
		contentPane.add(cbPesquisador, "cell 6 4 2 1,growx");
		
		JLabel lblInicioPesquisa = new JLabel("Início Pesquisa:");
		lblInicioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblInicioPesquisa, "cell 0 6,alignx trailing");
		
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dpDataInicioPesquisa = new DatePicker(dateSettings);
		contentPane.add(dpDataInicioPesquisa, "cell 1 6 3 1,growx");
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abilitarTabelaBotoesConsultaExclusao();
				redefinirComboboxsParaGeral();
				desativarBotoesDeEdicao();
			}
		});
		btnVoltar.setEnabled(false);
		btnVoltar.setVisible(false);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnVoltar, "cell 4 6,alignx center");
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetarTodosOsCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnLimpar, "cell 5 6,alignx center");
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirVacinas();
				atualizarTabelaComUltimoSeletor();
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnExcluir, "cell 2 8,alignx center");
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tentarEditar();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setVisible(false);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnEditar, "cell 6 6,alignx center");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarPessoas();
			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnConsultar, "cell 6 8,alignx center");
		
		tableResultados = new JTable();
		tableResultados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		definirModeloPadraoTabela();
		contentPane.add(tableResultados, "cell 0 10 10 1,grow");
		
		btnPagAnterior = new JButton("< Anterior");
		btnPagAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				atualizarTabelaComUltimoSeletor();
			}
		});
		btnPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPagAnterior, "cell 2 11,alignx center");
		
		btnPagProxima = new JButton("Próximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPagProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				atualizarTabelaComUltimoSeletor();
			}
		});
		contentPane.add(btnPagProxima, "cell 6 11,alignx center");
		
		lblPagAtual = new JLabel("1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblPagAtual, "cell 4 11 2 1,alignx center");
		
		btnPegarRegistro = new JButton("<html>Pegar Registro<br />para Edi\u00E7\u00E3o</html>");
		btnPegarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coletarRegistroParaEdicao();
			}
		});
		btnPegarRegistro.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPegarRegistro, "cell 4 12 2 1,alignx center");
	}

	protected void tentarEditar() {
		// Preencher o Objeto com os dados da tela
		Vacina vacinaAlterada = new Vacina();
		// pegar id
		int linhaSelecionada = tableResultados.getSelectedRow();
		vacinaAlterada.setId((Integer) tableResultados.getModel().getValueAt(linhaSelecionada, 0));
		vacinaAlterada.setNome(tfNomeVacina.getText());
		vacinaAlterada.setPaisOrigem(((Pais) cbPaisOrigem.getSelectedItem()).getNome());
		vacinaAlterada.setEstagioPesquisa(Vacina.getIntEstagioDePesquisa((String) cbEstagioPesquisa.getSelectedItem()));
		LocalDate novaDataInicioPEsquisa = dpDataInicioPesquisa.getDate();
		vacinaAlterada.setDataInicioPesquisa(novaDataInicioPEsquisa);
		vacinaAlterada.setPesquisadorResponsavel((Pessoa) cbPesquisador.getSelectedItem());
		
		// Instanciar um controller adequado
		ControllerVacina controller = new ControllerVacina();
		
		// Chamar o método salvar no controller e pegar a mensagem retornada
		StatusMensagem statusMensagem = controller.atualizar(vacinaAlterada);
		
		// Mostrar a mensagem devolvida pelo controller
		JOptionPane.showMessageDialog(null, statusMensagem.getMensagem());
		
		//caso sucesso na edicao:
		if(statusMensagem.getStatus()) {
			abilitarTabelaBotoesConsultaExclusao();
			redefinirComboboxsParaGeral();
			desativarBotoesDeEdicao();
			atualizarTabelaComUltimoSeletor();
		}
	}
	
	protected void coletarRegistroParaEdicao() {
		String mensagem = "";
		if(tableResultados.getSelectedRowCount() == 0) {
			mensagem = "Por favor selecione uma linha para poder continuar para a tela de edição.";
		} else if(tableResultados.getSelectedRowCount() > 1){
			mensagem = "Por favor selecione uma  única linha para poder continuar para a tela de edição.";
		} else {
			if(tableResultados.getSelectedRow() == 0) {
				mensagem = "A linha com as descrições dos campos não pode ser editada. Escolha um registro válido.";
			} else {
				int linhaSelecionada = tableResultados.getSelectedRow();
				Integer idSelecionado = (Integer) tableResultados.getModel().getValueAt(linhaSelecionada, 0);
				if(idSelecionado == null) {
					mensagem = "A linha não pode estar vazia! Para inserir uma nova vacina, vá para a tela de cadastro de vacinas.";
				} else {
					desabilitarTabelaBotoesConsultaExclusao();
					redefinirComboboxsParaEdicao();
					preencherInputsComALinhaDeEdicao(linhaSelecionada);
					ativarBotoesDeEdicao();
					mensagem = null;
				}
				
			}
		}
		if (mensagem != null) {
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
	
	private void preencherInputsComALinhaDeEdicao(int linhaSelecionada) {
		tfNomeVacina.setText((String) tableResultados.getModel().getValueAt(linhaSelecionada, 1));
		String nomePais = (String) tableResultados.getModel().getValueAt(linhaSelecionada, 2);
		cbPaisOrigem.setSelectedIndex(mapaPaisesParaIndicesEdicao.get(nomePais.toUpperCase()));
		cbEstagioPesquisa.setSelectedIndex(getIndexFromEstagioPesquisa((String) tableResultados.getModel().getValueAt(linhaSelecionada, 3)));
		dpDataInicioPesquisa.setDate(Utils.gerarLocalDateDeString((String) tableResultados.getModel().getValueAt(linhaSelecionada, 4)));
		cbPesquisador.setSelectedIndex(getIndexFromPesquisador((Pessoa) tableResultados.getModel().getValueAt(linhaSelecionada, 5)));
	}

	private int getIndexFromPesquisador(Pessoa pesquisadorSelecionado) {
		int indice = 0;
		
		for(Pessoa pesquisador : listaDePesquisadoresParaEdicao) {
			if(pesquisador.getId() == pesquisadorSelecionado.getId()) {
				break;
			}
			indice++;
		}
		
		return indice;
	}

	private int getIndexFromEstagioPesquisa(String estagioSelecionado) {
		int indice = 0 ;
		if(estagioSelecionado.equals(Constantes.LISTA_ESTAGIOS_VACINA_GERAL[1])) {
			indice = 0;
		} else if(estagioSelecionado.equals(Constantes.LISTA_ESTAGIOS_VACINA_GERAL[2])) {
			indice = 1;
		} else if(estagioSelecionado.equals(Constantes.LISTA_ESTAGIOS_VACINA_GERAL[3])) {
			indice = 2;
		}
		return indice;
	}

	private void redefinirComboboxsParaGeral() {
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(Constantes.LISTA_ESTAGIOS_VACINA_GERAL));
		cbPaisOrigem.setModel(new DefaultComboBoxModel(Constantes.OPCOES_PAISES_GERAL.toArray()));
		cbPesquisador.setModel(new DefaultComboBoxModel(listaDePesquisadoresGeral.toArray()));
	}
	
	private void redefinirComboboxsParaEdicao() {
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(Constantes.LISTA_ESTAGIOS_VACINA_EDICAO_CADASTRO));
		cbPaisOrigem.setModel(new DefaultComboBoxModel(Constantes.OPCOES_PAISES_EDICAO_CADASTRO));
		cbPesquisador.setModel(new DefaultComboBoxModel(listaDePesquisadoresParaEdicao.toArray()));
	}

	private void abilitarTabelaBotoesConsultaExclusao() {
		tableResultados.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnConsultar.setEnabled(true);
		tableResultados.setEnabled(true);
		btnPagAnterior.setEnabled(true);
		btnPegarRegistro.setEnabled(true);
		btnPagProxima.setEnabled(true);
	}
	
	private void desabilitarTabelaBotoesConsultaExclusao() {
		tableResultados.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnConsultar.setEnabled(false);
		tableResultados.setEnabled(false);
		btnPagAnterior.setEnabled(false);
		btnPegarRegistro.setEnabled(false);
		btnPagProxima.setEnabled(false);
	}

	protected void ativarBotoesDeEdicao() {
		btnEditar.setVisible(true);
		btnEditar.setEnabled(true);
		btnVoltar.setVisible(true);
		btnVoltar.setEnabled(true);
	}
	
	protected void desativarBotoesDeEdicao() {
		btnEditar.setVisible(false);
		btnEditar.setEnabled(false);
		btnVoltar.setVisible(false);
		btnVoltar.setEnabled(false);
	}
	
	protected void resetarTodosOsCampos() {
		tfNomeVacina.setText("");
		cbEstagioPesquisa.setSelectedIndex(0);
		cbPaisOrigem.setSelectedIndex(0);
		if(cbPesquisador.getItemCount() != 0) {
			cbPesquisador.setSelectedIndex(0);
		}
		dpDataInicioPesquisa.clear();
		definirModeloPadraoTabela();
	}
	
	protected void atualizarTabelaComUltimoSeletor() {
		if(ultimoSeletorUsado != null) {
			lblPagAtual.setText(paginaAtual + "");
			ultimoSeletorUsado.setPagina(paginaAtual);
			ultimoSeletorUsado.setLimite(Constantes.TAMANHO_PAGINA);
			ControllerVacina controlador = new ControllerVacina();
			List<Vacina> vacinas = controlador.listarVacinas(ultimoSeletorUsado);
			atualizarTabelaVacinas(vacinas);
		}
	}
	
	protected void excluirVacinas() {
		String mensagem = "";
		if(tableResultados.getSelectedRowCount() == 0) {
			mensagem = "Por favor selecione uma ou mais linhas para excluir os registros.";
		} else {
			if(tableResultados.getSelectedRow() == 0) {
				mensagem = "A linha com as descrições dos campos não pode ser excluída. Nenhuma linha será excluída.";
			} else {
				ControllerVacina controllerVacina = new ControllerVacina();
				List<Integer> idsASeremExcluidos = new ArrayList<Integer>();
				for(int row : tableResultados.getSelectedRows()) {
					Integer idASerExcluido = (Integer) (tableResultados.getValueAt(row, 0));
					if(idASerExcluido != null) {
						idsASeremExcluidos.add(idASerExcluido);
					}
				}
				mensagem = controllerVacina.excluir(idsASeremExcluidos);
			}
		}
		JOptionPane.showMessageDialog(null, mensagem);
	}

	protected void consultarPessoas() {
		paginaAtual = 1;
		lblPagAtual.setText(paginaAtual + "");

		ControllerVacina controlador = new ControllerVacina();
		VacinaSeletor seletor = new VacinaSeletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(Constantes.TAMANHO_PAGINA);
		
		// Preenche os campos de filtro da tela no seletor
		seletor.setNomeVacina(tfNomeVacina.getText());
		seletor.setPaisOrigem(((Pais)cbPaisOrigem.getSelectedItem()).getNome());
		seletor.setEstagioPesquisa(Vacina.getIntEstagioDePesquisa((String)cbEstagioPesquisa.getSelectedItem()));
		seletor.setDataInicioPesquisa(dpDataInicioPesquisa.getDate());
		seletor.setPesquisadorResponsavel((Pessoa)cbPesquisador.getSelectedItem());
		
		// aqui é feita a consulta das pessoas e atualizada a tabela
		List<Vacina> vacinas = controlador.listarVacinas(seletor);
		ultimoSeletorUsado = seletor;

		atualizarTabelaVacinas(vacinas);

	}
	
	//TODO data_inicio_pesquisa vom com um dia a menos do conjunto resultande de VacinaDAO, ver a causa
	private void atualizarTabelaVacinas(List<Vacina> vacinas) {
		this.definirModeloPadraoTabela();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		for (int i = 0; i < vacinas.size(); i++) {
			Vacina vacina = vacinas.get(i);
			String dataFormatada = vacina.getDataInicioPesquisa().format(formatter);
			
			modelo.setValueAt(vacina.getId(), i+1, 0);
			modelo.setValueAt(vacina.getNome(), i+1, 1);
			modelo.setValueAt(vacina.getPaisOrigem(), i+1, 2);
			modelo.setValueAt(Vacina.getStringEstagioDePesquisa(vacina.getEstagioPesquisa()), i+1, 3);
			modelo.setValueAt(dataFormatada, i+1, 4);
			modelo.setValueAt(vacina.getPesquisadorResponsavel(), i+1, 5);
		}
		
	}

	private void definirModeloPadraoTabela() {
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					{"#", "Nome da Vacina", "Pa\u00EDs de Origem", "Est\u00E1gio da Pesquisa", "In\u00EDcio Pesquisa", "Pesquisador"},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
				},
				new String[] {
					"#", "NomeDaVacina", "PaisDeOrigem", "EstagioDaPesquisa", "InicioPesquisa", "Pesquisador"
				}
			) {
				@Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
			});
	}
	
	private void preencherListaPesquisadoresGeral(List<Pessoa> listaPesquisadores) {
		listaDePesquisadoresGeral = new ArrayList<Pessoa>();
		for (Pessoa pessoa : listaPesquisadores) {
			listaDePesquisadoresGeral.add(pessoa.fazerCopia());
		}
		listaDePesquisadoresGeral.add(0, Constantes.OPCAO_PESQUISADOR_RESPONSAVEL_TODOS);
	}
	
	private void preencherMapaDePaisesParaIndiceEdicao() {
		int i = 0;
		for (Pais pais : Constantes.OPCOES_PAISES_EDICAO_CADASTRO) {
			 mapaPaisesParaIndicesEdicao.put(pais.getNome().toUpperCase(), i++);
		}
	}

}
