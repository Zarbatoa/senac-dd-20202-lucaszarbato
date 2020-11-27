package br.sc.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TelaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setTitle("VacinApp");
		frame.setBounds(100, 100, 609, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnPessoa = new JMenu("Pessoa");
		menuBar.add(mnPessoa);
		
		JMenuItem mntmNovocadastro = new JMenuItem("Novo (Cadastro)");
		mnPessoa.add(mntmNovocadastro);
		
		JMenuItem mntmGerenciar = new JMenuItem("Gerenciar");
		mnPessoa.add(mntmGerenciar);
		
		JMenu mnVacina = new JMenu("Vacina");
		menuBar.add(mnVacina);
		
		JMenuItem mntmNovocadastro_1 = new JMenuItem("Novo (Cadastro)");
		mnVacina.add(mntmNovocadastro_1);
		
		JMenuItem mntmGerenciar_1 = new JMenuItem("Gerenciar");
		mnVacina.add(mntmGerenciar_1);
		
		JMenu mnAvaliaoVacina = new JMenu("Avalia\u00E7\u00E3o Vacina");
		menuBar.add(mnAvaliaoVacina);
		
		JMenuItem mntmNovocadastro_2 = new JMenuItem("Novo (Cadastro)");
		mnAvaliaoVacina.add(mntmNovocadastro_2);
		
		JMenuItem mntmGerenciar_2 = new JMenuItem("Gerenciar");
		mnAvaliaoVacina.add(mntmGerenciar_2);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmGerarRelatrio = new JMenuItem("Gerar Relat\u00F3rio");
		mnRelatrio.add(mntmGerarRelatrio);
	}

}
