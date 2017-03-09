package interfacesCadastroConsulta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import login.ListaAcesso;
import model.Empresa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class InterfaceConsultaAcessos extends Interface {
		private JCheckBox [] jcb;
		private ButtonGroup grupo;
		private String [] checkNome = {
				bn.getString("menu.consulta.acessos"),bn.getString("menu.consulta.acessosEmpresa"),
				bn.getString("menu.consulta.acessosData"),bn.getString("menu.consulta.acessosSindi"),
		};
		private JLabel [] label;
		private String [] header1 ={bn.getString("menu.cadastro.nome"),
				bn.getString("menu.cadastro.cpf"),
				bn.getString("menu.consulta.acessoIdF"),
				bn.getString("menu.consulta.acessoIdA"),
				bn.getString("menu.consulta.acessoDS"),
				bn.getString("menu.consulta.acessoDE"),
				bn.getString("menu.cadastro.associado")
				};
		private String [][] data;
		private JPanel jp;
		private JTable cad;
		private Container c;
		private JScrollPane scrollPane;
		private ArrayList<ListaAcesso> aa = new ArrayList<ListaAcesso>();
		
		
	
	public InterfaceConsultaAcessos(ResourceBundle a,int perfil){
		super(a,perfil);
		c = new Container();
		
		c = getContentPane();
		
		c.setLayout(new BorderLayout());
		
		jcb = new JCheckBox[checkNome.length];
		label = new JLabel[checkNome.length];
		jp = new JPanel();
		jp.setLayout(new FlowLayout());
		grupo = new ButtonGroup();
		Handler ha = new Handler();
		
		for(int i=0;i<checkNome.length;i++){
			
			label[i] = new JLabel(checkNome[i]);
			jcb[i] = new JCheckBox();
			jcb[i].addActionListener(ha);
			grupo.add(jcb[i]);
			jp.add(label[i]);
			jp.add(jcb[i]);
			
			
		}
		jcb[0].setSelected(true);
		ListaAcesso la = new ListaAcesso();
		la.getRegistro();
		aa = la.getAa();
		
		
		setTabela(aa);
		
		c.add(jp, BorderLayout.NORTH);
		

		setSize(850,300);
		setResizable(false);
		inter();
	}
	
	
	public void inter(){
		super.inter();
		setTitle(bn.getString("menu.menu.empresa"));

		String [] header2 ={bn.getString("menu.cadastro.nome"),
				bn.getString("menu.cadastro.cpf"),
				bn.getString("menu.consulta.acessoIdF"),
				bn.getString("menu.consulta.acessoIdA"),
				bn.getString("menu.consulta.acessoDS"),
				bn.getString("menu.consulta.acessoDE"),
				bn.getString("menu.cadastro.associado")
				};
		
		
		
		

		String [] checkNome2 = {
				bn.getString("menu.consulta.acessos"),bn.getString("menu.consulta.acessosEmpresa"),
				bn.getString("menu.consulta.acessosData"),bn.getString("menu.consulta.acessosSindi"),
		};
		
		
		for(int i=0;i<checkNome2.length;i++){
			
			label[i].setText(checkNome2[i]);	

		}
		
		for(int x=0;x<header2.length;x++){
				cad.getColumnModel().getColumn(x).setHeaderValue(header2[x]);
		}
		
		
		
		
		
		
		
		repaint();
}
	
	
	
	
	
	
	
	public void setTabela(ArrayList<ListaAcesso> aa){

		
		
		data = new String[aa.size()][header1.length];
		
		
		

		for(int i=0;i<header1.length;i++){
			Iterator<ListaAcesso> it =  aa.iterator();
			int j=0;
				while(it.hasNext()){
				ListaAcesso c = it.next();
			
				if(i==0){//nome
					
					data[j][i] = c.getNome();
					}
				if(i==1){//cpf
					data[j][i] = c.getCpf();
					}
				if(i==2){//idFuncionario
					data[j][i] = ""+c.getId();
					}
				if(i==3){//idAcesso
					data[j][i] = ""+c.getIdAcesso();
					}
				if(i==4){//data Saida
					data[j][i] = c.getDiaSaida()+"/"+(c.getMesSaida()+1)+"/"+c.getAnoSaida()+" - "+c.getHoraSaida()+":"+c.getMinutoSaida();
					}
				if(i==5){//Data Entrada
					data[j][i] = c.getDiaEntrada()+"/"+(c.getMesEntrada()+1)+"/"+c.getAnoEntrada()+" - "+c.getHoraEntrada()+":"+c.getMinutoEntrada();
				}
				if(i==6){//cnpj
					data[j][i] = c.getCnpj();
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
	
	
	/*
	 
	  Todo acesso ao edifício deve ser registrado, armazenando as seguintes informações: identificação
	da pessoa, dia e horário de entrada e saída. Disponibilizar uma consulta listando os acessos ao
	edifício filtrando por um período de datas e por empresa (criar uma opção para pesquisa dos
	acessos de atendentes e síndico). O período não pode ser superior a 1 ano. As informações
	nome, cpf, data e horário de entrada e saída devem estar ordenadas de forma decrescente pelo
	dia e horário de saída. Somente o síndico terá acesso a esta consulta.
	  
	 */
	
	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			
			
			
			
			if(e.getSource() == jcb[0]){
			ListaAcesso la = new ListaAcesso();
			la.getRegistro();
			aa = la.getAa();
			setTabela(aa);
			validate();
			}
			
			
			
			
			
			if(e.getSource() == jcb[1]){
				ListaAcesso lc = new ListaAcesso();
				lc.getRegistroEmpresa();
				aa = lc.getAa();
				setTabela(aa);
				validate();
				
				
				
				
				
				
			}
			if(e.getSource() == jcb[2]){
				try{
				String [] g = {"Dia:","Mes: ","Ano: "};
				JLabel [] dia = new JLabel[3];
				
				JTextField [] texto = new JTextField[3];
				
				JPanel entrada = new JPanel();
				entrada.setLayout(new GridLayout(3,2));
				
				for(int i =0;i<g.length;i++){
					dia[i] = new JLabel(g[i]);
					texto[i] = new JTextField();
					
					entrada.add(dia[i]);
					entrada.add(texto[i]);
					
					
					
				}
				
				JOptionPane.showMessageDialog(null, entrada);
				
				int d = Integer.parseInt(texto[0].getText());
				int m = Integer.parseInt(texto[1].getText());
				int a = Integer.parseInt(texto[2].getText());
				
				ListaAcesso lc = new ListaAcesso();
				lc.getRegistroData(d,m,a);
				aa = lc.getAa();
				setTabela(aa);
				validate();
				
					
				}catch(Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Entrada invalida");
				}
				
				
				
			}
			if(e.getSource() == jcb[3]){
				ListaAcesso lc = new ListaAcesso();
				lc.getRegistroSindico();
				aa = lc.getAa();
				setTabela(aa);
				validate();
				
				
			}
			
			
		}

	}
	
	
	
	
	
	
	
	
	
	
}
