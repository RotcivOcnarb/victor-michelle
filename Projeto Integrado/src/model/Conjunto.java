package model;

public class Conjunto {
	
	private int ocupado;
	private int id;
	private String cnpj;
	
	public Conjunto(int id, int ocupado, String cnpj) {
		super();
		this.ocupado = ocupado;
		this.id = id;
	}

	public int isOcupado() {
		return ocupado;
	}

	public void setOcupado(int ocupado) {
		this.ocupado = ocupado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
