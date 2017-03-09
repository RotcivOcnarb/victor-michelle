package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import login.ListaAcesso;


public class LoginRegistroDao {
	private int id;
	private int hora;
    private int minuto;
    private int dia;
    private int mes;
    private int ano;
	private int horaEntrada,horaSaida;
    private int minutoEntrada,minutoSaida;
    private int diaEntrada,diaSaida;
    private int mesEntrada,mesSaida;
    private int anoEntrada,anoSaida;
    private String cnpj,cpf,nome;
    private int idAcesso;
    private boolean saiu,achou;
    private ArrayList<ListaAcesso> aa = new ArrayList<ListaAcesso>();
    private int numeroAcessos;
    Connection conn = null;
    
    
    public LoginRegistroDao(int id,int hora,int minuto,int dia,int mes,int ano){
    	this.id = id;
    	this.hora = hora;
    	this.minuto = minuto;
    	this.dia = dia;
    	this.mes = mes;
    	this.ano = ano;
    	
    }
    public LoginRegistroDao(int hora,int minuto,int dia,int mes,int ano){

    	this.hora = hora;
    	this.minuto = minuto;
    	this.dia = dia;
    	this.mes = mes;
    	this.ano = ano;
    	
    }
    public LoginRegistroDao(){
    	consultaNumeroDeAcessos();
    }
    
    public LoginRegistroDao(boolean a){
    	
    }
	public String getCnpj() {
		return cnpj;
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
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
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
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	 
	public boolean isAchou() {
		return achou;
	}
	public void setAchou(boolean achou) {
		this.achou = achou;
	}
	public int getNumeroAcessos() {
		return numeroAcessos;
	}
	public void setNumeroAcessos(int numeroAcessos) {
		this.numeroAcessos = numeroAcessos;
	}
	
	
	
	


	public int getId() {
		return id;
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
	
	public int getIdAcesso() {
		return idAcesso;
	}
	public void setIdAcesso(int idAcesso) {
		this.idAcesso = idAcesso;
	}
	public boolean isSaiu() {
		return saiu;
	}
	public void setSaiu(boolean saiu) {
		this.saiu = saiu;
	}
	
	
	
	public void registrarEntrada(){
		 String sqlInsert = "insert into Acesso(horaEntrada, minutoEntrada, diaEntrada, mesEntrada, anoEntrada,idPessoa) values (?,?,?,?,?,?);";
		    PreparedStatement stm = null;

		    try
		    {
		    	AcessoBD bd = new AcessoBD();
			    conn = bd.obtemConexao();
			    conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlInsert);
		      
		       stm.setInt(1, getHora());
		       stm.setInt(2, getMinuto());
		       stm.setInt(3, getDia());
		       stm.setInt(4, getMes());
		       stm.setInt(5, getAno());
		       stm.setInt(6, getId());
		       
		       
		   
		       //System.out.println(stm);
		       stm .execute();

		       conn.commit(); // ADDED
		       
		       stm.close();
		       
		    }
		    catch (Exception e)
		    {
		    	 //JOptionPane.showMessageDialog(null, "Mensagem generica de erro de bd");
		    	e.printStackTrace();
		    	
		    	
		       try
		       {
		          conn.rollback();
		          
		       }
		       catch (SQLException e1){
		    	  
		       }
		    }
		    finally
		    {
		       if (stm != null)
		       {
		          try
		          {
		        	  
		             stm.close();
		          }
		          catch (SQLException e1)
		          {
		        	 
		             //System.out.print(e1.getStackTrace());
		          }
		       }
		    }

		
		}
	
	public void registrarSaida(int idAcesso){
		 String sqlInsert = "UPDATE Acesso SET saiu = true,horaSaida=?,minutoSaida=?,diaSaida=?,mesSaida=?,anoSaida=? WHERE idAcesso=?;";
		    PreparedStatement stm = null;

		    try
		    {
		    	AcessoBD bd = new AcessoBD();
			    conn = bd.obtemConexao();
			    conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlInsert);
		      
		       stm.setInt(1, getHora());
		       stm.setInt(2, getMinuto());
		       stm.setInt(3, getDia());
		       stm.setInt(4, getMes());
		       stm.setInt(5, getAno());
		       stm.setInt(6, idAcesso);
		       
		       
		   
		       //System.out.println(stm);
		       stm .execute();

		       conn.commit(); // ADDED
		       
		       stm.close();
		       
		    }
		    catch (Exception e)
		    {
		    	 //JOptionPane.showMessageDialog(null, "Mensagem generica de erro de bd");
		    	e.printStackTrace();
		    	
		    	
		       try
		       {
		          conn.rollback();
		          
		       }
		       catch (SQLException e1){
		    	   e.printStackTrace();
		       }
		    }
		    finally
		    {
		       if (stm != null)
		       {
		          try
		          {
		        	  
		             stm.close();
		          }
		          catch (SQLException e1)
		          {
		        	  e1.printStackTrace();
		             //System.out.print(e1.getStackTrace());
		          }
		       }
		    }

		
		}
	
	
	
	
	
	 public void consultaAcesso(int id)
	 {
	    String sqlSelect = "SELECT * FROM Acesso where idPessoa = ? and saiu=false";
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    try
	    {
	    	 AcessoBD bd = new AcessoBD();
	         conn = bd.obtemConexao();
	         conn.setAutoCommit(false);
	       stm = conn.prepareStatement(sqlSelect);
	       stm.setInt(1, id);
	       rs = stm.executeQuery();
	       if (rs.next())
	       {
	    	   setSaiu(rs.getBoolean(1));//tem um registro de entrada
	    	   setIdAcesso(rs.getInt(2));
	    	   setAchou(true);
	    	  
	   
	       }else{
	    	   //n tem registro de entrada
	    	  setAchou(false); 
	       }
	    }
	    catch (Exception e)
	    {
	    	
	       e.printStackTrace();
	       try
	       {
	          conn.rollback();
	          
	       }
	       catch (SQLException e1)
	       {
	    	  
	       }
	    }
	    finally
	    {
	       if (stm != null)
	       {
	          try
	          {
	             stm.close();
	             
	          }
	          catch (SQLException e1)
	          {
	            
	          }
	       }
	    }
	    
	 }
	 
	 
	 public void consultaNumeroDeAcessos(){
		 
		 
		    String sqlSelect = "select  idAcesso from Acesso order by idAcesso desc limit 1;";
		    PreparedStatement stm = null;
		    ResultSet rs = null;
		    try
		    {
		       AcessoBD bd = new AcessoBD();
			   conn = bd.obtemConexao();
			   conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlSelect);
		       rs = stm.executeQuery();
		       if (rs.next())
		       {
		         setNumeroAcessos(rs.getInt(1));
		         
		         
		       }
		    }
		    catch (Exception e)
		    {
		       e.printStackTrace();
		       try
		       {
		          conn.rollback();
		       }
		       catch (SQLException e1)
		       {
		          System.out.print(e1.getStackTrace());
		       }
		    }
		    finally
		    {
		       if (stm != null)
		       {
		          try
		          {
		             stm.close();
		          }
		          catch (SQLException e1)
		          {
		             System.out.print(e1.getStackTrace());
		          }
		       }
		    }
		 
		 
	 }
	

	 public void getRegistro()
	 {
	   
	    String sqlSelect = "SELECT acesso.*, pessoa.nome, pessoa.cpf , pessoa.empresaCnpj FROM acesso JOIN pessoa USING(idPessoa) order by mesSaida desc ,diaSaida desc,horaSaida desc,minutoSaida desc;";
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    try
	    {
	    	
	    	 AcessoBD bd = new AcessoBD();
	 	    conn = bd.obtemConexao();
	 	    conn.setAutoCommit(false);
	    	
	 	    
	    	
	       stm = conn.prepareStatement(sqlSelect);
	       rs = stm.executeQuery();
	       
	       while(rs.next()){
	   		ListaAcesso aux = new ListaAcesso();

	   		aux.setIdAcesso(rs.getInt(2));
	   		aux.setHoraEntrada(rs.getInt(3));
	   		aux.setMinutoEntrada(rs.getInt(4));
	   		aux.setDiaEntrada(rs.getInt(5));
	   		aux.setMesEntrada(rs.getInt(6));
	   		aux.setAnoEntrada(rs.getInt(7));
    		
	   		aux.setHoraSaida(rs.getInt(8));
	   		aux.setMinutoSaida(rs.getInt(9));
	   		aux.setDiaSaida(rs.getInt(10));
	   		aux.setMesSaida(rs.getInt(11));
	   		aux.setAnoSaida(rs.getInt(12));
	   		aux.setId(rs.getInt(13));
			
	   		aux.setNome(rs.getString(14));
	   		aux.setCpf(rs.getString(15));
	   		aux.setCnpj(rs.getString(16));
			

			aa.add(aux);
			
			
			
		}
	
	    }
	    catch (Exception e)
	    {
	       e.printStackTrace();
	       try
	       {
	          conn.rollback();
	       }
	       catch (SQLException e1)
	       {
	          System.out.print(e1.getStackTrace());
	       }
	    }
	    finally
	    {
	       if (stm != null)
	       {
	          try
	          {
	             stm.close();
	          }
	          catch (SQLException e1)
	          {
	             System.out.print(e1.getStackTrace());
	          }
	       }
	    }
	 }
	 
	 public void getRegistroEmpresa()
	 {
	   
	    String sqlSelect = "SELECT acesso.*, pessoa.nome, pessoa.cpf , pessoa.empresaCnpj FROM acesso JOIN pessoa USING(idPessoa) order by empresaCnpj,mesSaida desc ,diaSaida desc,horaSaida desc,minutoSaida desc;";
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    try
	    {
	    	
	    	 AcessoBD bd = new AcessoBD();
	 	    conn = bd.obtemConexao();
	 	    conn.setAutoCommit(false);
	    	
	 	    
	    	
	       stm = conn.prepareStatement(sqlSelect);
	       rs = stm.executeQuery();
	       
	       while(rs.next()){
	   		ListaAcesso aux = new ListaAcesso();

	   		aux.setIdAcesso(rs.getInt(2));
	   		aux.setHoraEntrada(rs.getInt(3));
	   		aux.setMinutoEntrada(rs.getInt(4));
	   		aux.setDiaEntrada(rs.getInt(5));
	   		aux.setMesEntrada(rs.getInt(6));
	   		aux.setAnoEntrada(rs.getInt(7));
    		
	   		aux.setHoraSaida(rs.getInt(8));
	   		aux.setMinutoSaida(rs.getInt(9));
	   		aux.setDiaSaida(rs.getInt(10));
	   		aux.setMesSaida(rs.getInt(11));
	   		aux.setAnoSaida(rs.getInt(12));
	   		aux.setId(rs.getInt(13));
			
	   		aux.setNome(rs.getString(14));
	   		aux.setCpf(rs.getString(15));
	   		aux.setCnpj(rs.getString(16));
			
	   		
			aa.add(aux);
			
			
			
		}
	
	    }
	    catch (Exception e)
	    {
	       e.printStackTrace();
	       try
	       {
	          conn.rollback();
	       }
	       catch (SQLException e1)
	       {
	          System.out.print(e1.getStackTrace());
	       }
	    }
	    finally
	    {
	       if (stm != null)
	       {
	          try
	          {
	             stm.close();
	          }
	          catch (SQLException e1)
	          {
	             System.out.print(e1.getStackTrace());
	          }
	       }
	    }
	 }
	 
	 
	 public void getRegistroData(int dia,int mes,int ano)
	 {
	   
	    String sqlSelect = "SELECT acesso.*, pessoa.nome, pessoa.cpf , pessoa.empresaCnpj FROM acesso JOIN pessoa USING(idPessoa) where diaSaida=? and mesSaida=? and anoSaida=? order by empresaCnpj, mesSaida desc ,diaSaida desc,horaSaida desc,minutoSaida desc ;";
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    try
	    {
	    	
	    	 AcessoBD bd = new AcessoBD();
	 	    conn = bd.obtemConexao();
	 	    conn.setAutoCommit(false);
	       stm = conn.prepareStatement(sqlSelect);
	       stm.setInt(1, dia);
	       stm.setInt(2, mes-1);
	       stm.setInt(3, ano);
	       rs = stm.executeQuery();
	       
	       while(rs.next()){
	   		ListaAcesso aux = new ListaAcesso();

	   		aux.setIdAcesso(rs.getInt(2));
	   		aux.setHoraEntrada(rs.getInt(3));
	   		aux.setMinutoEntrada(rs.getInt(4));
	   		aux.setDiaEntrada(rs.getInt(5));
	   		aux.setMesEntrada(rs.getInt(6));
	   		aux.setAnoEntrada(rs.getInt(7));
    		
	   		aux.setHoraSaida(rs.getInt(8));
	   		aux.setMinutoSaida(rs.getInt(9));
	   		aux.setDiaSaida(rs.getInt(10));
	   		aux.setMesSaida(rs.getInt(11));
	   		aux.setAnoSaida(rs.getInt(12));
	   		aux.setId(rs.getInt(13));
			
	   		aux.setNome(rs.getString(14));
	   		aux.setCpf(rs.getString(15));
	   		aux.setCnpj(rs.getString(16));
			
	   		
			aa.add(aux);
			
			
			
		}
	
	    }
	    catch (Exception e)
	    {
	       e.printStackTrace();
	       try
	       {
	          conn.rollback();
	       }
	       catch (SQLException e1)
	       {
	          System.out.print(e1.getStackTrace());
	       }
	    }
	    finally
	    {
	       if (stm != null)
	       {
	          try
	          {
	             stm.close();
	          }
	          catch (SQLException e1)
	          {
	             System.out.print(e1.getStackTrace());
	          }
	       }
	    }
	 }
	 
	 public void getRegistroSindico()
	 {
	   
	    String sqlSelect = " SELECT acesso.*, pessoa.nome, pessoa.cpf , pessoa.empresaCnpj, pessoa.perfil from acesso JOIN pessoa USING(idPessoa) where pessoa.perfil=1 or pessoa.perfil=2 order by empresaCnpj, mesSaida desc ,diaSaida desc,horaSaida desc,minutoSaida desc;";
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    try
	    {
	    	
	    	 AcessoBD bd = new AcessoBD();
	 	    conn = bd.obtemConexao();
	 	    conn.setAutoCommit(false);
	    	
	 	    
	    	
	       stm = conn.prepareStatement(sqlSelect);
	       rs = stm.executeQuery();
	       
	       while(rs.next()){
	   		ListaAcesso aux = new ListaAcesso();

	   		aux.setIdAcesso(rs.getInt(2));
	   		aux.setHoraEntrada(rs.getInt(3));
	   		aux.setMinutoEntrada(rs.getInt(4));
	   		aux.setDiaEntrada(rs.getInt(5));
	   		aux.setMesEntrada(rs.getInt(6));
	   		aux.setAnoEntrada(rs.getInt(7));
    		
	   		aux.setHoraSaida(rs.getInt(8));
	   		aux.setMinutoSaida(rs.getInt(9));
	   		aux.setDiaSaida(rs.getInt(10));
	   		aux.setMesSaida(rs.getInt(11));
	   		aux.setAnoSaida(rs.getInt(12));
	   		aux.setId(rs.getInt(13));
			
	   		aux.setNome(rs.getString(14));
	   		aux.setCpf(rs.getString(15));
	   		aux.setCnpj(rs.getString(16));
			
	   		
			aa.add(aux);
			
			
			
		}
	
	    }
	    catch (Exception e)
	    {
	       e.printStackTrace();
	       try
	       {
	          conn.rollback();
	       }
	       catch (SQLException e1)
	       {
	          System.out.print(e1.getStackTrace());
	       }
	    }
	    finally
	    {
	       if (stm != null)
	       {
	          try
	          {
	             stm.close();
	          }
	          catch (SQLException e1)
	          {
	             System.out.print(e1.getStackTrace());
	          }
	       }
	    }
	 }
	 
	 
	 
	 
	 
	 
	

	 
	 
	public ArrayList<ListaAcesso> getAa() {
		return aa;
	}
	public void setAa(ArrayList<ListaAcesso> aa) {
		this.aa = aa;
	}
	 
	 
	 
	 
	 
	 

	
	

}

