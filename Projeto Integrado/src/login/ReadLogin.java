package login;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import dao.LoginDao;


public class ReadLogin {
	private Scanner input;
	private int id,pf;
	private String senha;
	private boolean check = false;
	private byte[] encrypt;
	CryptoAES cr;
	Login lg;
	private Date date;
	private Date dateCompareOne;
	private Date dateCompareTwo;
	private static final String inputFormat = "HH:mm";
	private SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);


	public ReadLogin(int a,String b){
		
		id = a;
		senha= b;
		cr = new CryptoAES(b);
		
		try {
			encrypt = cr.encrypt(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ReadLogin(){};
	
	public String openFile(){
		try{
			input = new Scanner( new File( "Login.txt" ) );
			return "";
		} 
		catch ( FileNotFoundException fileNotFoundException ){
			return "Sem Conexão aos Registros.";
			
		} 
	} 


	
	public void readRecords(){ // object to be written to screen
	
		lg = new Login();

	
	try {
			while ( input.hasNext() ){
			lg.setPerfil(input.nextInt());
			lg.setId(input.nextInt());
			lg.setSenha(input.next());
			lg.setHemi(input.next());
			lg.setHem(input.next());
			lg.setAcesso(input.nextBoolean());
			
			
			 //System.out.println( "Senha  "+ x +"   Entrada  "+ aux +"      "+cr.decrypt(encrypt));
			
			
			//System.out.println("id  "+id+"  lg.id" + lg.getId()+"\n" );
			//System.out.println("senha  "+getSenhaCrip()+"  lg.senha"+ lg.getSenha()     );
			//System.out.println("verifica horario"+ verificaHorario() );
			//System.out.println(lg.isAcesso());
			
			
			
			
			if(id == lg.getId() && getSenhaCrip().equals(lg.getSenha()) && (verificaHorario() || lg.isAcesso())){
				//System.out.println( "Senha  "+ lg.getSenha() + "ID: "+  lg.getId()+lg.hemi + lg.getHem());
				//System.out.println( "OK" );
				check = true;
				registraAcesso(lg.getId(),lg.getSenha());
				break;
			}
			
			
			
			
			} // end while
		}catch ( Exception e ){
			e.printStackTrace();	
			input.close();
		}
	}
	
	
	
	public boolean logar(int id,String senha){ // object to be written to screen
		CryptoAES cr = new CryptoAES(senha);
		String aux="";
		
		try {
			encrypt = cr.encrypt(senha);
	        for (int i=0; i<encrypt.length; i++){
	        	aux += encrypt[i];
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		LoginDao ld = new LoginDao(id,aux);
		ld.consultaLogin();
		
		boolean verificador1 = ld.isLog();
		lg = new Login();
		
		
		lg.setPerfil(ld.getPerfil());
		lg.setId(ld.getId());
		lg.setSenha(ld.getSenha());
		
		lg.setAcesso(ld.isAcesso());
		
		lg.setHemi(ld.getHoraMin());
		lg.setHem(ld.getHoraMax());
		
		//System.out.println(""+verificaHorario() + lg.isAcesso());
		

		
		//System.out.println(verificaHorario()  + "      id: "+id+" Senha digitada: "+aux +"\n bd "+ lg.getId() +" senha "+lg.getSenha());
			
			if(verificador1 && (verificaHorario() || lg.isAcesso())){
			//	System.out.println("oi");
				
				setPf(lg.getPerfil());
				return true;
			}else{
				//System.out.println("tchau");
				return false;
			}

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void registraAcesso(int id,String cnpj){
		LoginRegistro lr = new LoginRegistro(id,cnpj);
		lr.registraEntrada();
		
		
	}
	
	
	
	
	public int getPerfil(){//pega o perfil do user e envia pra interface que passara para o resto
		return lg.getPerfil();
	}
	
	public boolean getCheck(){
		return check;
	}
	
	public void closeFile(){
		if ( input != null )
			input.close(); // close file
	} // end method closeFile
	public byte[] getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(byte[] encrypt) {
		this.encrypt = encrypt;
	}
	public String getSenhaCrip() throws Exception{
		encrypt = cr.encrypt(senha);
        String aux="";
         for (int i=0; i<encrypt.length; i++)
              aux += encrypt[i];
         return aux;
	}
	public boolean verificaHorario(){
		
		String aux = lg.getHemi();
		String aux1 = lg.getHem();
		
		
	    Calendar now = Calendar.getInstance();
	   

	    int hour = now.get(Calendar.HOUR_OF_DAY);
	    int minute = now.get(Calendar.MINUTE);

	    date = parseDate(hour + ":" + minute);
	    
	    //System.out.println("hour --> "+hour);
	    //System.out.println("minute --> "+minute);
	    //System.out.println("date --> "+date);
	    
	    dateCompareOne = parseDate(aux);
	    dateCompareTwo = parseDate(aux1);
	    
	    
	    //System.out.println(hour+""+minute+"  hora entrada min"+aux +"  hora entrada max" +aux1+"\n"+dateCompareOne.before( date ) + dateCompareTwo.after(date));
	    
	   // System.out.println(date +"          "+dateCompareOne+"         "+dateCompareTwo);
	    //System.out.println("comparacao   "+  String.valueOf(dateCompareOne.before( date ) && dateCompareTwo.after(date)));
	    
	    
	    if(dateCompareOne.getHours() > dateCompareTwo.getHours()){
	    	 if ( dateCompareOne.after (date ) && dateCompareTwo.after(date)) {
	    			
		    	 //System.out.println("entro");
		    	return true;
		    	
		    }else{
		    //System.out.println("nao  entro");
			return false;
		    }
	    
	    
	    }else{
	    
	    if ( dateCompareOne.before (date ) && dateCompareTwo.after(date)) {
	
	    	 //System.out.println("entro");
	    	return true;
	    	
	    }else{
	    //System.out.println("nao  entro");
		return false;
	    }
	    
	}
	}

private Date parseDate(String date) {

		try {
				return inputParser.parse(date);
			} catch (java.text.ParseException e) {
				return new Date(0);
			}
	}

public int getPf() {
	return pf;
}

public void setPf(int pf) {
	this.pf = pf;
}


	
}





