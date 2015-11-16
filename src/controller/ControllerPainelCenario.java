package controller;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Cenario;
import model.Facada;
import view.PainelCenarios;

public class ControllerPainelCenario extends KeyAdapter implements
		ActionListener, ListSelectionListener {

	private static ControllerPainelCenario instance = null;
	private PainelCenarios painel;

	public static synchronized ControllerPainelCenario getInstance(
			PainelCenarios painel) {
		if (instance == null)
			instance = new ControllerPainelCenario(painel);
		return instance;
	}

	private ControllerPainelCenario(PainelCenarios painel) {
		this.painel = painel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == painel.getTextField_2()) {
			String caracteres = "!@#$%¨&*()_+-`{^}^?:><'\"\\";
			if (caracteres.contains(e.getKeyChar() + "")) {
				e.consume();
			}
		}
		if (e.getSource() == painel.getTextField()) {
			String caracteres = "!@#$%¨&*()_+-`{^}^?:><'\"\\";
			if (caracteres.contains(e.getKeyChar() + "")) {
				e.consume();
			}
		}
		if (e.getSource() == painel.getTextField_1()) {
			String caracteres = "0987654321";
			if (!caracteres.contains(e.getKeyChar() + "")) {
				e.consume();
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == painel.getList_2()) {
			int index = painel.getList_2().getSelectedIndex();
			if (index < 0) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			painel.setCenario(Facada
					.getInstance()
					.getCenarioPorNome(
							painel.getListModel_2().getElementAt(index)));
			painel.getPanel_6().setBorder(
					new TitledBorder(null, painel.getCenario().getNome(),
							TitledBorder.CENTER, TitledBorder.TOP, null, null));
			HashMap<String, String> layer = painel.getCenario().getLayers();
			HashMap<String, String> teleport = painel.getCenario()
					.getTeleporte();
			painel.getListModel().removeAllElements();
			painel.getListModel_1().removeAllElements();
			for (String l : layer.keySet()) {
				painel.getListModel().addElement(l);
			}
			for (Entry<String, String> t : teleport.entrySet()) {
				painel.getListModel_1().addElement(t.getValue()+"-"+t.getKey());
			}
			if (layer.size() > 0)
				painel.getBtnRemoverLayer().setEnabled(true);
			else
				painel.getBtnRemoverLayer().setEnabled(false);

			if (teleport.size() > 0)
				painel.getBtnRemoverTeleport().setEnabled(true);
			else
				painel.getBtnRemoverTeleport().setEnabled(false);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == painel.getBtnAdicionarCenrio()) {
			String name = painel.getTextField_2().getText();

			if (name.equals("") || alreadyInList(name)) {
				Toolkit.getDefaultToolkit().beep();
				painel.getTextField_2().requestFocusInWindow();
				painel.getTextField_2().selectAll();
				return;
			}

			painel.getListModel_2().addElement(name);

			Facada.getInstance().getListaCenarios()
					.add(new Cenario(name));

			painel.getList_2().setSelectedIndex(
					painel.getListModel_2().getSize() - 1);
			painel.getTextField_2().requestFocusInWindow();
			painel.getTextField_2().setText("");
			painel.getBtnRemover().setEnabled(true);
			updateComboBox();
		}
		if (e.getSource() == painel.getBtnAdicionarLayer()) {
			String name = painel.getTextField().getText();
			String select = "";
			if (painel.getRdbtnLayerInferior().isSelected())
				select = "inferior";
			else if (painel.getRdbtnLayerSuperior().isSelected())
				select = "superior";

			if (name.equals("") || alreadyInListLayer(name)
					|| select.equalsIgnoreCase("") || select == null) {
				Toolkit.getDefaultToolkit().beep();
				painel.getTextField().requestFocusInWindow();
				painel.getTextField().selectAll();
				return;
			}

			HashMap<String, String> layer = painel.getCenario().getLayers();

			layer.put(name, select);

			painel.getListModel().addElement(name);
			painel.getBtnRemoverLayer().setEnabled(true);
			painel.getTextField().requestFocusInWindow();
			painel.getTextField().setText("");
			painel.getBg().clearSelection();

		}
		if (e.getSource() == painel.getBtnAdicionarTeleport()) {
			int index = painel.getComboBox().getSelectedIndex();
			String name = painel.getComboBox().getItemAt(index);
			String select = painel.getTextField_1().getText();

			if (index < 0 || name == null || name.equals("") || select == null
					|| select.equalsIgnoreCase("")) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			
			if (alreadyInListTeleport(select)) {
				int result = JOptionPane.showConfirmDialog(null,
						"Este Teleport já existe deseja sobrepor?",
						"Teleport Existente", JOptionPane.YES_NO_OPTION);
				if (result > 0) {
					return;
				}
				HashMap<String, String> teleport = painel.getCenario()
						.getTeleporte();
				teleport.put(select, name);
				int i = alreadyInListTeleportIndex(select);
				painel.getListModel_1().remove(i);
				painel.getListModel_1().add(i, name + "-" + select);
				painel.getComboBox().setSelectedIndex(-1);
				painel.getTextField_1().setText("");
				painel.getTextField_1().requestFocusInWindow();
			}
			else{
				HashMap<String, String> teleport = painel.getCenario()
						.getTeleporte();
				teleport.put(select, name);
				painel.getListModel_1().addElement(name + "-" + select);
				painel.getBtnRemoverTeleport().setEnabled(true);
				painel.getComboBox().setSelectedIndex(-1);
				painel.getTextField_1().setText("");
				painel.getTextField_1().requestFocusInWindow();
			}

		}
		if (e.getSource() == painel.getBtnRemover()) {
			int index = painel.getList_2().getSelectedIndex();
			if (index < 0) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}

			ArrayList<Cenario> cenarios = Facada.getInstance()
					.getListaCenarios();
			for (int i = 0; i < cenarios.size(); i++) {
				if (cenarios.get(i).getNome()
						.equalsIgnoreCase(painel.getListModel_2().get(index))) {
					Facada.getInstance().getListaCenarios()
							.remove(i);
				}
			}
			painel.getListModel_2().remove(index);
			int size = painel.getListModel_2().getSize();
			if (size == 0) {
				painel.getBtnRemover().setEnabled(false);
			} else {
				if (index == painel.getListModel_2().getSize()) {
					index--;
				}
				painel.getList_2().setSelectedIndex(index);
				painel.getList_2().ensureIndexIsVisible(index);
			}
			updateComboBox();
		}
		if(e.getSource()==painel.getBtnRemoverLayer()){
			if (painel.getCenario() != null) {
				int index = painel.getList().getSelectedIndex();
				if (index < 0) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				
				HashMap<String, String> removed = new HashMap<String, String>();
				
				HashMap<String, String> a = painel.getCenario().getLayers();
				for (Entry<String, String> iterable_element : a.entrySet()) {
					if (iterable_element.getKey().equalsIgnoreCase(painel.getListModel().get(index))) {
						removed.put(iterable_element.getKey(),iterable_element.getValue());
					}
				}
				
				for (Entry<String, String> string : removed.entrySet()) {
					a.remove(string.getKey());
				}
				
				painel.getListModel().remove(index);
				int size = painel.getListModel().getSize();
				if (size == 0) {
					painel.getBtnRemoverLayer().setEnabled(false);
				} else {
					if (index == painel.getListModel().getSize()) {
						index--;
					}
					painel.getList().setSelectedIndex(index);
					painel.getList().ensureIndexIsVisible(index);
				}
			}
		}
		if(e.getSource()==painel.getBtnRemoverTeleport()){
			if (painel.getCenario() != null) {
				int index = painel.getList_1().getSelectedIndex();
				if (index < 0) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				String tele = painel.getListModel_1().get(index);
				String ext[] = tele.split("-");
				
				HashMap<String, String> removed = new HashMap<String, String>();
				
				HashMap<String, String> a = painel.getCenario().getTeleporte();
				for (Entry<String, String> iterable_element : a.entrySet()) {
					if (iterable_element.getValue().equalsIgnoreCase(ext[0])) {
						removed.put(iterable_element.getKey(),iterable_element.getValue());
					}
				}
				
				for (Entry<String, String> string : removed.entrySet()) {
					a.remove(string.getKey());
				}
				
				painel.getListModel_1().remove(index);
				int size = painel.getListModel_1().getSize();
				if (size == 0) {
					painel.getBtnRemoverTeleport().setEnabled(false);
				} else {
					if (index == painel.getListModel_1().getSize()) {
						index--;
					}
					painel.getList_1().setSelectedIndex(index);
					painel.getList_1().ensureIndexIsVisible(index);
				}
			}
		}
		
		
		
		
	}

	public void updateList() {
		ArrayList<Cenario> c = Facada.getInstance()
				.getListaCenarios();
		painel.getListModel_2().removeAllElements();
		for (Cenario cenario : c) {
			painel.getListModel_2().addElement(cenario.getNome());
		}
		painel.getPanel_6().setBorder(
				new TitledBorder(null, "Cenario", TitledBorder.CENTER,
						TitledBorder.TOP, null, null));
		if (c.size() > 0)
			painel.getBtnRemover().setEnabled(true);
		else
			painel.getBtnRemover().setEnabled(false);
		painel.setCenario(null);
	}

	private boolean alreadyInList(String name) {
		for (int i = 0; i < painel.getListModel_2().getSize(); i++) {
			if (painel.getListModel_2().getElementAt(i).equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	private boolean alreadyInListLayer(String name) {
		for (int i = 0; i < painel.getListModel().getSize(); i++) {
			if (painel.getListModel().getElementAt(i).equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean alreadyInListTeleport(String name) {
		for (int i = 0; i < painel.getListModel_1().getSize(); i++) {
			String tele = painel.getListModel_1().getElementAt(i);
			String ext[] = tele.split("-");
			if (ext[1].equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	private int alreadyInListTeleportIndex(String name) {
		for (int i = 0; i < painel.getListModel_1().getSize(); i++) {
			String tele = painel.getListModel_1().getElementAt(i);
			String ext[] = tele.split("-");
			if (ext[1].equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	private void updateComboBox() {
		ArrayList<Cenario> e = Facada.getInstance()
				.getListaCenarios();
		painel.getComboBoxModel().removeAllElements();
		for (Cenario cenario : e) {
			painel.getComboBoxModel().addElement(cenario.getNome());
		}
		painel.getComboBox().setSelectedIndex(-1);
	}
}
