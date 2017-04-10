package login;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


import java.util.Locale;
import java.util.ResourceBundle;

public class LoginGUICatraca extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel log,sen;
	private JTextField tlog;
	private JPasswordField pass;
	private JButton confirm;
	private JRadioButtonMenuItem idi[];
	private ButtonGroup regGroup;
	private String regName[] = {"English","Portugues","Español"};
	private Container c;
	private JMenu regMenu;
	private ResourceBundle bn;

	ReadLogin rl;
	
	public LoginGUICatraca(){
		super();

		
		bn = ResourceBundle.getBundle( "sistema/idioma_en_US", Locale.US );
		setTitle(bn.getString("menu.acesso"));
		MenuHandler ah = new MenuHandler();
		
		c = getContentPane();
		c.setLayout(new GridLayout(3,2));
		
		log = new JLabel( bn.getString("menu.login.login") );
		sen = new JLabel( bn.getString("menu.login.senha"));
		tlog= new JTextField(10);
		pass = new JPasswordField(10);
		confirm = new JButton( bn.getString("menu.login.ok"));
		confirm.addActionListener(ah);
		JLabel espaço = new JLabel();
		
		regMenu = new JMenu( bn.getString("menu.menu.idioma"));
		idi = new JRadioButtonMenuItem[regName.length];
		regGroup = new ButtonGroup();	
		
		
	
		
		for ( int count = 0; count < regName.length; count++ )
		{
			idi[ count ] = new JRadioButtonMenuItem( regName[ count ] ); 
			regMenu.add( idi[ count ] ); 
			regGroup.add(idi[ count ] );
			idi[ count ].addActionListener( ah );
			
		}
		idi[0].setSelected(true);
		
		
		
		
		JMenuBar bar = new JMenuBar();
		setJMenuBar( bar );
		bar.add(regMenu);
	
		c.add(log);
		c.add(tlog);
		c.add(sen);
		c.add(pass);
		c.add(espaço);
		c.add(confirm);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(300,150 ); 
		setVisible( true );
		setLocation(750,300);
		
		
	}
	
	
	public void Inter(){
		log.setText(bn.getString("menu.login.login") );
		sen.setText( bn.getString("menu.login.senha"));
		confirm.setText( bn.getString("menu.login.ok"));
		regMenu.setText( bn.getString("menu.menu.idioma"));
		validate();
		
		
	}
	
	
	

	private class MenuHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == confirm){
						
				int a = Integer.parseInt(tlog.getText());
				String b = new String(pass.getPassword());
			
				
			
				
					rl = new ReadLogin(a,b);	
					rl.openFile();
					rl.readRecords();
					
					//System.out.println(rl.getCheck());
					if(rl.getCheck()){
						
						
						
						
						JOptionPane.showMessageDialog(null,bn.getString("menu.janela.realizado"),"Login",1);
						tlog.setText("");
						pass.setText("");
					}else{
						JOptionPane.showMessageDialog(null,bn.getString("menu.janela.naoRealizado"),"Login",1);
						tlog.setText("");
						pass.setText("");
					}
					
					rl.closeFile();
					
					
				}
	
			
			if(idi[0].isSelected()){
				bn = ResourceBundle.getBundle( "sistema/idioma_en_US", Locale.US );
				Inter();

			}
			if(idi[1].isSelected()){
				bn = ResourceBundle.getBundle( "sistema/idioma_pt_BR", new Locale("pt", "BR") );
				Inter();
			}
			if(idi[2].isSelected()){
				bn = ResourceBundle.getBundle( "sistema/idioma_es_ES",new Locale("es", "ES") );
				Inter();
			}
			
			
			
				
		}
	
	
	}

}

