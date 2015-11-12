package model;

import java.util.ArrayList;

public class Elemento {

	private String nome;
	private String extend;
	private ArrayList<Atributo> atributos;

	public Elemento(String nome, String extend) {
		this.nome = nome;
		this.extend = extend;
		this.atributos = new ArrayList<Atributo>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public ArrayList<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(ArrayList<Atributo> atributos) {
		this.atributos = atributos;
	}

}
