package model;

public class ControleAr {
	private int conjunto,temperaturaAmbiente,temperaturaMaxima;
	private String cnpj;
	private boolean liga;
	
	public ControleAr(int conjunto, int temperaturaAmbiente, int temperaturaMaxima, String cnpj, boolean liga) {
		this.conjunto = conjunto;
		this.temperaturaAmbiente = temperaturaAmbiente;
		this.temperaturaMaxima = temperaturaMaxima;
		this.cnpj = cnpj;
		this.liga = liga;
	}
	public ControleAr(){
		
	}
	public int getConjunto() {
		return conjunto;
	}
	public int getTemperaturaAmbiente() {
		return temperaturaAmbiente;
	}
	public int getTemperaturaMaxima() {
		return temperaturaMaxima;
	}
	public String getCnpj() {
		return cnpj;
	}
	public boolean isLiga() {
		return liga;
	}
	public void setConjunto(int conjunto) {
		this.conjunto = conjunto;
	}
	public void setTemperaturaAmbiente(int temperaturaAmbiente) {
		this.temperaturaAmbiente = temperaturaAmbiente;
	}
	public void setTemperaturaMaxima(int temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setLiga(boolean liga) {
		this.liga = liga;
	}

}
