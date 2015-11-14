package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Cenario;
import model.Gerador;

public class PainelCenarios extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JList<String> list_1;
	private DefaultListModel<String> listModel_1;
	private JList<String> list_2;
	private DefaultListModel<String> listModel_2;
	private JComboBox<String> comboBox;
	private DefaultComboBoxModel<String> comboBoxModel;
	private JTextField textField_1;
	private JTextField textField_2;
	private Cenario cenario;
	private JPanel panel_6;
	private JButton btnAdicionarLayer;
	private JButton btnRemoverLayer;
	private JButton btnAdicionarTeleport;
	private JButton btnRemoverTeleport;
	private JButton btnRemover;

	/**
	 * Create the panel.
	 */
	public PainelCenarios() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(450, 200));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Cen\u00E1rios", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(110, 350));
		panel_1.add(panel_11, BorderLayout.WEST);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_11.add(scrollPane_2, BorderLayout.CENTER);
		
		listModel_2 = new DefaultListModel<String>();
		list_2 = new JList<String>(listModel_2);
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_2.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = list_2.getSelectedIndex();
				if (index < 0) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				cenario = Gerador.getInstance().getGame()
						.getCenarioPorNome(listModel_2.getElementAt(index));
				panel_6.setBorder(new TitledBorder(null, cenario.getNome(), TitledBorder.CENTER, TitledBorder.TOP, null, null));
				HashMap<String, String> layer = cenario.getLayers();
				HashMap<String, String> teleport = cenario.getTeleporte();
				listModel.removeAllElements();
				listModel_1.removeAllElements();
				for (String l : layer.keySet()) {
					listModel.addElement(l);
				}
				for (String t : teleport.keySet()) {
					listModel_1.addElement(t);
				}
				if(layer.size()>0)
					btnRemoverLayer.setEnabled(true);
				else
					btnRemoverLayer.setEnabled(false);
					
				if(teleport.size()>0)
					btnRemoverTeleport.setEnabled(true);
				else
					btnRemoverTeleport.setEnabled(false);
				
				
			}
		});
		scrollPane_2.setViewportView(list_2);
		
		btnRemover = new JButton("Deletar");
		btnRemover.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/delete.png")));
		panel_11.add(btnRemover, BorderLayout.SOUTH);
		
		JPanel panel_12 = new JPanel();
		panel_1.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_12.add(panel_13, BorderLayout.CENTER);
		
		JLabel lblNomeDoCenrio = new JLabel("Nome do Cen\u00E1rio:");
		panel_13.add(lblNomeDoCenrio);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_13.add(textField_2);
		textField_2.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				String caracteres = "!@#$%¨&*()_+-`{^}^?:><'\"\\";
				if (caracteres.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
			}
		});
		
		JButton btnAdicionarCenrio = new JButton("Adicionar");
		btnAdicionarCenrio.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/add_file.png")));
		panel_12.add(btnAdicionarCenrio, BorderLayout.SOUTH);
		btnAdicionarCenrio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = textField_2.getText();

				if (name.equals("") || alreadyInList(name)) {
					Toolkit.getDefaultToolkit().beep();
					textField_2.requestFocusInWindow();
					textField_2.selectAll();
					return;
				}

				listModel_2.addElement(name);
				
				Gerador.getInstance()
						.getGame()
						.getListaCenarios()
						.add(new Cenario(name));

				list_2.setSelectedIndex(listModel_2.getSize()-1);
				textField_2.requestFocusInWindow();
				textField_2.setText("");
				btnRemover.setEnabled(true);
				updateComboBox();
			}
		});
		
		panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(225,300));
		panel.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2, BorderLayout.NORTH);
		panel_2.setPreferredSize(new Dimension(225, 130));
		panel_2.setBorder(new TitledBorder(null, "Layers", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.WEST);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblNomeLayer = new JLabel("Nome Layer:");
		lblNomeLayer.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNomeLayer);
		
		textField = new JTextField();
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
		
		final ButtonGroup bg = new ButtonGroup();
		
		final JRadioButton rdbtnLayerInferior = new JRadioButton("Layer Inferior");
		rdbtnLayerInferior.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(rdbtnLayerInferior);
		bg.add(rdbtnLayerInferior);
		
		final JRadioButton rdbtnLayerSuperior = new JRadioButton("Layer Superior");
		rdbtnLayerSuperior.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(rdbtnLayerSuperior);
		bg.add(rdbtnLayerSuperior);
		
		btnAdicionarLayer = new JButton("Adicionar");
		btnAdicionarLayer.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/add_file.png")));
		panel_5.add(btnAdicionarLayer, BorderLayout.SOUTH);
		btnAdicionarLayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String select="";
				if(rdbtnLayerInferior.isSelected())
					select = "inferior";
				else if(rdbtnLayerSuperior.isSelected())
					select = "superior";
				
				if (name.equals("") || alreadyInListLayer(name) || select.equalsIgnoreCase("") || select==null) {
					Toolkit.getDefaultToolkit().beep();
					textField.requestFocusInWindow();
					textField.selectAll();
					return;
				}
				
				HashMap<String, String> layer = cenario.getLayers();
					
				layer.put(name, select);
				
				listModel.addElement(name);
				btnRemoverLayer.setEnabled(true);
				textField.requestFocusInWindow();
				textField.setText("");
				bg.clearSelection();
				
			}
		});
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_7.add(scrollPane);
		listModel = new DefaultListModel<>();
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		btnRemoverLayer = new JButton("Deletar");
		btnRemoverLayer.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/delete.png")));
		panel_7.add(btnRemoverLayer, BorderLayout.SOUTH);
		
		comboBoxModel = new DefaultComboBoxModel<String>();
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Teleport", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_9.setPreferredSize(new Dimension(225, 150));
		panel_6.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel_3 = new JPanel();
		panel_9.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblDestino);
		
		comboBox = new JComboBox<String>();
		panel_8.add(comboBox);
		comboBox.setModel(comboBoxModel);
		
		JLabel lblLocal = new JLabel("Local:");
		lblLocal.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblLocal);
		
		textField_1 = new JTextField();
		panel_8.add(textField_1);
		textField_1.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				String caracteres = "0987654321";
				if (!caracteres.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
			}
		});
		
		btnAdicionarTeleport = new JButton("Adicionar");
		btnAdicionarTeleport.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/add_file.png")));
		panel_3.add(btnAdicionarTeleport, BorderLayout.SOUTH);
		btnAdicionarTeleport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				String name = comboBox.getItemAt(index);
				String select = textField_1.getText();
				
				if (index < 0 || name == null || name.equals("") || select==null || select.equalsIgnoreCase("")) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				
				HashMap<String, String> teleport = cenario.getTeleporte();
					
				teleport.put(select, name);
				
				listModel_1.addElement(name+"-"+select);
				btnRemoverTeleport.setEnabled(true);
				comboBox.requestFocusInWindow();
				comboBox.setSelectedIndex(-1);
			}
		});

		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_10.add(scrollPane_1);
		listModel_1 = new DefaultListModel<String>();
		list_1 = new JList<String>(listModel_1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(list_1);
		
		btnRemoverTeleport = new JButton("Deletar");
		btnRemoverTeleport.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/delete.png")));
		panel_10.add(btnRemoverTeleport, BorderLayout.SOUTH);

	}

	public void updateList() {
		ArrayList<Cenario> c = Gerador.getInstance().getGame().getListaCenarios();
		listModel_2.removeAllElements();
		for (Cenario cenario : c) {
			listModel_2.addElement(cenario.getNome());
		}
		panel_6.setBorder(new TitledBorder(null, "Cenario", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		if(c.size()>0)
			btnRemover.setEnabled(true);
		else
			btnRemover.setEnabled(false);	
		cenario = null;
	}
	
	private boolean alreadyInList(String name) {
		for (int i = 0; i < listModel.getSize(); i++) {
			if (listModel.getElementAt(i).equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean alreadyInListLayer(String name) {
		for (int i = 0; i < listModel.getSize(); i++) {
			if (listModel.getElementAt(i).equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	private void updateComboBox() {
		ArrayList<Cenario> e = Gerador.getInstance().getGame().getListaCenarios();
		comboBoxModel.removeAllElements();
		for (Cenario cenario : e) {
			comboBoxModel.addElement(cenario.getNome());
		}
		comboBox.setSelectedIndex(-1);
	}
}
