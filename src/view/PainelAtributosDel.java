package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import model.Atributo;
import model.Elemento;
import model.Gerador;

public class PainelAtributosDel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7341909519667330946L;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JList<String> list_1;
	private DefaultListModel<String> listModel_1;
	private JPanel panel_4;
	private Elemento elemento;
	private JButton btnRemoverAtributo;

	/**
	 * Create the panel.
	 */
	public PainelAtributosDel() {
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
					btnRemoverAtributo.setEnabled(true);
				}
			}
		});

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		listModel_1 = new DefaultListModel<>();

		list_1 = new JList<String>(listModel_1);
		list_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Atributos",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_3.add(list_1, BorderLayout.EAST);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Elemento 1",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnRemoverAtributo = new JButton("Remover Atributo");
		panel_4.add(btnRemoverAtributo);
		btnRemoverAtributo.addActionListener(new ActionListener() {
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
						btnRemoverAtributo.setEnabled(false);
						
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
