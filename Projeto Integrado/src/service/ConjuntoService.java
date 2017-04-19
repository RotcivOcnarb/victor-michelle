package service;

import java.util.ArrayList;

import dao.ConjuntoDao;
import model.Conjunto;

public class ConjuntoService {
	
	ConjuntoDao dao;
	
	public ConjuntoService(){
		dao = new ConjuntoDao();
	}
	
	public ArrayList<Conjunto> getListaConjunto(){
		return dao.getListaConjunto();
	}

	public boolean alteraConjunto(Conjunto conj) {
		return dao.alteraConjunto(conj);
	}
	
	public ArrayList<Conjunto> ConjuntoByCnpj(String CNPJ){
		ArrayList<Conjunto> lista = new ArrayList<Conjunto>();
		for(Conjunto c : dao.getListaConjunto()){
			if(c.getCnpj().equals(CNPJ)){
				lista.add(c);
			}
		}
		return lista;
	}

}
