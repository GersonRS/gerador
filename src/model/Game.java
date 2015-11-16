package model;

import java.util.ArrayList;

public class Game {

	private ArrayList<Elemento> listaElementos;
	private ArrayList<Cenario> listaCenarios;
	private boolean interacoes_mouse;
	private boolean interacoes_teclado;
	private boolean acoes_movimento;
	private boolean acoes_colisao;

	public Game() {
		listaElementos = new ArrayList<Elemento>();
		listaCenarios = new ArrayList<Cenario>();
	}

	public Elemento getElementoPorNome(String nome) {
		for (Elemento elemento : listaElementos) {
			if (elemento.getNome().equalsIgnoreCase(nome))
				return elemento;
		}
		return null;
	}

	public Cenario getCenarioPorNome(String nome) {
		for (Cenario cenario : listaCenarios) {
			if (cenario.getNome().equalsIgnoreCase(nome))
				return cenario;
		}
		return null;
	}

	public ArrayList<Elemento> getListaElementos() {
		return listaElementos;
	}

	public ArrayList<Cenario> getListaCenarios() {
		return listaCenarios;
	}

	public boolean isInteracoes_mouse() {
		return interacoes_mouse;
	}

	public void setInteracoes_mouse(boolean interacoes_mouse) {
		this.interacoes_mouse = interacoes_mouse;
	}

	public boolean isInteracoes_teclado() {
		return interacoes_teclado;
	}

	public void setInteracoes_teclado(boolean interacoes_teclado) {
		this.interacoes_teclado = interacoes_teclado;
	}

	public boolean isAcoes_movimento() {
		return acoes_movimento;
	}

	public void setAcoes_movimento(boolean acoes_movimento) {
		this.acoes_movimento = acoes_movimento;
	}

	public boolean isAcoes_colisao() {
		return acoes_colisao;
	}

	public void setAcoes_colisao(boolean acoes_colisao) {
		this.acoes_colisao = acoes_colisao;
	}

	public void setListaElementos(ArrayList<Elemento> listaElementos) {
		this.listaElementos = listaElementos;
	}

	public void setListaCenarios(ArrayList<Cenario> listaCenarios) {
		this.listaCenarios = listaCenarios;
	}

}
