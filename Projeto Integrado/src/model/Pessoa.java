package model;

import java.util.ArrayList;

import dao.PessoaDao;


public class Pessoa {
	private int perfil,numero,id;
	private String nome,cpf,senha,entradaMax,entradaMin,cnpj;
	private boolean acesso;	
	//1 sindico ; 2 atendente ; 3 funcionario ; 0 não entra no sistema ; 4 Administrador
	//FAZER INTERFACE PRA ESCOLHER EMPRESA ATRAVES DE ~CHECK BOX~ <- usa combobox pf
	
	public Pessoa(int id,String nome,String senha,String cpf,int perfil,String ema,String emi,String cnpj,boolean acesso){
		this.id=id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.cpf = cpf;
		this.perfil = perfil;
		entradaMax = ema;
		entradaMin = emi;
		this.acesso = acesso;
		this.senha= senha;
	}
	
	public Pessoa(){
		
	}
	
	
	
//	public boolean valida(String nome,int perfil,String senha,String cpf,String ema,String emi,String cnpj,boolean acesso){
//		String [] aux  ={Integer.toString(perfil),nome,senha,cpf,ema,emi,String.valueOf(acesso),cnpj};
//		for(int i=0;i<aux.length;i++){
//			if(aux[i] == null || aux[i].equals("")){
//				return false;
//			}
//			
//		}
//
//		
//		return true;
//	}
	
	
	
	
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		
		this.id = id;
		System.out.println("set"+this.id);
	}

	public boolean isAcesso() {
		return acesso;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setAcesso(boolean acesso) {
		this.acesso = acesso;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setcCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public int getPerfil() {
		return perfil;
	}



	public String getNome() {
		return nome;
	}



	public String getCpf() {
		return cpf;
	}



	public String getSenha() {
		return senha;
	}



	public String getEntradaMax() {
		return entradaMax;
	}



	public String getEntradaMin() {
		return entradaMin;
	}



	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public void setEntradaMax(String entradaMax) {
		this.entradaMax = entradaMax;
	}

	public int getLastId(){
		PessoaDao pd = new PessoaDao();
		return pd.getLastId();
	}
	
	public void setNumero(){
		PessoaDao dao = new PessoaDao();
		numero = dao.getNumeroFuncionario();
	}
	public int getNumero(){
		return numero;
	}
	

	public void setEntradaMin(String entradaMin) {
		this.entradaMin = entradaMin;
	}

	public void getDados(){
		System.out.println(perfil+" "+ nome +" "+cpf +" "+senha +" "+entradaMax+" "+ entradaMin);
	}
	
//	public boolean cadastraPessoa(String nome,int perfil,String senha,String cpf,String ema,String emi,String cnpj,boolean acesso){
//		//System.out.print("nome :"+nome+"perfil: "+perfil+"senha: "+senha+"cpf: "+cpf+"ema: "+ema+"emi: "+emi+"cnpj: "+cnpj+"acesso: "+acesso);
//		//System.out.println(valida(nome,perfil,senha,cpf,ema,emi,cnpj,acesso));
//		
//		if(valida(nome,perfil,senha,cpf,ema,emi,cnpj,acesso)){
//		PessoaDao pd = new PessoaDao(perfil,nome,senha,cpf,ema,emi,acesso,cnpj);
//		return pd.cadastra();
//		}return false;
//	
//	}

	
	
//	public void consulta(){
//		boolean a = true;	
//		setNumero();
//		int x =1;
//		int aux=1;
//		while(a){
//		
//			PessoaDao con = new PessoaDao();
//			Pessoa cont = con.consulta(x);
//			if(cont.getCpf() !=null ){
//			ar.add(cont);
//			aux++;
//			//System.out.println("x   "+x);
//			//System.out.println("aux   "+aux);
//			
//			}else if(aux>numero){
//				a=false;
//			}
//			x++;
//		}
//	
//	}

	

	
	
	
	
	
}
