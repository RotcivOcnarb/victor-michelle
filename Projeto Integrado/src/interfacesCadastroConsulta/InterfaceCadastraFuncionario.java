package interfacesCadastroConsulta;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import model.Empresa;
import model.Pessoa;
import service.EmpresaService;
import service.PessoaService;

public class InterfaceCadastraFuncionario extends InterfaceCadastra {
	private static final long serialVersionUID = 1L;
	private JLabel cn,acesso;
	private ArrayList<Empresa> ar;
	private JCheckBox cb;
	private JPanel painel;
	private JComboBox<Object> jcb;
	private Vector<Object> combo;
	private JLabel[] espaco;//1 sindico ; 2 atendente ; 3 funcionario ; 0 não entra no sistema ; 4 Administrador
	private String [] cargos = {"Funcionario","Sindico","Atendente","FuncionarioAutorizado" };
	private int user;//se 0 cadastro ; se 1 atualiza
	private int identificador;//id para atualizar
	private int chave;
	private JLabel passL;
	private JPasswordField passP;
	private PessoaService pesService;
	private EmpresaService emService;
	
	public  InterfaceCadastraFuncionario(ResourceBundle a,int perfil,int user){//user 0 para o alterar
		super(a,perfil,true); 
		this.user = user;

		remove(save);
		remove(cancel);
		
		jp = new JPanel();
		jp.setLayout(new GridLayout(9,2));
		espaco = new JLabel[2];
		espaco[0] = new JLabel();
		espaco[1] = new JLabel();
	
		pesService = new PessoaService();	
		emService = new EmpresaService();
		
		for(int i=0;i<label.length;i++){
			label[i] = new JLabel(labelNome[i]);
			jp.add(label[i]);
			if(i!= 2 && i!=3 && i!=4){
			
			
			field[i] =new JTextField();
			jp.add(field[i]);
			}
			if(i==2){
				
				SpinnerModel cargos = new SpinnerListModel(this.cargos); 
				spin1[0] = new JSpinner(cargos);
				jp.add(spin1[0]);
			}else if(i==3){
				
				
				SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, 24, 1);  
				spin1[1] = new JSpinner(model1);
				
				SpinnerNumberModel modelh = new SpinnerNumberModel(0, 0, 60, 1);  
				spin1[2] = new JSpinner(modelh);
				jp.add(espaco[0]);
				jp.add(spin1[1]);
				jp.add(spin1[2]);
				
				
				
			}else if(i==4){
				jp.add(espaco[1]);
				SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, 24, 1);  
				spin1[3] = new JSpinner(model1);
				jp.add(spin1[3]);
				SpinnerNumberModel modelh = new SpinnerNumberModel(0, 0, 60, 1);  
				spin1[4] = new JSpinner(modelh);
				jp.add(spin1[4]);
				
				
			}
			
			
		}
		if(user!=0){
			passL = new JLabel(bn.getString("menu.login.senha"));
			passP = new JPasswordField(4);
		    PlainDocument document = (PlainDocument) passP.getDocument();
	        document.setDocumentFilter(new DocumentFilter() {

	            @Override
	            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

	                if (string.length() <= 4) {
	                    super.replace(fb, offset, length, text, attrs); 
	                }
	            }

	        });
			
			
			
			
			jp.add(passL);
			jp.add(passP);
		}
		
	
		
		add(jp);
		
		
		
		combo = new Vector<Object>();
		
		painel = new JPanel();
		painel.setLayout(new GridLayout(2,1));
		
		ar = emService.getListaEmpresas();

		Iterator<Empresa> it =  ar.iterator();
		//int contadorAux=0;
		//1 sindico ; 2 atendente ; 3 funcionario ; 0 não entra no sistema ; 4 Administrador
				
		
		
		
		while(it.hasNext()){
			Empresa aux = it.next();
			combo.add(aux.getCnpj());	
		}
		cn = new JLabel("CNPJ's:");
		
		
		jcb = new JComboBox<Object>(combo);
		
		
		
		
		painel.add(cn);
		painel.add(jcb);
		
		Acao ac = new Acao();
		save.addActionListener(ac);
		cancel.addActionListener(ac);
		
		add(painel);
	
		acesso = new JLabel(bn.getString("menu.cadastro.livre"));
		cb = new JCheckBox();
		
		add(acesso);
		add(cb);
		
		add(save);
		add(cancel);
		
		setSize(850,225);
		inter();
		
		
		//validate();
	}
	

	
	public int getChave() {
		return chave;
	}



	public void setChave(int chave) {
		this.chave = chave;
	}



	public int getIdentificador() {
		return identificador;
	}



	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}



	public void inter(){
		super.inter();
		setTitle(bn.getString("menu.menu.funcionario"));
		acesso.setText(bn.getString("menu.cadastro.livre"));
		
		
		
		
		String [] header2 = {  bn.getString("menu.cadastro.nome"),
				bn.getString("menu.cadastro.cpf"),
			bn.getString("menu.cadastro.cargo"),
			bn.getString("menu.cadastro.horaEntradaMinimo"),
			bn.getString("menu.cadastro.horaEntradaMaxima")
	};
		
	
		
		for(int i=0;i<header2.length;i++){
			label[i].setText(header2[i]);
		}
		validate();
	}

	
	
	
	
	
	
	private class Acao implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == save){
				
				
				String aux="";
				for(int i=0;i<=3;i++){
					aux+= (int)(Math.random()*10);
					
				}
				//System.out.print(aux);
				//cria objeto empresa e o salva no bd
				String horaMin=""+spin1[1].getValue();
				String minutoMin = ""+spin1[2].getValue();
				if(horaMin.length() < 2){
					horaMin ="0"+spin1[1].getValue();
				}
				if(minutoMin.length() < 2){
					minutoMin ="0"+spin1[2].getValue();
				}
				
				
				String horaMax=""+spin1[3].getValue();
				String minutoMax = ""+spin1[4].getValue();
				if(horaMax.length() < 2){
					horaMax ="0"+spin1[3].getValue();
				}
				if(minutoMax.length() < 2){
					minutoMax ="0"+spin1[4].getValue();
				}
				//1 sindico ; 2 atendente ; 3 funcionario ; 0 não entra no sistema ; 4 Administrador
				int perfil=0;
					for(int i=0;i<4;i++){
						
						if(spin1[0].getValue().equals(cargos[i])){
							perfil=i;
						}
						System.out.println(perfil);
					
					}
					
				
				
				
				String horaMinutoMin = horaMin+":"+minutoMin;//hora mininima entrada
				String horaMinutoMax = horaMax+":"+minutoMax ;//hora maxima
					
				Pessoa pessoa = new Pessoa();
				pessoa.setId(getIdentificador());
				pessoa.setNome(field[0].getText());
				pessoa.setPerfil(perfil);
				pessoa.setSenha(aux);
				pessoa.setCpf(field[1].getText());
				pessoa.setEntradaMin(horaMinutoMin);
				pessoa.setEntradaMax(horaMinutoMax);
				pessoa.setAcesso(cb.isSelected());
				pessoa.setCnpj(jcb.getSelectedItem().toString());
				
				//System.out.println("hora minima"+horaMinutoMin );
				//System.out.println("hora maxima"+ horaMinutoMax);
				if(user == 0){
					if(pesService.cadastra(pessoa))
						JOptionPane.showMessageDialog(null,bn.getString("menu.cadastro.sucesso")+"\nId: "+pesService.getLastId()+ "\n"+bn.getString("menu.login.senha") +aux);
					else
						JOptionPane.showMessageDialog(null,bn.getString("menu.cadastro.falha"));
				}else{
					if(pesService.altera(pessoa)){
						JOptionPane.showMessageDialog(null,bn.getString("menu.cadastro.sucesso"));
						dispose();
						new InterfaceConsultaFuncionario(bn,getChave(),perfil);
					}
					else
						JOptionPane.showMessageDialog(null,bn.getString("menu.cadastro.falha"));
				}
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

	

