package sistema;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Painel extends JPanel{
	private static final long serialVersionUID = 1L;
	//Atributo
	private String arquivo;
		
	//Construtor
	public Painel( String arquivo )
	{
		setArquivo( arquivo );
		setLayout( null );
		
	}
	
	//Set
	public void setArquivo( String arquivo )
	{
		this.arquivo = arquivo;
	}
	
	//Get
	public String getArquivo(  )
	{
		return arquivo;
	}
	
	
	//Pinta a imagem de fundo 
	public void paintComponent( Graphics grafico )
	{
		Graphics2D gr = ( Graphics2D ) grafico.create( );
		
		try
		{
			BufferedImage b = ImageIO.read( new File( getArquivo( ) ) );
			gr.drawImage( b , null , 0 , 0 );
			
		}
		catch( Exception e )
		{
			//JOptionPane.showMessageDialog( null , "Erro ao desenhar a imagem ");
		}
	}
}	