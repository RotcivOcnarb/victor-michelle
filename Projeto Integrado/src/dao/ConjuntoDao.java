package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Conjunto;


public class ConjuntoDao {

	 public Conjunto consulta(int id)
	 {
		Connection conn = AcessoBD.obtemConexao();
	    String sqlSelect = "SELECT ocupado FROM Conjunto WHERE idConjunto = ?";
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    try
	    {
	 	   
	 	    conn.setAutoCommit(false);
	    	
	    	
	       stm = conn.prepareStatement(sqlSelect);
	       stm.setInt(1, id);
	       rs = stm.executeQuery();
	       if (rs.next())
	       {
	    	  Conjunto conj = new Conjunto(rs.getInt("idConjunto"), rs.getBoolean("ocupado"), rs.getString("EmpresaCnpj"));
	    	  return conj;
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
	    return null;
	 }
	 
	 public boolean alteraConjunto(Conjunto conj){
		 Connection conn = AcessoBD.obtemConexao();
		 String sqlUpdate = "UPDATE Conjunto SET ocupado = ? ,EmpresaCnpj = ? WHERE idConjunto= ?;";
		 PreparedStatement stm = null;
		    try
		    {
			       conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlUpdate);
		       stm.setString(1, Boolean.toString(conj.isOcupado()));
		       stm.setString(2, conj.getCnpj());
		       stm.setInt(3, conj.getId());
		       
		       //System.out.println(stm);
		       stm .executeUpdate();

		       conn.commit(); // ADDED
		       
		       stm.close();
		       
		       return true;
		       
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
		    return false;
		 
		 }

	 public ArrayList<Conjunto> getListaConjunto(){
		 	Connection conn = AcessoBD.obtemConexao();
		 
		    String sqlSelect = "select * from Conjunto;";
		    PreparedStatement stm = null;
		    ResultSet rs = null;
		    try
		    {
			   
			   conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlSelect);
		       rs = stm.executeQuery();
		       ArrayList<Conjunto> conjuntos = new ArrayList<Conjunto>();
		       while (rs.next())
		       {
		    	  Conjunto conj = new Conjunto(rs.getInt("idConjunto"), rs.getBoolean("ocupado"), rs.getString("EmpresaCnpj"));
		    	  conjuntos.add(conj);
		       }
		       return conjuntos;
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
		 return null;
		 
	 }
	
}
