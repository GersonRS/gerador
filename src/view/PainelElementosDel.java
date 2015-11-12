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

import model.Elemento;
import model.Gerador;

public class PainelElementosDel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7341909519667330946L;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JButton btnRemover;

	/**
	 * Create the panel.
	 */
	public PainelElementosDel() {
		setLayout(new BorderLayout(0, 0));

		setBorder(BorderFactory.createTitledBorder("Elementos"));

		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		listModel = new DefaultListModel<>();

		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnRemover = new JButton("Remover");
		panel_1.add(btnRemover);
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = list.getSelectedIndex();
				if (index < 0) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				
				ArrayList<Elemento> e = Gerador.getInstance().getListaElementos();
				for (int i = 0; i < e.size(); i++) {
					if(e.get(i).getNome().equalsIgnoreCase(listModel.get(index))){
						Gerador.getInstance().getListaElementos().remove(i);
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
			}
		});

	}

	public void updateList() {
		ArrayList<Elemento> e = Gerador.getInstance().getListaElementos();
		 listModel.removeAllElements();
		for (Elemento elemento : e) {
			listModel.addElement(elemento.getNome());
			btnRemover.setEnabled(true);
		}
	}

}
