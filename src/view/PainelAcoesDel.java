package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class PainelAcoesDel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public PainelAcoesDel() {
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
						panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						
						JButton btnRemoverAo = new JButton("Remover A\u00E7\u00E3o");
						panel_4.add(btnRemoverAo);

		
	}
	
}
