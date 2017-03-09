package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConjuntoDao {
	int id;
	boolean ocupado;
	Connection conn = null;
	int numero=0;
	
	
	public ConjuntoDao(){
		 getNumeroConjunto();
		
	}


	public int getId() {
		return id;
	}


	public boolean isOcupado() {
		return ocupado;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	public void setNumero(int i){
		numero = i;
	}
	public int getNumero(){
		return numero;
	}
	
	 public void setConjuntos(int id)
	 {
	   
	    String sqlSelect = "SELECT ocupado FROM Conjunto WHERE idConjunto = ?";
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
	       this.ocupado =rs.getBoolean(1);
	       //System.out.print(this.ocupado);

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
	 
	 public void alteraConjunto(String a,int b){
		 String sqlUpdate = "UPDATE Conjunto SET ocupado = true ,EmpresaCnpj = ? WHERE idConjunto= ?;";
		 PreparedStatement stm = null;
		    try
		    {
		    	AcessoBD bd = new AcessoBD();
			       conn = bd.obtemConexao();
			       conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlUpdate);
		       stm.setString(1, a);
		       stm.setInt(2, b);
		   
		       //System.out.println(stm);
		       stm .executeUpdate();

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
	 
	 public void alteraConjunto(String a){
		 String sqlUpdate = "UPDATE Conjunto SET ocupado = false ,EmpresaCnpj = null WHERE empresaCnpj= ?;";
		 PreparedStatement stm = null;
		    try
		    {
		    	AcessoBD bd = new AcessoBD();
			       conn = bd.obtemConexao();
			       conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlUpdate);
		       stm.setString(1, a);
		     
		   
		       //System.out.println(stm);
		       stm .executeUpdate();

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
		
	 
	 
	 
		
	 public void getNumeroConjunto(){
		 
		 
		    String sqlSelect = "select COUNT(*) from Conjunto;";
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
		         setNumero(rs.getInt(1));
		         
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
	

}
