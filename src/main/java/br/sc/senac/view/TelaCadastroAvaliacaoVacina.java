package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.sc.senac.controller.ControllerNota;
import br.sc.senac.controller.ControllerPessoa;
import br.sc.senac.controller.ControllerVacina;
import br.sc.senac.model.utilidades.Utils;
import br.sc.senac.model.vo.Nota;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaCadastroAvaliacaoVacina extends JFrame {

	private JPanel contentPane;
	private JComboBox cbPessoaTestada;
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
		ControllerPessoa controllerPessoa = new ControllerPessoa();
		ControllerVacina controllerVacina = new ControllerVacina();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow][][161.00][grow][63.00][]", "[][][][][][][][]"));
		
		JLabel lblCadastroDePessoa = new JLabel("Cadastro Avalia\u00E7\u00E3o Vacina");
		lblCadastroDePessoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblCadastroDePessoa, "cell 3 0 2 1");
		
		JLabel lblNomeVacina = new JLabel("Nome Vacina:");
		lblNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNomeVacina, "cell 0 2,alignx trailing");
		
		MaskFormatter mascaraNota = new MaskFormatter("#.#");
		
		List<Vacina> listaDeVacinas = controllerVacina.coletarTodasVacinas();
		cbNomeVacina = new JComboBox();
		cbNomeVacina.setModel(new DefaultComboBoxModel(listaDeVacinas.toArray()));
		contentPane.add(cbNomeVacina, "cell 1 2 2 1,growx");
		
		JLabel lblPessoaTestada = new JLabel("Pessoa Testada:");
		lblPessoaTestada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPessoaTestada, "cell 4 2,alignx trailing");
		
		List<Pessoa> listaDePessoas = controllerPessoa.coletarTodasPessoas();
		cbPessoaTestada = new JComboBox();
		cbPessoaTestada.setModel(new DefaultComboBoxModel(listaDePessoas.toArray()));
		cbPessoaTestada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbPessoaTestada, "cell 5 2 2 1,growx");
		
		JLabel lbNota = new JLabel("Nota (1 a 5):");
		lbNota.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lbNota, "cell 0 4,alignx trailing");
		
		ftfNota = new JFormattedTextField(mascaraNota);
		ftfNota.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(ftfNota, "cell 1 4,growx");
		final JFrame janelaAtual = this;
		
				
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Preencher o Objeto com os dados da tela
				Nota novaNota = new Nota();
				novaNota.setPessoa((Pessoa)cbPessoaTestada.getSelectedItem());
				novaNota.setVacina((Vacina)cbNomeVacina.getSelectedItem());
				novaNota.setValor(Utils.stringToDouble(ftfNota.getText()));
				
				// Instanciar um controller adequado
				ControllerNota controller = new ControllerNota();
				
				// Chamar o método salvar no controller e pegar a mensagem retornada
				String mensagem = controller.salvar(novaNota);

				// Mostrar a mensagem devolvida pelo controller
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		contentPane.add(btnSalvar, "cell 2 6");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaAtual.dispose();
			}
		});
		contentPane.add(btnCancelar, "cell 5 6");
	}

}
