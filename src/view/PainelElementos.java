package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import controller.ControllerPainelElementos;

public class PainelElementos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7341909519667330946L;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private DefaultComboBoxModel<String> comboBoxModel;
	protected JButton btnRemover;
	private JButton btnAdicionar;

	/**
	 * Create the panel.
	 */
	public PainelElementos() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(150, 280));
		panel.add(scrollPane, BorderLayout.CENTER);

		listModel = new DefaultListModel<>();
		list = new JList<String>(listModel);
		list.setBorder(new TitledBorder(null, "Elementos", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		scrollPane.setViewportView(list);

		btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/user_blue_delete.png")));
		panel.add(btnRemover, BorderLayout.SOUTH);
		btnRemover.addActionListener(ControllerPainelElementos.getInstance(this));

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setPreferredSize(new Dimension(300, 300));
		panel_1.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(28, 11, 59, 20);
		panel_1.add(lblNome);

		textField = new JTextField();
		textField.setBounds(97, 11, 154, 20);
		panel_1.add(textField);
		textField.addKeyListener(ControllerPainelElementos.getInstance(this));

		JLabel lblUm = new JLabel("\u00E9 um:");
		lblUm.setBounds(28, 64, 59, 20);
		panel_1.add(lblUm);

		comboBoxModel = new DefaultComboBoxModel<String>();
		comboBox = new JComboBox<String>(comboBoxModel);
		comboBox.setBounds(97, 64, 154, 20);
		panel_1.add(comboBox);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/user_blue_add2.png")));
		btnAdicionar.setBounds(28, 121, 223, 25);
		panel_1.add(btnAdicionar);
		btnAdicionar.addActionListener(ControllerPainelElementos.getInstance(this));

	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public JList<String> getList() {
		return list;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public DefaultComboBoxModel<String> getComboBoxModel() {
		return comboBoxModel;
	}

	public JButton getBtnRemover() {
		return btnRemover;
	}

	public JButton getBtnAdicionar() {
		return btnAdicionar;
	}

	public JTextField getTextField() {
		return textField;
	}
}
