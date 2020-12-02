package br.sc.senac.view.tempTelasMenu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaSobreComMigLayout extends JPanel {
	
	//variável relacionada a paginação
	private int paginaAtual = 1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobreComMigLayout frame = new TelaSobreComMigLayout();
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
	public TelaSobreComMigLayout() {
		setBounds(100, 100, 541, 300);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new MigLayout("", "[grow][113.00,grow][35.00][134.00,grow][142.00,grow][][42.00]", "[][][][][grow][][][][][][][][]"));
		
		JLabel lblFiguraPrograma = new JLabel("");
		lblFiguraPrograma.setIcon(new ImageIcon("icones/seringa_2.png"));
		this.add(lblFiguraPrograma, "cell 1 4");
		
		JTextPane txtpnTextoPrograma = new JTextPane();
		txtpnTextoPrograma.setEditable(false);
		txtpnTextoPrograma.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnTextoPrograma.setBackground(SystemColor.menu);
		txtpnTextoPrograma.setText("O programa VacinApp serve para o cadastro \r\nde pesquisas relacionadas as vacinas que est\u00E3o \r\nsendo desenvolvidas para o combate a diversas \r\ndoen\u00E7as, como o Corona v\u00EDrus.");
		this.add(txtpnTextoPrograma, "cell 3 4 2 1,alignx left,growy");
		
		JLabel lblAutores = new JLabel("Autores:");
		lblAutores.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblAutores, "cell 3 5");
		
		JLabel lblAline = new JLabel("Aline Herculano");
		lblAline.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblAline, "cell 3 6,alignx right");
		
		JLabel lblLucas = new JLabel("Lucas Zarbato");
		lblLucas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblLucas, "cell 3 7,alignx right");
		
		JLabel lblRosana = new JLabel("Rosana Debiasi");
		lblRosana.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblRosana, "cell 3 8,alignx right");
			

	}



}
