package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import login.CryptoAES;
import model.Pessoa;

public class PessoaDao {
//	private int perfil,total,id;
//	private String nome,cpf,senha,entradaMax,entradaMin,cnpj;
//	private boolean acesso;
	private byte[] encrypt;
	private CryptoAES cr;
	Connection conn = null;

	public boolean cadastra(Pessoa pessoa){
		cr = new CryptoAES(pessoa.getSenha());
		String aux="";
		try{
			encrypt = cr.encrypt(pessoa.getSenha());
	        for (int i=0; i<encrypt.length; i++){
	        	aux += encrypt[i];
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		pessoa.setSenha(aux);

		boolean retorno = true;
	    String sqlInsert = "insert into Pessoa(nome,perfil,cpf,senha,entradaMax,entradaMin,acesso,EmpresaCnpj ) values(?,?,?,?,?,?,?,?)";
	    PreparedStatement stm = null;

	    try{
	    	AcessoBD bd = new AcessoBD();
		    conn = bd.obtemConexao();
		    conn.setAutoCommit(false);
		    stm = conn.prepareStatement(sqlInsert);
		    stm.setString(1, pessoa.getNome());
		    stm.setInt(2, pessoa.getPerfil());
		    stm.setString(3, pessoa.getCpf());
		    stm.setString(4, pessoa.getSenha());
		    stm.setString(5, pessoa.getEntradaMax());
		    stm.setString(6, pessoa.getEntradaMin());
		    stm.setBoolean(7, pessoa.isAcesso());
		    stm.setString(8, pessoa.getCnpj());
		    
		    stm .execute();
		    conn.commit();
		    stm.close();
	       
	    }
	    catch (Exception e){
	    	e.printStackTrace();
	    	retorno = false;
	       try{
	    	   conn.rollback();
	       }
	       catch (SQLException e1){
	    	   retorno = false;
	       }
	    }
	    finally{
	       if (stm != null){
	          try{
	        	  stm.close();
	          }
	          catch (SQLException e1){
	        	  retorno = false;
	          }
	       }
	    }
	    return retorno;
	}
	
	
	public int getLastId(){
		 String sqlSelect = "SELECT IDENT_CURRENT('pessoa')";
		 PreparedStatement stm = null;
		 ResultSet rs = null;
		 try{
			 AcessoBD bd = new AcessoBD();
			 conn = bd.obtemConexao();
			 conn.setAutoCommit(false);
			 stm = conn.prepareStatement(sqlSelect);
			 rs = stm.executeQuery();
			 if (rs.next()){
				 return rs.getInt(1);
			 }
		 }
		 catch (Exception e){
			 e.printStackTrace();
			 try{
				 conn.rollback();
			 }
			 catch (SQLException e1){
				 System.out.print(e1.getStackTrace());
			 }
		 }
		 finally{
			 if (stm != null){
				 try{
					 stm.close();
				 }
				 catch (SQLException e1){
					 System.out.print(e1.getStackTrace());
				 }
			 }
		 }
		 return -1;
	}
		
	public Pessoa consulta(int id){
		String sqlSelect = "SELECT * FROM Pessoa where idPessoa = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			AcessoBD bd = new AcessoBD();
	        conn = bd.obtemConexao();
	        conn.setAutoCommit(false);
	        stm = conn.prepareStatement(sqlSelect);
	        stm.setInt(1, id);
	        rs = stm.executeQuery();
	        
	        if (rs.next()){
	        	Pessoa pessoa = new Pessoa();
	    	  
	        	pessoa.setId(rs.getInt(1));
	        	pessoa.setNome(rs.getString(2));
	        	pessoa.setPerfil(rs.getInt(3));
	        	pessoa.setCpf(rs.getString(4));
	        	pessoa.setSenha(rs.getString(5));
	        	pessoa.setEntradaMax(rs.getString(6));
	        	pessoa.setEntradaMin(rs.getString(7));
	        	pessoa.setAcesso(rs.getBoolean(8));
	        	pessoa.setCnpj(rs.getString(9));
	        	return pessoa;
	        }
	   }
	   catch (Exception e){
		   e.printStackTrace();
		   try{
			   conn.rollback();
		   }
		   catch (SQLException e1){
			   System.out.print(e1.getStackTrace());
		   }
	   }
	   finally{
		   if (stm != null){
			   try{
				   stm.close();
			   }
			   catch (SQLException e1){
				   System.out.print(e1.getStackTrace());
			   }
		   }
	   }
	return null;
	}
	public int getNumeroFuncionario(){
	    String sqlSelect = "select COUNT(*) from Pessoa;";
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    try{
	    	AcessoBD bd = new AcessoBD();
	    	conn = bd.obtemConexao();
	    	conn.setAutoCommit(false);
	    	stm = conn.prepareStatement(sqlSelect);
	    	rs = stm.executeQuery();
	    	if (rs.next()){
	    		return rs.getInt(1);
	    	}
	    }
	    catch (Exception e){
	    	e.printStackTrace();
	    	try{
	    		conn.rollback();
	    	}
	    	catch (SQLException e1){
	    		System.out.print(e1.getStackTrace());
	    	}
	    }
	    finally{
	    	if (stm != null){
	    		try{
	    			stm.close();
	    		}
	    		catch (SQLException e1){
	    			System.out.print(e1.getStackTrace());
	    		}
	    	}
	    }
	    return -1;
	}
	public void altera(Pessoa pessoa){
		String sqlUpdate = "update Pessoa set nome=?,perfil=?,cpf=?,senha=?,entradaMax=?,entradaMin=?,acesso=?,EmpresaCnpj=? where idPessoa=?;";
		PreparedStatement stm = null;
		cr = new CryptoAES(pessoa.getSenha());
		String aux="";
		try{
			encrypt = cr.encrypt(pessoa.getSenha());
		       for (int i=0; i<encrypt.length; i++){
		       	aux += encrypt[i];
		       }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try{
		    AcessoBD bd = new AcessoBD();
		    conn = bd.obtemConexao();
		    conn.setAutoCommit(false);
		    stm = conn.prepareStatement(sqlUpdate);
	
		    stm.setString(1, pessoa.getNome());
		    stm.setInt(2, pessoa.getPerfil());
		    stm.setString(3, pessoa.getCpf());
		    stm.setString(4, aux);
		    stm.setString(5, pessoa.getEntradaMax());
		    stm.setString(6, pessoa.getEntradaMin());
		    stm.setBoolean(7, pessoa.isAcesso());
		    stm.setString(8, pessoa.getCnpj());
		    stm.setInt(9, pessoa.getId());
		    stm .executeUpdate();
		    conn.commit(); // ADDED
		    stm.close();
		}
		catch (Exception e){
			e.printStackTrace();
			try{
				conn.rollback();
			}
			catch (SQLException e1){
				e.printStackTrace();
			}
		}
		finally{
			if (stm != null){
				try{
					stm.close();
				}
				catch (SQLException e1){
					e1.getStackTrace();
				}
			}
		}
	}	
	public void exclui(int id){
		 String sqlUpdate = "delete from Pessoa where idPessoa=?;";
		 PreparedStatement stm = null;
		 try{
			 AcessoBD bd = new AcessoBD();
			 conn = bd.obtemConexao();
			 conn.setAutoCommit(false);
			 stm = conn.prepareStatement(sqlUpdate);
			 stm.setInt(1, id);
			 stm .executeUpdate();
			 conn.commit(); // ADDED
			 stm.close();
		 }
		 catch (Exception e){
			 e.printStackTrace();
			 try{
				 conn.rollback();
			 }
			 catch (SQLException e1){
				 e1.printStackTrace();
			 }
		 }
		 finally{
			 if (stm != null){
				 try{
					 stm.close();
				 }
				 catch (SQLException e1){
					 e1.printStackTrace();
				 }
			 }
		 } 
	}
}

