package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.ConjuntoDao;
import dao.EmpresaDao;



public class Empresa {
	private String cnpj,razaoSocial,horaAbertura,horaFechamento,temperaturaAr;
	private int conjunto;
	
	ArrayList<Empresa> ar = new ArrayList<Empresa> ();
	public Empresa(String cnpj ,String razao_social,String hora_abertura, String hora_fechamento , String temperatura_ar ){
	
	setCnpj(cnpj);
	setRazaoSocial(razao_social);
	setHoraAbertura(hora_abertura);
	setHoraFechamento(hora_fechamento);
	setTemperaturaAr(temperatura_ar);

	}
	
	public Empresa(){
		
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getHoraAbertura() {
		return horaAbertura;
	}

	public String getHoraFechamento() {
		return horaFechamento;
	}

	public String getTemperaturaAr() {
		return temperaturaAr;
	}

	public int getConjunto() {
		return conjunto;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setHoraAbertura(String horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public void setHoraFechamento(String horaFechamento) {
		this.horaFechamento = horaFechamento;
	}


	public void setTemperaturaAr(String temperaturaAr) {
		this.temperaturaAr = temperaturaAr;
	}

	public void setConjunto(int conjunto) {
		this.conjunto = conjunto;
	}
	



}
