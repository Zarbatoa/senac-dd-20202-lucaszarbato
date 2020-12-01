package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private JLabel lblPagAtual;
	
	//variável relacionada a paginação
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
		ControllerPessoa controllerPessoa = new ControllerPessoa();
		
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
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(Constantes.LISTA_ESTAGIOS_VACINA));
		cbEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbEstagioPesquisa, "cell 6 2 2 1,growx");
		
		JLabel lblPaisDeOrigem = new JLabel("Pa\u00EDs de Origem:");
		lblPaisDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPaisDeOrigem, "cell 0 4,alignx trailing");
		
		Pais[] listaPaises = Pais.createCountryList();
		cbPaisOrigem = new JComboBox();
		cbPaisOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbPaisOrigem.setModel(new DefaultComboBoxModel(listaPaises));
		contentPane.add(cbPaisOrigem, "cell 1 4 3 1,growx");
		
		JLabel lblPesquisador = new JLabel("Pesquisador:");
		lblPesquisador.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPesquisador, "cell 5 4,alignx trailing");
		
		List<Pessoa> listaDePesquisadores = controllerPessoa.coletarListaDePesquisadores();
		cbPesquisador = new JComboBox();
		cbPesquisador.setModel(new DefaultComboBoxModel(listaDePesquisadores.toArray()));
		contentPane.add(cbPesquisador, "cell 6 4 2 1,growx");
		
		JLabel lblInicioPesquisa = new JLabel("Início Pesquisa:");
		lblInicioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblInicioPesquisa, "cell 0 6,alignx trailing");
		
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dpDataInicioPesquisa = new DatePicker(dateSettings);
		contentPane.add(dpDataInicioPesquisa, "cell 1 6 3 1,growx");
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setEnabled(false);
		btnVoltar.setVisible(false);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnVoltar, "cell 4 6,alignx center");
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnLimpar, "cell 5 6,alignx center");
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnExcluir, "cell 2 8,alignx center");
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setVisible(false);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnEditar, "cell 6 6,alignx center");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnConsultar, "cell 6 8,alignx center");
		
		tableResultados = new JTable();
		tableResultados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		definirModeloPadraoTabela();
		contentPane.add(tableResultados, "cell 0 10 10 1,grow");
		
		btnPagAnterior = new JButton("< Anterior");
		btnPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPagAnterior, "cell 2 11,alignx center");
		
		btnPagProxima = new JButton("Próximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPagProxima, "cell 6 11,alignx center");
		
		lblPagAtual = new JLabel("1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblPagAtual, "cell 4 11 2 1,alignx center");
		
		btnPegarRegistro = new JButton("<html>Pegar Registro<br />para Edi\u00E7\u00E3o</html>");
		btnPegarRegistro.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPegarRegistro, "cell 4 12 2 1,alignx center");
	}

	protected void editarVacinas() {
		// TODO Auto-generated method stub
		
	}

	protected void consultarVacinas() {
		lblPagAtual.setText(paginaAtual + "");

		ControllerVacina controlador = new ControllerVacina();
		VacinaSeletor seletor = new VacinaSeletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(Constantes.TAMANHO_PAGINA);
		
		// Preenche os campos de filtro da tela no seletor
		seletor.setNomeVacina(tfNomeVacina.getText());
		//TODO seletor.setNomePesquisador((Pessoa)tfNomePesquisador.getClientProperty(tfNomePesquisador)); //não sei se está correto
		seletor.setEstagioPesquisa(Integer.parseInt((String) cbEstagioPesquisa.getSelectedItem())); //verificar se está correto
		//TODO seletor.setInstituicao((Instituicao)cbInstituicao.getSelectedItem());
		seletor.setDataInicioPesquisa(dpDataInicioPesquisa.getDate());

		// aqui é feita a consulta das pessoas e atualizada a tabela
		List<Vacina> vacinas = controlador.listarVacinas(seletor); 
		atualizarTabelaVacinas(vacinas);
	}
	
	protected void atualizarTabelaVacinas(List<Vacina> vacinas) {
		// atualiza o atributo vacinas consultadas
		//this.limparTabela();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		for (Vacina vacina : vacinas) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ordem do cabeçalho da tabela
			String dataFormatada = vacina.getDataInicioPesquisa().format(formatter);

			String[] novaLinha = new String[] { vacina.getNome(),  vacina.getPaisOrigem(), vacina.getEstagioPesquisa() + "", 
					dataFormatada, vacina.getPesquisadorResponsavel().getNomeCompleto(), vacina.getPesquisadorResponsavel().getInstituicao().getNome() }; 
			modelo.addRow(novaLinha);
		}
	}

	private void definirModeloPadraoTabela() {
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					{"#", "Nome", "Pa\u00EDs de Origem", "Est\u00E1gio da Pesquisa", "In\u00EDcio Pesquisa", "Pesquisador"},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
				},
				new String[] {
					"#", "Nome", "PaisDeOrigem", "EstagioDaPesquisa", "InicioPesquisa", "Pesquisador"
				}
			) {
				@Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
			});
	}

}
