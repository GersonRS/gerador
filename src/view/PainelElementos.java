package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import model.Elemento;
import model.Gerador;

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
		btnRemover.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/user_blue_delete.png")));
		panel.add(btnRemover, BorderLayout.SOUTH);
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = list.getSelectedIndex();
				if (index < 0) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}

				ArrayList<Elemento> e = Gerador.getInstance().getGame()
						.getListaElementos();
				for (int i = 0; i < e.size(); i++) {
					if (e.get(i).getNome()
							.equalsIgnoreCase(listModel.get(index))) {
						Gerador.getInstance().getGame().getListaElementos()
								.remove(i);
					}
				}
				listModel.remove(index);

				int size = listModel.getSize();

				if (size == 0) { // Nobody's left, disable firing.
					btnRemover.setEnabled(false);

				} else { // Select an index.
					if (index == listModel.getSize()) {
						// removed item in last position
						index--;
					}

					list.setSelectedIndex(index);
					list.ensureIndexIsVisible(index);
				}
				updateComboBox();
			}
		});

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
		textField.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				String caracteres = "!@#$%¨&*()_+-`{^}^?:><'\"\\";
				if (caracteres.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
			}
		});

		JLabel lblUm = new JLabel("\u00E9 um:");
		lblUm.setBounds(28, 64, 59, 20);
		panel_1.add(lblUm);

		comboBoxModel = new DefaultComboBoxModel<String>();
		comboBox = new JComboBox<String>(comboBoxModel);
		comboBox.setBounds(97, 64, 154, 20);
		panel_1.add(comboBox);


		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/user_blue_add2.png")));
		btnAdicionar.setBounds(28, 121, 223, 25);
		panel_1.add(btnAdicionar);
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

				String nome = textField.getText().substring(0,1).toUpperCase().concat(textField.getText().substring(1));
				String extend = comboBox.getItemAt(comboBox.getSelectedIndex());

				listModel.addElement(nome);
				
				Gerador.getInstance()
						.getGame()
						.getListaElementos()
						.add(new Elemento(nome, extend));

				textField.requestFocusInWindow();
				textField.setText("");
				btnRemover.setEnabled(true);
				updateComboBox();
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
		ArrayList<Elemento> e = Gerador.getInstance().getGame()
				.getListaElementos();
		listModel.removeAllElements();
		for (Elemento elemento : e) {
			listModel.addElement(elemento.getNome());
		}
		if(e.size()>0)
			btnRemover.setEnabled(true);
		else
			btnRemover.setEnabled(false);
		updateComboBox();
	}

	private void updateComboBox() {
		ArrayList<Elemento> e = Gerador.getInstance().getGame()
				.getListaElementos();
		String[] elementos = { "","Elemento", "Personagem", "Obstaculo" };
		comboBoxModel.removeAllElements();
		for (int i = 0; i < elementos.length; i++) {
			comboBoxModel.addElement(elementos[i]);
		}
		for (Elemento elemento : e) {
			comboBoxModel.addElement(elemento.getNome());
		}
	}

	public JTextField getTextField() {
		return textField;
	}
}
