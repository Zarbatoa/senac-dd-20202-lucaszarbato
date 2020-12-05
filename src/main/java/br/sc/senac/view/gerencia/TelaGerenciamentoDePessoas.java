package br.sc.senac.view.gerencia;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import br.sc.senac.controller.ControllerPessoa;
import br.sc.senac.model.seletor.PessoaSeletor;
import br.sc.senac.model.utilidades.Constantes;
import br.sc.senac.model.utilidades.StatusMensagem;
import br.sc.senac.model.utilidades.Utils;
import br.sc.senac.model.vo.Instituicao;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.TipoPessoa;
import br.sc.senac.view.PanelComDados;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaGerenciamentoDePessoas extends PanelComDados {

	private JTextField tfNome;
	private JTextField tfInstituicao;
	private JComboBox cbSexo;
	private DatePicker dpDataNascimento;
	private JFormattedTextField ftfCpf;
	private JTable tableResultados;
	private JTextField tfSobrenome;
	private JComboBox cbCategoria;
	
	private JLabel lblPagAtual;
	private JButton btnExcluir;
	private JButton btnConsultar;
	private JButton btnPagAnterior;
	private JButton btnPagProxima;
	private JButton btnVoltar;
	private JButton btnPegarRegistro;
	private JButton btnEditar;
	private JButton btnLimpar;
	
	private PessoaSeletor ultimoSeletorUsado;
	private int paginaAtual = 1;
	
	private List<Pessoa> ultimasPessoasConsultadas;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciamentoDePessoas frame = new TelaGerenciamentoDePessoas();
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
	public TelaGerenciamentoDePessoas() throws ParseException {
		ultimasPessoasConsultadas = new ArrayList<Pessoa>();
		dadosPreenchidos = true;
		ultimoSeletorUsado = null;
		
		setBounds(100, 100, 700, 490);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new MigLayout("", "[grow][][grow][24.00][105.00][47.00][grow][][]", "[][][][][][][][][][][][][96.00][][]"));
		
		JLabel lblGerenciamentoDePessoa = new JLabel("Gerenciamento de Pessoas");
		lblGerenciamentoDePessoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(lblGerenciamentoDePessoa, "cell 3 0 3 1,alignx center");
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblNome, "cell 0 2,alignx trailing");
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(tfNome, "cell 1 2 3 1,growx");
		tfNome.setColumns(10);
		
		MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblSobrenome, "cell 5 2,alignx trailing");
		
		tfSobrenome = new JTextField();
		tfSobrenome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(tfSobrenome, "cell 6 2 2 1,growx");
		tfSobrenome.setColumns(10);
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblCpf, "cell 0 4,alignx trailing");
		ftfCpf = new JFormattedTextField(mascaraCpf);
		ftfCpf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(ftfCpf, "cell 1 4 3 1,growx");
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblSexo, "cell 5 4,alignx trailing");
		cbSexo = new JComboBox();
		cbSexo.setModel(new DefaultComboBoxModel(Constantes.OPCOES_SEXO_GERAL));
		cbSexo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(cbSexo, "cell 6 4 2 1,growx");
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblCategoria, "cell 0 6,alignx trailing");
		
		cbCategoria = new JComboBox();
		cbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbCategoria.setModel(new DefaultComboBoxModel(Constantes.OPCOES_TIPO_PESSOA_GERAL));
		cbCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				definirInstituicaoEnabled();
			}
		});
		this.add(cbCategoria, "cell 1 6 3 1,growx");
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblDataDeNascimento, "cell 5 6,alignx trailing");
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dpDataNascimento = new DatePicker(dateSettings);
		this.add(dpDataNascimento,"cell 6 6 2 1,growx");
		
		JLabel lblInstituicao = new JLabel("Institui\u00E7\u00E3o:");
		lblInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblInstituicao, "cell 0 8,alignx trailing");
		
		tfInstituicao = new JTextField();
		tfInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(tfInstituicao, "cell 1 8 3 1,growx");
		tfInstituicao.setColumns(10);
		definirInstituicaoEnabled();
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnExcluir, "cell 2 10,alignx center");
		
		btnExcluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				excluirPessoas();
				atualizarTabelaComUltimoSeletor();
			}
		});
		
		tableResultados = new JTable();
		tableResultados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		definirModeloPadraoTabela();
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnConsultar, "cell 6 10,alignx center");
		
		btnConsultar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				consultarPessoas();
			}
		});
		this.add(tableResultados, "cell 0 12 9 1,grow");
		
		btnPagAnterior = new JButton("< Anterior");
		btnPagAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnPagAnterior, "cell 2 13,alignx center");
		
		btnPagAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				atualizarTabelaComUltimoSeletor();
			}
		});
		
		lblPagAtual = new JLabel(paginaAtual+"");
		lblPagAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(lblPagAtual, "cell 4 13 2 1,alignx center");
		
		btnPagProxima = new JButton("Pr\u00F3ximo >");
		btnPagProxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnPagProxima, "cell 6 13,alignx center");
		
		btnPagProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				atualizarTabelaComUltimoSeletor();
			}
		});
		
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEditar.setEnabled(false);
		btnEditar.setVisible(false);
		btnEditar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				tentarEditar();
			}
		});
		this.add(btnEditar, "cell 6 8,alignx center");
		
		
		btnPegarRegistro = new JButton("<html>Pegar Registro<br />para Edi\u00E7\u00E3o</html>");
		btnPegarRegistro.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPegarRegistro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				coletarRegistroParaEdicao();
			}
		});
		this.add(btnPegarRegistro, "cell 4 14 2 1,alignx center");
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setEnabled(false);
		btnVoltar.setVisible(false);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abilitarTabelaBotoesConsultaExclusao();
				redefinirComboboxsParaGeral();
				desativarBotoesDeEdicao();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnVoltar, "cell 4 8,alignx center,aligny top");
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetarTodosOsCampos();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.add(btnLimpar, "cell 5 8,alignx center");
	}
	
	
	protected void definirInstituicaoEnabled() {
		TipoPessoa opcaoSelecionada = (TipoPessoa)cbCategoria.getSelectedItem();
		
		if(opcaoSelecionada == Constantes.TIPO_PESQUISADOR) {
			tfInstituicao.setEnabled(true);
		} else {
			tfInstituicao.setText("");
			tfInstituicao.setEnabled(false);
		}
	}
	
	protected void tentarEditar() {
		// Preencher o Objeto com os dados da tela
		Pessoa pessoaAlterada = new Pessoa();
		// pegar id
		int linhaSelecionada = tableResultados.getSelectedRow();
		pessoaAlterada.setId((Integer) tableResultados.getModel().getValueAt(linhaSelecionada, 0));
		pessoaAlterada.setTipo((TipoPessoa)cbCategoria.getSelectedItem());
		pessoaAlterada.setInstituicao(new Instituicao(-1,tfInstituicao.getText()));
		pessoaAlterada.setNome(tfNome.getText());
		pessoaAlterada.setSobrenome(tfSobrenome.getText());
		LocalDate novaDataNascimento = dpDataNascimento.getDate();
		pessoaAlterada.setDataNascimento(novaDataNascimento);
		pessoaAlterada.setSexo(((String)cbSexo.getSelectedItem()).charAt(0));
		pessoaAlterada.setCpf(Utils.desformatarCpf(ftfCpf.getText()));
		
		// Instanciar um controller adequado
		ControllerPessoa controller = new ControllerPessoa();
		
		// Chamar o método salvar no controller e pegar a mensagem retornada
		StatusMensagem statusMensagem = controller.atualizar(pessoaAlterada);
		
		// Mostrar a mensagem devolvida pelo controller
		JOptionPane.showMessageDialog(null, statusMensagem.getMensagem());
		
		//caso sucesso na edicao:
		if(statusMensagem.getStatus()) {
			abilitarTabelaBotoesConsultaExclusao();
			redefinirComboboxsParaGeral();
			desativarBotoesDeEdicao();
			atualizarTabelaComUltimoSeletor();
		}
	}
	
	protected void coletarRegistroParaEdicao() {
		String mensagem = "";
		if(tableResultados.getSelectedRowCount() == 0) {
			mensagem = "Por favor selecione uma linha para poder continuar para a tela de edição.";
		} else if(tableResultados.getSelectedRowCount() > 1){
			mensagem = "Por favor selecione uma  única linha para poder continuar para a tela de edição.";
		} else {
			if(tableResultados.getSelectedRow() == 0) {
				mensagem = "A linha com as descrições dos campos não pode ser editada. Escolha um registro válido.";
			} else {
				int linhaSelecionada = tableResultados.getSelectedRow();
				Integer idSelecionado = (Integer) tableResultados.getModel().getValueAt(linhaSelecionada, 0);
				if(idSelecionado == null) {
					mensagem = "A linha não pode estar vazia! Para inserir uma nova pessoa, vá para a tela de cadastro de pessoas.";
				} else {
					desabilitarTabelaBotoesConsultaExclusao();
					redefinirComboboxsParaEdicao();
					preencherInputsComALinhaDeEdicao(linhaSelecionada);
					ativarBotoesDeEdicao();
					mensagem = null;
				}
				
			}
		}
		if (mensagem != null) {
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
	
	private void preencherInputsComALinhaDeEdicao(int linhaSelecionada) {
		tfNome.setText((String) tableResultados.getModel().getValueAt(linhaSelecionada, 1));
		tfSobrenome.setText((String) tableResultados.getModel().getValueAt(linhaSelecionada, 2));
		if(((Character) tableResultados.getModel().getValueAt(linhaSelecionada, 3)) == Constantes.SEXO_MASCULINO.charAt(0)) {
			cbSexo.setSelectedIndex(0);
		} else {
			cbSexo.setSelectedIndex(1);
		}
		ftfCpf.setText(Utils.desformatarCpf((String) tableResultados.getModel().getValueAt(linhaSelecionada, 4)));
		dpDataNascimento.setDate( Utils.gerarLocalDateDeString((String) tableResultados.getModel().getValueAt(linhaSelecionada, 5)));
		cbCategoria.setSelectedIndex(getIndexFromTipo((TipoPessoa)tableResultados.getModel().getValueAt(linhaSelecionada, 6)));
		tfInstituicao.setText((String) tableResultados.getModel().getValueAt(linhaSelecionada, 7));
	}

	private int getIndexFromTipo(TipoPessoa tipoSelecionado) {
		int indice = 0 ;
		if(tipoSelecionado.equals(Constantes.OPCOES_TIPO_PESSOA_GERAL[1])) {
			indice = 0;
		} else if(tipoSelecionado.equals(Constantes.OPCOES_TIPO_PESSOA_GERAL[2])) {
			indice = 1;
		} else if(tipoSelecionado.equals(Constantes.OPCOES_TIPO_PESSOA_GERAL[3])) {
			indice = 2;
		}
		return indice;
	}

	private void redefinirComboboxsParaGeral() {
		cbSexo.setModel(new DefaultComboBoxModel(Constantes.OPCOES_SEXO_GERAL));
		cbCategoria.setModel(new DefaultComboBoxModel(Constantes.OPCOES_TIPO_PESSOA_GERAL));
	}
	
	private void redefinirComboboxsParaEdicao() {
		cbSexo.setModel(new DefaultComboBoxModel(Constantes.OPCOES_SEXO_EDICAO_CADASTRO));
		cbCategoria.setModel(new DefaultComboBoxModel(Constantes.OPCOES_TIPO_PESSOA_EDICAO_CADASTRO));
	}

	private void abilitarTabelaBotoesConsultaExclusao() {
		tableResultados.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnConsultar.setEnabled(true);
		tableResultados.setEnabled(true);
		btnPagAnterior.setEnabled(true);
		btnPegarRegistro.setEnabled(true);
		btnPagProxima.setEnabled(true);
	}
	
	private void desabilitarTabelaBotoesConsultaExclusao() {
		tableResultados.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnConsultar.setEnabled(false);
		tableResultados.setEnabled(false);
		btnPagAnterior.setEnabled(false);
		btnPegarRegistro.setEnabled(false);
		btnPagProxima.setEnabled(false);
	}

	protected void ativarBotoesDeEdicao() {
		btnEditar.setVisible(true);
		btnEditar.setEnabled(true);
		btnVoltar.setVisible(true);
		btnVoltar.setEnabled(true);
	}
	
	protected void desativarBotoesDeEdicao() {
		btnEditar.setVisible(false);
		btnEditar.setEnabled(false);
		btnVoltar.setVisible(false);
		btnVoltar.setEnabled(false);
	}

	protected void resetarTodosOsCampos() {
		tfNome.setText("");
		tfSobrenome.setText("");
		ftfCpf.setText("");
		cbSexo.setSelectedIndex(0);
		cbCategoria.setSelectedIndex(0);
		dpDataNascimento.clear();
		tfInstituicao.setText("");
		definirModeloPadraoTabela();
	}
	
	protected void atualizarTabelaComUltimoSeletor() {
		if(ultimoSeletorUsado != null) {
			lblPagAtual.setText(paginaAtual + "");
			ultimoSeletorUsado.setPagina(paginaAtual);
			ultimoSeletorUsado.setLimite(Constantes.TAMANHO_PAGINA);
			ControllerPessoa controlador = new ControllerPessoa();
			List<Pessoa> pessoas = controlador.listarPessoas(ultimoSeletorUsado);
			ultimasPessoasConsultadas = pessoas;
			atualizarTabelaPessoas(pessoas);
		}
	}
	
	protected void excluirPessoas() {
		String mensagem = "";
		if(tableResultados.getSelectedRowCount() == 0) {
			mensagem = "Por favor selecione uma ou mais linhas para excluir os registros.";
		} else {
			if(tableResultados.getSelectedRow() == 0) {
				mensagem = "A linha com as descrições dos campos não pode ser excluída. Nenhuma linha será excluída.";
			} else {
				ControllerPessoa controllerPessoa = new ControllerPessoa();
				List<Integer> idsASeremExcluidos = new ArrayList<Integer>();
				for(int row : tableResultados.getSelectedRows()) {
					Integer idASerExcluido = (Integer) (tableResultados.getValueAt(row, 0));
					if(idASerExcluido != null) {
						idsASeremExcluidos.add(idASerExcluido);
					}
				}
				mensagem = controllerPessoa.excluir(idsASeremExcluidos);
			}
		}
		JOptionPane.showMessageDialog(null, mensagem);
	}

	protected void consultarPessoas() {
		paginaAtual = 1;
		lblPagAtual.setText(paginaAtual + "");

		ControllerPessoa controlador = new ControllerPessoa();
		PessoaSeletor seletor = new PessoaSeletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(Constantes.TAMANHO_PAGINA);
		
		// Preenche os campos de filtro da tela no seletor
		seletor.setNome(tfNome.getText());
		seletor.setSobrenome(tfSobrenome.getText());
		seletor.setSexo(cbSexo.getSelectedItem().toString().charAt(0)); 
		seletor.setCpf(Utils.desformatarCpf(ftfCpf.getText()));
		seletor.setNomeInstituicao(tfInstituicao.getText()); 
		
		seletor.setTipo((TipoPessoa)cbCategoria.getSelectedItem()); 
				
		seletor.setDataNascimento(dpDataNascimento.getDate());
		
		// aqui é feita a consulta das pessoas e atualizada a tabela
		List<Pessoa> pessoas = controlador.listarPessoas(seletor);
		ultimoSeletorUsado = seletor;
		ultimasPessoasConsultadas = pessoas;

		atualizarTabelaPessoas(pessoas);

	}

	protected void atualizarTabelaPessoas(List<Pessoa> pessoas) {
		this.definirModeloPadraoTabela();
		
		DefaultTableModel modelo = (DefaultTableModel) tableResultados.getModel();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		for (int i = 0; i < pessoas.size(); i++) {
			Pessoa pessoa = pessoas.get(i);
			String dataFormatada = pessoa.getDataNascimento().format(formatter);
			String nomeInstituicao = pessoa.getInstituicao() == null ? "" : pessoa.getInstituicao().toString();

			modelo.setValueAt(pessoa.getId(), i+1, 0);
			modelo.setValueAt(pessoa.getNome(), i+1, 1);
			modelo.setValueAt(pessoa.getSobrenome(), i+1, 2);
			modelo.setValueAt(pessoa.getSexo(), i+1, 3);
			modelo.setValueAt(Utils.formatarCpf(pessoa.getCpf()), i+1, 4);
			modelo.setValueAt(dataFormatada, i+1, 5);
			modelo.setValueAt(pessoa.getTipo(), i+1, 6);
			modelo.setValueAt(nomeInstituicao, i+1, 7);
		}
		
	}

	private void definirModeloPadraoTabela() {
		tableResultados.setModel(new DefaultTableModel(
				new Object[][] {
					getNomesColunas(),
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
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
	public String[] getNomesColunas() {
		return new String[] {
				"#", "Nome", "Sobrenome", "Sexo", "CPF ", "Dt.  Nascimento", "Categoria", "Institui\u00E7\u00E3o"
			};
	}

	@Override
	public List<String[]> getDadosVisiveis() {
		//this.ultimasPessoasConsultadas;
		List<String[]> dadosVisiveis = new ArrayList<String[]>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		for (int i = 0; i < ultimasPessoasConsultadas.size(); i++) {
			String[] dadoAtual = new String[8];
			Pessoa pessoa = ultimasPessoasConsultadas.get(i);
			String dataFormatada = pessoa.getDataNascimento().format(formatter);
			String nomeInstituicao = pessoa.getInstituicao() == null ? "" : pessoa.getInstituicao().toString();

			dadoAtual[0] = pessoa.getId() + "";
			dadoAtual[1] = pessoa.getNome();
			dadoAtual[2] = pessoa.getSobrenome();
			dadoAtual[3] = pessoa.getSexo() + "";
			dadoAtual[4] = Utils.formatarCpf(pessoa.getCpf());
			dadoAtual[5] = dataFormatada;
			dadoAtual[6] = pessoa.getTipo().toString();
			dadoAtual[7] = nomeInstituicao;
			
			dadosVisiveis.add(dadoAtual);
		}
		return dadosVisiveis;
	}

	@Override
	public List<String[]> getDadosCompletos() {
		// TODO Auto-generated method stub
		return null;
	}

}
