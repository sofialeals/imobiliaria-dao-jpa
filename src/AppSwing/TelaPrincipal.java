package AppSwing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Classes.Morador;
import RegrasNegocio.Fachada;

public class TelaPrincipal {

	private JFrame menuPrincipal;
	private JTextField inputNome;
	private JTextField inputCpf;
	private JTextField inputCpfAcordo;
	private JTextField inputNomeCond;
	private JTextField inputEndCond;
	private JTextField inputIdCond;
	private JTextField inputIdCC;
	private JTextField inputCpfCC;
	private JTextField inputValorAluguel;
	private JTextField inputIdEC;
	private JTextField inputCpfEC;
	private JTextField inputNMoradores;
	private JTextField inputIdCondInads;
	private JTextField inputAnoRef;
	private JTextField idPagarBoleto;
	private JTextField inputNumDoAp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		CarregarFonte.carregarFonte("/Arquivos/CoreSansDS35Regular.ttf");
		CarregarFonte.carregarFonte("/Arquivos/CoreSansDS55Bold.ttf");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
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
		String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
		
		menuPrincipal = new JFrame();
		JTextArea listagemMoradores = new JTextArea();
		JTextArea exibirTodosConds = new JTextArea();
		JLabel lblMesRef = new JLabel("");
		
		menuPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				int mesAtual = LocalDate.now().getMonthValue();
				lblMesRef.setText(meses[mesAtual-1]);
				
				listagemMoradores.setText(Fachada.exibirMoradores());
				exibirTodosConds.setText(Fachada.exibirCondominios());
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});
		
		menuPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/Arquivos/apartamento.png")));
		menuPrincipal.setTitle("Imobiliária Silva");
		menuPrincipal.setBounds(100, 100, 750, 550);
		menuPrincipal.setResizable(false);
		menuPrincipal.setLocationRelativeTo(null);
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPrincipal.getContentPane().setLayout(null);
		menuPrincipal.setVisible(true);
		
		JPanel moradores = new JPanel();
		moradores.setBackground(new Color(225, 225, 225));
		moradores.setBounds(0, 0, 734, 480);
		menuPrincipal.getContentPane().add(moradores);
		moradores.setLayout(null);
		moradores.setVisible(true);
		
		JLabel lblMoradores = new JLabel("MORADORES");
		lblMoradores.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 25));
		lblMoradores.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoradores.setBounds(23, 11, 688, 23);
		moradores.add(lblMoradores);
		
		JLabel lblCadastrar = new JLabel("CADASTRAR MORADOR");
		lblCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
		lblCadastrar.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
		lblCadastrar.setBounds(71, 52, 591, 14);
		moradores.add(lblCadastrar);
		
		inputNome = new JTextField();
		inputNome.setToolTipText("Nome do morador.");
		inputNome.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputNome.setBounds(71, 94, 197, 34);
		moradores.add(inputNome);
		inputNome.setColumns(10);
		
		JLabel lblNome = new JLabel("NOME:");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblNome.setBounds(71, 77, 159, 14);
		moradores.add(lblNome);
		
		inputCpf = new JTextField();
		inputCpf.setToolTipText("CPF do morador.");
		inputCpf.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputCpf.setColumns(10);
		inputCpf.setBounds(287, 94, 197, 34);
		moradores.add(inputCpf);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpf.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblCpf.setBounds(287, 78, 159, 14);
		moradores.add(lblCpf);
		
		JButton botaoCadastrar = new JButton("CADASTRAR");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeCadastro = inputNome.getText();
				String cpfCadastro = inputCpf.getText();
				try {
					Fachada.cadastrarMorador(nomeCadastro, cpfCadastro);
					JOptionPane.showMessageDialog(menuPrincipal, "Cadastro realizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					inputNome.setText("");
					inputCpf.setText("");
					inputNome.requestFocus();
					listagemMoradores.setText(Fachada.exibirMoradores());
				} catch (Exception erroCadastro) {
					JOptionPane.showMessageDialog(menuPrincipal, erroCadastro.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
					inputNome.setText("");
					inputCpf.setText("");
					inputNome.requestFocus();
				}
			}
		});
		botaoCadastrar.setBackground(new Color(0, 102, 51));
		botaoCadastrar.setForeground(new Color(255, 255, 255));
		botaoCadastrar.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoCadastrar.setBounds(504, 94, 158, 34);
		moradores.add(botaoCadastrar);
		
		JLabel lblListar = new JLabel("LISTAR MORADORES");
		lblListar.setHorizontalAlignment(SwingConstants.CENTER);
		lblListar.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
		lblListar.setBounds(48, 163, 272, 14);
		moradores.add(lblListar);
		
		JSeparator separadorV1 = new JSeparator();
		separadorV1.setOrientation(SwingConstants.VERTICAL);
		separadorV1.setBounds(366, 164, 13, 287);
		moradores.add(separadorV1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 188, 272, 218);
		moradores.add(scrollPane);
		
		
		listagemMoradores.setEditable(false);
		listagemMoradores.setForeground(new Color(0, 0, 0));
		listagemMoradores.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 17));
		scrollPane.setViewportView(listagemMoradores);
		
		JButton botaoListar = new JButton("LISTAR");
		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagemMoradores.setText(Fachada.exibirMoradores());
			}
		});
		botaoListar.setForeground(Color.WHITE);
		botaoListar.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoListar.setBackground(new Color(0, 102, 51));
		botaoListar.setBounds(48, 417, 272, 34);
		moradores.add(botaoListar);
		
		JLabel lblFazerAcordo = new JLabel("FAZER ACORDO");
		lblFazerAcordo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFazerAcordo.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
		lblFazerAcordo.setBounds(440, 164, 163, 14);
		moradores.add(lblFazerAcordo);
		
		JLabel lblCpfAcordo = new JLabel("CPF:");
		lblCpfAcordo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpfAcordo.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblCpfAcordo.setBounds(415, 189, 159, 14);
		moradores.add(lblCpfAcordo);
		
		inputCpfAcordo = new JTextField();
		inputCpfAcordo.setToolTipText("CPF do morador.");
		inputCpfAcordo.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputCpfAcordo.setColumns(10);
		inputCpfAcordo.setBounds(415, 205, 159, 34);
		moradores.add(inputCpfAcordo);
		
		JTextArea exibirNome = new JTextArea();
		exibirNome.setEditable(false);
		JTextArea exibirValor = new JTextArea();
		exibirValor.setEditable(false);
		
		JButton botaoBuscar = new JButton("BUSCAR");
		botaoBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpfMorador = inputCpfAcordo.getText();
				Morador morador;
				
				if(cpfMorador.equals("")){
					JOptionPane.showMessageDialog(menuPrincipal, "O campo CPF está vazio.", "Erro", JOptionPane.WARNING_MESSAGE);
				} else {
					morador = Fachada.buscarMorador(cpfMorador);
					if(morador == null) {
						JOptionPane.showMessageDialog(menuPrincipal, "O morador de CPF "+cpfMorador+" não está cadastrado.", "Erro", JOptionPane.WARNING_MESSAGE);
						inputCpfAcordo.setText("");
						exibirNome.setText("");
						exibirValor.setText("");
						inputCpfAcordo.requestFocus();
					} else {
						try {
							morador = Fachada.buscarMorador(cpfMorador);
							exibirNome.setText(morador.getNome());
							exibirValor.setText(Double.toString(Fachada.valorAtrasados(cpfMorador)));
						} catch (Exception erroMorador) {
							JOptionPane.showMessageDialog(menuPrincipal, erroMorador.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
							inputCpfAcordo.setText("");
							exibirNome.setText("");
							exibirValor.setText("");
							inputCpfAcordo.requestFocus();
						}
					}
				}
			}
		});
		botaoBuscar.setForeground(Color.WHITE);
		botaoBuscar.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoBuscar.setBackground(new Color(0, 102, 51));
		botaoBuscar.setBounds(584, 205, 104, 34);
		moradores.add(botaoBuscar);
		
		JLabel lblNomeAcordo = new JLabel("MORADOR:");
		lblNomeAcordo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeAcordo.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblNomeAcordo.setBounds(415, 268, 273, 14);
		moradores.add(lblNomeAcordo);
		
		JButton botaoConfirmar = new JButton("CONFIRMAR");
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpfMorador = inputCpfAcordo.getText();
				try {
					boolean acordo = Fachada.fazerAcordo(cpfMorador);
					if(acordo) {
						JOptionPane.showMessageDialog(menuPrincipal, "Acordo realizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						inputCpfAcordo.setText("");
						exibirNome.setText("");
						exibirValor.setText("");
						inputCpfAcordo.requestFocus();
					}
				} catch (Exception erroAcordo) {
					JOptionPane.showMessageDialog(menuPrincipal, erroAcordo.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
					inputCpfAcordo.setText("");
					exibirNome.setText("");
					exibirValor.setText("");
					inputCpfAcordo.requestFocus();
				}
			}
		});
		botaoConfirmar.setForeground(Color.WHITE);
		botaoConfirmar.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoConfirmar.setBackground(new Color(0, 102, 51));
		botaoConfirmar.setBounds(415, 417, 273, 34);
		moradores.add(botaoConfirmar);
		
		JLabel lblConfimar = new JLabel("Confirmar pagamento do acordo?");
		lblConfimar.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfimar.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblConfimar.setBounds(415, 388, 273, 23);
		moradores.add(lblConfimar);
		exibirNome.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		exibirNome.setBounds(415, 286, 273, 23);
		moradores.add(exibirNome);
		
		JLabel lblValorTotal = new JLabel("VALOR TOTAL DA DÍVIDA:");
		lblValorTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorTotal.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblValorTotal.setBounds(415, 320, 273, 14);
		moradores.add(lblValorTotal);
		exibirValor.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		exibirValor.setBounds(415, 338, 273, 23);
		moradores.add(exibirValor);
		
		JSeparator separadorV1_1 = new JSeparator();
		separadorV1_1.setBounds(48, 146, 640, 23);
		moradores.add(separadorV1_1);
		
		JPanel condominios = new JPanel();
		condominios.setLayout(null);
		condominios.setBackground(new Color(225, 225, 225));
		condominios.setBounds(0, 0, 734, 480);
		menuPrincipal.getContentPane().add(condominios);
		condominios.setVisible(false);
		
		JLabel lblCondominio = new JLabel("CONDOMÍNIOS");
		lblCondominio.setVerticalAlignment(SwingConstants.TOP);
		lblCondominio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCondominio.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 25));
		lblCondominio.setBounds(23, 7, 688, 25);
		condominios.add(lblCondominio);
		
		JLabel lblCadCond = new JLabel("CADASTRAR CONDOMÍNIO");
		lblCadCond.setHorizontalAlignment(SwingConstants.LEFT);
		lblCadCond.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
		lblCadCond.setBounds(33, 43, 309, 30);
		condominios.add(lblCadCond);
		
		inputNomeCond = new JTextField();
		inputNomeCond.setToolTipText("Nome do morador.");
		inputNomeCond.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputNomeCond.setColumns(10);
		inputNomeCond.setBounds(33, 96, 150, 25);
		condominios.add(inputNomeCond);
		
		JLabel lblNomeCond = new JLabel("NOME:");
		lblNomeCond.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeCond.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblNomeCond.setBounds(33, 79, 130, 14);
		condominios.add(lblNomeCond);
		
		inputEndCond = new JTextField();
		inputEndCond.setToolTipText("CPF do morador.");
		inputEndCond.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputEndCond.setColumns(10);
		inputEndCond.setBounds(192, 96, 150, 25);
		condominios.add(inputEndCond);
		
		JLabel lblEndCond = new JLabel("ENDEREÇO:");
		lblEndCond.setHorizontalAlignment(SwingConstants.LEFT);
		lblEndCond.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblEndCond.setBounds(192, 77, 130, 17);
		condominios.add(lblEndCond);
		
		JButton botaoCadCond = new JButton("CADASTRAR");
		botaoCadCond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeCond = inputNomeCond.getText();
				String endCond = inputEndCond.getText();
				
				try {
					Fachada.cadastrarCondominio(nomeCond, endCond);
					JOptionPane.showMessageDialog(menuPrincipal, "Condomínio cadastrado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					inputNomeCond.setText("");
					inputEndCond.setText("");
					inputNomeCond.requestFocus();
					exibirTodosConds.setText(Fachada.exibirCondominios());
				} catch (Exception erroCond) {
					JOptionPane.showMessageDialog(menuPrincipal, erroCond.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
					inputNomeCond.setText("");
					inputEndCond.setText("");
					inputNomeCond.requestFocus();
				}
				
			}
		});
		botaoCadCond.setForeground(Color.WHITE);
		botaoCadCond.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoCadCond.setBackground(new Color(0, 102, 51));
		botaoCadCond.setBounds(112, 132, 150, 25);
		condominios.add(botaoCadCond);
		
		JLabel lblListarConds = new JLabel("TODOS OS CONDOMÍNIOS");
		lblListarConds.setHorizontalAlignment(SwingConstants.CENTER);
		lblListarConds.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
		lblListarConds.setBounds(23, 191, 219, 23);
		condominios.add(lblListarConds);
		
		JLabel lblExcCond = new JLabel("EXCLUIR CONDOMÍNIO");
		lblExcCond.setVerticalAlignment(SwingConstants.TOP);
		lblExcCond.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcCond.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
		lblExcCond.setBounds(391, 46, 320, 19);
		condominios.add(lblExcCond);
		
		JLabel lblIdCond = new JLabel("ID DO CONDOMÍNIO:");
		lblIdCond.setVerticalAlignment(SwingConstants.TOP);
		lblIdCond.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdCond.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblIdCond.setBounds(486, 75, 130, 18);
		condominios.add(lblIdCond);
		
		inputIdCond = new JTextField();
		inputIdCond.setToolTipText("Nome do morador.");
		inputIdCond.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputIdCond.setColumns(10);
		inputIdCond.setBounds(476, 96, 150, 25);
		condominios.add(inputIdCond);
		
		JButton botaoExcCond = new JButton("EXCLUIR");
		botaoExcCond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(inputIdCond.getText().equals("")) {
						JOptionPane.showMessageDialog(menuPrincipal, "O campo ID está vazio. Preencha-o e tente novamente.", "Erro", JOptionPane.WARNING_MESSAGE);
						inputIdCond.requestFocus();
					} else {
						int idCond = Integer.valueOf(inputIdCond.getText());
						Fachada.excluirCondominio(idCond);
						JOptionPane.showMessageDialog(menuPrincipal, "O condomínio de ID "+idCond+" foi excluído.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						inputIdCond.setText("");
						inputIdCond.requestFocus();
						exibirTodosConds.setText(Fachada.exibirCondominios());	
					}
				} catch (Exception erroExcCond) {
					JOptionPane.showMessageDialog(menuPrincipal, erroExcCond.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
					inputIdCond.setText("");
					inputIdCond.requestFocus();
				}
			}
		});
		botaoExcCond.setForeground(Color.WHITE);
		botaoExcCond.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoExcCond.setBackground(new Color(0, 102, 51));
		botaoExcCond.setBounds(476, 132, 150, 25);
		condominios.add(botaoExcCond);
		
		JLabel lblOcupConds = new JLabel("OCUPAÇÃO DOS CONDOMÍNIOS");
		lblOcupConds.setHorizontalAlignment(SwingConstants.CENTER);
		lblOcupConds.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 16));
		lblOcupConds.setBounds(259, 191, 215, 23);
		condominios.add(lblOcupConds);
		
		JLabel lblInads = new JLabel("INADIMPLENTES");
		lblInads.setVerticalAlignment(SwingConstants.TOP);
		lblInads.setHorizontalAlignment(SwingConstants.CENTER);
		lblInads.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
		lblInads.setBounds(496, 191, 215, 23);
		condominios.add(lblInads);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 221, 215, 203);
		condominios.add(scrollPane_1);
		
		exibirTodosConds.setEditable(false);
		scrollPane_1.setViewportView(exibirTodosConds);
		exibirTodosConds.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 18));
		
		JButton botaoListarConds = new JButton("LISTAR");
		botaoListarConds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirTodosConds.setText(Fachada.exibirCondominios());
			}
		});
		botaoListarConds.setForeground(Color.WHITE);
		botaoListarConds.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoListarConds.setBackground(new Color(0, 102, 51));
		botaoListarConds.setBounds(23, 435, 215, 25);
		condominios.add(botaoListarConds);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(259, 221, 215, 180);
		condominios.add(scrollPane_2);
		
		JTextArea exibirOcupConds = new JTextArea();
		exibirOcupConds.setEditable(false);
		scrollPane_2.setViewportView(exibirOcupConds);
		exibirOcupConds.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 18));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(496, 221, 215, 180);
		condominios.add(scrollPane_3);
		
		JTextArea exibirInads = new JTextArea();
		exibirInads.setEditable(false);
		scrollPane_3.setViewportView(exibirInads);
		exibirInads.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 18));
		
		JButton botaoListarInads = new JButton("LISTAR");
		botaoListarInads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(inputIdCondInads.getText().equals("")) {
						JOptionPane.showMessageDialog(menuPrincipal, "O campo ID está vazio. Digite o ID de um condomínio e tente novamemente.", "Erro", JOptionPane.WARNING_MESSAGE);
						inputIdCondInads.requestFocus();
					} else {
						int idCond = Integer.valueOf(inputIdCondInads.getText());
						exibirInads.setText(Fachada.exibirInadsCondX(idCond));
						inputIdCondInads.setText("");
						inputIdCondInads.requestFocus();
					}
				} catch (Exception erroInads) {
					JOptionPane.showMessageDialog(menuPrincipal, erroInads.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
					inputIdCondInads.setText("");
					inputIdCondInads.requestFocus();
				}
			}
		});
		botaoListarInads.setForeground(Color.WHITE);
		botaoListarInads.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoListarInads.setBackground(new Color(0, 102, 51));
		botaoListarInads.setBounds(496, 435, 215, 25);
		condominios.add(botaoListarInads);
		
		JLabel lblQntMoradores = new JLabel("QNT. MORADORES:");
		lblQntMoradores.setHorizontalAlignment(SwingConstants.LEFT);
		lblQntMoradores.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 14));
		lblQntMoradores.setBounds(258, 410, 115, 14);
		condominios.add(lblQntMoradores);
		
		inputNMoradores = new JTextField();
		inputNMoradores.setToolTipText("Nome do morador.");
		inputNMoradores.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputNMoradores.setColumns(10);
		inputNMoradores.setBounds(379, 405, 95, 25);
		condominios.add(inputNMoradores);
		
		JLabel lblIdInads = new JLabel("ID DO CONDOMÍNIO:");
		lblIdInads.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdInads.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 14));
		lblIdInads.setBounds(497, 409, 121, 16);
		condominios.add(lblIdInads);
		
		JButton botaoListarOcup = new JButton("LISTAR");
		botaoListarOcup.setBounds(259, 435, 215, 25);
		condominios.add(botaoListarOcup);
		botaoListarOcup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(inputNMoradores.getText().equals("")) {
						JOptionPane.showMessageDialog(menuPrincipal, "O campo Número de Moradores está vazio. Digite uma quantidade e tente novamemente. ", "Erro", JOptionPane.WARNING_MESSAGE);
						inputNMoradores.requestFocus();
					} else {
						int numMoradores = Integer.valueOf(inputNMoradores.getText());
						exibirOcupConds.setText(Fachada.exibirOcupEdifs(numMoradores));
						inputNMoradores.setText("");
						inputNMoradores.requestFocus();
					}
				} catch (Exception erroOcupEdifs) {
					JOptionPane.showMessageDialog(menuPrincipal, erroOcupEdifs.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
					inputNMoradores.setText("");
					inputNMoradores.requestFocus();
				}
			}
		});
		botaoListarOcup.setForeground(Color.WHITE);
		botaoListarOcup.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoListarOcup.setBackground(new Color(0, 102, 51));
		
		inputIdCondInads = new JTextField();
		inputIdCondInads.setToolTipText("Nome do morador.");
		inputIdCondInads.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputIdCondInads.setColumns(10);
		inputIdCondInads.setBounds(622, 405, 89, 25);
		condominios.add(inputIdCondInads);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 183, 688, 14);
		condominios.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(366, 57, 15, 115);
		condominios.add(separator_1);
		
		JPanel financeiro = new JPanel();
		financeiro.setLayout(null);
		financeiro.setBackground(new Color(225, 225, 225));
		financeiro.setBounds(0, 0, 734, 480);
		menuPrincipal.getContentPane().add(financeiro);
		financeiro.setVisible(false);
		
		JLabel lblContratos = new JLabel("FINANCEIRO");
		lblContratos.setHorizontalAlignment(SwingConstants.CENTER);
		lblContratos.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 25));
		lblContratos.setBounds(23, 11, 688, 23);
		financeiro.add(lblContratos);
		
		JLabel lblCriarCont = new JLabel("CRIAR CONTRATO");
		lblCriarCont.setHorizontalAlignment(SwingConstants.CENTER);
		lblCriarCont.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
		lblCriarCont.setBounds(23, 53, 310, 14);
		financeiro.add(lblCriarCont);
		
		JLabel lblEncCont = new JLabel("ENCERRAR CONTRATO");
		lblEncCont.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncCont.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
		lblEncCont.setBounds(401, 53, 310, 14);
		financeiro.add(lblEncCont);
		
		inputIdCC = new JTextField();
		inputIdCC.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputIdCC.setBounds(23, 110, 150, 25);
		financeiro.add(inputIdCC);
		inputIdCC.setColumns(10);
		
		JLabel lblIdCC = new JLabel("ID DO CONDOMÍNIO:");
		lblIdCC.setVerticalAlignment(SwingConstants.TOP);
		lblIdCC.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblIdCC.setBounds(23, 89, 150, 17);
		financeiro.add(lblIdCC);
		
		JLabel lblCpfCC = new JLabel("CPF DO CLIENTE:");
		lblCpfCC.setVerticalAlignment(SwingConstants.TOP);
		lblCpfCC.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblCpfCC.setBounds(183, 89, 150, 17);
		financeiro.add(lblCpfCC);
		
		inputCpfCC = new JTextField();
		inputCpfCC.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputCpfCC.setColumns(10);
		inputCpfCC.setBounds(183, 110, 150, 25);
		financeiro.add(inputCpfCC);
		
		JLabel lblValorAluguel = new JLabel("VALOR DO ALUGUEL:");
		lblValorAluguel.setVerticalAlignment(SwingConstants.TOP);
		lblValorAluguel.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblValorAluguel.setBounds(23, 146, 150, 17);
		financeiro.add(lblValorAluguel);
		
		inputValorAluguel = new JTextField();
		inputValorAluguel.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputValorAluguel.setColumns(10);
		inputValorAluguel.setBounds(23, 167, 150, 25);
		financeiro.add(inputValorAluguel);
		
		JTextArea exibirBoletos = new JTextArea();
		
		JButton botaoCriarCont = new JButton("CRIAR CONTRATO");
		botaoCriarCont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idCond = inputIdCC.getText();
				String cpfMorador = inputCpfCC.getText();
				String valorAluguel = inputValorAluguel.getText();
				
				if(idCond.equals("") || cpfMorador.equals("") || valorAluguel.equals("")) {
					JOptionPane.showMessageDialog(menuPrincipal, "Existem campos vazios. Preencha-os e tente novamente.", "Erro", JOptionPane.WARNING_MESSAGE);
					inputIdCC.requestFocus();
				} else {
					try {
						Fachada.criarContrato(Integer.valueOf(idCond), cpfMorador, Double.valueOf(valorAluguel));
						JOptionPane.showMessageDialog(menuPrincipal, "Contrato criado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						listagemMoradores.setText(Fachada.exibirMoradores());
						exibirBoletos.setText(Fachada.exibirTodosOsBoletos());
						inputIdCC.setText("");
						inputCpfCC.setText("");
						inputValorAluguel.setText("");
						inputIdCC.requestFocus();
					} catch (Exception erroCriarContrato) {
						JOptionPane.showMessageDialog(menuPrincipal, erroCriarContrato.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
						inputIdCC.setText("");
						inputCpfCC.setText("");
						inputValorAluguel.setText("");
						inputIdCC.requestFocus();
					}
				}
			}
		});
		botaoCriarCont.setForeground(new Color(255, 255, 255));
		botaoCriarCont.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoCriarCont.setBackground(new Color(0, 102, 51));
		botaoCriarCont.setBounds(183, 167, 150, 25);
		financeiro.add(botaoCriarCont);
		
		JLabel lblIdEC = new JLabel("ID DO CONDOMÍNIO:");
		lblIdEC.setVerticalAlignment(SwingConstants.TOP);
		lblIdEC.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblIdEC.setBounds(401, 89, 150, 17);
		financeiro.add(lblIdEC);
		
		inputIdEC = new JTextField();
		inputIdEC.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputIdEC.setColumns(10);
		inputIdEC.setBounds(401, 110, 150, 25);
		financeiro.add(inputIdEC);
		
		JLabel lblCpfEC = new JLabel("CPF DO CLIENTE:");
		lblCpfEC.setVerticalAlignment(SwingConstants.TOP);
		lblCpfEC.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblCpfEC.setBounds(560, 89, 150, 17);
		financeiro.add(lblCpfEC);
		
		inputCpfEC = new JTextField();
		inputCpfEC.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputCpfEC.setColumns(10);
		inputCpfEC.setBounds(560, 110, 150, 25);
		financeiro.add(inputCpfEC);
		
		JButton botaoEncCont = new JButton("ENCERRAR CONTRATO");
		botaoEncCont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idCond = inputIdEC.getText();
				String cpfMorador = inputCpfEC.getText();
				String numAp = inputNumDoAp.getText();
				
				if(idCond.equals("") || cpfMorador.equals("")) {
					JOptionPane.showMessageDialog(menuPrincipal, "Existem campos vazios. Preencha-os e tente novamente.", "Erro", JOptionPane.WARNING_MESSAGE);
					inputIdEC.requestFocus();
				} else {
					try {
						Fachada.encerrarContrato(Integer.valueOf(idCond), cpfMorador, numAp);
						JOptionPane.showMessageDialog(menuPrincipal, "Contrato encerrado com sucesso.", "Sucesso", JOptionPane.WARNING_MESSAGE);
						inputIdEC.setText("");
						inputCpfEC.setText("");
						inputNumDoAp.setText("");
						inputIdEC.requestFocus();
					} catch (Exception erroEncContrato) {
						JOptionPane.showMessageDialog(menuPrincipal, erroEncContrato.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
						inputIdEC.setText("");
						inputCpfEC.setText("");
						inputNumDoAp.setText("");
						inputIdEC.requestFocus();
					}
				}
			}
		});
		botaoEncCont.setForeground(Color.WHITE);
		botaoEncCont.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoEncCont.setBackground(new Color(0, 102, 51));
		botaoEncCont.setBounds(560, 168, 151, 25);
		financeiro.add(botaoEncCont);
		
		JLabel lblBoletos = new JLabel("PAGAR BOLETO");
		lblBoletos.setVerticalAlignment(SwingConstants.TOP);
		lblBoletos.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoletos.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
		lblBoletos.setBounds(284, 245, 165, 20);
		financeiro.add(lblBoletos);
		
		JLabel lblQntBol = new JLabel("");
		JButton botaoGerarBol = new JButton("GERAR");
		botaoGerarBol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int boletosGerados = Fachada.adcBoletos();
				lblQntBol.setText(String.valueOf(boletosGerados));
			}
		});
		botaoGerarBol.setForeground(Color.WHITE);
		botaoGerarBol.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoGerarBol.setBackground(new Color(0, 102, 51));
		botaoGerarBol.setBounds(279, 440, 95, 25);
		financeiro.add(botaoGerarBol);
		
		lblMesRef.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		lblMesRef.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesRef.setBounds(279, 404, 95, 23);
		financeiro.add(lblMesRef);
		
		JTextArea fundoMesRef = new JTextArea();
		fundoMesRef.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		fundoMesRef.setBounds(279, 404, 95, 23);
		financeiro.add(fundoMesRef);
		
		JLabel tituloMesRef = new JLabel("MÊS ATUAL:");
		tituloMesRef.setVerticalAlignment(SwingConstants.TOP);
		tituloMesRef.setHorizontalAlignment(SwingConstants.LEFT);
		tituloMesRef.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		tituloMesRef.setBounds(279, 382, 96, 17);
		financeiro.add(tituloMesRef);
		
		JLabel tituloQntBol = new JLabel("GERADOS:");
		tituloQntBol.setVerticalAlignment(SwingConstants.TOP);
		tituloQntBol.setHorizontalAlignment(SwingConstants.CENTER);
		tituloQntBol.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 13));
		tituloQntBol.setBounds(385, 382, 68, 17);
		financeiro.add(tituloQntBol);
		
		lblQntBol.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 30));
		lblQntBol.setHorizontalAlignment(SwingConstants.CENTER);
		lblQntBol.setBounds(384, 404, 69, 61);
		financeiro.add(lblQntBol);
		
		JTextArea fundoQntBol = new JTextArea();
		fundoQntBol.setEditable(false);
		fundoQntBol.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 25));
		fundoQntBol.setBounds(384, 404, 69, 61);
		financeiro.add(fundoQntBol);
		
		JLabel lblBoletos_1 = new JLabel("BOLETOS ATRASADOS");
		lblBoletos_1.setVerticalAlignment(SwingConstants.TOP);
		lblBoletos_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoletos_1.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 20));
		lblBoletos_1.setBounds(476, 221, 235, 25);
		financeiro.add(lblBoletos_1);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(476, 251, 235, 157);
		financeiro.add(scrollPane_4);
		
		JTextArea exibirBolAtrasados = new JTextArea();
		exibirBolAtrasados.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		scrollPane_4.setViewportView(exibirBolAtrasados);
		
		JSeparator separadorHFinan1 = new JSeparator();
		separadorHFinan1.setBounds(23, 217, 688, 8);
		financeiro.add(separadorHFinan1);
		
		JButton botaoExibirAtrasados = new JButton("LISTAR");
		botaoExibirAtrasados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ano = inputAnoRef.getText();
				if(ano.equals("")) {
					JOptionPane.showMessageDialog(menuPrincipal, "O campo Ano de Referência está vazio. Preencha-o e tente novamente.", "Erro", JOptionPane.WARNING_MESSAGE);
					exibirBolAtrasados.setText("");
					inputAnoRef.requestFocus();
				} else {
					exibirBolAtrasados.setText(Fachada.exibirBoletosNP(Integer.valueOf(ano)));
					inputAnoRef.setText("");
					inputAnoRef.requestFocus();
				}
			}
		});
		botaoExibirAtrasados.setForeground(Color.WHITE);
		botaoExibirAtrasados.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoExibirAtrasados.setBackground(new Color(0, 102, 51));
		botaoExibirAtrasados.setBounds(476, 440, 235, 25);
		financeiro.add(botaoExibirAtrasados);
		
		inputAnoRef = new JTextField();
		inputAnoRef.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
		inputAnoRef.setBounds(612, 413, 99, 23);
		financeiro.add(inputAnoRef);
		inputAnoRef.setColumns(10);
		
		JLabel lblAnoRef = new JLabel("ANO DE REFERÊNCIA:");
		lblAnoRef.setBounds(476, 415, 132, 18);
		financeiro.add(lblAnoRef);
		lblAnoRef.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnoRef.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 14));
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(366, 66, 12, 140);
		financeiro.add(separator_2);
		
		JLabel lblBoletos_1_1 = new JLabel("TODOS OS BOLETOS");
		lblBoletos_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblBoletos_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoletos_1_1.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 20));
		lblBoletos_1_1.setBounds(23, 221, 235, 25);
		financeiro.add(lblBoletos_1_1);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(23, 250, 235, 179);
		financeiro.add(scrollPane_5);
		
			exibirBoletos.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
			scrollPane_5.setViewportView(exibirBoletos);
			
			JButton botaoListarBoletos = new JButton("LISTAR");
			botaoListarBoletos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exibirBoletos.setText(Fachada.exibirTodosOsBoletos());
				}
			});
			botaoListarBoletos.setForeground(Color.WHITE);
			botaoListarBoletos.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
			botaoListarBoletos.setBackground(new Color(0, 102, 51));
			botaoListarBoletos.setBounds(23, 440, 235, 25);
			financeiro.add(botaoListarBoletos);
			
			JLabel lblBoletos_2 = new JLabel("GERAÇÃO DE BOLETOS");
			lblBoletos_2.setVerticalAlignment(SwingConstants.TOP);
			lblBoletos_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblBoletos_2.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 18));
			lblBoletos_2.setBounds(277, 353, 180, 25);
			financeiro.add(lblBoletos_2);
			
			JLabel lblIdDoBoleto = new JLabel("ID DO BOLETO:");
			lblIdDoBoleto.setHorizontalAlignment(SwingConstants.LEFT);
			lblIdDoBoleto.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 14));
			lblIdDoBoleto.setBounds(279, 278, 89, 18);
			financeiro.add(lblIdDoBoleto);
			
			idPagarBoleto = new JTextField();
			idPagarBoleto.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
			idPagarBoleto.setColumns(10);
			idPagarBoleto.setBounds(371, 276, 83, 23);
			financeiro.add(idPagarBoleto);
			
			JButton botaoPagar = new JButton("PAGAR");
			botaoPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String idBoleto = idPagarBoleto.getText();
					
					if(idBoleto.equals("")) {
						JOptionPane.showMessageDialog(menuPrincipal, "O campo ID Boleto está vazio. Preencha-o e tente novamente.", "Erro", JOptionPane.WARNING_MESSAGE);
						idPagarBoleto.setText("");
						idPagarBoleto.requestFocus();
					} else {
						try {
							Fachada.pagarBoleto(Integer.valueOf(idBoleto));
							JOptionPane.showMessageDialog(menuPrincipal, "O boleto de ID "+idBoleto+" foi pago.", "Sucesso.", JOptionPane.INFORMATION_MESSAGE);
							idPagarBoleto.setText("");
							idPagarBoleto.requestFocus();
						} catch (NumberFormatException erroConvNum) {
							JOptionPane.showMessageDialog(menuPrincipal, erroConvNum.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
							idPagarBoleto.setText("");
							idPagarBoleto.requestFocus();
						} catch (Exception erroPagamento) {
							JOptionPane.showMessageDialog(menuPrincipal, erroPagamento.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
							idPagarBoleto.setText("");
							idPagarBoleto.requestFocus();
						}
					}
				}
			});
			botaoPagar.setForeground(Color.WHITE);
			botaoPagar.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
			botaoPagar.setBackground(new Color(0, 102, 51));
			botaoPagar.setBounds(279, 305, 175, 25);
			financeiro.add(botaoPagar);
			
			JSeparator separadorHFinan1_1 = new JSeparator();
			separadorHFinan1_1.setBounds(280, 343, 174, 8);
			financeiro.add(separadorHFinan1_1);
			
			JLabel lblNumDoAp = new JLabel("Nº DO APARTAMENTO:");
			lblNumDoAp.setVerticalAlignment(SwingConstants.TOP);
			lblNumDoAp.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
			lblNumDoAp.setBounds(401, 146, 150, 17);
			financeiro.add(lblNumDoAp);
			
			inputNumDoAp = new JTextField();
			inputNumDoAp.setFont(new Font("Core Sans DS 35 Regular", Font.PLAIN, 15));
			inputNumDoAp.setColumns(10);
			inputNumDoAp.setBounds(401, 167, 150, 25);
			financeiro.add(inputNumDoAp);
		
		JMenuBar menuBar = new JMenuBar();
		menuPrincipal.setJMenuBar(menuBar);
		
		JButton botaoMoradores = new JButton("MORADORES");
		JButton botaoCondominios = new JButton("CONDOMÍNIOS");
		JButton botaoContratos = new JButton("FINANCEIRO");
		
		botaoMoradores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				condominios.setVisible(false);
				financeiro.setVisible(false);
				moradores.setVisible(true);
				botaoMoradores.setForeground(new Color(0, 102, 153));
				botaoMoradores.setBackground(new Color(225, 225, 225));
				
				botaoCondominios.setBackground(new Color(0, 102, 153));
				botaoCondominios.setForeground(new Color(225, 225, 225));
				
				botaoContratos.setBackground(new Color(0, 102, 153));
				botaoContratos.setForeground(new Color(225, 225, 225));
			}
		});
		botaoMoradores.setFocusable(false);
		botaoMoradores.setBackground(new Color(225, 225, 225));
		botaoMoradores.setForeground(new Color(0, 102, 153));
		botaoMoradores.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		botaoMoradores.setEnabled(true);
		menuBar.add(botaoMoradores);
		
		botaoCondominios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moradores.setVisible(false);
				financeiro.setVisible(false);
				condominios.setVisible(true);
				botaoCondominios.setForeground(new Color(0, 102, 153));
				botaoCondominios.setBackground(new Color(225, 225, 225));
				
				botaoContratos.setBackground(new Color(0, 102, 153));
				botaoContratos.setForeground(new Color(225, 225, 225));
				
				botaoMoradores.setBackground(new Color(0, 102, 153));
				botaoMoradores.setForeground(new Color(225, 225, 225));
			}
		});
		botaoCondominios.setFocusable(false);
		botaoCondominios.setBackground(new Color(0, 102, 153));
		botaoCondominios.setForeground(new Color(255, 255, 255));
		botaoCondominios.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		menuBar.add(botaoCondominios);
		
		botaoContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moradores.setVisible(false);
				condominios.setVisible(false);
				financeiro.setVisible(true);
				botaoContratos.setForeground(new Color(0, 102, 153));
				botaoContratos.setBackground(new Color(225, 225, 225));
				
				botaoCondominios.setBackground(new Color(0, 102, 153));
				botaoCondominios.setForeground(new Color(225, 225, 225));
				
				botaoMoradores.setBackground(new Color(0, 102, 153));
				botaoMoradores.setForeground(new Color(225, 225, 225));
			}
		});
		botaoContratos.setFocusable(false);
		botaoContratos.setBackground(new Color(0, 102, 153));
		botaoContratos.setForeground(new Color(255, 255, 255));
		botaoContratos.setFont(new Font("Core Sans DS 55 Bold", Font.PLAIN, 15));
		menuBar.add(botaoContratos);
	}
}
