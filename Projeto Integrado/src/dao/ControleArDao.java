package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ControleAr;

public class ControleArDao {

	//Consulta controles de ar por CNPJ
	 public ArrayList<ControleAr> getLista()
	 {
		  Connection conn = AcessoBD.obtemConexao();
	    String sqlSelect = "select  conjunto.idConjunto ,conjunto.empresaCnpj,empresa.temperaturaAr From conjunto join empresa on Conjunto.EmpresaCnpj = empresa.cnpj;";
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    try
	    {
	       
	         conn.setAutoCommit(false);
	       ArrayList<ControleAr> lista = new ArrayList<ControleAr>();
	         
	         stm = conn.prepareStatement(sqlSelect);
	       rs = stm.executeQuery();
	       
	       while(rs.next()){
		   		
	    	   ControleAr aux = new ControleAr();
	    	   
	    	   aux.setConjunto(rs.getInt(1));
	    	   aux.setCnpj(rs.getString(2));
	    	   aux.setTemperaturaMaxima(rs.getInt(3));

	    	   
				lista.add(aux);

			}
	       
	       return lista;
		
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
	    return null;
	 }
	 
	
	
	
	
	
	
	
}
