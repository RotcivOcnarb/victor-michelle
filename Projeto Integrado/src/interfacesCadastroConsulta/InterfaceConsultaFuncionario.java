package interfacesCadastroConsulta;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Pessoa;


public class InterfaceConsultaFuncionario extends InterfaceConsulta {
	private static final long serialVersionUID = 1L;
	
	String[][] data;
	ArrayList<Pessoa> ar;
	String [] header1 = { "id", bn.getString("menu.cadastro.nome"),
			bn.getString("menu.cadastro.cargo"),
			bn.getString("menu.cadastro.cpf"),
		bn.getString("menu.cadastro.horaEntradaMinimo"),
		bn.getString("menu.cadastro.horaEntradaMaxima"),
		bn.getString("menu.cadastro.livre"),
		bn.getString("menu.cadastro.cnpj")
};
	
	
	
	public InterfaceConsultaFuncionario(ResourceBundle a,int chave,int perfil){
		super(a,chave,perfil);
		setTabela();
		inter();
		
	}
	
	
	
	
	
	public void inter(){
			super.inter();
			setTitle(bn.getString("menu.menu.funcionario"));
			
			String [] header2 = { "Id", bn.getString("menu.cadastro.nome"),
					bn.getString("menu.cadastro.cargo"),
					bn.getString("menu.cadastro.cpf"),
				bn.getString("menu.cadastro.horaEntradaMinimo"),
				bn.getString("menu.cadastro.horaEntradaMaxima"),
				bn.getString("menu.cadastro.livre"),
				bn.getString("menu.cadastro.cnpj")
		};
			
			
			for(int i=0;i<header2.length;i++){
				cad.getColumnModel().getColumn(i).setHeaderValue(header2[i]);
			}
			
			repaint();
	}

	
	

public void setTabela(){
	Pessoa em = new Pessoa();
	em.setNumero();
	em.consulta();
	int linhas = em.getNumero();
	ar = em.getAr();
	
	
	data = new String[linhas][header1.length];

	


	for(int i=0;i<header1.length;i++){
		Iterator<Pessoa> it =  ar.iterator();
		int j=0;
			while(it.hasNext()){
			Pessoa c = it.next();
		
			if(i==0){
				data[j][i] = ""+c.getId();
				}
			if(i==1){
				data[j][i] = c.getNome();
				}
			if(i==2){
				if(c.getPerfil()==0){
					data[j][i] = "Funcionario" ;
				}else if(c.getPerfil()==1){
					data[j][i] = "FuncionarioAutorizado" ;
				}else if(c.getPerfil()==2){
					data[j][i] = "Atendente" ;
				}else if(c.getPerfil()==3){
					data[j][i] = "Sindico" ;
				}

				
				}
			if(i==3){
				data[j][i] = c.getCpf();
				}
			if(i==4){
				data[j][i] = c.getEntradaMin();
				}
			if(i==5){
				data[j][i] = c.getEntradaMax();
				}
			
			if(i==6){
				data[j][i] = ""+c.isAcesso();
				}
			if(i==7){
				data[j][i] = c.getCnpj();
				}
			
			
			j++;
			}
			
			
			DefaultTableModel model = new DefaultTableModel(data, header1);
	        cad = new JTable(model) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int columnIndex) {
	             
	            	return false;
	              
	            }
	        };
	           
	        
	  
			
			scrollPane = new JScrollPane(cad);
	

	
			add(scrollPane,BorderLayout.CENTER);
		}

	

	}
		
	

	

}
