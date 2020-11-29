package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

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

import br.sc.senac.model.vo.Pessoa;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaGerenciamentoDeVacinas extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfNomePesquisador;
	private JComboBox cbEstagioPesquisa;
	private JTable table;
	private DatePicker dataInicioPesquisa;
	
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[74.00,grow][23.00,grow][150.00,grow][105.00][111.00][127.00,grow][15.00][]", "[][][][][][][][][][136.00][][]"));
		
		JLabel lblGerenciamentoVacinas = new JLabel("Gerenciamento de Vacinas");
		lblGerenciamentoVacinas.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblGerenciamentoVacinas, "cell 3 0");
		
		JLabel lblNomeVacina = new JLabel("Nome Vacina:");
		lblNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNomeVacina, "cell 0 2,alignx trailing");
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfNome, "cell 1 2 2 1,growx");
		tfNome.setColumns(10);
		
		String[] opcoesSexo = {Pessoa.SEXO_MASCULINO, Pessoa.SEXO_FEMININO}; 
		
		JLabel lblEstagioPesquisa = new JLabel("Est\u00E1gio da Pesquisa:");
		lblEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblEstagioPesquisa, "cell 4 2,alignx trailing");
		cbEstagioPesquisa = new JComboBox(opcoesSexo);
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Inicial", "Intermedi\u00E1rio", "Final"}));
		cbEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbEstagioPesquisa, "cell 5 2 2 1,growx");
		
		JLabel lblNomePesquisador = new JLabel("Nome Pesquisador:");
		lblNomePesquisador.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNomePesquisador, "cell 0 4,alignx trailing");
		
		tfNomePesquisador = new JTextField();
		tfNomePesquisador.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfNomePesquisador, "cell 1 4 2 1,growx");
		tfNomePesquisador.setColumns(10);
		
		JLabel lblInstituicao = new JLabel("Institui\u00E7\u00E3o:");
		lblInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblInstituicao, "cell 4 4,alignx trailing");
		
		JComboBox cbInstituicao = new JComboBox();
		cbInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbInstituicao, "cell 5 4 2 1,growx");
		
		JLabel lblPaisDeOrigem = new JLabel("Pa\u00EDs de Origem:");
		lblPaisDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPaisDeOrigem, "cell 0 6,alignx trailing");
		
		JComboBox cbPaisOrigem = new JComboBox();
		cbPaisOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbPaisOrigem.setModel(new DefaultComboBoxModel(new String[] {"Argélia", "China", "Reino Unido", "R\u00FAssia"}));
		contentPane.add(cbPaisOrigem, "cell 1 6 2 1,growx");
		
		JLabel lblInicioPesquisa = new JLabel("Início Pesquisa:");
		lblInicioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblInicioPesquisa, "cell 4 6,alignx trailing");
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dataInicioPesquisa = new DatePicker(dateSettings);
		contentPane.add(dataInicioPesquisa,"cell 5 6 2 1,growx");
		
		//ftfInicioPesquisa = new JFormattedTextField(mascaraData);
		//ftfInicioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		//contentPane.add(ftfInicioPesquisa, "cell 6 6 2 1,growx");
		
		final JFrame janelaAtual = this;
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnFiltrar, "cell 2 8");
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnEditar, "cell 3 8,alignx right");
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaAtual.dispose();
			}
		});
		
		contentPane.add(btnExcluir, "cell 5 8");
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Nome Vacina", "País de Origem", "Estágio de Pesquisa", "Início Pesquisa", "Pesquisador", "Instituição"},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		contentPane.add(table, "cell 0 9 8 1,grow");
		
		JButton buttonPagAnterior = new JButton("< Anterior");
		buttonPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(buttonPagAnterior, "cell 2 10");
		
		JButton btnPagProxima = new JButton("Próximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPagProxima, "cell 5 10");
		
		JLabel lblPagAtual = new JLabel("1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblPagAtual, "cell 3 10,alignx right");
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnVoltar, "cell 3 11,alignx right");
	}

}
