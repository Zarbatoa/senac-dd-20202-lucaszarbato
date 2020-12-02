package br.sc.senac.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.sc.senac.view.cadastro.TelaCadastroPessoa;

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
		setBounds(100, 100, 609, 448);
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
				
				TelaCadastroPessoa painelSobre;
				try {
					painelSobre = new TelaCadastroPessoa();
					setContentPane(painelSobre);
					revalidate();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnPessoa.add(mntmNovoCadastroPessoa);
		
		JMenuItem mntmGerenciarPessoa = new JMenuItem("Gerenciar");
		mnPessoa.add(mntmGerenciarPessoa);
		
		JMenu mnVacina = new JMenu("Vacina");
		menuBar.add(mnVacina);
		
		JMenuItem mntmNovoCadastroVacina = new JMenuItem("Novo (Cadastro)");
		mnVacina.add(mntmNovoCadastroVacina);
		
		JMenuItem mntmGerenciarVacina = new JMenuItem("Gerenciar");
		mnVacina.add(mntmGerenciarVacina);
		
		JMenu mnAvaliaoVacina = new JMenu("Avalia\u00E7\u00E3o Vacina");
		menuBar.add(mnAvaliaoVacina);
		
		JMenuItem mntmNovaAvaliacaoVacina = new JMenuItem("Novo (Cadastro)");
		mnAvaliaoVacina.add(mntmNovaAvaliacaoVacina);
		
		JMenuItem mntmGerenciarAvaliacaoVacina = new JMenuItem("Gerenciar");
		mnAvaliaoVacina.add(mntmGerenciarAvaliacaoVacina);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmRelatorio = new JMenuItem("Consultar Relat\u00F3rios");
		mnRelatrio.add(mntmRelatorio);
	}

}
