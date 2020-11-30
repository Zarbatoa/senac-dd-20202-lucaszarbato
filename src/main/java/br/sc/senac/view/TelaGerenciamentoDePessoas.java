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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import br.sc.senac.controller.ControllerPessoa;
import br.sc.senac.model.Utils;
import br.sc.senac.model.seletor.PessoaSeletor;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaGerenciamentoDePessoas extends JFrame {

	public static final String OPCAO_SEXO_TODOS = "Todos";
	public static final TipoPessoa OPCAO_CATEGORIA_TODAS = new TipoPessoa(-1,"Todas");
	
	private static final int TAMANHO_PAGINA = 5; // relacionado a paginação

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
	
	//variável relacionada a paginação
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
		
		String[] opcoesSexo = {OPCAO_SEXO_TODOS, Pessoa.SEXO_MASCULINO, Pessoa.SEXO_FEMININO}; 
		
		MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
		
		TipoPessoa[] opcoesTipoPessoa = {OPCAO_CATEGORIA_TODAS ,TipoPessoa.TIPO_PESQUISADOR, TipoPessoa.TIPO_VOLUNTARIO, TipoPessoa.TIPO_PUBLICO_GERAL};
		
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
		//TODO pensar sobre um botão cancelar ou voltar --> acabei de acrescentar o botão de Voltar!
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
					tfInstituicao.setText("");
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
		tfInstituicao.setEnabled(false);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnConsultar, "cell 2 10");
		
		// criei esse método para já adicionar o filtro seletor nessa tela
		btnConsultar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				consultarPessoas();
			}
		});
		
		JButton btnEditar = new JButton("Editar"); // preencho nos campos? se sim precisa de um botão de Salvar.
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
		
		//DefaultTableModel tableModel = new 
		tableResultados = new JTable();
		tableResultados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		definirModeloPadraoTabela();
		contentPane.add(tableResultados, "cell 0 12 9 1,grow");
		
		JButton buttonPagAnterior = new JButton("< Anterior");
		buttonPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(buttonPagAnterior, "cell 2 13");
		
		//evento de passar pág anterior
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
		
		//evento para passar a próxima página
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

	// método relacionado ao Botão Filtrar
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
		seletor.setCpf(Utils.desformatarCpf(ftfCpf.getText()));
		seletor.setNomeInstituicao(tfInstituicao.getText()); 
		
		seletor.setTipo((TipoPessoa)cbCategoria.getSelectedItem()); 
				
		seletor.setDataNascimento(dpDataInicioPesquisa.getDate());
		
		// aqui é feita a consulta das pessoas e atualizada a tabela
		List<Pessoa> pessoas = controlador.listarPessoas(seletor);

		atualizarTabelaPessoas(pessoas);

	}

	protected void atualizarTabelaPessoas(List<Pessoa> pessoas) {
		this.definirModeloPadraoTabela();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		for (int i = 0; i < pessoas.size(); i++) {
			Pessoa pessoa = pessoas.get(i);
			String dataFormatada = pessoa.getDataNascimento().format(formatter);
			String nomeInstituicao = pessoa.getInstituicao() == null ? "-" : pessoa.getInstituicao().toString();

			String[] novaLinha = new String[] { pessoa.getNome(),  pessoa.getSobrenome(), pessoa.getSexo() + "", 
					Utils.formatarCpf(pessoa.getCpf()) , dataFormatada, pessoa.getTipo().toString(), nomeInstituicao };
			modelo.setValueAt(novaLinha[0], i+1, 0);//insertRow(i+1, novaLinha);
			modelo.setValueAt(novaLinha[1], i+1, 1);
			modelo.setValueAt(novaLinha[2], i+1, 2);
			modelo.setValueAt(novaLinha[3], i+1, 3);
			modelo.setValueAt(novaLinha[4], i+1, 4);
			modelo.setValueAt(novaLinha[5], i+1, 5);
			modelo.setValueAt(novaLinha[6], i+1, 6);
		}
		
	}

	private void definirModeloPadraoTabela() {
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
					"Nome", "Sobrenome", "Sexo", "CPF", "DataNascimento", "Categoria", "Instituicao"
				}
			) {
				@Override
			    public boolean isCellEditable(int row, int column) {
			       // nenhuma celula eh editavel
			       return false;
			    }
			});
	}

}
