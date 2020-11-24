package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.time.DateTimeException;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroPessoa extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfInstituicao;
	
	private JComboBox cbCategoria;
	private JComboBox cbSexo;
	private JTextField tfSobrenome;
	private JFormattedTextField ftfDataNascimento;
	private JFormattedTextField ftfCpf;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPessoa frame = new TelaCadastroPessoa();
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
	public TelaCadastroPessoa() throws ParseException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][][][grow][][]", "[][][][][][][][][][][]"));
		
		JLabel lblCadastroDePessoa = new JLabel("Cadastro de Pessoa");
		lblCadastroDePessoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblCadastroDePessoa, "cell 3 0");
		
		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "cell 0 2,alignx trailing");
		
		tfNome = new JTextField();
		contentPane.add(tfNome, "cell 1 2 2 1,growx");
		tfNome.setColumns(10);
		
		String[] opcoesSexo = {Pessoa.SEXO_MASCULINO, Pessoa.SEXO_FEMININO}; 
		
		MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		contentPane.add(lblSobrenome, "cell 4 2,alignx trailing");
		
		tfSobrenome = new JTextField();
		contentPane.add(tfSobrenome, "cell 5 2 2 1,growx");
		tfSobrenome.setColumns(10);
		
		JLabel lblCpf = new JLabel("Cpf:");
		contentPane.add(lblCpf, "cell 0 4,alignx trailing");
		ftfCpf = new JFormattedTextField(mascaraCpf);
		contentPane.add(ftfCpf, "cell 1 4 2 1,growx");
		
		MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		
		JLabel lblSexo = new JLabel("Sexo:");
		contentPane.add(lblSexo, "cell 4 4,alignx trailing");
		cbSexo = new JComboBox(opcoesSexo);
		contentPane.add(cbSexo, "cell 5 4 2 1,growx");
		
		JLabel lblCategoria = new JLabel("Categoria:");
		contentPane.add(lblCategoria, "cell 0 6,alignx trailing");
		
		TipoPessoa[] opcoesTipoPessoa = {TipoPessoa.TIPO_PESQUISADOR, TipoPessoa.TIPO_VOLUNTARIO, TipoPessoa.TIPO_PUBLICO_GERAL};
		cbCategoria = new JComboBox(opcoesTipoPessoa);
		contentPane.add(cbCategoria, "cell 1 6 2 1,growx");
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		contentPane.add(lblDataDeNascimento, "cell 4 6,alignx trailing");
		ftfDataNascimento = new JFormattedTextField(mascaraData);
		contentPane.add(ftfDataNascimento, "cell 5 6 2 1,growx");
		
		JLabel lblInstituicao = new JLabel("Institui\u00E7\u00E3o:");
		contentPane.add(lblInstituicao, "cell 4 8,alignx trailing");
		
		tfInstituicao = new JTextField();
		contentPane.add(tfInstituicao, "cell 5 8 2 1,growx");
		tfInstituicao.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Preencher o Objeto com os dados da tela
				Pessoa novaPessoa = new Pessoa();
				novaPessoa.setTipo((TipoPessoa)cbCategoria.getSelectedItem());
				novaPessoa.setInstituicao(new Instituicao(-1,tfInstituicao.getText()));
				novaPessoa.setNome(tfNome.getText());
				novaPessoa.setSobrenome(tfSobrenome.getText());
				LocalDate novaDataNascimento = null;
				try {
					novaDataNascimento = Utils.gerarLocalDateDeString(ftfDataNascimento.getText());
				} catch (ArrayIndexOutOfBoundsException arrayException) {
					JOptionPane.showMessageDialog(null, "Por favor, insira todos os campos de data de nascimento.");
					return;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Por favor, insira uma data válida em data de nascimento.");
					return;
				}
				novaPessoa.setDataNascimento(novaDataNascimento);
				novaPessoa.setSexo(((String)cbSexo.getSelectedItem()).charAt(0));
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
		contentPane.add(btnSalvar, "cell 2 10");
		
		JButton btnCancelar = new JButton("Cancelar");
		contentPane.add(btnCancelar, "cell 5 10");
	}

}
