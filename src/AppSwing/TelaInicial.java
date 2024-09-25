package AppSwing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TelaInicial {

	private JFrame telaInicial;
	private JTextField inputUsuario;
	private JPasswordField inputSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		CarregarFonte.carregarFonte("/Arquivos/CoreSansDS35Regular.ttf");
		CarregarFonte.carregarFonte("/Arquivos/CoreSansDS55Bold.ttf");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		telaInicial = new JFrame();
		telaInicial.getContentPane().setForeground(new Color(225, 225, 225));
		telaInicial.getContentPane().setBackground(SystemColor.text);
		telaInicial.getContentPane().setLayout(null);
		telaInicial.setVisible(true);
		
		JLabel logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		
		logo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/Arquivos/apartamento-1.png"))));
		
		logo.setBounds(184, 25, 50, 50);
		telaInicial.getContentPane().add(logo);
		
		
		JPanel fundoTitulo = new JPanel();
		fundoTitulo.setBounds(75, 71, 267, 52);
		telaInicial.getContentPane().add(fundoTitulo);
		fundoTitulo.setLayout(null);
		
		JLabel titulo = new JLabel("Imobiliária Silva");
		titulo.setBounds(27, 7, 213, 38);
		fundoTitulo.add(titulo);
		titulo.setForeground(new Color(0, 102, 153));
		titulo.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 30));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		inputUsuario = new JTextField();
		inputUsuario.setToolTipText("Digite o seu usuário.");
		inputUsuario.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputUsuario.setBounds(75, 187, 267, 38);
		telaInicial.getContentPane().add(inputUsuario);
		inputUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 17));
		lblUsuario.setBounds(75, 166, 62, 22);
		telaInicial.getContentPane().add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 17));
		lblSenha.setBounds(75, 254, 62, 22);
		telaInicial.getContentPane().add(lblSenha);
		
		JButton botaoEntrar = new JButton("ENTRAR");
		botaoEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal telaPrincipal = new TelaPrincipal();
				telaInicial.dispose();
			}
		});
		botaoEntrar.setBackground(new Color(0, 102, 51));
		botaoEntrar.setForeground(new Color(255, 255, 255));
		botaoEntrar.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoEntrar.setBounds(158, 359, 101, 35);
		telaInicial.getContentPane().add(botaoEntrar);
		
		inputSenha = new JPasswordField();
		inputSenha.setFont(new Font("Arial", Font.PLAIN, 15));
		inputSenha.setBounds(75, 275, 267, 38);
		telaInicial.getContentPane().add(inputSenha);
		telaInicial.setTitle("Imobiliária Silva - Login");
		telaInicial.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/Arquivos/apartamento.png")));
		telaInicial.setBounds(100, 100, 434, 483);
		telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaInicial.setLocationRelativeTo(null);
		telaInicial.setResizable(false);
	}
}
