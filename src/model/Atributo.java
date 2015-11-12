package model;

public class Atributo {

	private String nome;
	private String tipo;
	private String valor;

	public Atributo(String nome, String tipo, String valor) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
