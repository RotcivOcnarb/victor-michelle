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
		for(int i=1; i <= conjuntos.size(); i++){

			jl[i-1] = new JLabel(conjunto + i);
			jp.add(jl[i-1]);
			jcb[i-1] = new JCheckBox();
			jp.add(jcb[i-1]);
			if(conjService.getByID(i).isOcupado() == 1){
				jcb[i-1].setEnabled(false);
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

			//cria objeto empresa e o salva no bd
				Empresa empresa = new Empresa(field[0].getText(),
						field[1].getText(),
						field[2].getText(),
						field[3].getText(),
						field[4].getText());
				
				if(emService.cadastra(empresa)){
					for(int i = 1; i <= jcb.length;i++){
						if(jcb[i-1].isSelected()){
							Conjunto conj = new Conjunto(i, 1, field[0].getText());
							conjService.alteraConjunto(conj);
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "ERO");
				}
		
					new InterfaceCadastraEmpresa(a,perfil);
					dispose();
		
				
					
				}
				//JOptionPane.showMessageDialog(null,bn.getString("menu.cadastro.salvo"));
				//}
				//}else JOptionPane.showMessageDialog(null,w);

			
			
			

		if(e.getSource() == cancel){
			for(int i=0;i<label.length;i++){
				field[i].setText("");
			}
			validate();
		}
	}
	}

	
	
}

