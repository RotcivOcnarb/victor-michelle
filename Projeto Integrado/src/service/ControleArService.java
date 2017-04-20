package service;

import java.util.ArrayList;
import java.util.Formatter;

import dao.ConjuntoDao;
import dao.ControleArDao;
import model.Conjunto;
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
	
	public void registraTemperaturas(){//registra no txt com "Conjunto - TemperaturaAmbiente"
		if(criaArquivo()){
			
			ConjuntoDao conDao = new ConjuntoDao();
			ArrayList<Conjunto> conjuntos = conDao.getListaConjunto();
			try{
				int i = 0;
				int i2 = 0;
				for(Conjunto c : conjuntos){
					if(c.isOcupado() == 1){
						int rand = (int)(Math.random()*40);
						temperaturaAtual[i] = rand;
					output.format("%s %s ",i2,rand);
					i2++;
					}
					i++;
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
