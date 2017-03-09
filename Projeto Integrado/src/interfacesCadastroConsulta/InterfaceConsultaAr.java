package interfacesCadastroConsulta;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.ListaAcesso;
import model.ControleAr;

public class InterfaceConsultaAr extends Interface{
	private ArrayList<ControleAr> aa = new ArrayList<ControleAr>();
	private String [] header1 ={
			bn.getString("menu.menu.empresa"),
				bn.getString("menu.cadastro.empresaC"),
				bn.getString("menu.consulta.temperaturaA"),
				bn.getString("menu.consulta.temperaturaM"),
				bn.getString("menu.consulta.ligadoDesligado")
			};
	private String [][] data;
	int [] temperaturaAtual ;
	private JPanel jp;
	private JTable cad;
	private Container c;
	private JScrollPane scrollPane;
	
	
	
	
	public InterfaceConsultaAr(ResourceBundle a,int perfil){
		super(a,perfil);
		
		
		
		c = new Container();
		
		c = getContentPane();
		
		c.setLayout(new BorderLayout());
		
		
		
		ControleAr la = new ControleAr();
		la.registraTemperaturas();		
		la.getConsulta();
		aa = la.getAa();
		temperaturaAtual = la.getTemperaturaAtual();
		
		setTabela(aa);

		

		setSize(850,300);
		setResizable(false);
		inter();
		
	}
public void setTabela(ArrayList<ControleAr> aa){
		
		 
		
		data = new String[aa.size()][header1.length];
		
		
		

		for(int i=0;i<header1.length;i++){
			Iterator<ControleAr> it =  aa.iterator();
			int j=0;
				while(it.hasNext()){
				ControleAr c = it.next();
			
				if(i==0){//cnpj
					
					data[j][i] = c.getCnpj();
					}
				if(i==1){//conjunto id
					data[j][i] = ""+c.getConjunto();
					}
				if(i==2){//TemperaturaAtual
					
					data[j][i] = ""+temperaturaAtual[j];
					}
				if(i==3){//TemperaturaMax
					data[j][i] = ""+c.getTemperaturaMaxima();
					}
				if(i==4){//Ligado/desligado
					if(temperaturaAtual[j]>c.getTemperaturaMaxima()){
						data[j][i] ="|";
								
					}else data[j][i] = "O" ;
					}
				

				j++;
				}
				
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
	
	public void inter(){
		super.inter();
		setTitle("");
		
		String [] header2 = { bn.getString("menu.menu.empresa"),
				bn.getString("menu.cadastro.empresaC"),
				bn.getString("menu.consulta.temperaturaA"),
				bn.getString("menu.consulta.temperaturaM"),
				bn.getString("menu.consulta.ligadoDesligado")
			};


		for(int x=0;x<header2.length;x++){
			cad.getColumnModel().getColumn(x).setHeaderValue(header2[x]);
		}
		
	
		
		
		
		repaint();
	}
	
	
}
