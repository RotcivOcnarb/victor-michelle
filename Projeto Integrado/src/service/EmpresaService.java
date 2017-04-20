package service;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.EmpresaDao;
import model.Conjunto;
import model.Empresa;

public class EmpresaService {
	
	EmpresaDao dao;
	
	public EmpresaService(){
		dao = new EmpresaDao();
	}
	
	public boolean cadastra(Empresa empresa) throws RuntimeException{
		if(empresa.getCnpj().length() == 14 && empresa.getRazaoSocial().length() >0 && empresa.getHoraAbertura().length() == 5 &&
				empresa.getHoraFechamento().length() == 5 && empresa.getTemperaturaAr().length() == 2){
			return dao.cadastra(empresa);
		}
		else{
			throw new RuntimeException("ERRO DE VALIDA�AO");
		}
	}
	
	public ArrayList<Empresa> getListaEmpresas(){
		return dao.getListaEmpresas();
	}
	
	public boolean altera(Empresa empresa){
		if(empresa.getCnpj().length() == 14 && empresa.getRazaoSocial().length() >0 && empresa.getHoraAbertura().length() == 5 &&
				empresa.getHoraFechamento().length() == 5 && empresa.getTemperaturaAr().length() == 2){
			dao.altera(empresa);
			return true;
			
			
		}else JOptionPane.showMessageDialog(null, "Erro ao tentar alterar empresa, valores com falha de valida��o");
		
		return false;
	
		
	}
	
	public boolean exclui(String c){
		
		ConjuntoService cs = new ConjuntoService();
		try{
		for(Conjunto conj : cs.ConjuntoByCnpj(c)){
			conj.setOcupado(0);
			cs.alteraConjunto(conj);
		}
		dao.exclui(c);
		return true;
		
		}catch(Exception e){
			return false;
		}
		}

}
