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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import br.sc.senac.controller.ControllerVacina;
import br.sc.senac.model.vo.Vacina;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaCadastroVacina extends JFrame {

	private JPanel contentPane;
	private JTextField tfNomeVacina;
	private JComboBox cbEstagioPesquisa;
	private DatePicker dataInicioPesquisa;
	private JComboBox cbPaisOrigem;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroVacina frame = new TelaCadastroVacina();
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
	public TelaCadastroVacina() throws ParseException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow][][][135.00,grow][47.00]", "[][][][][][][][][]"));
		
		JLabel lblCadastroDeVacina = new JLabel("Cadastro de Vacina");
		lblCadastroDeVacina.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblCadastroDeVacina, "cell 3 0 2 1");
		
		JLabel lblNomeVacina = new JLabel("Nome Vacina:");
		lblNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNomeVacina, "cell 0 2,alignx trailing");
		
		tfNomeVacina = new JTextField();
		tfNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfNomeVacina, "cell 1 2 2 1,growx");
		tfNomeVacina.setColumns(10);
		
		JLabel lblPesquisadorResponsavel = new JLabel("Pesquisador Respons\u00E1vel:");
		lblPesquisadorResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPesquisadorResponsavel, "cell 4 2,alignx trailing");
		
		JComboBox cbPesquisadorResponsavel = new JComboBox();
		contentPane.add(cbPesquisadorResponsavel, "cell 5 2 2 1,growx");
		
		JLabel lblPaisDeOrigem = new JLabel("Pa\u00EDs de Origem:");
		lblPaisDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPaisDeOrigem, "cell 0 4,alignx trailing");
		
		cbPaisOrigem = new JComboBox();
		cbPaisOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbPaisOrigem.setModel(new DefaultComboBoxModel(new String[] {"Argélia", "Brasil", "China", "Estados Unidos", "Reino Unido", "Rússia"}));
		contentPane.add(cbPaisOrigem, "cell 1 4 2 1,growx");
		
		String [] estagiosVacina = Vacina.getEstagiosDeVacina();
		JLabel lblEstagioPesquisa = new JLabel("Est\u00E1gio da Pesquisa:");
		lblEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblEstagioPesquisa, "cell 4 4,alignx trailing");
		cbEstagioPesquisa = new JComboBox();
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(estagiosVacina));
		cbEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbEstagioPesquisa, "cell 5 4 2 1,growx");
		
		JLabel lblnicioPesquisa = new JLabel("In\u00EDcio Pesquisa:");
		lblnicioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblnicioPesquisa, "cell 0 6,alignx trailing");
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dataInicioPesquisa = new DatePicker(dateSettings);
		contentPane.add(dataInicioPesquisa,"cell 1 6 2 1,growx");
		final JFrame janelaAtual = this;
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Preencher o Objeto com os dados da tela
				Vacina novaVacina = new Vacina();
				//TODO settar atributos do Objeto
				// Instanciar um controller adequado
				ControllerVacina controller = new ControllerVacina();
				
				// Chamar o método salvar no controller e pegar a mensagem retornada
				String mensagem = controller.salvar(novaVacina);
				
				// Mostrar a mensagem devolvida pelo controller
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		contentPane.add(btnSalvar, "cell 1 8");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaAtual.dispose();
			}
		});
		contentPane.add(btnCancelar, "cell 5 8");
	}

}
