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
import model.Gerador;
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
				Gerador.getInstance().loadXMLFile(frame.getFc().getSelectedFile());
				atualizaComponentes();
				atualizaTelaInicial();
			}
		}
		if(e.getSource()==frame.getMntmSalvarBase()){
			if (frame.getFc().showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
				String nomeArq = frame.getFc().getSelectedFile().getAbsolutePath();
				if (!nomeArq.endsWith(".uml"))   
					nomeArq += ".uml"; 
				Gerador.getInstance().generateXMLFile(new File(nomeArq));
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
			Gerador.getInstance().generateXMLFile(null);
			new Thread(Gerador.getInstance()).start();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==frame.getChckbxmntmMouse()){
			if (frame.getChckbxmntmMouse().isSelected()) {
				Gerador.getInstance().getGame().setInteracoes_mouse(true);
				frame.getPanel().getLblMouse().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/confirm.png")));
			} else {
				Gerador.getInstance().getGame().setInteracoes_mouse(false);
				frame.getPanel().getLblMouse().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/excluir.png")));
			}
		}
		if(e.getSource()==frame.getChckbxmntmTeclado()){
			if (frame.getChckbxmntmTeclado().isSelected()) {
				Gerador.getInstance().getGame().setInteracoes_teclado(true);
				frame.getPanel().getLblTeclado().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/confirm.png")));
			} else {
				Gerador.getInstance().getGame().setInteracoes_teclado(false);
				frame.getPanel().getLblTeclado().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/excluir.png")));
			}
		}
		if(e.getSource()==frame.getChckbxmntmColiso()){
			if (frame.getChckbxmntmColiso().isSelected()) {
				Gerador.getInstance().getGame().setAcoes_colisao(true);
				frame.getPanel().getLblColiso().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/confirm.png")));
			} else {
				Gerador.getInstance().getGame().setAcoes_colisao(false);
				frame.getPanel().getLblColiso().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/excluir.png")));
			}
		}
		if(e.getSource()==frame.getChckbxmntmMovimento()){
			if (frame.getChckbxmntmMovimento().isSelected()) {
				Gerador.getInstance().getGame().setAcoes_movimento(true);
				frame.getPanel().getLblMovimento().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/confirm.png")));
			} else {
				Gerador.getInstance().getGame().setAcoes_movimento(false);
				frame.getPanel().getLblMovimento().setIcon(new ImageIcon(getClass().getClassLoader()
						.getResource("images/excluir.png")));
			}
		}
	}

	public void atualizaComponentes(){
		frame.getChckbxmntmColiso().setSelected(Gerador.getInstance().getGame().isAcoes_colisao());
		frame.getChckbxmntmMovimento().setSelected(Gerador.getInstance().getGame().isAcoes_movimento());
		frame.getChckbxmntmMouse().setSelected(Gerador.getInstance().getGame().isInteracoes_mouse());
		frame.getChckbxmntmTeclado().setSelected(Gerador.getInstance().getGame().isInteracoes_teclado());
	}
	
	public void atualizaTelaInicial(){
		ImageIcon excluir = new ImageIcon(getClass().getClassLoader()
				.getResource("images/excluir.png"));
		ImageIcon confirm = new ImageIcon(getClass().getClassLoader()
				.getResource("images/confirm.png"));
		
		frame.getPanel().getLblMovimento().setIcon(Gerador.getInstance().getGame().isAcoes_movimento()?confirm:excluir);
		frame.getPanel().getLblColiso().setIcon(Gerador.getInstance().getGame().isAcoes_colisao()?confirm:excluir);
		frame.getPanel().getLblMouse().setIcon(Gerador.getInstance().getGame().isInteracoes_mouse()?confirm:excluir);
		frame.getPanel().getLblTeclado().setIcon(Gerador.getInstance().getGame().isInteracoes_teclado()?confirm:excluir);
		
		ArrayList<Elemento> e = Gerador.getInstance().getGame().getListaElementos();
		ArrayList<Cenario> c = Gerador.getInstance().getGame().getListaCenarios();
		
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
