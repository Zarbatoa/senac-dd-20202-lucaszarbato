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
public class TelaRelatorioVacinas extends JFrame {
	
	private static final int TAMANHO_PAGINA = 0; // relacionado a paginação

	private JPanel contentPane;
	private JComboBox cbRelatorioVacinas;
	private JTable tableResultados;
	private JTextField txtUsuario;
	private DatePicker dpDataFiltro;
	
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
					TelaRelatorioVacinas frame = new TelaRelatorioVacinas();
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
	public TelaRelatorioVacinas() throws ParseException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][27.00,grow][grow][][91.00][41.00][142.00,grow][42.00]", "[][][][][][][][][][][][]"));
		
		JLabel lbbRelatorioVacinas = new JLabel("Relat\u00F3rio de Vacinas");
		lbbRelatorioVacinas.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lbbRelatorioVacinas, "cell 3 0 3 1");
		
		String[] opcoesSexo = {Pessoa.SEXO_MASCULINO, Pessoa.SEXO_FEMININO}; 
		
		cbRelatorioVacinas = new JComboBox(opcoesSexo);
		cbRelatorioVacinas.setModel(new DefaultComboBoxModel(new String[] {"Total de Vacinas por Pesquisador", "==================", "Total de Vacinas por Pa\u00EDs de origem", "Total de Vacinas por Pa\u00EDs de origem a partir de uma data", "Total de Vacinas por Est\u00E1gio de Pesquisa", "Total de Vacinas por Est\u00E1gio de Pesquisa a partir de uma data"}));
		cbRelatorioVacinas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbRelatorioVacinas, "cell 0 2 8 1,growx");
		
		JLabel lblPesquisador = new JLabel("Pesquisador:");
		lblPesquisador.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPesquisador, "cell 0 4,alignx trailing");
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(txtUsuario, "cell 1 4 2 1,growx");
		txtUsuario.setColumns(10);
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		
		//TODO pensar sobre um botão cancelar ou voltar
		final JFrame janelaAtual = this;
		
		DatePickerSettings dateSettings2 = new DatePickerSettings();
		dateSettings2.setAllowKeyboardEditing(false);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblData, "cell 5 4,alignx trailing");
		dpDataFiltro = new DatePicker(dateSettings2);
		contentPane.add(dpDataFiltro,"cell 6 4 2 1,growx");
		
		//JFormattedTextField ftfDataFinal = new JFormattedTextField();
		//contentPane.add(ftfDataFinal, "cell 6 6 2 1,growx");
		
		JButton btnGerarRelatorio = new JButton("Gerar Relat\u00F3rio");
		btnGerarRelatorio.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnGerarRelatorio, "cell 2 6,alignx right");
		
		JButton btnGerarXls = new JButton("Gerar xls");
		btnGerarXls.setIcon(new ImageIcon("C:\\Users\\rosan\\git\\senac-dd-20202-lucaszarbato2\\icones\\iconeExcelmenor.png"));
		btnGerarXls.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnGerarXls, "cell 6 6");
		
		//método chamar xls no botão. Falta fazer o método de gerar relatório.
		btnGerarXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaArquivos = new JFileChooser();

				int opcaoSelecionada = janelaArquivos.showSaveDialog(null);

				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminho = janelaArquivos.getSelectedFile().getAbsolutePath();

					ControllerVacina controller = new ControllerVacina();
					// Não deixar erros no código!
//					String mensagem = controller.gerarRelatorioTotalVacinaPorPesquisador(vacinas, caminhoEscolhido, tipoRelatorio); //aqui está ligado ao método gerar relatório, que equivale a uma consulta de vacina
//
//					JOptionPane.showMessageDialog(null, mensagem);
				}
			}
		});
		
		tableResultados = new JTable();
		tableResultados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tableResultados.setModel(new DefaultTableModel(
			new Object[][] {
				{"Usu\u00E1rio", "Data Inicial", "Data Final", "País de Origem", "Instituição", "Total de Vacinas"},
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
		contentPane.add(tableResultados, "cell 0 8 8 1,grow");
		
		JButton buttonPagAnterior = new JButton("< Anterior");
		buttonPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(buttonPagAnterior, "cell 2 10");
		
		//evento de passar pág anterior
		buttonPagAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				gerarRelatorio();
			}
		});
		
		lblPagAtual = new JLabel("1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblPagAtual, "cell 4 10,alignx center");
		
		JButton btnPagProxima = new JButton("Pr\u00F3ximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPagProxima, "cell 6 10");
		
		//evento para passar a próxima página
		btnPagProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				gerarRelatorio();
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnVoltar, "cell 4 11,alignx center");
	}

	protected void gerarRelatorio() {
		// TODO Auto-generated method stub
	}

}
