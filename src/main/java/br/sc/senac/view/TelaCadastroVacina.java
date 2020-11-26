package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.sc.senac.controller.ControllerPessoa;
import br.sc.senac.model.Utils;
import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;
import net.miginfocom.swing.MigLayout;
import javax.swing.DefaultComboBoxModel;

public class TelaCadastroVacina extends JFrame {

	private JPanel contentPane;
	private JTextField tfNomeVacina;
	private JComboBox cbEstagioPesquisa;
	
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
		setBounds(100, 100, 650, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow][][][135.00,grow][47.00]", "[][][][][][][][][][][]"));
		
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
		
		String[] opcoesSexo = {Pessoa.SEXO_MASCULINO, Pessoa.SEXO_FEMININO}; 
		
		MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
		
		JLabel lblPesquisadorResponsavel = new JLabel("Pesquisador Respons\u00E1vel:");
		lblPesquisadorResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPesquisadorResponsavel, "cell 4 2,alignx trailing");
		
		JComboBox cbPesquisadorResponsavel = new JComboBox();
		contentPane.add(cbPesquisadorResponsavel, "cell 5 2 2 1,growx");
		
		JLabel lblPaisDeOrigem = new JLabel("Pa\u00EDs de Origem:");
		lblPaisDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPaisDeOrigem, "cell 0 4,alignx trailing");
		
		MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		
		JComboBox cbPaisOrigem = new JComboBox();
		cbPaisOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbPaisOrigem.setModel(new DefaultComboBoxModel(new String[] {"Argélia", "Brasil", "China", "Estados Unidos", "Reino Unido", "R\u00FAssia"}));
		contentPane.add(cbPaisOrigem, "cell 1 4 2 1,growx");
		
		JLabel lblEstagioPesquisa = new JLabel("Est\u00E1gio da Pesquisa:");
		lblEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblEstagioPesquisa, "cell 4 4,alignx trailing");
		cbEstagioPesquisa = new JComboBox(opcoesSexo);
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Inicial", "Intermedi\u00E1rio", "Final"}));
		cbEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbEstagioPesquisa, "cell 5 4 2 1,growx");
		
		TipoPessoa[] opcoesTipoPessoa = {TipoPessoa.TIPO_PESQUISADOR, TipoPessoa.TIPO_VOLUNTARIO, TipoPessoa.TIPO_PUBLICO_GERAL};
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Preencher o Objeto com os dados da tela
				Pessoa novaPessoa = new Pessoa();
				novaPessoa.setTipo((TipoPessoa)cbCategoria.getSelectedItem());
				novaPessoa.setInstituicao(new Instituicao(-1,tfInstituicao.getText()));
				novaPessoa.setNome(tfNomeVacina.getText());
				novaPessoa.setSobrenome(tfSobrenome.getText());
				LocalDate novaDataNascimento = null;
				try {
					novaDataNascimento = Utils.gerarLocalDateDeString(ftfDataNascimento.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Por favor, insira uma data válida em data de nascimento.");
					return;
				}
				novaPessoa.setDataNascimento(novaDataNascimento);
				novaPessoa.setSexo(((String)cbEstagioPesquisa.getSelectedItem()).charAt(0));
				// verificar se cpf é vazio e se ele é válido no BO
				novaPessoa.setCpf(Utils.desformatarCpf(ftfCpf.getText()));
				
				// Instanciar um controller adequado
				ControllerPessoa controller = new ControllerPessoa();
				
				// Chamar o método salvar no controller e pegar a mensagem retornada
				String mensagem = controller.salvar(novaPessoa);
				
				// Mostrar a mensagem devolvida pelo controller
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		
		JLabel lblnicioPesquisa = new JLabel("In\u00EDcio Pesquisa:");
		lblnicioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblnicioPesquisa, "cell 0 6,alignx trailing");
		
		JFormattedTextField ftfInicioPesquisa = new JFormattedTextField();
		contentPane.add(ftfInicioPesquisa, "cell 1 6 2 1,growx");
		contentPane.add(btnSalvar, "cell 2 10");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		final JFrame janelaAtual = this;
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaAtual.dispose();
			}
		});
		contentPane.add(btnCancelar, "cell 5 10");
	}

}
