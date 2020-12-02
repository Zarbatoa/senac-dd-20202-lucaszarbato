package br.sc.senac.view.tempTelasMenu;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TelaPrincipal {

	private JFrame frame;
	private JDesktopPane desktopPane; //do JInternalPane mas não deu certo --> se não usar o JInternalPane, retirar essa variável
	TelaSobreComMigLayoutEJInternalJFrame telaInternalJFrame = null; //do JInternalPane mas não deu certo --> se não usar o JInternalPane, retirar essa variável
	//private JPanel contentPane; // variável acrescentada para passar o JPanel.
	//TelaSobreComMigLayoutEJPanel telaPanel = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					// Inicializa a tela principal MAXIMIZADA
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //--> só que se for assim o frame vira static
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
		
		JMenu mnHome = new JMenu("Home");
		menuBar.add(mnHome);

		//aqui que coloca o evento para trocar de tela -> como JFrame
		//No visual: apertar com botão direito do mouse -> add event handler -> action -> action performed
		//No código dá um erro e sugere a correção por try/catch. Fiz isso e funcionou.
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSobreComMigLayout tela = null;
				try {
					tela = new TelaSobreComMigLayout();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tela.setVisible(true);
			}
		});
		mnHome.add(mntmSobre);
		
		//aqui que coloca o evento para trocar de tela -> como JInternalFrame --> não está rodando, está com erro.
		JMenuItem mntmSobrejinternalframe = new JMenuItem("Sobre(JInternalFrame)");
		mntmSobrejinternalframe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					telaInternalJFrame = new TelaSobreComMigLayoutEJInternalJFrame();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				desktopPane.add(telaInternalJFrame);
				telaInternalJFrame.show();
			}

		});
		mnHome.add(mntmSobrejinternalframe);

		//teste Jpanel -> fiz igual ao professor. Não entendi porque não funciona o setContentPane. 
		// no código do professor funcionava, talvez seja a versão da JVM diferente :/
		JMenuItem mntmSobrejpanel = new JMenuItem("Sobre(JPanel)");
		mntmSobrejpanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//TODO já vejo isso
					TelaSobreComMigLayoutEJPanel painelSobre = new TelaSobreComMigLayoutEJPanel();
					frame.setContentPane(painelSobre);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//setContentPane(painelSobre); // no do professor roda, aqui não :/
			}
		});
		mnHome.add(mntmSobrejpanel);
		
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
		
		JMenuItem mntmRelatorioPessoas = new JMenuItem("Pessoas");
		mnRelatrio.add(mntmRelatorioPessoas);
		
		JMenuItem mntmRelatorioVacinas = new JMenuItem("Vacinas");
		mnRelatrio.add(mntmRelatorioVacinas);
		
		JMenuItem mntmRelatorioAvaliacaoDeVacinas = new JMenuItem("Avalia\u00E7\u00E3o de Vacinas");
		mnRelatrio.add(mntmRelatorioAvaliacaoDeVacinas);
	}

}
