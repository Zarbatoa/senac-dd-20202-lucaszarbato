package br.sc.senac.view.gerencia;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.sc.senac.controller.ControllerNota;
import br.sc.senac.controller.ControllerPessoa;
import br.sc.senac.controller.ControllerVacina;
import br.sc.senac.model.seletor.NotaSeletor;
import br.sc.senac.model.utilidades.Constantes;
import br.sc.senac.model.utilidades.Utils;
import br.sc.senac.model.vo.Nota;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;
import br.sc.senac.view.PanelComDados;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaGerenciamentoDeAvaliacaoVacinas extends PanelComDados {
	
	//TODO tamanho pagina volta pra cá?
	//private static final int TAMANHO_PAGINA = 0;

	private JComboBox cbPessoas;
	private JTable tableResultados;
	private JFormattedTextField ftfNotaInicial;
	private JLabel lblPagAtual;
	
	private JComboBox cbVacina;
	private JButton btnConsultar;
	private JButton btnPagAnterior;
	private JButton btnPagProxima;
	
	
	private NotaSeletor ultimoSeletorUsado;
	private int paginaAtual = 1;
	private JButton btnLimpar;
	private JLabel lblNotaFinal;
	private JFormattedTextField ftfNotaFinal;
	
	private List<Nota> ultimasNotasConsultadas;
	
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
		ultimasNotasConsultadas = new ArrayList<Nota>();
		ultimoSeletorUsado = null;
		ControllerPessoa controllerPessoa = new ControllerPessoa();
		ControllerVacina controllerVacina = new ControllerVacina();
		
		setBounds(100, 100, 655, 380);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new MigLayout("", "[grow][27.00,grow][grow][][105.00,grow][47.00][96.00,grow][42.00]", "[][][][][][][99.00][][]"));
		
		JLabel lblGerenciamentoDeVacina = new JLabel("Gerenciamento de Avalia\u00E7\u00E3o de Vacinas");
		lblGerenciamentoDeVacina.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(lblGerenciamentoDeVacina, "cell 2 0 4 1");
		
		JLabel lblNomeVacina = new JLabel("Nome Vacina:");
		lblNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblNomeVacina, "cell 0 2,alignx trailing");
		
		List<Vacina> listaDeVacinas = controllerVacina.coletarTodasVacinas();
		listaDeVacinas.add(0, Constantes.OPCAO_VACINA_TODAS);
		cbVacina = new JComboBox();
		cbVacina.setModel(new DefaultComboBoxModel(listaDeVacinas.toArray()));
		this.add(cbVacina, "cell 1 2 2 1,growx");
		
		JLabel lblPessoa = new JLabel("Pessoa testada:");
		lblPessoa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblPessoa, "cell 5 2,alignx trailing");
		
		List<Pessoa> listaDePessoas = controllerPessoa.coletarTodasPessoas();
		listaDePessoas.add(0, Constantes.OPCAO_PESSOA_TESTADA_TODAS);
		cbPessoas = new JComboBox();
		cbPessoas.setModel(new DefaultComboBoxModel(listaDePessoas.toArray()));
		cbPessoas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(cbPessoas, "cell 6 2 2 1,growx");
		
		JLabel lblNotaInicial = new JLabel("<html>Nota<br />Inicial:</html>");
		lblNotaInicial.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblNotaInicial, "cell 0 4,alignx center");
		
		MaskFormatter mascaraNotaInicial = new MaskFormatter("#.#");
		ftfNotaInicial = new JFormattedTextField(mascaraNotaInicial);
		this.add(ftfNotaInicial, "cell 1 4,growx");
		
		lblNotaFinal = new JLabel("<html>Nota<br />Final:</html>");
		this.add(lblNotaFinal, "cell 3 4,alignx trailing");
		
		MaskFormatter mascaraNotaFinal = new MaskFormatter("#.#");
		ftfNotaFinal = new JFormattedTextField(mascaraNotaFinal);
		this.add(ftfNotaFinal, "cell 4 4,growx");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnConsultar, "cell 6 4,alignx center");
		
		btnConsultar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				consultarNotas();
			}
		});
		
		tableResultados = new JTable();
		tableResultados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		definirModeloPadraoTabela();
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetarTodosOsCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnLimpar, "cell 4 5,alignx center");
		this.add(tableResultados, "cell 0 6 8 1,grow");
		
		btnPagAnterior = new JButton("< Anterior");
		btnPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnPagAnterior, "cell 1 7");
		
		btnPagAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				atualizarTabelaComUltimoSeletor();
			}
		});
		
		btnPagProxima = new JButton("Pr\u00F3ximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnPagProxima, "cell 6 7");
		
		btnPagProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				atualizarTabelaComUltimoSeletor();
			}
		});
		
		lblPagAtual = new JLabel("1");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(lblPagAtual, "cell 2 7 4 1,alignx center");
	}

	protected void resetarTodosOsCampos() {
		cbVacina.setSelectedIndex(0);
		cbPessoas.setSelectedIndex(0);
		ftfNotaInicial.setText("");
		ftfNotaFinal.setText("");
		definirModeloPadraoTabela();
	}
	
	protected void atualizarTabelaComUltimoSeletor() {
		if(ultimoSeletorUsado != null) {
			lblPagAtual.setText(paginaAtual + "");
			ultimoSeletorUsado.setPagina(paginaAtual);
			ultimoSeletorUsado.setLimite(Constantes.TAMANHO_PAGINA);
			ControllerNota controlador = new ControllerNota();
			List<Nota> notas = controlador.listarNotas(ultimoSeletorUsado);
			ultimasNotasConsultadas = notas;
			atualizarTabelaNotas(notas);
		}
	}
	
	protected void consultarNotas() {
		paginaAtual = 1;
		lblPagAtual.setText(paginaAtual + "");

		ControllerNota controlador = new ControllerNota();
		NotaSeletor seletor = new NotaSeletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(Constantes.TAMANHO_PAGINA);
		
		// Preenche os campos de filtro da tela no seletor
		seletor.setVacina((Vacina) cbVacina.getSelectedItem());
		seletor.setPessoa((Pessoa) cbPessoas.getSelectedItem());
		seletor.setValorInicial(Utils.stringToDouble(ftfNotaInicial.getText()));
		seletor.setValorFinal(Utils.stringToDouble(ftfNotaFinal.getText()));
		
		// aqui é feita a consulta das pessoas e atualizada a tabela
		List<Nota> notas = controlador.listarNotas(seletor);
		ultimoSeletorUsado = seletor;
		ultimasNotasConsultadas = notas;

		atualizarTabelaNotas(notas);
	}
	
	private void atualizarTabelaNotas(List<Nota> notas) {
		this.definirModeloPadraoTabela();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();

		for (int i = 0; i < notas.size(); i++) {
			Nota nota = notas.get(i);
			
			modelo.setValueAt(nota.getId(), i+1, 0);
			modelo.setValueAt(nota.getVacina(), i+1, 1);
			modelo.setValueAt(nota.getPessoa(), i+1, 2);
			modelo.setValueAt(nota.getValor(), i+1, 3);
		}
	}

	private void definirModeloPadraoTabela() {
		tableResultados.setModel(new DefaultTableModel(
			new Object[][] {
				getNomesColunas(),
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			getNomesColunas()
		) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	}

	@Override
	public boolean hasDados() {
		return (ultimoSeletorUsado != null);
	}

	@Override
	public String[] getNomesColunas() {
		return new String[] {
				"#", "Nome Vacina", "Pessoa Testada", "Nota"
			};
	}

	@Override
	public List<String[]> getDadosVisiveis() {
		List<String[]> dadosVisiveis = new ArrayList<String[]>();
		
		for (int i = 0; i < ultimasNotasConsultadas.size(); i++) {
			String[] dadoAtual = new String[4];
			Nota nota = ultimasNotasConsultadas.get(i);
			
			dadoAtual[0] = nota.getId() + "";
			dadoAtual[1] = nota.getVacina().toString();
			dadoAtual[2] = nota.getPessoa().toString();
			dadoAtual[3] = nota.getValor() + "";
			
			dadosVisiveis.add(dadoAtual);
		}
		return dadosVisiveis;
	}

	@Override
	public List<String[]> getDadosCompletos() {
		List<String[]> dadosVisiveis = new ArrayList<String[]>();
		ControllerNota controller = new ControllerNota();
		
		int ultimaPagUsada = ultimoSeletorUsado.getPagina();
		ultimoSeletorUsado.setPagina(-1);
		List<Nota> listaCompleta = controller.listarNotas(ultimoSeletorUsado);
		ultimoSeletorUsado.setPagina(ultimaPagUsada);
		
		for (int i = 0; i < listaCompleta.size(); i++) {
			String[] dadoAtual = new String[4];
			Nota nota = listaCompleta.get(i);
			
			dadoAtual[0] = nota.getId() + "";
			dadoAtual[1] = nota.getVacina().toString();
			dadoAtual[2] = nota.getPessoa().toString();
			dadoAtual[3] = nota.getValor() + "";
			
			dadosVisiveis.add(dadoAtual);
		}
		return dadosVisiveis;
	}

}
