package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ControleAr;

public class ControleArDao {
	private int conjunto,temperaturaMaxima;
	private String cnpj;
	private boolean liga;
	private ArrayList<ControleAr> aa = new ArrayList<ControleAr>();
	 Connection conn = null;
	

	
	public ControleArDao(int conjunto, int temperaturaMaxima, String cnpj, boolean liga) {
		super();
		this.conjunto = conjunto;
		this.temperaturaMaxima = temperaturaMaxima;
		this.cnpj = cnpj;
		this.liga = liga;
	}
	public ControleArDao(){}
	
	
	
	
	
	
	public ArrayList<ControleAr> getAa() {
		return aa;
	}
	public void setAa(ArrayList<ControleAr> aa) {
		this.aa = aa;
	}
	public int getConjunto() {
		return conjunto;
	}

	public int getTemperaturaMaxima() {
		return temperaturaMaxima;
	}
	public String getCnpj() {
		return cnpj;
	}
	public boolean isLiga() {
		return liga;
	}
	public void setConjunto(int conjunto) {
		this.conjunto = conjunto;
	}
	public void setTemperaturaMaxima(int temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setLiga(boolean liga) {
		this.liga = liga;
	}
	
	
	 public void consulta()
	 {
	    String sqlSelect = "select  conjunto.idConjunto ,conjunto.empresaCnpj,empresa.temperaturaAr From conjunto join empresa on Conjunto.EmpresaCnpj = empresa.cnpj;";
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
		   		
	    	   ControleAr aux = new ControleAr();
	    	   
	    	   aux.setConjunto(rs.getInt(1));
	    	   aux.setCnpj(rs.getString(2));
	    	   aux.setTemperaturaMaxima(rs.getInt(3));


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
	 
	
	
	
	
	
	
	
}
