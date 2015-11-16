package model;

import java.io.File;
import java.util.ArrayList;

public class Facada{
	private static Facada instance = null;
	private Game game;
	private Gerador gerador;

	private Facada() {
		game = new Game();
		gerador = new Gerador(game);
	}

	public static synchronized Facada getInstance() {
		if (instance == null)
			instance = new Facada();
		return instance;
	}

	public void generateXMLFile(File f) {
		gerador.generateXMLFile(f);
	}

	public void gerarCodigo(File f) {
		gerador.gerarCodigo(f);
	}

	public void loadXMLFile(File f) {
		gerador.loadXMLFile(f);
	}
	
	public ArrayList<Elemento> getListaElementos() {
		return game.getListaElementos();
	}
	public ArrayList<Cenario> getListaCenarios() {
		return game.getListaCenarios();
	}

	public Elemento getElementoPorNome(String nome) {
		for (Elemento elemento : game.getListaElementos()) {
			if (elemento.getNome().equalsIgnoreCase(nome))
				return elemento;
		}
		return null;
	}
	
	public Cenario getCenarioPorNome(String nome) {
		for (Cenario cenario : game.getListaCenarios()) {
			if (cenario.getNome().equalsIgnoreCase(nome))
				return cenario;
		}
		return null;
	}

	public boolean isAcoes_colisao() {
		return game.isAcoes_colisao();
	}

	public boolean isAcoes_movimento() {
		return game.isAcoes_movimento();
	}

	public boolean isInteracoes_mouse() {
		return game.isInteracoes_mouse();
	}

	public boolean isInteracoes_teclado() {
		return game.isInteracoes_teclado();
	}

	public void setInteracoes_mouse(boolean b) {
		game.setInteracoes_mouse(b);
	}

	public void setInteracoes_teclado(boolean b) {
		game.setInteracoes_teclado(b);
	}

	public void setAcoes_colisao(boolean b) {
		game.setAcoes_colisao(b);
	}

	public void setAcoes_movimento(boolean b) {
		game.setAcoes_movimento(b);
	}

	public void addElemento(Elemento elemento) {
		game.getListaElementos().add(elemento);
	}
}
