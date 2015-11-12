package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import model.Atributo;
import model.Elemento;
import model.Gerador;

public class PainelAtributosAdd extends JPanel {
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
	private JPanel panel_4;
	private Elemento elemento;
	private JComboBox<String> comboBox;

	/**
	 * Create the panel.
	 */
	public PainelAtributosAdd() {
		setLayout(new BorderLayout(0, 0));

		setBorder(BorderFactory.createTitledBorder("Atributos"));

		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(10, 100));

		listModel = new DefaultListModel<>();

		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list, BorderLayout.CENTER);

		panel_4 = new JPanel();
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("TitledBorder.border"));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnSelecionarElemento = new JButton("Selecionar Elemento");
		panel_2.add(btnSelecionarElemento);
		btnSelecionarElemento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = list.getSelectedIndex();
				if (index < 0) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				elemento = Gerador.getInstance().getElementoPorNome(
						listModel.getElementAt(index));
				panel_4.setBorder(new TitledBorder(null, elemento.getNome(),
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				ArrayList<Atributo> a = elemento.getAtributos();
				listModel_1.removeAllElements();
				for (Atributo atributo : a) {
					listModel_1.addElement(atributo.getNome());
				}
			}
		});

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		listModel_1 = new DefaultListModel<>();
		listModel_1.addElement("asd");

		list_1 = new JList<String>(listModel_1);
		list_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Atributos",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_3.add(list_1, BorderLayout.EAST);

		panel_3.add(panel_4, BorderLayout.CENTER);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_4.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		JLabel lblNome = new JLabel("nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 1;
		panel_4.add(lblNome, gbc_lblNome);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_4.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblTipo = new JLabel("tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 1;
		gbc_lblTipo.gridy = 2;
		panel_4.add(lblTipo, gbc_lblTipo);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"String", "Integer", "Boolean", "Float" }));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 2;
		panel_4.add(comboBox, gbc_comboBox);

		JLabel lblValor = new JLabel("valor:");
		GridBagConstraints gbc_lblValor = new GridBagConstraints();
		gbc_lblValor.anchor = GridBagConstraints.EAST;
		gbc_lblValor.insets = new Insets(0, 0, 5, 5);
		gbc_lblValor.gridx = 1;
		gbc_lblValor.gridy = 3;
		panel_4.add(lblValor, gbc_lblValor);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		panel_4.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JButton btnAdicionarAtributo = new JButton("Adicionar Atributo");
		GridBagConstraints gbc_btnAdicionarAtributo = new GridBagConstraints();
		gbc_btnAdicionarAtributo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdicionarAtributo.gridwidth = 3;
		gbc_btnAdicionarAtributo.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdicionarAtributo.gridx = 1;
		gbc_btnAdicionarAtributo.gridy = 6;
		panel_4.add(btnAdicionarAtributo, gbc_btnAdicionarAtributo);
		btnAdicionarAtributo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (elemento != null) {
					String name = textField.getText();

					if (name.equals("") || alreadyInList(name)) {
						Toolkit.getDefaultToolkit().beep();
						textField_2.requestFocusInWindow();
						textField_2.selectAll();
						textField.requestFocusInWindow();
						textField.selectAll();
						return;
					}

					listModel_1.addElement(textField.getText());

					textField.requestFocusInWindow();

					Atributo a = new Atributo(textField.getText(), comboBox
							.getItemAt(comboBox.getSelectedIndex()),
							textField_2.getText());

					elemento.getAtributos().add(a);

					textField.setText("");
					textField_2.setText("");
				}
			}
		});

	}

	private boolean alreadyInList(String name) {
		for (int i = 0; i < listModel_1.getSize(); i++) {
			if (listModel_1.getElementAt(i).equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void updateList() {
		ArrayList<Elemento> e = Gerador.getInstance().getListaElementos();
		listModel.removeAllElements();
		listModel_1.removeAllElements();
		for (Elemento elemento : e) {
			listModel.addElement(elemento.getNome());
		}
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		elemento = null;
	}
}
