package login;

import java.util.Calendar;

import dao.LoginRegistroDao;

public class LoginRegistro {
	private int id;
	private Calendar dataSistema = Calendar.getInstance();
	private int hora = dataSistema.get(Calendar.HOUR_OF_DAY);
    private int minuto = dataSistema.get(Calendar.MINUTE);
    private int dia = dataSistema.get(Calendar.DAY_OF_MONTH);
    private int mes = dataSistema.get(Calendar.MONTH);
    private int ano = dataSistema.get(Calendar.YEAR);
    private String cnpj;
    private int idAcesso;
    private boolean saiu;
    
    public LoginRegistro(int id,String cnpj){
    	this.id = id;
    	this.cnpj = cnpj;
    }
    public LoginRegistro(){}
    

    public int getIdAcesso() {
		return idAcesso;
	}
	public void setIdAcesso(int idAcesso) {
		this.idAcesso = idAcesso;
	}
	
	
    
	public String getCnpj() {
		return cnpj;
	}



	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}



	public int getId() {
		return id;
	}


	public Calendar getDataSistema() {
		return dataSistema;
	}


	public int getHora() {
		return hora;
	}


	public int getMinuto() {
		return minuto;
	}


	public int getDia() {
		return dia;
	}


	public int getMes() {
		return mes;
	}


	public int getAno() {
		return ano;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setDataSistema(Calendar dataSistema) {
		this.dataSistema = dataSistema;
	}


	public void setHora(int hora) {
		this.hora = hora;
	}


	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}


	public void setDia(int dia) {
		this.dia = dia;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}
	
	
	public boolean consultaAcesso(){
		LoginRegistroDao lr  = new LoginRegistroDao();
		lr.consultaAcesso(id);
		
		if(lr.isAchou()){
			setIdAcesso(lr.getIdAcesso());
			setSaiu(lr.isSaiu());
			return true;
		}else{
			return false;
			
		}
	}
	
	
	
	
	public void registraEntrada(){
		System.out.println(consultaAcesso());
  	  	System.out.println(getIdAcesso());
		
		
		
		if(!consultaAcesso()){
			LoginRegistroDao lr = new LoginRegistroDao(getId(),getHora(),getMinuto(),getDia(),getMes(),getAno());
			lr.registrarEntrada();
			System.out.println("entro");
		}else{
			LoginRegistroDao lr = new LoginRegistroDao(getHora(),getMinuto(),getDia(),getMes(),getAno());
			lr.registrarSaida(getIdAcesso());
			System.out.println("saiu");
			
		}
		
	}

	public boolean isSaiu() {
		return saiu;
	}

	public void setSaiu(boolean saiu) {
		this.saiu = saiu;
	}
	
	
	
	
}
