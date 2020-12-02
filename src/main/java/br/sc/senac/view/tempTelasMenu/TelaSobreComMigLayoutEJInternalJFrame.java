package br.sc.senac.view.tempTelasMenu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JInternalFrame;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import br.sc.senac.controller.ControllerVacina;
import br.sc.senac.model.utilidades.Constantes;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class TelaSobreComMigLayoutEJInternalJFrame extends JInternalFrame {

	private JPanel contentPane;

	
	//variável relacionada a paginação
	private int paginaAtual = 1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobreComMigLayoutEJInternalJFrame frame = new TelaSobreComMigLayoutEJInternalJFrame();
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
	public TelaSobreComMigLayoutEJInternalJFrame() throws ParseException {
		
		setTitle("Sobre o Sistema");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // aqui antes estava como Exit on close. Precisa mudar para o que está ali. 
		setBounds(100, 100, 541, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][113.00,grow][35.00][134.00,grow][142.00,grow][][42.00]", "[][][][][][grow][][][][][][][][]"));
		
		JLabel lblSobre = new JLabel("Sobre o Sistema");
		lblSobre.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblSobre, "cell 3 1,alignx center,aligny bottom");
		
		JLabel lblFiguraPrograma = new JLabel("");
		lblFiguraPrograma.setIcon(new ImageIcon("C:\\Users\\rosan\\git\\senac-dd-20202-lucaszarbato2\\icones\\seringa_2.png"));
		contentPane.add(lblFiguraPrograma, "cell 1 5");
		
		JTextPane txtpnTextoPrograma = new JTextPane();
		txtpnTextoPrograma.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnTextoPrograma.setBackground(SystemColor.menu);
		txtpnTextoPrograma.setText("O programa VacinApp serve para o cadastro \r\nde pesquisas relacionadas as vacinas que est\u00E3o \r\nsendo desenvolvidas para o combate a diversas \r\ndoen\u00E7as, como o Corona v\u00EDrus.");
		contentPane.add(txtpnTextoPrograma, "cell 3 5 2 1,alignx left,growy");
		
		JLabel lblAutores = new JLabel("Autores:");
		lblAutores.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblAutores, "cell 3 6");
		
		JLabel lblAline = new JLabel("Aline Herculano");
		lblAline.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblAline, "cell 3 7,alignx right");
		
		JLabel lblLucas = new JLabel("Lucas Zarbato");
		lblLucas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblLucas, "cell 3 8,alignx right");
		
		JLabel lblRosana = new JLabel("Rosana Debiasi");
		lblRosana.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblRosana, "cell 3 9,alignx right");
			

	}



}
