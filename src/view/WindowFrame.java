package view;

import java.awt.CardLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import controller.ControllerWindowFrame;

public class WindowFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9169081595910092733L;
	private JPanel contentPane;
	private JCheckBoxMenuItem chckbxmntmColiso;
	private JCheckBoxMenuItem chckbxmntmMovimento;
	private JCheckBoxMenuItem chckbxmntmTeclado;
	private JCheckBoxMenuItem chckbxmntmMouse;
	private JFileChooser fc;
	private JMenuItem mntmInicio;
	private JMenuItem mntmCarregarBase;
	private JMenuItem mntmSalvarBase;
	private JMenuItem mntmSair;
	private JMenuItem mntmGerenciar_Elementos;
	private JMenuItem mntmGerenciar_Atributos;
	private JMenuItem mntmGerenciar_Cenarios;
	private JMenuItem mntmGerarCodigo;
	private PainelElementos panel_1;
	private PainelAtributos panel_2;
	private PainelCenarios panel_3;
	private PainelVisaoGeral panel;

	/**
	 * Create the frame.
	 */
	public WindowFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 350);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		panel = new PainelVisaoGeral();
		contentPane.add(panel, "inicio");

		panel_1 = new PainelElementos();
		contentPane.add(panel_1, "elementos");

		panel_2 = new PainelAtributos();
		contentPane.add(panel_2, "atributos");

		panel_3 = new PainelCenarios();
		contentPane.add(panel_3, "cenarios");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		mntmInicio = new JMenuItem("Inicio");
		mntmInicio.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/Home.png")));
		mnArquivo.add(mntmInicio);
		mntmInicio.addActionListener(ControllerWindowFrame.getInstance(this));

		mntmCarregarBase = new JMenuItem("Carregar Base");
		mntmCarregarBase.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/open.png")));
		mnArquivo.add(mntmCarregarBase);
		mntmCarregarBase.addActionListener(ControllerWindowFrame
				.getInstance(this));

		mntmSalvarBase = new JMenuItem("Salvar Base");
		mntmSalvarBase.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/salvar.png")));
		mntmSalvarBase.addActionListener(ControllerWindowFrame
				.getInstance(this));
		mnArquivo.add(mntmSalvarBase);

		mntmSair = new JMenuItem("Sair");
		mntmSair.setIcon(new ImageIcon(getClass().getClassLoader().getResource(
				"images/Exit.png")));
		mntmSair.addActionListener(ControllerWindowFrame.getInstance(this));
		mnArquivo.add(mntmSair);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenu mnElementos_1 = new JMenu("Elementos");
		mnElementos_1.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/user_blue.png")));
		mnGame.add(mnElementos_1);

		mntmGerenciar_Elementos = new JMenuItem("Gerenciar");
		mntmGerenciar_Elementos.setIcon(new ImageIcon(getClass()
				.getClassLoader().getResource("images/user_blue_config.png")));
		mnElementos_1.add(mntmGerenciar_Elementos);
		mntmGerenciar_Elementos.addActionListener(ControllerWindowFrame
				.getInstance(this));

		JMenu mnAtributos_1 = new JMenu("Atributos");
		mnAtributos_1.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/attribute.png")));
		mnGame.add(mnAtributos_1);

		mntmGerenciar_Atributos = new JMenuItem("Gerenciar");
		mntmGerenciar_Atributos.setIcon(new ImageIcon(getClass()
				.getClassLoader().getResource("images/settings_file.png")));
		mnAtributos_1.add(mntmGerenciar_Atributos);
		mntmGerenciar_Atributos.addActionListener(ControllerWindowFrame
				.getInstance(this));

		JMenu mnCenrios = new JMenu("Cen\u00E1rios");
		mnCenrios.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/cenario.png")));
		mnGame.add(mnCenrios);

		mntmGerenciar_Cenarios = new JMenuItem("Gerenciar");
		mntmGerenciar_Cenarios.setIcon(new ImageIcon(getClass()
				.getClassLoader().getResource("images/settings.png")));
		mnCenrios.add(mntmGerenciar_Cenarios);
		mntmGerenciar_Cenarios.addActionListener(ControllerWindowFrame
				.getInstance(this));

		JMenu mnInterao = new JMenu("Intera\u00E7\u00E3o");
		mnInterao.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/interactive.png")));
		mnGame.add(mnInterao);

		chckbxmntmMouse = new JCheckBoxMenuItem("Mouse");
		mnInterao.add(chckbxmntmMouse);
		chckbxmntmMouse
				.addItemListener(ControllerWindowFrame.getInstance(this));

		chckbxmntmTeclado = new JCheckBoxMenuItem("Teclado");
		mnInterao.add(chckbxmntmTeclado);
		chckbxmntmTeclado.addItemListener(ControllerWindowFrame
				.getInstance(this));

		JMenu mnAes = new JMenu("A\u00E7\u00F5es");
		mnAes.setIcon(new ImageIcon(getClass().getClassLoader().getResource(
				"images/action.png")));
		mnGame.add(mnAes);

		chckbxmntmMovimento = new JCheckBoxMenuItem("Movimento");
		mnAes.add(chckbxmntmMovimento);
		chckbxmntmMovimento.addItemListener(ControllerWindowFrame
				.getInstance(this));

		chckbxmntmColiso = new JCheckBoxMenuItem("Colis\u00E3o");
		mnAes.add(chckbxmntmColiso);
		chckbxmntmColiso.addItemListener(ControllerWindowFrame
				.getInstance(this));

		mntmGerarCodigo = new JMenuItem("Gerar Codigo");
		mnGame.add(mntmGerarCodigo);
		mntmGerarCodigo.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/gerador.png")));

		mntmGerarCodigo.addActionListener(ControllerWindowFrame
				.getInstance(this));

		final File initialdir = new File("resource/model");

		fc = new JFileChooser(initialdir);

		fc.getComponent(0).setVisible(false);

		fc.setComponentPopupMenu(new JPopupMenu("nada"));

		fc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent e) {
				if (e.getPropertyName().equals(
						JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
					fc.setCurrentDirectory(initialdir);
				}
			}
		});

		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);

		javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileFilter() {
			public boolean accept(java.io.File file) {
				if (file.isDirectory()) {
					return true;
				} else {
					if (file.getName().endsWith(".uml")) {
						return true;
					}
				}
				return false;
			}

			public String getDescription() {
				return "XMI File (*.uml)";
			}
		};

		fc.setFileFilter(filter);
	}

	public JCheckBoxMenuItem getChckbxmntmColiso() {
		return chckbxmntmColiso;
	}

	public JCheckBoxMenuItem getChckbxmntmMovimento() {
		return chckbxmntmMovimento;
	}

	public JCheckBoxMenuItem getChckbxmntmTeclado() {
		return chckbxmntmTeclado;
	}

	public JCheckBoxMenuItem getChckbxmntmMouse() {
		return chckbxmntmMouse;
	}

	public JFileChooser getFc() {
		return fc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JMenuItem getMntmInicio() {
		return mntmInicio;
	}

	public JMenuItem getMntmCarregarBase() {
		return mntmCarregarBase;
	}

	public JMenuItem getMntmSalvarBase() {
		return mntmSalvarBase;
	}

	public JMenuItem getMntmSair() {
		return mntmSair;
	}

	public JMenuItem getMntmGerenciar_Elementos() {
		return mntmGerenciar_Elementos;
	}

	public JMenuItem getMntmGerenciar_Atributos() {
		return mntmGerenciar_Atributos;
	}

	public JMenuItem getMntmGerenciar_Cenarios() {
		return mntmGerenciar_Cenarios;
	}

	public JMenuItem getMntmGerarCodigo() {
		return mntmGerarCodigo;
	}

	public PainelElementos getPanel_1() {
		return panel_1;
	}

	public PainelAtributos getPanel_2() {
		return panel_2;
	}

	public PainelCenarios getPanel_3() {
		return panel_3;
	}

	public PainelVisaoGeral getPanel() {
		return panel;
	}

}
