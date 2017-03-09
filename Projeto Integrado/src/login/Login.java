package login;

import java.util.Arrays;

public class Login {

	public int perfil,id;
	public String senha,hem,hemi;
	boolean acesso;
	

	
	public Login(int id,String senha){//login
		this.id = id;
		this.senha = senha;
		
		}
	public Login(int perfil,int login,String senha,String hem,String hemi,boolean acesso){//para criar login
			this.perfil = perfil;
			id = login;
			this.senha = senha;
			this.hem = hem;
			this.hemi = hemi;
			this.acesso = acesso;

	}

	
	public Login(){}
	public int getPerfil() {
		return perfil;
	}
	public int getId() {
		return id;
	}
	public String getSenha() {
		return senha;
	}
	public String getHem() {
		return hem;
	}
	public String getHemi() {
		return hemi;
	}
	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setHem(String hem) {
		this.hem = hem;
	}
	public void setHemi(String hemi) {
		this.hemi = hemi;
	}
	public boolean isAcesso() {
		return acesso;
	}
	public void setAcesso(boolean acesso) {
		this.acesso = acesso;
	}
	


	
	
}
