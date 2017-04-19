package interfacesCadastroConsulta;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Empresa;
import service.EmpresaService;
import service.PessoaService;


public class InterfaceConsulta extends Interface {
	private static final long serialVersionUID = 1L;
	
	private JButton [] jb;
	private int chave;
	protected JTable cad;
	protected JScrollPane scrollPane;
	private PessoaService pesService;
	private EmpresaService emService;
	
	protected InterfaceConsulta(ResourceBundle a,int chave,int perfil){
		super(a,perfil);
		this.chave = chave;
		AllHandler ah = new AllHandler();
		 c.setLayout(new BorderLayout());
		setTitle(bn.getString("menu.menu.empresa"));
		
		pesService = new PessoaService();
		emService = new EmpresaService();
		
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
		
		String [] jbname =  {
				bn.getString("menu.botao.alterar"),bn.getString("menu.botao.excluir")
				};
		jb = new JButton[jbname.length];
		for ( int count = 0; count < jbname.length; count++ )
		{
			jb[ count ] = new JButton( jbname[ count ] ); 
			jb[ count ].addActionListener( ah );
			jp.add(jb[count]);
			
		}
		c.add(jp, BorderLayout.SOUTH);
		setSize(850,300);
		setResizable(false);

		setPerfil();
		
		
	}
	public void inter(){
			super.inter();
			setTitle(bn.getString("menu.menu.empresa"));
			jb[0].setText(bn.getString("menu.botao.alterar"));
			jb[1].setText(bn.getString("menu.botao.excluir"));
			validate();
	}
	
	public void setPerfil(){
		if(perfil == 3){
			jb[1].setEnabled(false);
		}
	}
	
	private class AllHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
				if(e.getSource() == jb[0]){
					if(chave == 0){
						
						
						String cn = JOptionPane.showInputDialog(null,bn.getString("menu.alterar.empresa"));
						
						
						String [] labelNome ={bn.getString("menu.cadastro.razao"),
								bn.getString("menu.cadastro.horarioAbrirEmpresa"),
								bn.getString("menu.cadastro.horarioFecharEmpresa"),
								bn.getString("menu.cadastro.temperaturaAr")
						};
						JLabel [] label = new JLabel[labelNome.length];
						JTextField [] field = new JTextField[labelNome.length];
					    JPanel jp;
						
					    jp = new JPanel();
						jp.setLayout(new GridLayout(5,2));
						
						
						
						for(int i=0;i<label.length;i++){
							label[i] = new JLabel(labelNome[i]);
							jp.add(label[i]);
							field[i] =new JTextField();
							jp.add(field[i]);
						}
						
						
						
						
						JOptionPane.showMessageDialog(null,jp);
					    
						Empresa em = new Empresa(cn,
								field[0].getText(),
								field[1].getText(),
								field[2].getText(),
								field[3].getText()
								);
						
						if(emService.altera(em)){ 
							JOptionPane.showMessageDialog(null,"Mensagem generica de sucesso");
							new InterfaceConsultaEmpresa(bn,chave,perfil);
							dispose();
						}
						else JOptionPane.showMessageDialog(null,"Mensagem generica fracasso");
						
						
						//instacia tela de alteração de empresa
					}else{
						try{
						int id = Integer.parseInt(JOptionPane.showInputDialog(null,bn.getString("menu.alterar.funcionario")));
						
						setVisible(false);
						InterfaceCadastraFuncionario fd = new InterfaceCadastraFuncionario(bn,perfil,1);
						fd.setChave(chave);
						fd.setIdentificador(id);
						}catch(Exception a){
							JOptionPane.showMessageDialog(null,"id invalido");
						}

					}

				}
				if(e.getSource() == jb[1]){
					if(chave == 0){
						String cn =JOptionPane.showInputDialog(null,bn.getString("menu.excluir.empresa"));
						//istancia tela de exclusao de empresa

						if(emService.exclui(cn)){ 
							JOptionPane.showMessageDialog(null,"Mensagem generica de sucesso");
							new InterfaceConsultaEmpresa(bn,chave,perfil);
							dispose();
						}
						else JOptionPane.showMessageDialog(null,"Mensagem generica fracasso");

					}else{
						int id = Integer.parseInt(JOptionPane.showInputDialog(null,bn.getString("menu.excluir.funcionario")));
						if(pesService.exclui(id)){ 
							JOptionPane.showMessageDialog(null,"Mensagem generica de sucesso");
							new InterfaceConsultaFuncionario(bn,chave,perfil);
							dispose();
						}
						else JOptionPane.showMessageDialog(null,"Mensagem generica fracasso");
						
						//instacia tela de exclusao de funcionario
					}
					
				}
				
			}
		}
	

		
	
	

}


	
	

