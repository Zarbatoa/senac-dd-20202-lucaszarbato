package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.sc.senac.view.cadastro.TelaCadastroAvaliacaoVacina;
import br.sc.senac.view.cadastro.TelaCadastroPessoa;
import br.sc.senac.view.cadastro.TelaCadastroVacina;
import br.sc.senac.view.gerencia.TelaGerenciamentoDeAvaliacaoVacinas;
import br.sc.senac.view.gerencia.TelaGerenciamentoDePessoas;
import br.sc.senac.view.gerencia.TelaGerenciamentoDeVacinas;

@SuppressWarnings({"serial"})
public class TelaPrincipal extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("VacinApp");
		setBounds(100, 100, 700, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnHome = new JMenu("Home");
		menuBar.add(mnHome);

		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSobre painelSobre = new TelaSobre();
				setContentPane(painelSobre);
				revalidate();
			}
		});
		mnHome.add(mntmSobre);
		
		JMenu mnPessoa = new JMenu("Pessoa");
		menuBar.add(mnPessoa);
		
		JMenuItem mntmNovoCadastroPessoa = new JMenuItem("Novo (Cadastro)");
		mntmNovoCadastroPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastroPessoa painelCadastroPessoa;
				try {
					painelCadastroPessoa = new TelaCadastroPessoa();
					setContentPane(painelCadastroPessoa);
					revalidate();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnPessoa.add(mntmNovoCadastroPessoa);
		
		JMenuItem mntmGerenciarPessoa = new JMenuItem("Gerenciar");
		mntmGerenciarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciamentoDePessoas paintelGerenciamentoPessoas;
				try {
					paintelGerenciamentoPessoas = new TelaGerenciamentoDePessoas();
					setContentPane(paintelGerenciamentoPessoas);
					revalidate();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnPessoa.add(mntmGerenciarPessoa);
		
		JMenu mnVacina = new JMenu("Vacina");
		menuBar.add(mnVacina);
		
		JMenuItem mntmNovoCadastroVacina = new JMenuItem("Novo (Cadastro)");
		mntmNovoCadastroVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastroVacina painelCadastroVacina;
				try {
					painelCadastroVacina = new TelaCadastroVacina();
					setContentPane(painelCadastroVacina);
					revalidate();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnVacina.add(mntmNovoCadastroVacina);
		
		JMenuItem mntmGerenciarVacina = new JMenuItem("Gerenciar");
		mntmGerenciarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciamentoDeVacinas paintelGerenciamentoVacinas;
				try {
					paintelGerenciamentoVacinas = new TelaGerenciamentoDeVacinas();
					setContentPane(paintelGerenciamentoVacinas);
					revalidate();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnVacina.add(mntmGerenciarVacina);
		
		JMenu mnAvaliaoVacina = new JMenu("Avalia\u00E7\u00E3o Vacina");
		menuBar.add(mnAvaliaoVacina);
		
		JMenuItem mntmNovaAvaliacaoVacina = new JMenuItem("Novo (Cadastro)");
		mntmNovaAvaliacaoVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastroAvaliacaoVacina painelCadastroAvaliacaoVacina;
				try {
					painelCadastroAvaliacaoVacina = new TelaCadastroAvaliacaoVacina();
					setContentPane(painelCadastroAvaliacaoVacina);
					revalidate();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnAvaliaoVacina.add(mntmNovaAvaliacaoVacina);
		
		JMenuItem mntmGerenciarAvaliacaoVacina = new JMenuItem("Gerenciar");
		mntmGerenciarAvaliacaoVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciamentoDeAvaliacaoVacinas paintelGerenciamentoDeAvaliacaoVacinas;
				try {
					paintelGerenciamentoDeAvaliacaoVacinas = new TelaGerenciamentoDeAvaliacaoVacinas();
					setContentPane(paintelGerenciamentoDeAvaliacaoVacinas);
					revalidate();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnAvaliaoVacina.add(mntmGerenciarAvaliacaoVacina);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmRelatorio = new JMenuItem("Consultar Relat\u00F3rios");
		mntmRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaRelatorios paintelRelatorios;
				try {
					paintelRelatorios = new TelaRelatorios();
					setContentPane(paintelRelatorios);
					revalidate();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnRelatrio.add(mntmRelatorio);
	}

}
