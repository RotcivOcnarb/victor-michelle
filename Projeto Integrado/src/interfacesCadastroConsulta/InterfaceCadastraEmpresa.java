package interfacesCadastroConsulta;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Conjunto;
import model.Empresa;
import service.ConjuntoService;
import service.EmpresaService;
import sistema.InterfacePrincipal;




public class InterfaceCadastraEmpresa extends InterfaceCadastra{
	private static final long serialVersionUID = 1L;
		private JCheckBox [] jcb;
		private JLabel [] jl;
		protected JPanel jp;
		private int perfil;
		private ResourceBundle a; 
		private EmpresaService emService;
		private ConjuntoService conjService;
		
		ArrayList<Conjunto> conjuntos;
	public InterfaceCadastraEmpresa(ResourceBundle a,int perfil){
		super(a,perfil);
		this.a = a;
		this.perfil = perfil;
		
		conjService = new ConjuntoService();

		conjuntos = conjService.getListaConjunto();
		
		jcb = new JCheckBox[conjuntos.size()];
		jl = new JLabel[conjuntos.size()];
		
		jp = new JPanel();
		
		emService = new EmpresaService();
	
		String conjunto = bn.getString("menu.cadastro.empresaC")+" ";
		jp.setLayout(new GridLayout(4,3));
		if(conjunto != "a"){
		for(int i=0;i<conjuntos.size();i++){
			
			//System.out.print(aux[i]+" ");
			if(!conjuntos.get(i).isOcupado()){
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
	
		for(int i=0;i<conjuntos.size();i++){
		
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

	private class Teste implements ActionListener{
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == save){
			
			boolean testa = false;
			
			try{
				Integer.parseInt(field[1].getText());
				testa = true;
			}catch(NumberFormatException nfe){
				testa = false;
			}
			
			
	
		
		
		if(testa){
			
			
			
			//cria objeto empresa e o salva no bd
				Empresa empresa = new Empresa(field[0].getText(),
						field[1].getText(),
						field[2].getText(),
						field[3].getText(),
						field[4].getText());
				
				if(emService.cadastra(empresa)){
					for(int i =0;i<jcb.length;i++){
						if(jcb[i].isSelected()){
							Conjunto conj = new Conjunto(i, true, field[0].getText());
							conjService.alteraConjunto(conj);
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

