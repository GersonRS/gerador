package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

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

import model.Cenario;
import controller.ControllerPainelCenario;

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
	private JButton btnAdicionarCenrio;
	private JRadioButton rdbtnLayerInferior;
	private JRadioButton rdbtnLayerSuperior;
	private ButtonGroup bg;

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
		panel_1.setBorder(new TitledBorder(null, "Cen\u00E1rios",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
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
		list_2.addListSelectionListener(ControllerPainelCenario.getInstance(this));
		scrollPane_2.setViewportView(list_2);

		btnRemover = new JButton("Deletar");
		btnRemover.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/delete.png")));
		panel_11.add(btnRemover, BorderLayout.SOUTH);
		btnRemover.addActionListener(ControllerPainelCenario.getInstance(this));

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
		textField_2.addKeyListener(ControllerPainelCenario.getInstance(this));

		btnAdicionarCenrio = new JButton("Adicionar");
		btnAdicionarCenrio.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/add_file.png")));
		panel_12.add(btnAdicionarCenrio, BorderLayout.SOUTH);
		btnAdicionarCenrio.addActionListener(ControllerPainelCenario.getInstance(this));

		panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(225, 300));
		panel.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2, BorderLayout.NORTH);
		panel_2.setPreferredSize(new Dimension(225, 130));
		panel_2.setBorder(new TitledBorder(null, "Layers", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
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
		textField.addKeyListener(ControllerPainelCenario.getInstance(this));

		bg = new ButtonGroup();

		rdbtnLayerInferior = new JRadioButton(
				"Layer Inferior");
		rdbtnLayerInferior.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(rdbtnLayerInferior);
		bg.add(rdbtnLayerInferior);

		rdbtnLayerSuperior = new JRadioButton(
				"Layer Superior");
		rdbtnLayerSuperior.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(rdbtnLayerSuperior);
		bg.add(rdbtnLayerSuperior);

		btnAdicionarLayer = new JButton("Adicionar");
		btnAdicionarLayer.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/add_file.png")));
		panel_5.add(btnAdicionarLayer, BorderLayout.SOUTH);
		btnAdicionarLayer.addActionListener(ControllerPainelCenario.getInstance(this));

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
		btnRemoverLayer.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/delete.png")));
		panel_7.add(btnRemoverLayer, BorderLayout.SOUTH);
		btnRemoverLayer.addActionListener(ControllerPainelCenario.getInstance(this));

		comboBoxModel = new DefaultComboBoxModel<String>();

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Teleport",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
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
		textField_1.addKeyListener(ControllerPainelCenario.getInstance(this));

		btnAdicionarTeleport = new JButton("Adicionar");
		btnAdicionarTeleport.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/add_file.png")));
		panel_3.add(btnAdicionarTeleport, BorderLayout.SOUTH);
		btnAdicionarTeleport.addActionListener(ControllerPainelCenario.getInstance(this));

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
		btnRemoverTeleport.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/delete.png")));
		panel_10.add(btnRemoverTeleport, BorderLayout.SOUTH);
		btnRemoverTeleport.addActionListener(ControllerPainelCenario.getInstance(this));

	}

	public JTextField getTextField() {
		return textField;
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

	public JList<String> getList_2() {
		return list_2;
	}

	public DefaultListModel<String> getListModel_2() {
		return listModel_2;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public DefaultComboBoxModel<String> getComboBoxModel() {
		return comboBoxModel;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public Cenario getCenario() {
		return cenario;
	}

	public void setCenario(Cenario cenario) {
		this.cenario = cenario;
	}

	public JPanel getPanel_6() {
		return panel_6;
	}

	public JButton getBtnAdicionarLayer() {
		return btnAdicionarLayer;
	}

	public JButton getBtnRemoverLayer() {
		return btnRemoverLayer;
	}

	public JButton getBtnAdicionarTeleport() {
		return btnAdicionarTeleport;
	}

	public JButton getBtnRemoverTeleport() {
		return btnRemoverTeleport;
	}

	public JButton getBtnRemover() {
		return btnRemover;
	}

	public JButton getBtnAdicionarCenrio() {
		return btnAdicionarCenrio;
	}

	public JRadioButton getRdbtnLayerInferior() {
		return rdbtnLayerInferior;
	}

	public JRadioButton getRdbtnLayerSuperior() {
		return rdbtnLayerSuperior;
	}

	public ButtonGroup getBg() {
		return bg;
	}

}
