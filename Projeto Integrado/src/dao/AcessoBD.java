package dao;



//Classe “AcessoBD.java”
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
 
 // -----------------------------------------------------------
 // Obtém conexão com o banco de dados
 public static Connection obtemConexao()
 {
	 try{
	 Connection conn = DriverManager.getConnection(
             "jdbc:mysql://localhost/empresa?user=alunos&password=alunos" ) ;
	 return conn;
	 }
	 catch(SQLException e){
		 e.printStackTrace();
		 return null;
	 }
 }
}