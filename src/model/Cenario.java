package model;

import java.util.HashMap;

public class Cenario {

	private String nome;
	private HashMap<String, String> layers;
	private HashMap<String, String> teleporte;
	
	public Cenario(String nome) {
		this.nome = nome;
		this.layers = new HashMap<String, String>();
		this.teleporte = new HashMap<String, String>();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public HashMap<String, String> getLayers() {
		return layers;
	}
	public void setLayers(HashMap<String, String> layers) {
		this.layers = layers;
	}
	public HashMap<String, String> getTeleporte() {
		return teleporte;
	}
	public void setTeleporte(HashMap<String, String> teleporte) {
		this.teleporte = teleporte;
	}
}