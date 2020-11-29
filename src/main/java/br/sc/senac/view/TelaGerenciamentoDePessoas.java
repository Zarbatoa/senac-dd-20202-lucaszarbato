package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import br.sc.senac.controller.ControllerPessoa;
import br.sc.senac.model.seletor.PessoaSeletor;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaGerenciamentoDePessoas extends JFrame {
	
	private static final int TAMANHO_PAGINA = 0; // relacionado a pagina��o

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfInstituicao;
	private JComboBox cbSexo;
	private DatePicker dpDataInicioPesquisa;
	private JFormattedTextField ftfCpf;
	private JTable tableResultados;
	private JTextField tfSobrenome;
	private JComboBox cbCategoria;
	
	private JLabel lblPagAtual;
	
	//vari�vel relacionada a pagina��o
	private int paginaAtual = 1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciamentoDePessoas frame = new TelaGerenciamentoDePessoas();
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
	public TelaGerenciamentoDePessoas() throws ParseException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][grow][24.00][105.00][47.00][grow][][]", "[][][][][][][][][][][][][96.00][][]"));
		
		JLabel lblGerenciamentoDePessoa = new JLabel("Gerenciamento de Pessoas");
		lblGerenciamentoDePessoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblGerenciamentoDePessoa, "cell 3 0 3 1");
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNome, "cell 0 2,alignx trailing");
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfNome, "cell 1 2 3 1,growx");
		tfNome.setColumns(10);
		
		String[] opcoesSexo = {Pessoa.SEXO_MASCULINO, Pessoa.SEXO_FEMININO}; 
		
		MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
		
		TipoPessoa[] opcoesTipoPessoa = {TipoPessoa.TIPO_PESQUISADOR, TipoPessoa.TIPO_VOLUNTARIO, TipoPessoa.TIPO_PUBLICO_GERAL};
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblSobrenome, "cell 5 2,alignx trailing");
		
		tfSobrenome = new JTextField();
		tfSobrenome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfSobrenome, "cell 6 2 2 1,growx");
		tfSobrenome.setColumns(10);
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblCpf, "cell 0 4,alignx trailing");
		ftfCpf = new JFormattedTextField(mascaraCpf);
		ftfCpf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(ftfCpf, "cell 1 4 3 1,growx");
		//TODO pensar sobre um bot�o cancelar ou voltar --> acabei de acrescentar o bot�o de Voltar!
		final JFrame janelaAtual = this;
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblSexo, "cell 5 4,alignx trailing");
		cbSexo = new JComboBox(opcoesSexo);
		cbSexo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbSexo, "cell 6 4 2 1,growx");
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblCategoria, "cell 0 6,alignx trailing");
		
		cbCategoria = new JComboBox();
		cbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbCategoria.setModel(new DefaultComboBoxModel(opcoesTipoPessoa));
		cbCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoPessoa opcaoSelecionada = (TipoPessoa)cbCategoria.getSelectedItem();
				
				if(opcaoSelecionada == TipoPessoa.TIPO_PESQUISADOR) {
					tfInstituicao.setEnabled(true);
				} else {
					tfInstituicao.setEnabled(false);
				}
			}
		});
		contentPane.add(cbCategoria, "cell 1 6 3 1,growx");
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblDataDeNascimento, "cell 5 6,alignx trailing");
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dpDataInicioPesquisa = new DatePicker(dateSettings);
		contentPane.add(dpDataInicioPesquisa,"cell 6 6 2 1,growx");
		
		JLabel lblInstituicao = new JLabel("Institui\u00E7\u00E3o:");
		lblInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblInstituicao, "cell 0 8,alignx trailing");
		
		tfInstituicao = new JTextField();
		tfInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfInstituicao, "cell 1 8 3 1,growx");
		tfInstituicao.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnFiltrar, "cell 2 10");
		
		// criei esse m�todo para j� adicionar o filtro seletor nessa tela
		btnFiltrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				consultarPessoas();
			}
		});
		
		JButton btnEditar = new JButton("Editar"); // preencho nos campos? se sim precisa de um bot�o de Salvar.
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnEditar, "cell 4 10,alignx right");
		
		btnEditar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				editarPessoas();
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnExcluir, "cell 6 10");
		
		btnExcluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				excluirPessoas();
			}
		});
		
		tableResultados = new JTable();
		tableResultados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tableResultados.setModel(new DefaultTableModel(
			new Object[][] {
				{"Nome", "Sobrenome", "Sexo", "CPF ", "Dt.  Nascimento", "Categoria", "Institui\u00E7\u00E3o"},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		contentPane.add(tableResultados, "cell 0 12 9 1,grow");
		
		JButton buttonPagAnterior = new JButton("< Anterior");
		buttonPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(buttonPagAnterior, "cell 2 13");
		
		//evento de passar p�g anterior
		buttonPagAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				consultarPessoas();
			}
		});
		
		lblPagAtual = new JLabel("                        1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblPagAtual, "cell 4 13");
		
		JButton btnPagProxima = new JButton("Pr\u00F3ximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPagProxima, "cell 6 13");
		
		//evento para passar a pr�xima p�gina
		btnPagProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				consultarPessoas();
			}
		});
				
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnVoltar, "cell 4 14,alignx right,aligny top");
	}

	protected void excluirPessoas() {
		// TODO Auto-generated method stub
		
	}

	protected void editarPessoas() {
		// TODO Auto-generated method stub
		
	}

	// m�todo relacionado ao Bot�o Filtrar
	protected void consultarPessoas() {
		
		lblPagAtual.setText(paginaAtual + "");

		ControllerPessoa controlador = new ControllerPessoa();
		PessoaSeletor seletor = new PessoaSeletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(TAMANHO_PAGINA);
		
		// Preenche os campos de filtro da tela no seletor
		seletor.setNome(tfNome.getText());
		seletor.setSobrenome(tfSobrenome.getText());
		seletor.setSexo(cbSexo.getSelectedItem().toString().charAt(0)); 
		seletor.setCpf(ftfCpf.getText());
		seletor.setNomeInstituicao(tfInstituicao.getText()); 
		
		seletor.setTipo((TipoPessoa)cbCategoria.getSelectedItem()); 
				
		seletor.setDataNascimento(dpDataInicioPesquisa.getDate());
		
		// aqui � feita a consulta das pessoas e atualizada a tabela
		List<Pessoa> pessoas = controlador.listarPessoas(seletor);
		atualizarTabelaPessoas(pessoas);

	}

	protected void atualizarTabelaPessoas(List<Pessoa> pessoas) {
		// atualiza o atributo pessoas consultadas
		List<Pessoa> pessoasConsultadas = pessoas;
		
		this.limparTabela();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		for (Pessoa pessoa : pessoas) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ordem do cabe�alho da tabela
			String dataFormatada = pessoa.getDataNascimento().format(formatter);

			String[] novaLinha = new String[] { pessoa.getNome(),  pessoa.getSobrenome(), pessoa.getSexo() + "", 
					pessoa.getCpf() , dataFormatada, pessoa.getTipo().getDescricao(), pessoa.getInstituicao().getNome() };
			modelo.addRow(novaLinha);
		}
		
	}

	private void limparTabela() {
		tableResultados.setModel(new DefaultTableModel(new String[][] { { "Nome", "Sobrenome", "Sexo", "Dt. Nascimento", "Institui��o" }, },
				new String[] { "Nome", "Sobrenome", "Sexo", "Dt. Nascimento", "Institui��o"}));
	}

}
