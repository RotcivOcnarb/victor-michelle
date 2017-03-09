package interfacesCadastroConsulta;


import java.awt.GridLayout;
import java.awt.event.*;

import java.util.ResourceBundle;
import javax.swing.*;

import login.LoginGUI;
import model.Empresa;
import model.ListaConjunto;
import sistema.InterfacePrincipal;




public class InterfaceCadastraEmpresa extends InterfaceCadastra{
		ListaConjunto lc = new ListaConjunto();
		boolean [] aux = lc.getIdsLivres();
		String [] conjuntos = new String[lc.getNumero()];
		private JCheckBox [] jcb = new JCheckBox[lc.getNumero()];
		private JLabel [] jl = new JLabel[lc.getNumero()];
		protected JPanel jp;
		private int perfil;
		private ResourceBundle a; 
		
	public InterfaceCadastraEmpresa(ResourceBundle a,int perfil){
		super(a,perfil);
		this.a = a;
		this.perfil = perfil;
		
		jp = new JPanel();
	
		String conjunto = bn.getString("menu.cadastro.empresaC")+" ";
		jp.setLayout(new GridLayout(4,3));
		if(libera()){
		for(int i=0;i<conjuntos.length;i++){
			
			//System.out.print(aux[i]+" ");
			if(!aux[i]){
			jl[i] = new JLabel(conjunto +(i+1));
			jp.add(jl[i]);
			jcb[i] = new JCheckBox();
			jp.add(jcb[i]);
			}else{
				jl[i] = new JLabel(conjunto +(i+1));
				jp.add(jl[i]);
				jcb[i] = new JCheckBox();
				jp.add(jcb[i]);
				jcb[i].setEnabled(false);
			}
		}
		
		remove(save);
		remove(cancel);
		
		add(jp);
		add(save);
		add(cancel);
		Teste te = new Teste();//actionListener
		save.addActionListener(te);
		cancel.addActionListener(te);
		
		}else {
			JOptionPane.showMessageDialog(null, "nenhum conjunto disponivel");
			new InterfacePrincipal(a,perfil);
			dispose();
		}
		setSize(800,200);
		inter();
		validate();
	}
	
	public void inter(){
		super.inter();
		String conjunto = bn.getString("menu.cadastro.empresaC")+" ";
	
		for(int i=0;i<conjuntos.length;i++){
		
			jl[i].setText(conjunto +(i+1));
		}
		
		
		
		
		
		
		String [] header2 = { bn.getString("menu.cadastro.cnpj"),bn.getString("menu.cadastro.razao"),
						bn.getString("menu.cadastro.horarioAbrirEmpresa"),
						bn.getString("menu.cadastro.horarioFecharEmpresa"),
						bn.getString("menu.cadastro.temperaturaAr")};
		
		for(int i=0;i<header2.length;i++){
			label[i].setText(header2[i]);
		}
		
	}
	public boolean libera(){
		if(lc.getNumeroVazios()==0)
			return false;
		
		return true;
		
	}
	
	
	private class Teste implements ActionListener{
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == save){
			
			boolean testa = false;
			
			try{
				int x = Integer.parseInt(field[1].getText());
				testa = true;
			}catch(NumberFormatException nfe){
				testa = false;
			}
			
			
	
		
		
		if(testa){
			
			
			
			//cria objeto empresa e o salva no bd
				Empresa em = new Empresa(field[0].getText(),
						field[1].getText(),
						field[2].getText(),
						field[3].getText(),
						field[4].getText());
				
				if(em.cadastra()){
					for(int i =0;i<jcb.length;i++){
						if(jcb[i].isSelected()){
							lc.updateConjunto(field[0].getText(), i+1);
						}
					}
				}
		
					new InterfaceCadastraEmpresa(a,perfil);
					dispose();
		
				
					
				}
				//JOptionPane.showMessageDialog(null,bn.getString("menu.cadastro.salvo"));
				//}
				//}else JOptionPane.showMessageDialog(null,w);
			
			}
			
			
			
			
			

		if(e.getSource() == cancel){
			for(int i=0;i<label.length;i++){
				field[i].setText("");
			}
			validate();
		}
	}
	}

	
	
}

