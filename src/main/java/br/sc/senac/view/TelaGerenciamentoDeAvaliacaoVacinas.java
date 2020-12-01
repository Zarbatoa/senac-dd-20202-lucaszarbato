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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.sc.senac.controller.ControllerNota;
import br.sc.senac.model.seletor.AvaliacaoVacinaSeletor;
import br.sc.senac.model.vo.Nota;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaGerenciamentoDeAvaliacaoVacinas extends JFrame {
	
	private static final int TAMANHO_PAGINA = 0; // relacionado a paginação

	private JPanel contentPane;
	private JTextField tfNome;
	private JComboBox cbPessoas;
	private JTable tableResultados;
	private JFormattedTextField ftfNota;
	
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
					TelaGerenciamentoDeAvaliacaoVacinas frame = new TelaGerenciamentoDeAvaliacaoVacinas();
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
	public TelaGerenciamentoDeAvaliacaoVacinas() throws ParseException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][27.00,grow][grow][][105.00][47.00][96.00,grow][42.00]", "[][][][][][][][][99.00][][][]"));
		
		JLabel lblGerenciamentoDeVacina = new JLabel("Gerenciamento de Avalia\u00E7\u00E3o de Vacinas");
		lblGerenciamentoDeVacina.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblGerenciamentoDeVacina, "cell 3 0 3 1");
		
		JLabel lblNomeVacina = new JLabel("Nome Vacina:");
		lblNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNomeVacina, "cell 0 2,alignx trailing");
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tfNome, "cell 1 2 2 1,growx");
		tfNome.setColumns(10);
		
		//String[] opcoesSexo = {Pessoa.SEXO_MASCULINO, Pessoa.SEXO_FEMININO}; 
		
		String[] opcoesNomeSobrenome = {"Nome Exemplo", "Pegar do Banco!", "Mudar!!!"};
		JLabel lblPessoa = new JLabel("Pessoa testada:");
		lblPessoa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblPessoa, "cell 5 2,alignx trailing");
		cbPessoas = new JComboBox(opcoesNomeSobrenome);
		cbPessoas.setModel(new DefaultComboBoxModel(opcoesNomeSobrenome));
		cbPessoas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(cbPessoas, "cell 6 2 2 1,growx");
		
		JLabel lblNota = new JLabel("Nota (1 a 5):");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNota, "cell 0 4,alignx trailing");
		
		ftfNota = new JFormattedTextField();
		contentPane.add(ftfNota, "cell 1 4,growx");
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnFiltrar, "cell 4 6,alignx right");
		
		// criei esse método para já adicionar o filtro seletor nessa tela
		btnFiltrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				consultarGerenciamentoAvaliacaoVacina() ;
			}
		});
		
		tableResultados = new JTable();
		tableResultados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tableResultados.setModel(new DefaultTableModel(
			new Object[][] {
				{"Nome Vacina", "Pessoa Testada", "Nota (1 a 5)"},
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
		contentPane.add(tableResultados, "cell 0 8 8 1,grow");
		
		JButton buttonPagAnterior = new JButton("< Anterior");
		buttonPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(buttonPagAnterior, "cell 1 10");
		
		//evento de passar pág anterior
				buttonPagAnterior.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (paginaAtual > 1) {
							paginaAtual--;
						}
						consultarGerenciamentoAvaliacaoVacina();
					}
				});
		
		JButton btnPagProxima = new JButton("Pr\u00F3ximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnPagProxima, "cell 6 10");
		
		//evento para passar a próxima página
		btnPagProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				consultarGerenciamentoAvaliacaoVacina();
			}
		});
		
		lblPagAtual = new JLabel("                          1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblPagAtual, "cell 4 10");
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnVoltar, "cell 4 11,alignx right,aligny baseline");
	}

	// método relacionado ao Botão Filtrar
	protected void consultarGerenciamentoAvaliacaoVacina() {
		
		lblPagAtual.setText(paginaAtual + "");

		ControllerNota controlador = new ControllerNota();
		AvaliacaoVacinaSeletor seletor = new AvaliacaoVacinaSeletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(TAMANHO_PAGINA);
		
		// Preenche os campos de filtro da tela no seletor
		seletor.setNomeVacina(tfNome.getText());
		seletor.setNota(Double.parseDouble(ftfNota.getText()));
		seletor.setNomePessoa(cbPessoas.getSelectedItem().toString());
		
		// aqui é feita a consulta da avaliacação de vacinas e atualizada a tabela
		List<Nota> notas = controlador.listarNotas(seletor);
		atualizarTabelaNotas(notas);

	}

	private void atualizarTabelaNotas(List<Nota> notas) {
		
		// atualiza o atributo avaliação vacinas consultadas
		List<Nota> notasVacinasConsultadas = notas;
		
		this.limparTabela();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		for (Nota nota : notas) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ordem do cabeçalho da tabela

			String[] novaLinha = new String[] { nota.getVacina().getNome(),  nota.getPessoa().getNomeCompleto(), nota.getValor() + "" };
			modelo.addRow(novaLinha);
		}
	}

	private void limparTabela() {
		tableResultados.setModel(new DefaultTableModel(new String[][] { { "Nome Vacina", "Pessoa Testada", "Nota (1 a 5)" }, },
				new String[] { "Nome Vacina", "Pessoa Testada", "Nota (1 a 5)"}));
	}

}
