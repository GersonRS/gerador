package controller;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.Elemento;
import model.Facada;
import view.PainelElementos;

public class ControllerPainelElementos extends KeyAdapter implements
		ActionListener {

	private static ControllerPainelElementos instance = null;
	private PainelElementos painel;

	public static synchronized ControllerPainelElementos getInstance(
			PainelElementos painel) {
		if (instance == null)
			instance = new ControllerPainelElementos(painel);
		return instance;
	}

	public ControllerPainelElementos(PainelElementos painel) {
		this.painel = painel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String caracteres = "!@#$%¨&*()_+-`{^}^?:><'\"\\";
		if (caracteres.contains(e.getKeyChar() + "")) {
			e.consume();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == painel.getBtnAdicionar()) {
			String name = painel.getTextField().getText();

			if (name.equals("") || alreadyInList(name)) {
				Toolkit.getDefaultToolkit().beep();
				painel.getTextField().requestFocusInWindow();
				painel.getTextField().selectAll();
				return;
			}

			String nome = painel.getTextField().getText().substring(0, 1)
					.toUpperCase()
					.concat(painel.getTextField().getText().substring(1));
			String extend = painel.getComboBox().getItemAt(
					painel.getComboBox().getSelectedIndex());

			painel.getListModel().addElement(nome);

			Facada.getInstance().addElemento(new Elemento(nome, extend));

			painel.getTextField().requestFocusInWindow();
			painel.getTextField().setText("");
			painel.getBtnRemover().setEnabled(true);
			updateComboBox();
		}
		if (e.getSource() == painel.getBtnRemover()) {
			int index = painel.getList().getSelectedIndex();
			if (index < 0) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}

			ArrayList<Elemento> elementos = Facada.getInstance()
					.getListaElementos();
			for (int i = 0; i < elementos.size(); i++) {
				if (elementos.get(i).getNome()
						.equalsIgnoreCase(painel.getListModel().get(index))) {
					Facada.getInstance().getListaElementos().remove(i);
				}
			}
			painel.getListModel().remove(index);

			int size = painel.getListModel().getSize();

			if (size == 0) { // Nobody's left, disable firing.
				painel.getBtnRemover().setEnabled(false);

			} else { // Select an index.
				if (index == painel.getListModel().getSize()) {
					// removed item in last position
					index--;
				}

				painel.getList().setSelectedIndex(index);
				painel.getList().ensureIndexIsVisible(index);
			}
			updateComboBox();
		}
	}

	private boolean alreadyInList(String name) {
		for (int i = 0; i < painel.getListModel().getSize(); i++) {
			if (painel.getListModel().getElementAt(i).equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public void updateList() {
		ArrayList<Elemento> e = Facada.getInstance().getListaElementos();
		painel.getListModel().removeAllElements();
		for (Elemento elemento : e) {
			painel.getListModel().addElement(elemento.getNome());
		}
		if (e.size() > 0)
			painel.getBtnRemover().setEnabled(true);
		else
			painel.getBtnRemover().setEnabled(false);
		updateComboBox();
	}

	private void updateComboBox() {
		ArrayList<Elemento> e = Facada.getInstance().getListaElementos();
		String[] elementos = { "", "Elemento", "Personagem", "Obstaculo" };
		painel.getComboBoxModel().removeAllElements();
		for (int i = 0; i < elementos.length; i++) {
			painel.getComboBoxModel().addElement(elementos[i]);
		}
		for (Elemento elemento : e) {
			painel.getComboBoxModel().addElement(elemento.getNome());
		}
	}

}
