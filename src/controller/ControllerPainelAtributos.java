package controller;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Atributo;
import model.Elemento;
import model.Gerador;
import view.PainelAtributos;

public class ControllerPainelAtributos extends KeyAdapter implements
		ActionListener, ListSelectionListener {

	private static ControllerPainelAtributos instance = null;
	private PainelAtributos painel;

	public static synchronized ControllerPainelAtributos getInstance(
			PainelAtributos painel) {
		if (instance == null)
			instance = new ControllerPainelAtributos(painel);
		return instance;
	}

	private ControllerPainelAtributos(PainelAtributos painel) {
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
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == painel.getList()) {
			int index = painel.getList().getSelectedIndex();
			if (index < 0) {
				return;
			}
			painel.setElemento(Gerador
					.getInstance()
					.getGame()
					.getElementoPorNome(
							painel.getListModel().getElementAt(index)));
			painel.getPanel_3().setBorder(
					new TitledBorder(null, painel.getElemento().getNome(),
							TitledBorder.CENTER, TitledBorder.TOP, null, null));
			ArrayList<Atributo> a = painel.getElemento().getAtributos();
			painel.getListModel_1().removeAllElements();
			for (Atributo atributo : a) {
				painel.getListModel_1().addElement(atributo.getNome());
			}
			painel.getBtnAdicionarAtributo().setEnabled(true);
			if (a.size() > 0)
				painel.getBtnRemover().setEnabled(true);
			else
				painel.getBtnRemover().setEnabled(false);
		}
		if(e.getSource()==painel.getList_1()){
			int index = painel.getList_1().getSelectedIndex();
			if (index < 0) {
				return;
			}
			Atributo a = painel.getElemento().getAtributoPorNome(painel.getListModel_1()
					.getElementAt(index));
			painel.getTextField().setText(a.getNome());
			painel.getComboBox().setSelectedItem(a.getTipo());
			painel.getTextField_2().setText(a.getValor());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==painel.getComboBox()){
			int index = painel.getComboBox().getSelectedIndex();
			if (index < 0) {
				return;
			}
			painel.getTextField_2().setEnabled(true);
			painel.getTextField_2().setVisible(true);
			painel.getComboBox_2().setVisible(false);
			for (int i = 0; i < painel.getTextField_2().getKeyListeners().length; i++) {
				painel.getTextField_2().removeKeyListener(painel.getTextField_2().getKeyListeners()[i]);
			}
			String select = painel.getComboBox().getItemAt(index);
			if (select.equalsIgnoreCase("String")) {

			} else if (select.equalsIgnoreCase("Integer")) {
				painel.getTextField_2().addKeyListener(new java.awt.event.KeyAdapter() {
					@Override
					public void keyTyped(java.awt.event.KeyEvent evt) {
						String caracteres = "0987654321";
						if (!caracteres.contains(evt.getKeyChar() + "")) {
							evt.consume();
						}
					}
				});
			} else if (select.equalsIgnoreCase("Boolean")) {
				painel.getTextField_2().setVisible(false);
				painel.getTextField_2().setEnabled(false);
				painel.getComboBox_2().setVisible(true);
			} else if (select.equalsIgnoreCase("Decimal")) {
				painel.getTextField_2().addKeyListener(new java.awt.event.KeyAdapter() {
					@Override
					public void keyTyped(java.awt.event.KeyEvent evt) {
						String caracteres = "0987654321.";
						if (!caracteres.contains(evt.getKeyChar() + "")) {
							evt.consume();
						}
					}
				});
			} else {
				painel.getTextField_2().setEnabled(false);
			}
			painel.getTextField_2().setText("");
		}
		if(e.getSource()==painel.getBtnAdicionarAtributo()){
			if (painel.getElemento() != null) {
				String name = painel.getTextField().getText();

				if (name.equals("") || name == null) {
					Toolkit.getDefaultToolkit().beep();
					painel.getTextField_2().requestFocusInWindow();
					painel.getTextField_2().selectAll();
					painel.getTextField().requestFocusInWindow();
					painel.getTextField().selectAll();
					return;
				}

				if (alreadyInList(name)) {
					int result = JOptionPane.showConfirmDialog(null,
							"Este atributo já existe deseja sobrepor?",
							"Atributo Existente", JOptionPane.YES_NO_OPTION);
					if (result > 0) {
						return;
					}
					Atributo a = painel.getElemento().getAtributoPorNome(name);
					String tipo = painel.getComboBox().getItemAt(painel.getComboBox()
							.getSelectedIndex());
					String valor = painel.getComboBox().getItemAt(
							painel.getComboBox().getSelectedIndex()).equalsIgnoreCase(
							"Boolean") ? painel.getComboBox_2().getItemAt(painel.getComboBox_2()
							.getSelectedIndex()) : painel.getTextField_2().getText();
					a.setTipo(tipo);
					a.setValor(valor);
					painel.getTextField().setText("");
					painel.getTextField_2().setText("");
				} else {
					painel.getListModel_1().addElement(painel.getTextField().getText());
					painel.getList().setSelectedIndex(-1);
					painel.getTextField().requestFocusInWindow();

					String nome = painel.getTextField().getText();
					String tipo = painel.getComboBox().getItemAt(painel.getComboBox()
							.getSelectedIndex());
					String valor = painel.getComboBox().getItemAt(
							painel.getComboBox().getSelectedIndex()).equalsIgnoreCase(
							"Boolean") ? painel.getComboBox_2().getItemAt(painel.getComboBox_2()
							.getSelectedIndex()) : painel.getTextField_2().getText();

					Atributo a = new Atributo(nome, tipo, valor);

					painel.getElemento().getAtributos().add(a);

					painel.getTextField().setText("");
					painel.getTextField_2().setText("");
					painel.getBtnRemover().setEnabled(true);
				}
			}
		}
		if(e.getSource()==painel.getBtnRemover()){
			if (painel.getElemento() != null) {
				int index = painel.getList_1().getSelectedIndex();
				if (index < 0) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				ArrayList<Atributo> a = painel.getElemento().getAtributos();
				for (int i = 0; i < a.size(); i++) {
					if (a.get(i).getNome()
							.equalsIgnoreCase(painel.getListModel_1().get(index))) {
						a.remove(i);
					}
				}
				painel.getListModel_1().remove(index);
				int size = painel.getListModel_1().getSize();
				if (size == 0) {
					painel.getBtnRemover().setEnabled(false);
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

	private boolean alreadyInList(String name) {
		for (int i = 0; i < painel.getListModel_1().getSize(); i++) {
			if (painel.getListModel_1().getElementAt(i).equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public void updateList() {
		String[] elementos = { "String", "Integer", "Boolean", "Decimal" };
		ArrayList<Elemento> e = Gerador.getInstance().getGame()
				.getListaElementos();
		painel.getListModel().removeAllElements();
		painel.getListModel_1().removeAllElements();
		painel.getComboBoxModel().removeAllElements();
		for (int i = 0; i < elementos.length; i++) {
			painel.getComboBoxModel().addElement(elementos[i]);
		}
		for (Elemento elemento : e) {
			painel.getListModel().addElement(elemento.getNome());
			painel.getComboBoxModel().addElement(elemento.getNome());
		}
		painel.getPanel_3().setBorder(
				new TitledBorder(null, "Elemento", TitledBorder.CENTER,
						TitledBorder.TOP, null, null));
		painel.setElemento(null);
		painel.getBtnRemover().setEnabled(false);
		painel.getBtnAdicionarAtributo().setEnabled(false);
	}
}
