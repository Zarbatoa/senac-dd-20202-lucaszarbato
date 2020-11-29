package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

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

import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaGerenciamentoDePessoas extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfInstituicao;
	private JComboBox cbSexo;
	private DatePicker dpDataInicioPesquisa;
	private JFormattedTextField ftfCpf;
	private JTable tableResultados;
	private JTextField tfSobrenome;
	private JComboBox cbCategoria;
	
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
		contentPane.setLayout(new MigLayout("", "[grow][][grow][][105.00][47.00][grow][][]", "[][][][][][][][][][][][][96.00][]"));
		
		JLabel lblGerenciamentoDePessoa = new JLabel("Gerenciamento de Pessoas");
		lblGerenciamentoDePessoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblGerenciamentoDePessoa, "cell 3 0 3 1");
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNome, "cell 0 2,alignx trailing");
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfNome, "cell 1 2 2 1,growx");
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
		contentPane.add(ftfCpf, "cell 1 4 2 1,growx");
		//TODO pensar sobre um botão cancelar ou voltar
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
		contentPane.add(cbCategoria, "cell 1 6 2 1,growx");
		
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
		contentPane.add(tfInstituicao, "cell 1 8 2 1,growx");
		tfInstituicao.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnFiltrar, "cell 2 10");
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnEditar, "cell 4 10,alignx right");
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnExcluir, "cell 6 10");
		
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
		
		JLabel lblPagAtual = new JLabel("1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblPagAtual, "cell 4 13,alignx center");
		
		JButton btnPagProxima = new JButton("Pr\u00F3ximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPagProxima, "cell 6 13");
	}

}
