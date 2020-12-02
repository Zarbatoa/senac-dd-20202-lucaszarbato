package br.sc.senac.view.cadastro;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

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

import br.sc.senac.controller.ControllerPessoa;
import br.sc.senac.controller.ControllerVacina;
import br.sc.senac.model.utilidades.Constantes;
import br.sc.senac.model.vo.Pais;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaCadastroVacina extends JFrame {

	private JPanel contentPane;
	private JTextField tfNomeVacina;
	private JComboBox cbEstagioPesquisa;
	private DatePicker dpDataInicioPesquisa;
	private JComboBox cbPaisOrigem;
	private JComboBox cbPesquisadorResponsavel;
	
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
		ControllerPessoa controllerPessoa = new ControllerPessoa();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 329);
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
		
		List<Pessoa> listaDePesquisadores = controllerPessoa.coletarListaDePesquisadores();
		
		JLabel lblEstagioPesquisa = new JLabel("Est\u00E1gio da Pesquisa:");
		lblEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblEstagioPesquisa, "cell 4 2,alignx trailing");
		
		cbEstagioPesquisa = new JComboBox();
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(Constantes.LISTA_ESTAGIOS_VACINA_EDICAO_CADASTRO));
		cbEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbEstagioPesquisa, "cell 5 2,growx");
		
		JLabel lblPaisDeOrigem = new JLabel("Pa\u00EDs de Origem:");
		lblPaisDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPaisDeOrigem, "cell 0 4,alignx trailing");
		
		cbPaisOrigem = new JComboBox();
		cbPaisOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbPaisOrigem.setModel(new DefaultComboBoxModel(Constantes.OPCOES_PAISES_EDICAO_CADASTRO));
		contentPane.add(cbPaisOrigem, "cell 1 4 2 1,growx");
		
		JLabel lblPesquisadorResponsavel = new JLabel("Pesquisador Respons\u00E1vel:");
		lblPesquisadorResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPesquisadorResponsavel, "cell 4 4,alignx trailing");
		cbPesquisadorResponsavel = new JComboBox();
		cbPesquisadorResponsavel.setModel(new DefaultComboBoxModel(listaDePesquisadores.toArray()));
		contentPane.add(cbPesquisadorResponsavel, "cell 5 4,growx");
		
		JLabel lblnicioPesquisa = new JLabel("In\u00EDcio Pesquisa:");
		lblnicioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblnicioPesquisa, "cell 0 6,alignx trailing");
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dpDataInicioPesquisa = new DatePicker(dateSettings);
		contentPane.add(dpDataInicioPesquisa,"cell 1 6 2 1,growx");
		final JFrame janelaAtual = this;
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Preencher o Objeto com os dados da tela
				Vacina novaVacina = new Vacina();
				novaVacina.setNome(tfNomeVacina.getText());
				novaVacina.setPesquisadorResponsavel((Pessoa)cbPesquisadorResponsavel.getSelectedItem());
				novaVacina.setPaisOrigem(((Pais)cbPaisOrigem.getSelectedItem()).getNome());
				novaVacina.setEstagioPesquisa(Vacina.getIntEstagioDePesquisa((String)cbEstagioPesquisa.getSelectedItem()));
				novaVacina.setDataInicioPesquisa(dpDataInicioPesquisa.getDate());
				
				// Instanciar um controller adequado
				ControllerVacina controller = new ControllerVacina();
				
				// Chamar o m�todo salvar no controller e pegar a mensagem retornada
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