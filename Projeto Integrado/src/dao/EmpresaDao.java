package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JOptionPane;


public class EmpresaDao {
	private String cnpj,razaoSocial,horaAbertura,horaFechamento,temperaturaAr;
	Connection conn = null;
	private int numero =0;
	
	
	public EmpresaDao(String a ,String b,String c, String d , String x ){
		setCnpj(a);
		setRazaoSocial(b);
		setHoraAbertura(c);
		setHoraFechamento(d);
		setTemperaturaAr(x);		
	}


public EmpresaDao( ){
	
}

	public String getCnpj() {
	return cnpj;
}

public String getRazaoSocial() {
	return razaoSocial;
}


public String getHoraAbertura() {
	return horaAbertura;
}


public String getHoraFechamento() {
	return horaFechamento;
}

public String getTemperaturaAr() {
	return temperaturaAr;
}

public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
}

public void setRazaoSocial(String razaoSocial) {
	this.razaoSocial = razaoSocial;
}

public void setHoraAbertura(String horaAbertura) {
	this.horaAbertura = horaAbertura;
}

public void setHoraFechamento(String horaFechamento) {
	this.horaFechamento = horaFechamento;
}

public void setTemperaturaAr(String temperaturaAr) {
	this.temperaturaAr = temperaturaAr;
}
public void setNumero(int x){
	numero = x;
}
public int getNumero(){
	return numero;
}




	public void cadastra()
	 {
		
	    String sqlInsert = "insert into Empresa(cnpj,razaoSocial,horaAbertura,horaFechamento,temperaturaAr) values(?,?,?,?,?);";
	    PreparedStatement stm = null;
	    try
	    {
	    	AcessoBD bd = new AcessoBD();
		    conn = bd.obtemConexao();
		    conn.setAutoCommit(false);
	       stm = conn.prepareStatement(sqlInsert);
	       stm.setString(1, getCnpj());
	       stm.setString(2, getRazaoSocial());
	       stm.setString(3, getHoraAbertura());
	       stm.setString(4, getHoraFechamento());
	       stm.setString(5, getTemperaturaAr());
	   
	       //System.out.println(stm);
	       stm .execute();

	       conn.commit(); // ADDED
	       
	       stm.close();
	       
	    }
	    catch (Exception e)
	    {
	    	 JOptionPane.showMessageDialog(null, "Mensagem generica de erro de bd");
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
	    
	 }
	 public void getNumeroEmpresa(){
		 
		 
		    String sqlSelect = "select COUNT(*) from Empresa;";
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

	 public void consulta(int id)
	 {
	    String sqlSelect = "SELECT * FROM Empresa where idEmpresa = ?";
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
	    	setCnpj(rs.getString(2)); 
	    	setRazaoSocial(rs.getString(3));
	    	setHoraAbertura(rs.getString(4));
	    	setHoraFechamento(rs.getString(5));
	    	setTemperaturaAr(rs.getString(6));
	    	
	    
	    

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
	 
	 public void altera(){
			 String sqlUpdate = "UPDATE Empresa SET razaoSocial= ?,horaAbertura=? ,horaFechamento=? ,temperaturaAr=? where cnpj=?;";
			 PreparedStatement stm = null;
			    try
			    {
			    	AcessoBD bd = new AcessoBD();
			        conn = bd.obtemConexao();
			        conn.setAutoCommit(false);
			       stm = conn.prepareStatement(sqlUpdate);
			      
			       
			       stm.setString(1, getRazaoSocial());
			       stm.setString(2, getHoraAbertura());
			       stm.setString(3, getHoraFechamento());
			       stm.setString(4, getTemperaturaAr());
			       stm.setString(5, getCnpj());
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
			
		 
	 public void exclui(String a){
		 String sqlUpdate = "delete from Empresa where cnpj=?;";
		 PreparedStatement stm = null;
		    try
		    {
		    	AcessoBD bd = new AcessoBD();
		        conn = bd.obtemConexao();
		        conn.setAutoCommit(false);
		       stm = conn.prepareStatement(sqlUpdate);
		      
		       
		       stm.setString(1,a);
		       
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
	 
	 
		 
		 
		 
	 }
	 
	 
	 
	 

