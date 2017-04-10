package interfacesCadastroConsulta;
import java.util.ResourceBundle;
import java.awt.*;
import javax.swing.*;


public class InterfaceCadastra extends Interface {
	private static final long serialVersionUID = 1L;
	
	protected JTable cad;
	protected JButton save,cancel;	
	protected String [] labelNome ={ bn.getString("menu.cadastro.cnpj"),bn.getString("menu.cadastro.razao"),
			bn.getString("menu.cadastro.horarioAbrirEmpresa"),
			bn.getString("menu.cadastro.horarioFecharEmpresa"),
			bn.getString("menu.cadastro.temperaturaAr")
	};
	
	protected JLabel [] label = new JLabel[labelNome.length];
	protected JTextField [] field = new JTextField[labelNome.length];
	protected JPanel jp,jp1;
	protected JSpinner []spin1 = new JSpinner[5];	

	
	
	public InterfaceCadastra(ResourceBundle a,int perfil,boolean chave){
		super(a,perfil);
		
		setTitle(super.bn.getString("menu.menu.empresa"));
		save = new  JButton(bn.getString("menu.cadastro.salvar"));
		add(save);
		cancel = new  JButton(bn.getString("menu.cadastro.cancelar"));
		add(cancel);
		
		
		setSize(850,200);
		setResizable(false);
		
		
	}
	
	
	
	protected InterfaceCadastra(ResourceBundle a,int perfil){
		super(a,perfil);
		jp = new JPanel();
		jp.setLayout(new GridLayout(5,2));
		
		
		
		for(int i=0;i<label.length;i++){
			label[i] = new JLabel(labelNome[i]);
			jp.add(label[i]);
			field[i] =new JTextField();
			jp.add(field[i]);
		}
		
		
	

		add(jp);
		setTitle(super.bn.getString("menu.menu.empresa"));
		save = new  JButton(bn.getString("menu.cadastro.salvar"));
		add(save);
		cancel = new  JButton(bn.getString("menu.cadastro.cancelar"));
		add(cancel);
		
		
		setSize(850,200);
		setResizable(false);
	}
	public void inter(){
			super.inter();
			setTitle(bn.getString("menu.menu.empresa"));
			save.setText(bn.getString("menu.cadastro.salvar"));
			cancel.setText(bn.getString("menu.cadastro.cancelar"));
			regMenu.setText((bn.getString("menu.menu.idioma")));
			fecharMenu.setText(bn.getString("menu.menu.sair"));
			close.setText(bn.getString("menu.empresa.retorno"));
			
			String [] labelName2 ={ bn.getString("menu.cadastro.cnpj"),bn.getString("menu.cadastro.razao"),
					bn.getString("menu.cadastro.horarioAbrirEmpresa"),
					bn.getString("menu.cadastro.horarioFecharEmpresa"),
					bn.getString("menu.cadastro.temperaturaAr")
			};
			
			for(int i=0;i<labelName2.length;i++){
				
				label[i].setText(labelName2[i]);
				
			}
			
			
			
			
			
			
			validate();
	}
	
	

		
	}
	




	
	

