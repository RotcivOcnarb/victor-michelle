package interfacesCadastroConsulta;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.LoginGUI;
import model.Empresa;


public class InterfaceConsultaEmpresa extends InterfaceConsulta {
	String[][] data;
	ArrayList<Empresa> ar;
	String [] header1 = { bn.getString("menu.cadastro.cnpj"),bn.getString("menu.cadastro.razao"),
			bn.getString("menu.cadastro.horarioAbrirEmpresa"),
			bn.getString("menu.cadastro.horarioFecharEmpresa"),
			bn.getString("menu.cadastro.temperaturaAr")
};
	
	public InterfaceConsultaEmpresa(ResourceBundle a,int chave,int perfil){
		super(a,chave,perfil);
				setTabela();
				inter();
				
			
		
	}
	public void inter(){
			super.inter();
				String [] header2 = { bn.getString("menu.cadastro.cnpj"),bn.getString("menu.cadastro.razao"),
								bn.getString("menu.cadastro.horarioAbrirEmpresa"),
								bn.getString("menu.cadastro.horarioFecharEmpresa"),
								bn.getString("menu.cadastro.temperaturaAr")
				};
				
				
				for(int x=0;x<header2.length;x++){
					cad.getColumnModel().getColumn(x).setHeaderValue(header2[x]);
				}
			setTitle(bn.getString("menu.menu.empresa"));
			repaint();
	}
	public void setTabela(){
		Empresa em = new Empresa();
		int linhas = em.getNumero();
		ar = em.getArray();
		
		
		data = new String[linhas][header1.length];
	
		
	

		for(int i=0;i<header1.length;i++){
			Iterator<Empresa> it =  ar.iterator();
			int j=0;
				while(it.hasNext()){
				Empresa c = it.next();
			
				if(i==0){
					data[j][i] = c.getCnpj();
					}
				if(i==1){
					data[j][i] = c.getRazaoSocial();
					}
				if(i==2){
					data[j][i] = c.getHoraAbertura();
					}
				if(i==3){
					data[j][i] = c.getHoraFechamento();
					}
				if(i==4){
					data[j][i] = c.getTemperaturaAr();
					}
				j++;
				}
				
				
				DefaultTableModel model = new DefaultTableModel(data, header1);
		        cad = new JTable(model) {

		            public boolean isCellEditable(int rowIndex, int columnIndex) {
		             
		            	return false;
		              
		            }
		        };
		           
		        
		  
				
				scrollPane = new JScrollPane(cad);
		

		
				add(scrollPane,BorderLayout.CENTER);
			}

		
	
	
		}
}



	
	

