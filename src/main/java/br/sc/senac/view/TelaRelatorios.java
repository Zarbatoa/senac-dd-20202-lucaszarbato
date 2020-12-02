package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import br.sc.senac.controller.ControllerPessoa;
import br.sc.senac.controller.ControllerVacina;
import br.sc.senac.model.utilidades.Constantes;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaRelatorios extends JFrame {

	private JPanel contentPane;
	private JComboBox cbRelatorioVacinas;
	private JTable tableResultados;
	private DatePicker dpDataInicioPesquisa;
	
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
		ControllerPessoa controllerPessoa = new ControllerPessoa();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][27.00,grow][grow][][91.00][41.00][142.00,grow][42.00]", "[][][][][][][][][][][][]"));
		
		JLabel lbbRelatorioVacinas = new JLabel("Relat\u00F3rio de Vacinas");
		lbbRelatorioVacinas.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lbbRelatorioVacinas, "cell 3 0 3 1");
		
		cbRelatorioVacinas = new JComboBox();
		cbRelatorioVacinas.setModel(new DefaultComboBoxModel(Constantes.RELATORIO_VACINA_OPCOES));
		cbRelatorioVacinas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbRelatorioVacinas, "cell 0 2 8 1,growx");
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblSexo, "cell 0 4,alignx trailing");
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		
		List<Pessoa> listaDePesquisadores = controllerPessoa.coletarListaDePesquisadores();
		JComboBox cbSexo = new JComboBox();
		cbSexo.setModel(new DefaultComboBoxModel(listaDePesquisadores.toArray()));
		contentPane.add(cbSexo, "cell 1 4 2 1,growx");
		
		JLabel lblData = new JLabel("<html>Data<br /> In\u00EDcio Pesquisa</html>");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblData, "cell 5 4,alignx trailing");
		dpDataInicioPesquisa = new DatePicker(dateSettings);
		contentPane.add(dpDataInicioPesquisa,"cell 6 4 2 1,growx");
		
		JButton btnGerarRelatorio = new JButton("Gerar Relat\u00F3rio");
		btnGerarRelatorio.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnGerarRelatorio, "cell 2 6,alignx right");
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnLimpar, "cell 4 6,alignx right");
		
		JButton btnGerarXls = new JButton("Gerar xls");
		btnGerarXls.setIcon(new ImageIcon("icones/iconeExcelmenor.png"));
		btnGerarXls.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnGerarXls, "cell 6 6");
		
		//método chamar xls no botão. Falta fazer o método de gerar relatório.
		btnGerarXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaArquivos = new JFileChooser();

				int opcaoSelecionada = janelaArquivos.showSaveDialog(null);

				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					//TODO quando eu for ver as telas de relatorio não se esquecer dessas variáveis
					@SuppressWarnings("unused")
					String caminho = janelaArquivos.getSelectedFile().getAbsolutePath();

					@SuppressWarnings("unused")
					ControllerVacina controller = new ControllerVacina();
					
					//TODO continuar implementacao proposta (onde esses argumentos serão declarados e instanciados?)
					String mensagem = ""; //controller.gerarRelatorioTotalVacinaPorPesquisador(vacinas, caminhoEscolhido, tipoRelatorio); //aqui está ligado ao método gerar relatório, que equivale a uma consulta de vacina

					JOptionPane.showMessageDialog(null, mensagem);
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
				atualizarTabelaComUltimoSeletor();
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
				atualizarTabelaComUltimoSeletor();
			}
		});
	}

	protected void gerarRelatorio() {
		// TODO Auto-generated method stub
	}

	protected void atualizarTabelaComUltimoSeletor() {
		//TODO
//		if(ultimoSeletorUsado != null) {
//			lblPagAtual.setText(paginaAtual + "");
//			ultimoSeletorUsado.setPagina(paginaAtual);
//			ultimoSeletorUsado.setLimite(Constantes.TAMANHO_PAGINA);
//			ControllerVacina controlador = new ControllerVacina();
//			List<Vacina> vacinas = controlador.listarVacinas(ultimoSeletorUsado);
//			atualizarTabelaVacinas(vacinas);
//		}
	}
	
}
