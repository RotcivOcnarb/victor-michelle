package service;

import java.util.ArrayList;
import java.util.Formatter;

import dao.ConjuntoDao;
import dao.ControleArDao;
import model.ControleAr;

public class ControleArService {
	
	ControleArDao dao;
	Formatter output;
	private int [] temperaturaAtual;
	
	public int[] getTemperaturaAtual() {
		return temperaturaAtual;
	}
	public void setTemperaturaAtual(int[] temperaturaAtual) {
		this.temperaturaAtual = temperaturaAtual;
	}
	
	public ControleArService(){
		dao = new ControleArDao();
	}
	
	public ArrayList<ControleAr> getLista(){
		return dao.getLista();
	}
	
	public boolean[] conjuntosOcupados(){//enche o vetor com true para conjunto ocupado e false para desocupado
		ConjuntoDao cd = new ConjuntoDao();
		int numeroDeConjuntos = cd.getNumero();
		boolean[] conjuntosOcupados = new boolean[numeroDeConjuntos];
		 int contadorAux=0;
		for(int i=0;i<numeroDeConjuntos;i++){
			cd.setConjuntos(i);
			conjuntosOcupados[i] = cd.isOcupado();
			if(cd.isOcupado()) contadorAux++;
		}
		temperaturaAtual = new int[contadorAux];
		return conjuntosOcupados;
	}
	
	public void registraTemperaturas(){//registra no txt com "Conjunto - TemperaturaAmbiente"
		boolean[] conjuntosOcupados = conjuntosOcupados();
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
	

}
