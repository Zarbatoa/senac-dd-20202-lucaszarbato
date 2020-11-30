package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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

import br.sc.senac.controller.ControllerVacina;
import br.sc.senac.model.vo.Pessoa;
import net.miginfocom.swing.MigLayout;
import javax.swing.ImageIcon;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaRelatorioAvaliacaoVacinas extends JFrame {
	
	private static final int TAMANHO_PAGINA = 0; // relacionado a paginação

	private JPanel contentPane;
	private JComboBox cbRelatorioAvaliacaoVacinas;
	private JTable table;
	private JTextField txtUsuario;
	private DatePicker dataInicio;
	private DatePicker dataFinal;
	
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
					TelaRelatorioAvaliacaoVacinas frame = new TelaRelatorioAvaliacaoVacinas();
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
	public TelaRelatorioAvaliacaoVacinas() throws ParseException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][27.00,grow][grow][][120.00][88.00][142.00,grow][42.00]", "[][][][][][][][][][][99.00][][][]"));
		
		JLabel lbbRelatorioAvaliacaoVacinas = new JLabel("Relat\u00F3rio de Avalia\u00E7\u00E3o de Vacinas");
		lbbRelatorioAvaliacaoVacinas.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lbbRelatorioAvaliacaoVacinas, "cell 3 0 3 1");
		
		String[] opcoesSexo = {Pessoa.SEXO_MASCULINO, Pessoa.SEXO_FEMININO}; 
		
		cbRelatorioAvaliacaoVacinas = new JComboBox(opcoesSexo);
		cbRelatorioAvaliacaoVacinas.setModel(new DefaultComboBoxModel(new String[] {"M\u00E9dia Nota por Vacina", "M\u00E9dia Nota por Usu\u00E1rio", "M\u00E9dia Nota por Categoria", "M\u00E9dia Nota por Sexo", "", "", ""}));
		cbRelatorioAvaliacaoVacinas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbRelatorioAvaliacaoVacinas, "cell 0 2 8 1,growx");
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblUsurio, "cell 0 4,alignx trailing");
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(txtUsuario, "cell 1 4 2 1,growx");
		txtUsuario.setColumns(10);
		
		JLabel lblDataInicio = new JLabel("De:");
		lblDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblDataInicio, "cell 0 6,alignx trailing");
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dataInicio = new DatePicker(dateSettings);
		contentPane.add(dataInicio,"cell 1 6 2 1,growx");
		
		//TODO pensar sobre um botão cancelar ou voltar
		final JFrame janelaAtual = this;
		
		JLabel lblDataFinal = new JLabel("At\u00E9:");
		lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblDataFinal, "cell 5 6,alignx trailing");
		
		DatePickerSettings dateSettings2 = new DatePickerSettings();
		dateSettings2.setAllowKeyboardEditing(false);
		dataFinal = new DatePicker(dateSettings2);
		contentPane.add(dataFinal,"cell 6 6 2 1,growx");
		
		JButton btnGerarRelatorio = new JButton("Gerar Relat\u00F3rio");
		btnGerarRelatorio.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnGerarRelatorio, "cell 2 8,alignx right");
		
		JButton btnGerarXls = new JButton("Gerar xls");
		btnGerarXls.setIcon(new ImageIcon("C:\\Users\\rosan\\git\\senac-dd-20202-lucaszarbato2\\icones\\iconeExcelmenor.png"));
		btnGerarXls.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnGerarXls, "cell 6 8,alignx center");
		
		//método chamar xls no botão. Falta fazer o método de gerar relatório.
		btnGerarXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaArquivos = new JFileChooser();

				int opcaoSelecionada = janelaArquivos.showSaveDialog(null);

				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminho = janelaArquivos.getSelectedFile().getAbsolutePath();

					ControllerVacina controller = new ControllerVacina();
					String mensagem = controller.gerarRelatorioTotalVacinaPorPesquisador(vacinas, caminhoEscolhido, tipoRelatorio); //aqui está ligado ao método gerar relatório, que equivale a uma consulta de vacina

					JOptionPane.showMessageDialog(null, mensagem);
				}
			}
		});
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Usuário", "Sexo", "Vacina", "Categoria", "Data Inicial", "Data Final", "Média Nota"},
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
		contentPane.add(table, "cell 0 10 8 1,grow");
		
		JButton buttonPagAnterior = new JButton("< Anterior");
		buttonPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(buttonPagAnterior, "cell 2 12");
		
		//evento de passar pág anterior
		buttonPagAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				gerarRelatorio();
			}
		});

		lblPagAtual = new JLabel("                 1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblPagAtual, "cell 4 12,alignx center");
		
		JButton btnPagProxima = new JButton("Próximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPagProxima, "cell 6 12");
		
		//evento para passar a próxima página
		btnPagProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				gerarRelatorio();
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnVoltar, "cell 4 13,alignx right");
	}

	protected void gerarRelatorio() {
		
		

	}

}
