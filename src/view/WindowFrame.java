package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Gerador;

public class WindowFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9169081595910092733L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public WindowFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,350);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		final JPanel panel = new JPanel();
		contentPane.add(panel, "inicio");
		
		final PainelElementos panel_1 = new PainelElementos();
		contentPane.add(panel_1, "elementos");

		final PainelAtributos panel_2 = new PainelAtributos();
		contentPane.add(panel_2, "atributos");
		
		final PainelCenarios panel_3 = new PainelCenarios();
		contentPane.add(panel_3, "cenarios");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmInicio = new JMenuItem("Inicio");
		mntmInicio.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Home.png")));
		mnArquivo.add(mntmInicio);
		mntmInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout layout = (CardLayout) contentPane.getLayout();
				layout.show(contentPane, "inicio");
			}
		});
		
		JMenuItem mntmCarregarBase = new JMenuItem("Carregar Base");
		mntmCarregarBase.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/open.png")));
		mnArquivo.add(mntmCarregarBase);
		
		JMenuItem mntmSalvarBase = new JMenuItem("Salvar Base");
		mntmSalvarBase.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/salvar.png")));
		mnArquivo.add(mntmSalvarBase);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Exit.png")));
		mnArquivo.add(mntmSair);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenu mnElementos_1 = new JMenu("Elementos");
		mnElementos_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/user_blue.png")));
		mnGame.add(mnElementos_1);

		JMenuItem mntmGerenciar = new JMenuItem("Gerenciar");
		mntmGerenciar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/user_blue_config.png")));
		mnElementos_1.add(mntmGerenciar);
		mntmGerenciar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) contentPane.getLayout();
				layout.show(contentPane, "elementos");
				panel_1.updateList();
			}
		});

		JMenu mnAtributos_1 = new JMenu("Atributos");
		mnAtributos_1.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/attribute.png")));
		mnGame.add(mnAtributos_1);

		JMenuItem mntmGerenciar_1 = new JMenuItem("Gerenciar");
		mntmGerenciar_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/settings_file.png")));
		mnAtributos_1.add(mntmGerenciar_1);
		mntmGerenciar_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) contentPane.getLayout();
				layout.show(contentPane, "atributos");
				panel_2.updateList();
			}
		});
		
		JMenu mnCenrios = new JMenu("Cen\u00E1rios");
		mnCenrios.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/cenario.png")));
		mnGame.add(mnCenrios);
		
		JMenuItem mntmGerenciar_2 = new JMenuItem("Gerenciar");
		mntmGerenciar_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/settings.png")));
		mnCenrios.add(mntmGerenciar_2);
		mntmGerenciar_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout layout = (CardLayout) contentPane.getLayout();
				layout.show(contentPane, "cenarios");
				panel_3.updateList();
			}
		});
		
		JMenu mnInterao = new JMenu("Intera\u00E7\u00E3o");
		mnInterao.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/interactive.png")));
		mnGame.add(mnInterao);
		
		final JCheckBoxMenuItem chckbxmntmMouse = new JCheckBoxMenuItem("Mouse");
		mnInterao.add(chckbxmntmMouse);
		chckbxmntmMouse.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxmntmMouse.isSelected()){
					Gerador.getInstance().getGame().setInteracoes_mouse(true);
				}else{
					Gerador.getInstance().getGame().setInteracoes_mouse(false);
				}
			}
		});
		
		final JCheckBoxMenuItem chckbxmntmTeclado = new JCheckBoxMenuItem("Teclado");
		mnInterao.add(chckbxmntmTeclado);
		chckbxmntmTeclado.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxmntmTeclado.isSelected()){
					Gerador.getInstance().getGame().setInteracoes_teclado(true);
				}else{
					Gerador.getInstance().getGame().setInteracoes_teclado(false);
				}
			}
		});
		
		JMenu mnAes = new JMenu("A\u00E7\u00F5es");
		mnAes.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/action.png")));
		mnGame.add(mnAes);
		
		final JCheckBoxMenuItem chckbxmntmMovimento = new JCheckBoxMenuItem("Movimento");
		mnAes.add(chckbxmntmMovimento);
		chckbxmntmMovimento.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxmntmMovimento.isSelected()){
					Gerador.getInstance().getGame().setAcoes_movimento(true);
				}else{
					Gerador.getInstance().getGame().setAcoes_movimento(false);
				}
			}
		});
		
		final JCheckBoxMenuItem chckbxmntmColiso = new JCheckBoxMenuItem("Colis\u00E3o");
		mnAes.add(chckbxmntmColiso);
		chckbxmntmColiso.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxmntmColiso.isSelected()){
					Gerador.getInstance().getGame().setAcoes_colisao(true);
				}else{
					Gerador.getInstance().getGame().setAcoes_colisao(false);
				}
			}
		});

		JMenuItem mntmGerarCodigo = new JMenuItem("Gerar Codigo");
		mnGame.add(mntmGerarCodigo);
		mntmGerarCodigo.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/gerador.png")));

		mntmGerarCodigo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gerador.getInstance().generateXMLFile();
			}
		});
	}
}
