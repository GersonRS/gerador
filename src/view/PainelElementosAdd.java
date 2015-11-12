package view;

import java.awt.BorderLayout;
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

import model.Elemento;
import model.Gerador;

public class PainelElementosAdd extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7341909519667330946L;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JList<String> list;
	private DefaultListModel<String> listModel;

	/**
	 * Create the panel.
	 */
	public PainelElementosAdd() {
		setLayout(new BorderLayout(0, 0));

		setBorder(BorderFactory.createTitledBorder("Elementos"));

		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(10, 100));

		listModel = new DefaultListModel<>();

		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panel.add(list, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 31, 86, 27, 84, 77, 0 };
		gbl_panel_1.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 1;
		panel_1.add(lblNome, gbc_lblNome);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblUm = new JLabel("\u00E9 um:");
		GridBagConstraints gbc_lblUm = new GridBagConstraints();
		gbc_lblUm.anchor = GridBagConstraints.WEST;
		gbc_lblUm.insets = new Insets(0, 0, 5, 5);
		gbc_lblUm.gridx = 1;
		gbc_lblUm.gridy = 3;
		panel_1.add(lblUm, gbc_lblUm);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Personagem", "Elemento", "Obstaculo" }));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		panel_1.add(comboBox, gbc_comboBox);

		JButton btnAdicionar = new JButton("Adicionar");
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdicionar.gridwidth = 2;
		gbc_btnAdicionar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdicionar.anchor = GridBagConstraints.NORTH;
		gbc_btnAdicionar.gridx = 1;
		gbc_btnAdicionar.gridy = 6;
		panel_1.add(btnAdicionar, gbc_btnAdicionar);
		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();

				if (name.equals("") || alreadyInList(name)) {
					Toolkit.getDefaultToolkit().beep();
					textField.requestFocusInWindow();
					textField.selectAll();
					return;
				}

				listModel.addElement(textField.getText());

				textField.requestFocusInWindow();

				Gerador.getInstance()
						.getListaElementos()
						.add(new Elemento(textField.getText(), comboBox
								.getItemAt(comboBox.getSelectedIndex())));
				
				textField.setText("");
			}
		});

	}

	private boolean alreadyInList(String name) {
		for (int i = 0; i < listModel.getSize(); i++) {
			if (listModel.getElementAt(i).equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public void updateList() {
		ArrayList<Elemento> e = Gerador.getInstance().getListaElementos();
		listModel.removeAllElements();
		for (Elemento elemento : e) {
			listModel.addElement(elemento.getNome());
		}
	}

	public JTextField getTextField() {
		return textField;
	}

}
