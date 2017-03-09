package interfacesCadastroConsulta;

import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.*;
import javax.swing.*;

import login.LoginGUI;
import sistema.InterfacePrincipal;


public class Interface extends JFrame implements ActionListener{
	protected ResourceBundle bn;
	protected JMenuItem reg[],close;
	protected String regName[] = {"English","Portugues","Español"};
	protected JMenu regMenu,fecharMenu;
	protected Container c;
	protected int perfil;


	

	
	protected Interface(ResourceBundle a,int perfil){
		super();
		bn = a;
		setTitle(bn.getString("menu.menu.empresa"));
		this.perfil = perfil;
		
		
		c = new Container();
		
		c = getContentPane();
		
		c.setLayout(new FlowLayout());
		
		regMenu = new JMenu(bn.getString("menu.menu.idioma"));
		reg = new JMenuItem[regName.length];

		
		for ( int count = 0; count < regName.length; count++ )
		{
			reg[ count ] = new JMenuItem( regName[ count ] ); 
			regMenu.add( reg[ count ] ); 
			reg[ count ].addActionListener( this );
			
		}
		
		
	


		
		fecharMenu = new JMenu(bn.getString("menu.menu.sair"));
		close = new JMenuItem(bn.getString("menu.empresa.retorno"));
		fecharMenu.add(close);
		close.addActionListener(this);
		
		
		JMenuBar bar = new JMenuBar();
		setJMenuBar( bar );
		bar.add(regMenu);
		bar.add(fecharMenu);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(300,150 ); 
		
		
		
		
	}
	public void inter(){
			setTitle(bn.getString("menu.menu.empresa"));
			regMenu.setText((bn.getString("menu.menu.idioma")));
			fecharMenu.setText(bn.getString("menu.menu.sair"));
			close.setText(bn.getString("menu.empresa.retorno"));
			repaint();
	}
	

	

		public void actionPerformed(ActionEvent e){
				if(e.getSource() == close){
					int x = JOptionPane.showConfirmDialog(null, bn.getString("menu.sair.confirma"));
					if(x == 0){
						new InterfacePrincipal(bn,perfil);
						
						dispose();
					}
					}
				if(e.getSource() == reg[0]){
					bn = ResourceBundle.getBundle( "sistema/idioma_en_US", Locale.US );
					inter();
					
				}
				if(e.getSource() == reg[1]){
					bn = ResourceBundle.getBundle( "sistema/idioma_pt_BR", new Locale("pt", "BR") );
					inter();
				
				}
				if(e.getSource() == reg[2]){
					bn = ResourceBundle.getBundle( "sistema/idioma_es_ES",new Locale("es", "ES") );
					inter();
					
				}
				
			
		}
	

		
	}
	




	
	

