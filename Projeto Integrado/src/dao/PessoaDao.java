package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import login.CryptoAES;

public class PessoaDao {
	private int perfil,total,id;
	private String nome,cpf,senha,entradaMax,entradaMin,cnpj;
	private boolean acesso;
	private byte[] encrypt;
	private CryptoAES cr;
	Connection conn = null;
	
	public PessoaDao(int perfil, String nome, String cpf, String senha, String entradaMax, String entradaMin,boolean acesso,
			String cnpj) {

		this.perfil = perfil;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.entradaMax = entradaMax;
		this.entradaMin = entradaMin;
		this.acesso = acesso;
		this.cnpj = cnpj;
		
		
	}
	public PessoaDao(int id,int perfil, String nome, String cpf, String senha, String entradaMax, String entradaMin,boolean acesso,
			String cnpj) {
		this.id = id;
		this.perfil = perfil;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.entradaMax = entradaMax;
		this.entradaMin = entradaMin;
		this.acesso = acesso;
		this.cnpj = cnpj;
		
		
	}
	
	
	
	
	public PessoaDao(){};


	
	
	public int getId() {
		return id;
	}
	public int getPerfil() {
		return perfil;
	}
	public int getTotal() {
		return total;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getSenha() {
		return senha;
	}
	public String getEntradaMax() {
		return entradaMax;
	}
	public String getEntradaMin() {
		return entradaMin;
	}
	public String getCnpj() {
		return cnpj;
	}
	public boolean isAcesso() {
		return acesso;
	}
	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setId(int id) {
		
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setEntradaMax(String entradaMax) {
		this.entradaMax = entradaMax;
	}
	public void setEntradaMin(String entradaMin) {
		this.entradaMin = entradaMin;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setAcesso(boolean acesso) {
		this.acesso = acesso;
	}
	public boolean cadastra()
	 {
		cr = new CryptoAES(senha);
		String aux="";
		
		try {
			encrypt = cr.encrypt(senha);
	        for (int i=0; i<encrypt.length; i++){
	        	aux += encrypt[i];
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setSenha(aux);
		
		
		
		
		boolean retorno = true;
	    String sqlInsert = "insert into Pessoa(nome,perfil,cpf,senha,entradaMax,entradaMin,acesso,EmpresaCnpj ) values(?,?,?,?,?,?,?,?)";
	    PreparedStatement stm = null;

	    try
	    {
	    	AcessoBD bd = new AcessoBD();
		    conn = bd.obtemConexao();
		    conn.setAutoCommit(false);
	       stm = conn.prepareStatement(sqlInsert);
	       stm.setString(1, getNome());
	       stm.setInt(2, getPerfil());
	       stm.setString(3, getCpf());
	       stm.setString(4, getSenha());
	       stm.setString(5, getEntradaMax());
	       stm.setString(6, getEntradaMin());
	       stm.setBoolean(7, isAcesso());
	       stm.setString(8, getCnpj());
	       
	       
	   
	       //System.out.println(stm);
	       stm .execute();

	       conn.commit(); // ADDED
	       
	       stm.close();
	       
	    }
	    catch (Exception e)
	    {
	    	 //JOptionPane.showMessageDialog(null, "Mensagem generica de erro de bd");
	    	e.printStackTrace();
	    	retorno = false;
	    	
	       try
	       {
	          conn.rollback();
	          
	       }
	       catch (SQLException e1){
	    	   retorno = false;
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
	        	  retorno = false;
	             //System.out.print(e1.getStackTrace());
	          }
	       }
	    }
	    return retorno;
	 }
	
	
public int getLastId(){
	 String sqlSelect = "select idPessoa from Pessoa order by idPessoa desc;";
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
		
public void consulta(int id)
{
   String sqlSelect = "SELECT * FROM Pessoa where idPessoa = ?";
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
    	  
   	setId(rs.getInt(1));
   	setNome(rs.getString(2));
   	setPerfil(rs.getInt(3));
   	setCpf(rs.getString(4));
   	setSenha(rs.getString(5));
   	setEntradaMax(rs.getString(6));
   	setEntradaMin(rs.getString(7));
   	setAcesso(rs.getBoolean(8));
   	setCnpj(rs.getString(9));
   


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
public void getNumeroFuncionario(){
	 
	 
    String sqlSelect = "select COUNT(*) from Pessoa;";
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
         setTotal(rs.getInt(1));
         
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
public void altera(int id,String nome,int perfil,String senha,String cpf,String ema,String emi,String cnpj,boolean acesso){
	 String sqlUpdate = "update Pessoa set nome=?,perfil=?,cpf=?,senha=?,entradaMax=?,entradaMin=?,acesso=?,EmpresaCnpj=? where idPessoa=?;";
	 PreparedStatement stm = null;
	 cr = new CryptoAES(senha);
		String aux="";
		try {
			encrypt = cr.encrypt(senha);
	        for (int i=0; i<encrypt.length; i++){
	        	aux += encrypt[i];
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	 
	 
	 try
	    {
	    	AcessoBD bd = new AcessoBD();
	        conn = bd.obtemConexao();
	        conn.setAutoCommit(false);
	       stm = conn.prepareStatement(sqlUpdate);
	      
	       
	       
	       
	       
	       stm.setString(1, nome);
	       stm.setInt(2, perfil);
	       stm.setString(3, cpf);
	       stm.setString(4, aux);
	       stm.setString(5, ema);
	       stm.setString(6, emi);
	       stm.setBoolean(7, acesso);
	       stm.setString(8, cnpj);
	       stm.setInt(9, id);
	       
	       
	       
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
	             e1.getStackTrace();
	          }
	       }
	    }
	    
	 }
	
public void exclui(int a){
	 String sqlUpdate = "delete from Pessoa where idPessoa=?;";
	 PreparedStatement stm = null;
	    try
	    {
	    	AcessoBD bd = new AcessoBD();
	        conn = bd.obtemConexao();
	        conn.setAutoCommit(false);
	       stm = conn.prepareStatement(sqlUpdate);
	      
	       
	       stm.setInt(1,a);
	       
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
