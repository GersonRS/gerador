package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, "branco");

		final PainelElementosAdd panel_1 = new PainelElementosAdd();
		contentPane.add(panel_1, "addElementos");

		final PainelElementosDel panel_2 = new PainelElementosDel();
		contentPane.add(panel_2, "delElementos");

		final PainelAtributosAdd panel_3 = new PainelAtributosAdd();
		contentPane.add(panel_3, "addAtributos");

		final PainelAtributosDel panel_4 = new PainelAtributosDel();
		contentPane.add(panel_4, "delAtributos");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnElementos = new JMenu("Elementos");
		menuBar.add(mnElementos);

		JMenuItem mntmAdicionar = new JMenuItem("Adicionar");
		mnElementos.add(mntmAdicionar);
		mntmAdicionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) contentPane.getLayout();
				layout.show(contentPane, "addElementos");
				panel_1.updateList();
			}
		});

		JMenuItem mntmRemover = new JMenuItem("Remover");
		mnElementos.add(mntmRemover);
		mntmRemover.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) contentPane.getLayout();
				layout.show(contentPane, "delElementos");
				panel_2.updateList();
			}
		});

		JMenu mnAtributos = new JMenu("Atributos");
		menuBar.add(mnAtributos);

		JMenuItem mntmAdicionar_1 = new JMenuItem("Adicionar");
		mnAtributos.add(mntmAdicionar_1);
		mntmAdicionar_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) contentPane.getLayout();
				layout.show(contentPane, "addAtributos");
				panel_3.updateList();
			}
		});

		JMenuItem mntmRemover_1 = new JMenuItem("Remover");
		mnAtributos.add(mntmRemover_1);
		mntmRemover_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) contentPane.getLayout();
				layout.show(contentPane, "delAtributos");
				panel_4.updateList();
			}
		});

		JMenu mnAes = new JMenu("A\u00E7\u00F5es");
		menuBar.add(mnAes);

		JMenuItem mntmAdicionar_2 = new JMenuItem("Adicionar");
		mnAes.add(mntmAdicionar_2);

		JMenuItem mntmRemover_2 = new JMenuItem("Remover");
		mnAes.add(mntmRemover_2);

		JMenu mnGerar = new JMenu("Gerar");
		menuBar.add(mnGerar);

		JMenuItem mntmGerarCodigo = new JMenuItem("Gerar Codigo");
		mnGerar.add(mntmGerarCodigo);
		
		System.out.println(new File("resource").getAbsolutePath()+ File.separator +"model1.uml");
		
		mntmGerarCodigo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Gerador.getInstance().generateXMLFile(new File("resource").getAbsolutePath());
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
	}
}
