package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	private int id,perfil;
	private String senha,horaMin,horaMax;
	byte[] encrypt;
	private boolean log,acesso;
	Connection conn = null;

	
	

	


	public LoginDao(int id, String senha) {
		this.id = id;
		this.senha = senha;
	}


	public int getId() {
		return id;
	}


	public String getSenha() {
		return senha;
	}


	public boolean isLog() {
		return log;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public void setLog(boolean log) {
		this.log = log;
	}


	public void consultaLogin()
	 {
	    String sqlSelect = "select idPessoa,senha,perfil,entradaMin,entradaMax,acesso from Pessoa where idPessoa=? and senha=?;";
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    try
	    {
	    	
	         conn = AcessoBD.obtemConexao();
	         conn.setAutoCommit(false);
	       stm = conn.prepareStatement(sqlSelect);
	       stm.setInt(1, id);
	       stm.setString(2, senha);
	       rs = stm.executeQuery();
	       if (rs.next())
	       {
	    	   setId(rs.getInt(1));
	    	   setSenha(rs.getString(2));
	    	   setPerfil(rs.getInt(3));	    	   
	    	   setHoraMin(   rs.getString(4)   );
	    	   setHoraMax(  rs.getString(5)  );
	    	   setAcesso(rs.getBoolean(6));
	    	   setLog(true);
	    	   
	       }
	       
	    }catch (Exception e)
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


	public boolean isAcesso() {
		return acesso;
	}


	public void setAcesso(boolean acesso) {
		this.acesso = acesso;
	}


	public int getPerfil() {
		return perfil;
	}


	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}











	public String getHoraMin() {
		return horaMin;
	}











	public void setHoraMin(String horaMin) {
		this.horaMin = horaMin;
	}











	public String getHoraMax() {
		return horaMax;
	}











	public void setHoraMax(String horaMax) {
		this.horaMax = horaMax;
	}
	 
	

}
