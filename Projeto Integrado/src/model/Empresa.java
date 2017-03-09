package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.ConjuntoDao;
import dao.EmpresaDao;



public class Empresa {
	private String cnpj,razaoSocial,horaAbertura,horaFechamento,temperaturaAr;
	private int conjunto;
	private int numero;
	ArrayList<Empresa> ar = new ArrayList<Empresa> ();
	public Empresa(String a ,String b,String c, String d , String e ){
	
	setCnpj(a);
	setRazaoSocial(b);
	setHoraAbertura(c);
	setHoraFechamento(d);
	setTemperaturaAr(e);
	
	
	
	}
	public Empresa(){
		setNumero();
		consulta();
	}
	
public void setNumero(){
	EmpresaDao ed = new EmpresaDao();
	ed.getNumeroEmpresa();
	numero = ed.getNumero();
}
public int getNumero(){
	return numero;
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
public ArrayList<Empresa> getArray(){
	return ar;
}




	public boolean cadastra(){
		if(cnpj.length() == 14 && razaoSocial.length() >0 && horaAbertura.length() == 5 &&
				horaFechamento.length() == 5 && temperaturaAr.length() == 2){
			EmpresaDao ed = new EmpresaDao(getCnpj(),getRazaoSocial(),getHoraAbertura(),getHoraFechamento(),getTemperaturaAr());
			ed.cadastra();
			return true;
			
			
		}else 
		
		return false;
		
		
	}
	public void consulta(){
		boolean a = true;	
		int x =1;
		int aux=1;
		while(a){
		
			EmpresaDao con = new EmpresaDao();
			con.consulta(x);
			Empresa cont = new Empresa(con.getCnpj(),con.getRazaoSocial(),con.getHoraAbertura(),con.getHoraFechamento(),con.getTemperaturaAr());
			
			if(cont.getCnpj() !=null ){
			ar.add(cont);
			aux++;
			//System.out.println("x   "+x);
			//System.out.println("aux   "+aux);
			
			}else if(aux>numero){
				a=false;
			}
			x++;
		}
		
			/*for(int i=1;i<=numero;i++){//numero de conjuntos qe tem
				EmpresaDao con = new EmpresaDao(i);
				Empresa cont  = new Empresa(con.getCnpj(),con.getRazaoSocial(),con.getHoraAbertura(),con.getHoraFechamento(),con.getTemperaturaAr());
				ar.add(cont);
				}*/
				

			}
			
	
	
	public boolean exclui(String c){
		
		EmpresaDao ed = new EmpresaDao();
		ConjuntoDao cd = new ConjuntoDao();
		try{
		cd.alteraConjunto(c);
		ed.exclui(c);
		return true;
		
		}catch(Exception e){
			return false;
		}
		}
	public boolean altera(){
		if(cnpj.length() == 14 && razaoSocial.length() >0 && horaAbertura.length() == 5 &&
				horaFechamento.length() == 5 && temperaturaAr.length() == 2){
			EmpresaDao ed = new EmpresaDao(getCnpj(),getRazaoSocial(),getHoraAbertura(),getHoraFechamento(),getTemperaturaAr());
			ed.altera();
			return true;
			
			
		}else JOptionPane.showMessageDialog(null, "mesangem generica de erro");
		
		return false;
	
		
	}

}
