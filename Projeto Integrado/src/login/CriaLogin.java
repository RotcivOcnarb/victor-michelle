package login;
import java.io.*;
import java.util.*;


public class CriaLogin {
	private Formatter output;
	byte[] encrypt;
	
	CryptoAES cr;
	
	
	public String criaArquivo(){
		try{
				output = new Formatter("Login.txt");
			}catch(SecurityException se){
					
					return "Acesso Negado";
					
			
			}catch ( FileNotFoundException filesNotFoundException ){
					return  "Erro,tente novamente." ;
					
			} 
			return"";	
	} 
	public String addLogin(int perfil,int login,String senha,String hem,String hemi,boolean acesso){
	
		
		try{
		Login lg = new Login(perfil,login,senha,hem,hemi,acesso);
		//String x = Arrays.toString(lg.getEncrypt());
		//System.out.println(encrypt);
	
		output.format("%s %s %s %s %s %s ",lg.getPerfil(),lg.getId(),lg.getSenha(),lg.getHemi(),lg.getHem(),lg.isAcesso());
		    
		
		
		return "Usuário Cadastrado com Sucesso";
		}catch(FormatterClosedException formatterClosedException){
			return "Erro ao Registrar Usuario";
		}catch(NoSuchElementException elementException){
			return "Dados inválidos";
		}
		
	}
	public void closeFile(){
	
	output.close();
	}
			 

}
