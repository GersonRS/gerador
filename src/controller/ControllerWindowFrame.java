package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import model.Cenario;
import model.Elemento;
import model.Facada;
import view.WindowFrame;

public class ControllerWindowFrame implements ActionListener, ItemListener {

	private static ControllerWindowFrame instance = null;
	private WindowFrame frame;

	public static synchronized ControllerWindowFrame getInstance(WindowFrame frame) {
		if (instance == null)
			instance = new ControllerWindowFrame(frame);
		return instance;
	}

	private ControllerWindowFrame(WindowFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==frame.getMntmInicio()){
			CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
			layout.show(frame.getContentPane(), "inicio");
			atualizaTelaInicial();
		}
		if(e.getSource()==frame.getMntmCarregarBase()){
			if (frame.getFc().showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
				Facada.getInstance().loadXMLFile(frame.getFc().getSelectedFile());
				atualizaComponentes();
				atualizaTelaInicial();
			}
		}
		if(e.getSource()==frame.getMntmSalvarBase()){
			if (frame.getFc().showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
				String nomeArq = frame.getFc().getSelectedFile().getAbsolutePath();
				if (!nomeArq.endsWith(".uml"))   
					nomeArq += ".uml"; 
				Facada.getInstance().generateXMLFile(new File(nomeArq));
			}
		}
		if(e.getSource()==frame.getMntmSair()){
			System.exit(0);
		}
		if(e.getSource()==frame.getMntmGerenciar_Elementos()){
			CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
			layout.show(frame.getContentPane(), "elementos");
			ControllerPainelElementos.getInstance(frame.getPanel_1()).updateList();
		}
		if(e.getSource()==frame.getMntmGerenciar_Atributos()){
			CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
			layout.show(frame.getContentPane(), "atributos");
			ControllerPainelAtributos.getInstance(frame.getPanel_2()).updateList();
		}
		if(e.getSource()==frame.getMntmGerenciar_Cenarios()){
			CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
			layout.show(frame.getContentPane(), "cenarios");
			ControllerPainelCenario.getInstance(frame.getPanel_3()).updateList();
		}
		if(e.getSource()==frame.getMntmGerarCodigo()){
			if (frame.getGeneration().showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
				Facada.getInstance().gerarCodigo(frame.getGeneration().getSelectedFile());
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==frame.getChckbxmntmMouse()){
			if (frame.getChckbxmntmMouse().isSelected()) {
				Facada.getInstance().setInteracoes_mouse(true);
				frame.getPanel().getLblMouse().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/confirm.png")));
			} else {
				Facada.getInstance().setInteracoes_mouse(false);
				frame.getPanel().getLblMouse().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/excluir.png")));
			}
		}
		if(e.getSource()==frame.getChckbxmntmTeclado()){
			if (frame.getChckbxmntmTeclado().isSelected()) {
				Facada.getInstance().setInteracoes_teclado(true);
				frame.getPanel().getLblTeclado().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/confirm.png")));
			} else {
				Facada.getInstance().setInteracoes_teclado(false);
				frame.getPanel().getLblTeclado().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/excluir.png")));
			}
		}
		if(e.getSource()==frame.getChckbxmntmColiso()){
			if (frame.getChckbxmntmColiso().isSelected()) {
				Facada.getInstance().setAcoes_colisao(true);
				frame.getPanel().getLblColiso().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/confirm.png")));
			} else {
				Facada.getInstance().setAcoes_colisao(false);
				frame.getPanel().getLblColiso().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/excluir.png")));
			}
		}
		if(e.getSource()==frame.getChckbxmntmMovimento()){
			if (frame.getChckbxmntmMovimento().isSelected()) {
				Facada.getInstance().setAcoes_movimento(true);
				frame.getPanel().getLblMovimento().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/confirm.png")));
			} else {
				Facada.getInstance().setAcoes_movimento(false);
				frame.getPanel().getLblMovimento().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/excluir.png")));
			}
		}
	}

	public void atualizaComponentes(){
		frame.getChckbxmntmColiso().setSelected(Facada.getInstance().isAcoes_colisao());
		frame.getChckbxmntmMovimento().setSelected(Facada.getInstance().isAcoes_movimento());
		frame.getChckbxmntmMouse().setSelected(Facada.getInstance().isInteracoes_mouse());
		frame.getChckbxmntmTeclado().setSelected(Facada.getInstance().isInteracoes_teclado());
	}
	
	public void atualizaTelaInicial(){
		ImageIcon excluir = new ImageIcon(getClass().getClassLoader()
				.getResource("images/excluir.png"));
		ImageIcon confirm = new ImageIcon(getClass().getClassLoader()
				.getResource("images/confirm.png"));
		
		frame.getPanel().getLblMovimento().setIcon(Facada.getInstance().isAcoes_movimento()?confirm:excluir);
		frame.getPanel().getLblColiso().setIcon(Facada.getInstance().isAcoes_colisao()?confirm:excluir);
		frame.getPanel().getLblMouse().setIcon(Facada.getInstance().isInteracoes_mouse()?confirm:excluir);
		frame.getPanel().getLblTeclado().setIcon(Facada.getInstance().isInteracoes_teclado()?confirm:excluir);
		
		ArrayList<Elemento> e = Facada.getInstance().getListaElementos();
		ArrayList<Cenario> c = Facada.getInstance().getListaCenarios();
		
		frame.getPanel().getListModel().removeAllElements();
		frame.getPanel().getListModel_1().removeAllElements();
		for (Elemento elemento : e) {
			frame.getPanel().getListModel().addElement(elemento.getNome());
		}
		for (Cenario cenario : c) {
			frame.getPanel().getListModel_1().addElement(cenario.getNome());
		}
	}
}
