package service;

import dao.PessoaDao;
import model.Pessoa;

public class PessoaService {
	
	PessoaDao dao;
	
	public PessoaService(){
		
	}
	
	public boolean cadastra(Pessoa pessoa){
		//System.out.print("nome :"+nome+"perfil: "+perfil+"senha: "+senha+"cpf: "+cpf+"ema: "+ema+"emi: "+emi+"cnpj: "+cnpj+"acesso: "+acesso);
		//System.out.println(valida(nome,perfil,senha,cpf,ema,emi,cnpj,acesso));
		
		if(valida(pessoa)){
		PessoaDao pd = new PessoaDao();
		return pd.cadastra(pessoa);
		}return false;
	
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


}
