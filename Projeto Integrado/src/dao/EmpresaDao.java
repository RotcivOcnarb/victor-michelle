package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Empresa;


public class EmpresaDao {
	public boolean cadastra(Empresa empresa)
	 {
		Connection conn = AcessoBD.obtemConexao();
		
	    String sqlInsert = "insert into Empresa(cnpj,razaoSocial,horaAbertura,horaFechamento,temperaturaAr) values(?,?,?,?,?);";
	    PreparedStatement stm = null;
	    try
	    {
	    	conn.setAutoCommit(false);
	       stm = conn.prepareStatement(sqlInsert);
	       stm.setString(1, empresa.getCnpj());
	       stm.setString(2, empresa.getRazaoSocial());
	       stm.setString(3, empresa.getHoraAbertura());
	       stm.setString(4, empresa.getHoraFechamento());
	       stm.setString(5, empresa.getTemperaturaAr());
	   
	       //System.out.println(stm);
	       stm .execute();

	       conn.commit(); // ADDED
	       
	       stm.close();
	       return true;
	       
	    }
	    catch (Exception e)
	    {
	    	 JOptionPane.showMessageDialog(null, e.getMessage());
	    	//e.printStackTrace();
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
	 public int getNumeroEmpresa(){
		 
		 Connection conn = AcessoBD.obtemConexao();
		    String sqlSelect = "select COUNT(*) from Empresa;";
		    PreparedStatement stm = null;
		    ResultSet rs = null;
		    try
		    {
		         conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlSelect);
		       rs = stm.executeQuery();
		       if (rs.next())
		       {
		         return rs.getInt(1);
		         
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
		 
		 return -1;
	 }


	 
	 public ArrayList<Empresa> getListaEmpresas(){
		 Connection conn = AcessoBD.obtemConexao();
		    String sqlSelect = "SELECT * FROM Empresa";
		    PreparedStatement stm = null;
		    ResultSet rs = null;
		    
		    try
		    {
		        conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlSelect);
		       rs = stm.executeQuery();
		       
		       ArrayList<Empresa> empresas = new ArrayList<Empresa>();
		       
		       while (rs.next())
		       {
		    	   Empresa empresa = new Empresa();
		    	   empresa.setCnpj(rs.getString(2)); 
		    	   empresa.setRazaoSocial(rs.getString(3));
		    	   empresa.setHoraAbertura(rs.getString(4));
		    	   empresa.setHoraFechamento(rs.getString(5));
		    	   empresa.setTemperaturaAr(rs.getString(6));
		    	   
		    	   empresas.add(empresa);
		       }
		       
		       return empresas;
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
	 
	 public Empresa consulta(int id)
	 {
		 Connection conn = AcessoBD.obtemConexao();
	    String sqlSelect = "SELECT * FROM Empresa where idEmpresa = ?";
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    try
	    {
	         conn.setAutoCommit(false);
	       stm = conn.prepareStatement(sqlSelect);
	       stm.setInt(1, id);
	       rs = stm.executeQuery();
	       
	       Empresa empresa = new Empresa();
	       
	       if (rs.next())
	       {
	    	   empresa.setCnpj(rs.getString(2)); 
	    	   empresa.setRazaoSocial(rs.getString(3));
	    	   empresa.setHoraAbertura(rs.getString(4));
	    	   empresa.setHoraFechamento(rs.getString(5));
	    	   empresa.setTemperaturaAr(rs.getString(6));
	       }
	       
	       return empresa;
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
	 
	 public boolean altera(Empresa empresa){
		 Connection conn = AcessoBD.obtemConexao();
			 String sqlUpdate = "UPDATE Empresa SET razaoSocial= ?,horaAbertura=? ,horaFechamento=? ,temperaturaAr=? where cnpj=?;";
			 PreparedStatement stm = null;
			    try
			    {

			        conn.setAutoCommit(false);
			       stm = conn.prepareStatement(sqlUpdate);
			      
			       
			       stm.setString(1, empresa.getRazaoSocial());
			       stm.setString(2, empresa.getHoraAbertura());
			       stm.setString(3, empresa.getHoraFechamento());
			       stm.setString(4, empresa.getTemperaturaAr());
			       stm.setString(5, empresa.getCnpj());
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
			
		 
	 public boolean exclui(String cnpj){
		 Connection conn = AcessoBD.obtemConexao();
		 String sqlUpdate = "delete from Empresa where cnpj=?;";
		 PreparedStatement stm = null;
		    try
		    {
		        conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlUpdate);
		      
		       
		       stm.setString(1,cnpj);
		       
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
	 
	 
		 
		 
		 
	 }
	 
	 
	 
	 

