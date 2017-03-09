package sistema;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.*;
import javax.swing.*;
import interfacesCadastroConsulta.*;
import login.CriaLogin;
import login.LoginGUI;
import model.Pessoa;




public class InterfacePrincipal extends JFrame {
		private Container c;
		private JButton [] jb;
		private JRadioButtonMenuItem empresa,funcionario;
		private JMenuItem reg[],close;
		private String regName[] = {"English","Portugues","Español"};
		private JMenu editMenu,fecharMenu,regMenu;
		private ButtonGroup bg;
		private ResourceBundle bn;
		private JPanel jp;
		public int perfil;
	
		
		public InterfacePrincipal( ResourceBundle a,int perfil){
			super();
			bn = a;
			this.perfil = perfil;
			setTitle(bn.getString("menu.janela.titulo"));
			AllHandler ah = new AllHandler();
			
			String [] jbname =  {
					bn.getString("menu.botao.cadastrar"),bn.getString("menu.botao.consultar"),
					bn.getString("menu.botao.enviarAcesso"),
					bn.getString("menu.botao.enviarTemperatura"),
					bn.getString("menu.botao.consultaCatraca"),
					bn.getString("menu.botao.ajuda")
					};
			
			c = new Container();
			c = getContentPane();
			c.setLayout(new BorderLayout());
			
			jp = new JPanel();
			jp.setLayout(new GridLayout(6,1));
			
			
			
			regMenu = new JMenu(bn.getString("menu.menu.idioma"));
			reg = new JMenuItem[regName.length];

			
			for ( int count = 0; count < regName.length; count++ )
			{
				reg[ count ] = new JMenuItem( regName[ count ] ); 
				regMenu.add( reg[ count ] ); 
				reg[ count ].addActionListener( ah );
				
			}

			
			editMenu = new JMenu(bn.getString("menu.menu.sistema"));
			empresa = new JRadioButtonMenuItem(bn.getString("menu.menu.empresa"));
			funcionario = new JRadioButtonMenuItem(bn.getString("menu.menu.funcionario"));
			editMenu.add(empresa);
			editMenu.add(funcionario);
			bg = new ButtonGroup();
			bg.add(empresa);
			bg.add(funcionario);
			empresa.setSelected(true);
			empresa.addActionListener( ah );
			funcionario.addActionListener( ah );
			
			
			fecharMenu = new JMenu(bn.getString("menu.menu.sair"));
			close = new JMenuItem(bn.getString("menu.menu.sair"));
			fecharMenu.add(close);
			close.addActionListener(ah);
			
		
			
			
			
			jb = new JButton[jbname.length];
			for ( int count = 0; count < jbname.length; count++ )
			{
				jb[ count ] = new JButton( jbname[ count ] ); 
				jb[ count ].addActionListener( ah );
				jp.add(jb[count]);
				
			}
			
			Painel imagem = new Painel("src/sistema/predio.png");
			c.add(imagem, BorderLayout.CENTER);
			
			
			setPerfil();
			
			
			
			c.add(jp,BorderLayout.EAST);
			JMenuBar bar = new JMenuBar();
			setJMenuBar( bar );
			bar.add(editMenu);
			bar.add(regMenu);
			bar.add(fecharMenu);
			setVisible(true);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setResizable(false);
			setSize(755,515 ); 
		
			
			
		}
		public void inter(){
			setTitle(bn.getString("menu.janela.titulo"));
			jb[0].setText(bn.getString("menu.botao.cadastrar"));
			jb[1].setText(bn.getString("menu.botao.consultar"));
			jb[2].setText(bn.getString("menu.botao.enviarAcesso"));
			jb[3].setText(bn.getString("menu.botao.enviarTemperatura"));
			jb[4].setText(bn.getString("menu.botao.consultaCatraca"));
			jb[5].setText(bn.getString("menu.botao.ajuda"));

			
			
			
			regMenu.setText((bn.getString("menu.menu.idioma")));
			editMenu.setText((bn.getString("menu.menu.sistema"))); 
			empresa.setText((bn.getString("menu.menu.empresa")));
			funcionario.setText((bn.getString("menu.menu.funcionario")));
			fecharMenu.setText(bn.getString("menu.menu.sair"));
			close.setText(bn.getString("menu.menu.sair"));
			validate();
	
		
		}
		
		public void setPerfil(){//1 sindico ; 2 atendente ; 3 funcionario ; 0 não entra no sistema ; 4 Administrador
			if(perfil ==2){
				jb[4].setEnabled(false);
			}else if(perfil == 3){
				jb[0].setEnabled(false);
				jb[2].setEnabled(false);
				jb[3].setEnabled(false);
				jb[4].setEnabled(false);
				funcionario.setEnabled(false);
				
				
				// pode alterar empresa , consultar empresa
			}
			
			
		}
	
		
		
		
	
	private class AllHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
	
			
			
				if(e.getSource() == close){
					int x = JOptionPane.showConfirmDialog(null, bn.getString("menu.sair.confirma"));
					if(x == 0){
						
						dispose();
						//new LoginGUI();
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
				
				if(e.getSource()== jb[0]){
					if(empresa.isSelected()){

						new InterfaceCadastraEmpresa(bn,perfil);
						dispose();
						
					}
					if(funcionario.isSelected()){
						new InterfaceCadastraFuncionario(bn,perfil,0);
						dispose();
					}
					
					
				}
				
				if(e.getSource()== jb[1]){
					if(empresa.isSelected()){

						new InterfaceConsultaEmpresa(bn,0,perfil);
						dispose();
						
					}
					if(funcionario.isSelected()){
						new InterfaceConsultaFuncionario(bn,1,perfil);
						dispose();
					}
						
				}
				if(e.getSource() == jb[2] ){
				 ArrayList<Pessoa> ar = new ArrayList<Pessoa> ();
				 Pessoa ps = new Pessoa();
				 ps.consulta();
				 ar = ps.getAr();
				 Iterator<Pessoa> it = ar.iterator();
				 CriaLogin cr = new CriaLogin();
				 cr.criaArquivo();
				 while(it.hasNext()){
					 Pessoa aux = it.next();
					 //perfil,login,senha,hem,hemi
					 
					 cr.addLogin(aux.getPerfil(),aux.getId(),aux.getSenha(),aux.getEntradaMax(),aux.getEntradaMin(),aux.isAcesso()); 
					 
				 }
				 
				 cr.closeFile();
				 
				 
				}
				
				
				if(e.getSource() == jb[4]){
					new InterfaceConsultaAcessos(bn,perfil);
				}
				
				
				if(e.getSource() == jb[5]){
					JOptionPane.showMessageDialog(null,bn.getString("menu.texto.ajuda"));
					//instruções
				}
				
				
				
			}
		}
	

		
	
	

}

