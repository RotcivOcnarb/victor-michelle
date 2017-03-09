package login;

import java.util.ArrayList;

import dao.LoginRegistroDao;

public class ListaAcesso {
		private int id;
		private int horaEntrada,horaSaida;
	    private int minutoEntrada,minutoSaida;
	    private int diaEntrada,diaSaida;
	    private int mesEntrada,mesSaida;
	    private int anoEntrada,anoSaida;
	    private String cnpj,cpf,nome;
	    private int idAcesso;
	    private ArrayList<ListaAcesso> aa = new ArrayList<ListaAcesso>();
	    
	    public ListaAcesso(){
	    	
	    }
	   
	    public void getRegistro(){
	    	LoginRegistroDao lr = new LoginRegistroDao();
	    	lr.getRegistro();
	    	aa = lr.getAa();
	    	
	    }
	    public void getRegistroEmpresa(){
	    	LoginRegistroDao lr = new LoginRegistroDao();
	    	lr.getRegistroEmpresa();
	    	aa = lr.getAa();
	    }
	    public void getRegistroData(int dia,int mes, int ano){
	    	LoginRegistroDao lr = new LoginRegistroDao();
	    	lr.getRegistroData(dia,mes,ano);
	    	aa = lr.getAa();
	    	
	    }
	    public void getRegistroSindico(){
	    	LoginRegistroDao lr = new LoginRegistroDao();
	    	lr.getRegistroSindico();
	    	aa = lr.getAa();
	    	
	    }
	    
	    
	    
		public int getId() {
			return id;
		}
		public int getHoraEntrada() {
			return horaEntrada;
		}
		public int getHoraSaida() {
			return horaSaida;
		}
		public int getMinutoEntrada() {
			return minutoEntrada;
		}
		public int getMinutoSaida() {
			return minutoSaida;
		}
		public int getDiaEntrada() {
			return diaEntrada;
		}
		public int getDiaSaida() {
			return diaSaida;
		}
		public int getMesEntrada() {
			return mesEntrada;
		}
		public int getMesSaida() {
			return mesSaida;
		}
		public int getAnoEntrada() {
			return anoEntrada;
		}
		public int getAnoSaida() {
			return anoSaida;
		}
		public String getCnpj() {
			return cnpj;
		}
		public String getCpf() {
			return cpf;
		}
		public String getNome() {
			return nome;
		}
		public int getIdAcesso() {
			return idAcesso;
		}
		public void setId(int id) {
			this.id = id;
		}
		public void setHoraEntrada(int horaEntrada) {
			this.horaEntrada = horaEntrada;
		}
		public void setHoraSaida(int horaSaida) {
			this.horaSaida = horaSaida;
		}
		public void setMinutoEntrada(int minutoEntrada) {
			this.minutoEntrada = minutoEntrada;
		}
		public void setMinutoSaida(int minutoSaida) {
			this.minutoSaida = minutoSaida;
		}
		public void setDiaEntrada(int diaEntrada) {
			this.diaEntrada = diaEntrada;
		}
		public void setDiaSaida(int diaSaida) {
			this.diaSaida = diaSaida;
		}
		public void setMesEntrada(int mesEntrada) {
			this.mesEntrada = mesEntrada;
		}
		public void setMesSaida(int mesSaida) {
			this.mesSaida = mesSaida;
		}
		public void setAnoEntrada(int anoEntrada) {
			this.anoEntrada = anoEntrada;
		}
		public void setAnoSaida(int anoSaida) {
			this.anoSaida = anoSaida;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public void setIdAcesso(int idAcesso) {
			this.idAcesso = idAcesso;
		}


		public ArrayList<ListaAcesso> getAa() {
			return aa;
		}


		public void setAa(ArrayList<ListaAcesso> aa) {
			this.aa = aa;
		}
	    
	    
	   
	    
	    
		
		
	
	
}
