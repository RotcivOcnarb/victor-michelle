package model;

import dao.ConjuntoDao;
import dao.ControleArDao;
import java.util.*;

public class ControleAr {
	private int conjunto,temperaturaAmbiente,temperaturaMaxima;
	private String cnpj;
	private boolean liga;
	private ArrayList<ControleAr> aa = new ArrayList<ControleAr>();
	private int [] temperaturaAtual;
	private boolean [] conjuntosOcupados;
	private Formatter output;
	
	public ControleAr(int conjunto, int temperaturaAmbiente, int temperaturaMaxima, String cnpj, boolean liga) {
		super();
		this.conjunto = conjunto;
		this.temperaturaAmbiente = temperaturaAmbiente;
		this.temperaturaMaxima = temperaturaMaxima;
		this.cnpj = cnpj;
		this.liga = liga;
	}
	public ControleAr(){
		
	}
	
	
	public int[] getTemperaturaAtual() {
		return temperaturaAtual;
	}
	public void setTemperaturaAtual(int[] temperaturaAtual) {
		this.temperaturaAtual = temperaturaAtual;
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
	public ArrayList<ControleAr> getAa() {
		return aa;
	}
	public void setAa(ArrayList<ControleAr> aa) {
		
		this.aa = aa;
	}
	
	
	public void conjuntosOcupados(){//enche o vetor com true para conjunto ocupado e false para desocupado
		ConjuntoDao cd = new ConjuntoDao();
		int numeroDeConjuntos = cd.getNumero();
		conjuntosOcupados = new boolean[numeroDeConjuntos];
		 int contadorAux=0;
		for(int i=0;i<numeroDeConjuntos;i++){
			cd.setConjuntos(i);
			conjuntosOcupados[i] = cd.isOcupado();
			if(cd.isOcupado()) contadorAux++;
		}
		temperaturaAtual = new int[contadorAux];
		
	}
	
	
	public void registraTemperaturas(){//registra no txt com "Conjunto - TemperaturaAmbiente"
		conjuntosOcupados();
		if(criaArquivo()){
			
			try{
				int contadorAux=0;
				for(int i=0;i<conjuntosOcupados.length;i++){
				
					if(conjuntosOcupados[i]){
						int temp = (int)(Math.random()*40);
						temperaturaAtual[contadorAux] = temp;
					output.format("%s %s ",i,temp);
					contadorAux++;
					}
				}
				
				
				}catch(Exception e){
					
					e.printStackTrace();
					closeFile();
				}
			closeFile();
			
		}
		
		
		
		
	}
	
	public boolean criaArquivo(){
		try{
				output = new Formatter("ControleAr.txt");
			}catch(Exception e){
					
					return false;

			}
			return true;	
	} 
	
	public void closeFile(){
		output.close();
		}

	
	public void getConsulta(){
	ControleArDao lr = new ControleArDao();
	lr.consulta();
	aa = lr.getAa();
	}
	
	

}
