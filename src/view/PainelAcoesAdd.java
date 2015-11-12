package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class PainelAcoesAdd extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public PainelAcoesAdd() {
		setLayout(new BorderLayout(0, 0));

		setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(10, 100));

		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"elemento 1", "elemento 2"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		panel.add(list, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("TitledBorder.border"));
		add(panel_1, BorderLayout.CENTER);
								panel_1.setLayout(new BorderLayout(0, 0));
						
						JPanel panel_2 = new JPanel();
						panel_1.add(panel_2, BorderLayout.NORTH);
						panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						
						JButton btnSelecionarElemento = new JButton("Selecionar Elemento");
						panel_2.add(btnSelecionarElemento);
						
						JPanel panel_3 = new JPanel();
						panel_1.add(panel_3, BorderLayout.CENTER);
						panel_3.setLayout(new BorderLayout(0, 0));
						
						JList<String> list_1 = new JList<String>();
						list_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "A\u00E7\u00F5es", TitledBorder.CENTER, TitledBorder.TOP, null, null));
						list_1.setModel(new AbstractListModel<String>() {
							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
							String[] values = new String[] {"hp - integer - 90", "atributo 2"};
							public int getSize() {
								return values.length;
							}
							public String getElementAt(int index) {
								return values[index];
							}
						});
						panel_3.add(list_1, BorderLayout.EAST);
						
						JPanel panel_4 = new JPanel();
						panel_4.setBorder(new TitledBorder(null, "Elemento 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						panel_3.add(panel_4, BorderLayout.CENTER);
						GridBagLayout gbl_panel_4 = new GridBagLayout();
						gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
						gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
						gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
						gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
						panel_4.setLayout(gbl_panel_4);
						
						JLabel lblNome = new JLabel("nome:");
						GridBagConstraints gbc_lblNome = new GridBagConstraints();
						gbc_lblNome.insets = new Insets(0, 0, 5, 5);
						gbc_lblNome.anchor = GridBagConstraints.EAST;
						gbc_lblNome.gridx = 1;
						gbc_lblNome.gridy = 1;
						panel_4.add(lblNome, gbc_lblNome);
						
						JComboBox<String> comboBox = new JComboBox<String>();
						comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Movimento", "Colis\u00E3o", "Falar"}));
						GridBagConstraints gbc_comboBox = new GridBagConstraints();
						gbc_comboBox.gridwidth = 2;
						gbc_comboBox.insets = new Insets(0, 0, 5, 5);
						gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
						gbc_comboBox.gridx = 2;
						gbc_comboBox.gridy = 1;
						panel_4.add(comboBox, gbc_comboBox);
						
						JButton btnAdicionarAo = new JButton("Adicionar A\u00E7\u00E3o");
						GridBagConstraints gbc_btnAdicionarAo = new GridBagConstraints();
						gbc_btnAdicionarAo.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnAdicionarAo.gridwidth = 3;
						gbc_btnAdicionarAo.insets = new Insets(0, 0, 5, 5);
						gbc_btnAdicionarAo.gridx = 1;
						gbc_btnAdicionarAo.gridy = 4;
						panel_4.add(btnAdicionarAo, gbc_btnAdicionarAo);

		
	}
	
}
