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
public class TelaCadastroVacina extends JPanel {

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

		setBounds(100, 100, 669, 329);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new MigLayout("", "[][grow][grow][][][135.00,grow][47.00]", "[][][][][][][][][]"));
		
		JLabel lblCadastroDeVacina = new JLabel("Cadastro de Vacina");
		lblCadastroDeVacina.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(lblCadastroDeVacina, "cell 3 0 2 1");
		
		JLabel lblNomeVacina = new JLabel("Nome Vacina:");
		lblNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblNomeVacina, "cell 0 2,alignx trailing");
		
		tfNomeVacina = new JTextField();
		tfNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(tfNomeVacina, "cell 1 2 2 1,growx");
		tfNomeVacina.setColumns(10);
		
		List<Pessoa> listaDePesquisadores = controllerPessoa.coletarListaDePesquisadores();
		
		JLabel lblEstagioPesquisa = new JLabel("Est\u00E1gio da Pesquisa:");
		lblEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblEstagioPesquisa, "cell 4 2,alignx trailing");
		
		cbEstagioPesquisa = new JComboBox();
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(Constantes.LISTA_ESTAGIOS_VACINA_EDICAO_CADASTRO));
		cbEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(cbEstagioPesquisa, "cell 5 2,growx");
		
		JLabel lblPaisDeOrigem = new JLabel("Pa\u00EDs de Origem:");
		lblPaisDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblPaisDeOrigem, "cell 0 4,alignx trailing");
		
		cbPaisOrigem = new JComboBox();
		cbPaisOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbPaisOrigem.setModel(new DefaultComboBoxModel(Constantes.OPCOES_PAISES_EDICAO_CADASTRO));
		this.add(cbPaisOrigem, "cell 1 4 2 1,growx");
		
		JLabel lblPesquisadorResponsavel = new JLabel("Pesquisador Respons\u00E1vel:");
		lblPesquisadorResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblPesquisadorResponsavel, "cell 4 4,alignx trailing");
		cbPesquisadorResponsavel = new JComboBox();
		cbPesquisadorResponsavel.setModel(new DefaultComboBoxModel(listaDePesquisadores.toArray()));
		this.add(cbPesquisadorResponsavel, "cell 5 4,growx");
		
		JLabel lblnicioPesquisa = new JLabel("In\u00EDcio Pesquisa:");
		lblnicioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblnicioPesquisa, "cell 0 6,alignx trailing");
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dpDataInicioPesquisa = new DatePicker(dateSettings);
		this.add(dpDataInicioPesquisa,"cell 1 6 2 1,growx");
		
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
				
				// Chamar o método salvar no controller e pegar a mensagem retornada
				String mensagem = controller.salvar(novaVacina);

				// Mostrar a mensagem devolvida pelo controller
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		this.add(btnSalvar, "cell 1 8");
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetarTodosOsCampos();
			}
		});
		this.add(btnLimpar, "cell 5 8");
	}
	
	protected void resetarTodosOsCampos() {
		tfNomeVacina.setText("");
		cbEstagioPesquisa.setSelectedIndex(0);
		dpDataInicioPesquisa.clear();
		cbPaisOrigem.setSelectedIndex(0);
		
		if(cbPesquisadorResponsavel.getItemCount() != 0) {
			cbPesquisadorResponsavel.setSelectedIndex(0);
		};
	}

}
