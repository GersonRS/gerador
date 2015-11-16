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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import model.Elemento;
import controller.ControllerPainelAtributos;

public class PainelAtributos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7341909519667330946L;
	private JTextField textField;
	private JTextField textField_2;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JList<String> list_1;
	private DefaultListModel<String> listModel_1;
	private JPanel panel_3;
	private Elemento elemento;
	private JComboBox<String> comboBox;
	private DefaultComboBoxModel<String> comboBoxModel;
	private JComboBox<String> comboBox_2;
	private JButton btnRemover, btnAdicionarAtributo;

	/**
	 * Create the panel.
	 */
	public PainelAtributos() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(10, 100));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(150, 300));
		panel.add(scrollPane_1, BorderLayout.CENTER);

		listModel = new DefaultListModel<>();
		list = new JList<String>(listModel);
		list.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Elementos",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(ControllerPainelAtributos.getInstance(this));
		scrollPane_1.add(list);
		scrollPane_1.setViewportView(list);

		JPanel panel_4 = new JPanel();
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		listModel_1 = new DefaultListModel<>();
		listModel_1.addElement("asd");

		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(null);

		JLabel lblNome = new JLabel("nome:");
		lblNome.setBounds(10, 28, 37, 20);
		panel_4.add(lblNome);

		textField = new JTextField();
		textField.setBounds(57, 28, 124, 20);
		panel_4.add(textField);
		textField.addKeyListener(ControllerPainelAtributos.getInstance(this));

		JLabel lblTipo = new JLabel("tipo:");
		lblTipo.setBounds(10, 59, 37, 20);
		panel_4.add(lblTipo);

		comboBoxModel = new DefaultComboBoxModel<String>();
		comboBox = new JComboBox<String>(comboBoxModel);
		comboBox.setBounds(57, 59, 124, 20);
		panel_4.add(comboBox);

		comboBox.addActionListener(ControllerPainelAtributos.getInstance(this));

		JLabel lblValor = new JLabel("valor:");
		lblValor.setBounds(10, 90, 37, 20);
		panel_4.add(lblValor);

		textField_2 = new JTextField();
		textField_2.setBounds(57, 90, 124, 20);
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {
				"false", "true" }));
		comboBox_2.setBounds(57, 90, 124, 20);
		comboBox_2.setVisible(false);
		panel_4.add(textField_2);
		panel_4.add(comboBox_2);

		btnAdicionarAtributo = new JButton("Adicionar");
		btnAdicionarAtributo.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/add_file.png")));
		btnAdicionarAtributo.setBounds(10, 121, 171, 23);
		panel_4.add(btnAdicionarAtributo);
		btnAdicionarAtributo.addActionListener(ControllerPainelAtributos.getInstance(this));

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.EAST);
		panel_5.setLayout(new BorderLayout(0, 0));
		panel_5.setPreferredSize(new Dimension(120, 200));

		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane, BorderLayout.CENTER);

		list_1 = new JList<String>(listModel_1);
		list_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Atributos",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.add(list_1);
		scrollPane.setViewportView(list_1);
		list_1.addListSelectionListener(ControllerPainelAtributos.getInstance(this));

		btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/delete.png")));
		panel_5.add(btnRemover, BorderLayout.SOUTH);

		btnRemover.addActionListener(ControllerPainelAtributos.getInstance(this));

	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField_2() {
		return textField_2;
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

	public JPanel getPanel_3() {
		return panel_3;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public DefaultComboBoxModel<String> getComboBoxModel() {
		return comboBoxModel;
	}

	public JComboBox<String> getComboBox_2() {
		return comboBox_2;
	}

	public JButton getBtnRemover() {
		return btnRemover;
	}

	public JButton getBtnAdicionarAtributo() {
		return btnAdicionarAtributo;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

}
