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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Atributo;
import model.Elemento;
import model.Gerador;

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
	private JButton btnRemover,btnAdicionarAtributo;

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
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = list.getSelectedIndex();
				if (index < 0) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				elemento = Gerador.getInstance().getGame()
						.getElementoPorNome(listModel.getElementAt(index));
				panel_3.setBorder(new TitledBorder(null, elemento.getNome(),
						TitledBorder.CENTER, TitledBorder.TOP, null, null));
				ArrayList<Atributo> a = elemento.getAtributos();
				listModel_1.removeAllElements();
				for (Atributo atributo : a) {
					listModel_1.addElement(atributo.getNome());
				}
				btnAdicionarAtributo.setEnabled(true);
				if(a.size()>0)
					btnRemover.setEnabled(true);
				else
					btnRemover.setEnabled(false);
			}
		});
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
		textField.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				String caracteres = "!@#$%¨&*()_+-`{^}^?:><'\"\\";
				if (caracteres.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
			}
		});

		JLabel lblTipo = new JLabel("tipo:");
		lblTipo.setBounds(10, 59, 37, 20);
		panel_4.add(lblTipo);

		comboBoxModel = new DefaultComboBoxModel<String>();
		comboBox = new JComboBox<String>(comboBoxModel);
		comboBox.setBounds(57, 59, 124, 20);
		panel_4.add(comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if (index < 0) {
					return;
				}
				textField_2.setEnabled(true);
				textField_2.setVisible(true);
				comboBox_2.setVisible(false);
				for (int i = 0; i < textField_2.getKeyListeners().length; i++) {
					textField_2.removeKeyListener(textField_2.getKeyListeners()[i]);
				}
				String select = comboBox.getItemAt(comboBox.getSelectedIndex());
				if(select.equalsIgnoreCase("String")){
					
				}else if(select.equalsIgnoreCase("Integer")){
					textField_2.addKeyListener(new java.awt.event.KeyAdapter() {
						@Override
						public void keyTyped(java.awt.event.KeyEvent evt) {
							String caracteres = "0987654321";
							if (!caracteres.contains(evt.getKeyChar() + "")) {
								evt.consume();
							}
						}
					});
				}else if(select.equalsIgnoreCase("Boolean")){
					textField_2.setVisible(false);
					textField_2.setEnabled(false);
					comboBox_2.setVisible(true);
				}else if(select.equalsIgnoreCase("Decimal")){
					textField_2.addKeyListener(new java.awt.event.KeyAdapter() {
						@Override
						public void keyTyped(java.awt.event.KeyEvent evt) {
							String caracteres = "0987654321.";
							if (!caracteres.contains(evt.getKeyChar() + "")) {
								evt.consume();
							}
						}
					});
				}else{
					textField_2.setEnabled(false);
				}
				textField_2.setText("");
			}
		});

		JLabel lblValor = new JLabel("valor:");
		lblValor.setBounds(10, 90, 37, 20);
		panel_4.add(lblValor);

		textField_2 = new JTextField();
		textField_2.setBounds(57, 90, 124, 20);
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"false", "true"}));
		comboBox_2.setBounds(57, 90, 124, 20);
		comboBox_2.setVisible(false);
		panel_4.add(textField_2);
		panel_4.add(comboBox_2);
		
		

		btnAdicionarAtributo = new JButton("Adicionar");
		btnAdicionarAtributo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/add_file.png")));
		btnAdicionarAtributo.setBounds(10, 121, 171, 23);
		panel_4.add(btnAdicionarAtributo);
		btnAdicionarAtributo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (elemento != null) {
					String name = textField.getText();

					if (name.equals("") || name==null) {
						Toolkit.getDefaultToolkit().beep();
						textField_2.requestFocusInWindow();
						textField_2.selectAll();
						textField.requestFocusInWindow();
						textField.selectAll();
						return;
					}
					
					if(alreadyInList(name)){
						int result = JOptionPane.showConfirmDialog(null, "Este atributo já existe deseja sobrepor?","alguma coisa", JOptionPane.YES_NO_OPTION);
						if(result>0){
							return;
						}
						Atributo a = elemento.getAtributoPorNome(name);
						a.setTipo(comboBox
							.getItemAt(comboBox.getSelectedIndex()));
						a.setValor(textField_2.getText());
						textField.setText("");
						textField_2.setText("");
					}else{
						listModel_1.addElement(textField.getText());
						list.setSelectedIndex(-1);
						textField.requestFocusInWindow();
						
						Atributo a = new Atributo(textField.getText(), comboBox
								.getItemAt(comboBox.getSelectedIndex()),
								textField_2.getText());
						
						elemento.getAtributos().add(a);
						
						textField.setText("");
						textField_2.setText("");
						btnRemover.setEnabled(true);
					}
				}
			}
		});

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
		list_1.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = list_1.getSelectedIndex();
				if (index < 0) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				Atributo a = elemento.getAtributoPorNome(listModel_1.getElementAt(index));
				textField.setText(a.getNome());
				comboBox.setSelectedItem(a.getTipo());
				textField_2.setText(a.getValor());
			}
		});
		

		btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/delete.png")));
		panel_5.add(btnRemover, BorderLayout.SOUTH);
		
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(elemento!=null){
					int index = list_1.getSelectedIndex();
					if (index < 0) {
						Toolkit.getDefaultToolkit().beep();
						return;
					}
					
					ArrayList<Atributo> a = elemento.getAtributos();
					for (int i = 0; i < a.size(); i++) {
						if(a.get(i).getNome().equalsIgnoreCase(listModel_1.get(index))){
							a.remove(i);
						}
					}
					listModel_1.remove(index);
					
					int size = listModel_1.getSize();
					
					if (size == 0) { // Nobody's left, disable firing.
						btnRemover.setEnabled(false);
					} else { // Select an index.
						if (index == listModel_1.getSize()) {
							// removed item in last position
							index--;
						}
						
						list_1.setSelectedIndex(index);
						list_1.ensureIndexIsVisible(index);
					}
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
		String[] elementos = { "String", "Integer", "Boolean", "Decimal" };
		ArrayList<Elemento> e = Gerador.getInstance().getGame()
				.getListaElementos();
		listModel.removeAllElements();
		listModel_1.removeAllElements();
		comboBoxModel.removeAllElements();
		for (int i = 0; i < elementos.length; i++) {
			comboBoxModel.addElement(elementos[i]);
		}
		for (Elemento elemento : e) {
			listModel.addElement(elemento.getNome());
			comboBoxModel.addElement(elemento.getNome());
		}
		panel_3.setBorder(new TitledBorder(null, "Elemento", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		elemento = null;
		btnRemover.setEnabled(false);
		btnAdicionarAtributo.setEnabled(false);
	}
}
