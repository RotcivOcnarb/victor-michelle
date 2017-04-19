package dao;



//Classe “AcessoBD.java”
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;
/**
* Classe responsável pela conexão com banco de dados MySQL
*/

public class AcessoBD
{
 // -----------------------------------------------------------
 // Carrega driver JDBC
 //
	
 static
 {
    try
    {
       Class.forName("com.mysql.jdbc.Driver");
    }
    catch (ClassNotFoundException e)
    {
       throw new RuntimeException(e);
    }
 }
 
 static Connection conn;
 
 // -----------------------------------------------------------
 // Obtém conexão com o banco de dados
 public static Connection obtemConexao()
 {
	 if(conn == null){
		 try{
		 conn = DriverManager.getConnection(
	             "jdbc:mysql://localhost/empresa?user=alunos&password=alunos" ) ;
		 }
		 catch(SQLException e){
			 JOptionPane.showMessageDialog(null, e.getMessage());
			 e.printStackTrace();
			 return null;
		 }
	 }

	return conn;
	 
 }
}