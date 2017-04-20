package service;

import java.util.ArrayList;

import dao.PessoaDao;
import model.Pessoa;

public class PessoaService {
	
	PessoaDao dao;
	
	public PessoaService(){
		dao = new PessoaDao();
	}
	
	public boolean cadastra(Pessoa pessoa){
		if(valida(pessoa)){
		return dao.cadastra(pessoa);
		}return false;
	}
	
	public boolean exclui(int id){
		return dao.exclui(id);
	}
	
	public int getLastId(){
		return dao.getLastId();
	}
	
	public boolean altera(Pessoa pessoa){
		return dao.altera(pessoa);
	}
	
	public boolean valida(Pessoa pessoa){
	String [] aux  ={String.valueOf(pessoa.getPerfil()), pessoa.getNome(), pessoa.getSenha(), pessoa.getCpf(), pessoa.getEntradaMax(), pessoa.getEntradaMin(), String.valueOf(pessoa.isAcesso()), pessoa.getCnpj()};
	for(int i=0;i<aux.length;i++){
		if(aux[i] == null || aux[i].equals("")){
			return false;
		}
		
	}

	
	return true;
}

	public ArrayList<Pessoa> getLista() {
		return dao.getLista();
	}


}
