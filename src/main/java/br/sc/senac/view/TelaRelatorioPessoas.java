package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import br.sc.senac.model.utilidades.Constantes;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaRelatorioPessoas extends JFrame {
	
	private JPanel contentPane;
	private JComboBox cbRelatorioPessoas;
	private JTable table;
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
					TelaRelatorioPessoas frame = new TelaRelatorioPessoas();
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
	public TelaRelatorioPessoas() throws ParseException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][27.00,grow][grow][][91.00][41.00][142.00,grow][42.00]", "[][][][][][][][][99.00][][][]"));
		
		JLabel lbbRelatorioPessoas = new JLabel("Relat\u00F3rio de Pessoas");
		lbbRelatorioPessoas.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lbbRelatorioPessoas, "cell 3 0 3 1");
		
		String[] opcoesSexo = {Constantes.SEXO_MASCULINO, Constantes.SEXO_FEMININO}; 
		
		cbRelatorioPessoas = new JComboBox(opcoesSexo);
		cbRelatorioPessoas.setModel(new DefaultComboBoxModel(new String[] {"Total de Pessoas por Sexo", "Total de Pessoas por Instituição", "Total de Pessoas por Categoria", "Total de Pessoas por Faixa de Idade (intervalo: 5 anos)", "Total de Pessoas por Faixa de Idade (intervalo: 10 anos)", "", ""}));
		cbRelatorioPessoas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbRelatorioPessoas, "cell 0 2 8 1,growx");
		
		JLabel lblDataInicio = new JLabel("De:");
		lblDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblDataInicio, "cell 0 4,alignx trailing");
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dataInicio = new DatePicker(dateSettings);
		contentPane.add(dataInicio,"cell 1 4 2 1,growx");
		
		JLabel lblDataFinal = new JLabel("At\u00E9:");
		contentPane.add(lblDataFinal, "cell 5 4,alignx trailing");
		
		DatePickerSettings dateSettings2 = new DatePickerSettings();
		dateSettings2.setAllowKeyboardEditing(false);
		dataFinal = new DatePicker(dateSettings2);
		contentPane.add(dataFinal,"cell 6 4 2 1,growx");
		
		JButton btnGerarRelatorio = new JButton("Gerar Relat\u00F3rio");
		btnGerarRelatorio.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnGerarRelatorio, "cell 2 6,alignx right");
		
		JButton btnGerarXls = new JButton("Gerar xls");
		btnGerarXls.setIcon(new ImageIcon("C:\\Users\\rosan\\git\\senac-dd-20202-lucaszarbato2\\icones\\iconeExcelmenor.png"));
		btnGerarXls.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnGerarXls, "cell 6 6");
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Masculino", "Feminino", "Todos"},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		contentPane.add(table, "cell 0 8 8 1,grow");
		
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
		contentPane.add(btnVoltar, "cell 4 11,alignx center,aligny baseline");
	}

	// para aparecer na tabela abaixo
	protected void gerarRelatorio() {
		// TODO Auto-generated method stub
		
	}



}
