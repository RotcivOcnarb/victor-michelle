package model;

import java.util.ArrayList;

import java.util.Iterator;

import dao.ConjuntoDao;



public class ListaConjunto{
	ArrayList<Conjunto> ar = new ArrayList<Conjunto> ();
	private boolean [] vet;
	ConjuntoDao cd = new ConjuntoDao();
			
	public ListaConjunto(){		
	vet = new boolean[cd.getNumero()];

	for(int i=1;i<=cd.getNumero();i++){//numero de conjuntos qe tem
		ConjuntoDao con = new ConjuntoDao();
		con.setConjuntos(i);
		Conjunto cont  = new Conjunto(i,con.isOcupado());
		ar.add(cont);
	
	}
	getIdsLivres();
	}
	public void updateConjunto(String a,int b){
		ConjuntoDao con = new ConjuntoDao();
		con.alteraConjunto(a, b);
	}
	
	
	
	public boolean[] getIdsLivres(){
		
		int i=0;
		Iterator<Conjunto> it =  ar.iterator();
		
		while(it.hasNext()){
			Conjunto aux = it.next();
			vet[i]= aux.ocupado;
			//System.out.print(vet[i]+" ");
			i++;
		}
		
		
		return vet;
	}
	
	
	public boolean getPosicao(int i){
		return vet[i];
	}
	public int getNumeroVazios(){
		int vazio =0;
		for(int i=0;i<vet.length;i++)
			if(!vet[i]) vazio++;
		
		return vazio;
	}
	public int getNumero(){
		return cd.getNumero();
	}

	private class Conjunto {
		boolean ocupado;
		
		public Conjunto(int i, boolean c){
		ocupado = c;	
		}
		
	
	
}
}



