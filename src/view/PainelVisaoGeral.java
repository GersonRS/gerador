package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class PainelVisaoGeral extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JList<String> list_1;
	private DefaultListModel<String> listModel_1;

	private JLabel lblMouse;

	private JLabel lblTeclado;

	private JLabel lblColiso;

	private JLabel lblMovimento;

	/**
	 * Create the panel.
	 */
	public PainelVisaoGeral() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 130, 275);
		add(scrollPane);

		listModel = new DefaultListModel<>();
		list = new JList<String>(listModel);
		list.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Elementos",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(list);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(160, 11, 130, 275);
		add(scrollPane_1);

		listModel_1 = new DefaultListModel<>();
		list_1 = new JList<String>(listModel_1);
		list_1.setBorder(new TitledBorder(null, "Cenarios",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		scrollPane_1.setViewportView(list_1);
		list_1.setEnabled(false);

		JPanel panel = new JPanel();
		panel.setBounds(311, 11, 130, 280);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Intera\u00E7\u00E3o",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setPreferredSize(new Dimension(130, 135));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));

		lblMouse = new JLabel("Mouse");
		lblMouse.setIcon(new ImageIcon(getClass().getClassLoader().getResource(
				"images/excluir.png")));
		lblMouse.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblMouse);

		lblTeclado = new JLabel("Teclado");
		lblTeclado.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/excluir.png")));
		lblTeclado.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblTeclado);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "A\u00E7\u00F5es",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));

		lblColiso = new JLabel("Colis\u00E3o");
		lblColiso.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/excluir.png")));
		lblColiso.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblColiso);

		lblMovimento = new JLabel("Movimento");
		lblMovimento.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/excluir.png")));
		lblMovimento.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblMovimento);

	}

	public JList<String> getList() {
		return list;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public JList<String> getList_1() {
		return list_1;
	}

	public DefaultListModel<String> getListModel_1() {
		return listModel_1;
	}

	public JLabel getLblMouse() {
		return lblMouse;
	}

	public JLabel getLblTeclado() {
		return lblTeclado;
	}

	public JLabel getLblColiso() {
		return lblColiso;
	}

	public JLabel getLblMovimento() {
		return lblMovimento;
	}

}
