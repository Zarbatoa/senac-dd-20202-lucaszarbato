package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.sc.senac.controller.ControllerPessoa;
import br.sc.senac.model.Utils;
import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;
import net.miginfocom.swing.MigLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaGerenciamentoDeVacinas extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfNomePesquisador;
	private JComboBox cbEstagioPesquisa;
	private JFormattedTextField ftfInicioPesquisa;
	private JTable table;
	
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
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][][105.00][47.00][grow][][]", "[][][][][][][][][][136.00][]"));
		
		JLabel lblGerenciamentoDeVacina = new JLabel("Gerenciamento de Vacinas");
		lblGerenciamentoDeVacina.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblGerenciamentoDeVacina, "cell 3 0 3 1");
		
		JLabel lblNomeVacina = new JLabel("Nome Vacina:");
		lblNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNomeVacina, "cell 0 2,alignx trailing");
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfNome, "cell 1 2 2 1,growx");
		tfNome.setColumns(10);
		
		String[] opcoesSexo = {Pessoa.SEXO_MASCULINO, Pessoa.SEXO_FEMININO}; 
		
		MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
		
		JLabel lblEstagioPesquisa = new JLabel("Est\u00E1gio da Pesquisa:");
		lblEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblEstagioPesquisa, "cell 5 2,alignx trailing");
		cbEstagioPesquisa = new JComboBox(opcoesSexo);
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Inicial", "Intermedi\u00E1rio", "Final"}));
		cbEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbEstagioPesquisa, "cell 6 2 2 1,growx");
		
		MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		
		TipoPessoa[] opcoesTipoPessoa = {TipoPessoa.TIPO_PESQUISADOR, TipoPessoa.TIPO_VOLUNTARIO, TipoPessoa.TIPO_PUBLICO_GERAL};
		
		JLabel lblNomePesquisador = new JLabel("Nome Pesquisador:");
		lblNomePesquisador.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNomePesquisador, "cell 0 4,alignx trailing");
		
		tfNomePesquisador = new JTextField();
		tfNomePesquisador.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfNomePesquisador, "cell 1 4 2 1,growx");
		tfNomePesquisador.setColumns(10);
		
		JLabel lblInstituicao = new JLabel("Institui\u00E7\u00E3o:");
		lblInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblInstituicao, "cell 5 4,alignx trailing");
		
		JComboBox cbInstituicao = new JComboBox();
		cbInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbInstituicao, "cell 6 4 2 1,growx");
		
		JLabel lblPaisDeOrigem = new JLabel("Pa\u00EDs de Origem:");
		lblPaisDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPaisDeOrigem, "cell 0 6,alignx trailing");
		
		JComboBox cbPaisOrigem = new JComboBox();
		cbPaisOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbPaisOrigem.setModel(new DefaultComboBoxModel(new String[] {"Arg\u00E9lia", "China", "Reino Unido", "R\u00FAssia"}));
		contentPane.add(cbPaisOrigem, "cell 1 6 2 1,growx");
		
		JLabel lblInicioPesquisa = new JLabel("In\u00EDcio Pesquisa:");
		lblInicioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblInicioPesquisa, "cell 5 6,alignx trailing");
		ftfInicioPesquisa = new JFormattedTextField(mascaraData);
		ftfInicioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(ftfInicioPesquisa, "cell 6 6 2 1,growx");
		final JFrame janelaAtual = this;
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Preencher o Objeto com os dados da tela
				Pessoa novaPessoa = new Pessoa();
				novaPessoa.setTipo((TipoPessoa)cbInstituicao.getSelectedItem());
				novaPessoa.setInstituicao(new Instituicao(-1,tfNomePesquisador.getText()));
				novaPessoa.setNome(tfNome.getText());
				novaPessoa.setSobrenome(tfSobrenome.getText());
				LocalDate novaDataNascimento = null;
				try {
					novaDataNascimento = Utils.gerarLocalDateDeString(ftfInicioPesquisa.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Por favor, insira uma data válida em data de nascimento.");
					return;
				}
				novaPessoa.setDataNascimento(novaDataNascimento);
				novaPessoa.setSexo(((String)cbEstagioPesquisa.getSelectedItem()).charAt(0));
				// verificar se cpf é vazio e se ele é válido no BO
				novaPessoa.setCpf(Utils.desformatarCpf(ftfCpf.getText()));
				
				// Instanciar um controller adequado
				ControllerPessoa controller = new ControllerPessoa();
				
				// Chamar o método salvar no controller e pegar a mensagem retornada
				String mensagem = controller.salvar(novaPessoa);
				
				// Mostrar a mensagem devolvida pelo controller
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		contentPane.add(btnFiltrar, "cell 2 8");
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnEditar, "cell 4 8,alignx right");
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaAtual.dispose();
			}
		});
		contentPane.add(btnExcluir, "cell 6 8");
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Nome Vacina", "Pa\u00EDs de Origem", "Est\u00E1gio de Pesquisa", "In\u00EDcio Pesquisa", "Pesquisador", "Institui\u00E7\u00E3o"},
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
		contentPane.add(table, "cell 0 9 9 1,grow");
		
		JButton buttonPagAnterior = new JButton("< Anterior");
		buttonPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(buttonPagAnterior, "cell 2 10");
		
		JLabel lblPagAtual = new JLabel("                    1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblPagAtual, "cell 4 10");
		
		JButton btnPagProxima = new JButton("Pr\u00F3ximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPagProxima, "cell 6 10");
	}

}
