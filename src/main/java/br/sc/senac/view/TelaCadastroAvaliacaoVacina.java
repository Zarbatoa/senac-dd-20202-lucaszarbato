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

public class TelaCadastroAvaliacaoVacina extends JFrame {

	private JPanel contentPane;
	private JComboBox cbNomeSobrenome;
	private JFormattedTextField ftfNota;
	private JComboBox cbNomeVacina;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroAvaliacaoVacina frame = new TelaCadastroAvaliacaoVacina();
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
	public TelaCadastroAvaliacaoVacina() throws ParseException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow][][161.00][grow][63.00][]", "[][][][][][][][][][][]"));
		
		JLabel lblCadastroDePessoa = new JLabel("Cadastro Avalia\u00E7\u00E3o Vacina");
		lblCadastroDePessoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblCadastroDePessoa, "cell 3 0 2 1");
		
		JLabel lblNomeVacina = new JLabel("Nome Vacina:");
		lblNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNomeVacina, "cell 0 2,alignx trailing");
		
		String[] opcoesSexo = {Pessoa.SEXO_MASCULINO, Pessoa.SEXO_FEMININO}; // retirar esse e trocar por opcoesNomeSobrenome
		
		MaskFormatter mascaraNota = new MaskFormatter("#.#");
		
		cbNomeVacina = new JComboBox();
		cbNomeVacina.setModel(new DefaultComboBoxModel(new String[] {"Oxford", "Russa"}));
		contentPane.add(cbNomeVacina, "cell 1 2 2 1,growx");
		
		JLabel lblNomeSobrenome = new JLabel("Nome e Sobrenome:");
		lblNomeSobrenome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNomeSobrenome, "cell 4 2,alignx trailing");
		
		cbNomeSobrenome = new JComboBox(opcoesNomeSobrenome); //fazer aqui a lista ou método se tiver
		cbNomeSobrenome.setModel(new DefaultComboBoxModel(new String[] {""}));
		cbNomeSobrenome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbNomeSobrenome, "cell 5 2 2 1,growx");
		
		JLabel lbNota = new JLabel("Nota (1 a 5):");
		lbNota.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lbNota, "cell 0 4,alignx trailing");
		
		ftfNota = new JFormattedTextField(mascaraNota);
		ftfNota.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(ftfNota, "cell 1 4,growx");
		
				
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
				novaPessoa.setSexo(((String)cbNomeSobrenome.getSelectedItem()).charAt(0));
				// verificar se cpf é vazio e se ele é válido no BO
				novaPessoa.setCpf(Utils.desformatarCpf(ftfNota.getText()));
				
				// Instanciar um controller adequado
				ControllerPessoa controller = new ControllerPessoa();
				
				// Chamar o método salvar no controller e pegar a mensagem retornada
				String mensagem = controller.salvar(novaPessoa);
				
				// Mostrar a mensagem devolvida pelo controller
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
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
