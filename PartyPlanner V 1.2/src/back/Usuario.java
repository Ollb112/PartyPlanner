package back;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Usuario {
	private String nome;
	private String cpf;
	private String cnpj;
	private String senha;
	private String email;
	private Sexo sexo;
	private JLabel icone;

	
	public Usuario(String n, String c, String e,String s, Sexo sx) {
		nome = n;
		cpf = c;
		email = e;
		setSenha(s);
		sexo = sx;
	}
	public Usuario(String n, String e,String s, Sexo sx) {
		nome = n;
		email = e;
		setSenha(s);
		sexo = sx;
	}
	
	public Usuario() {
	}
	
	public String toString() {
		return nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cnpj) {
		this.cnpj = cpf;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public JLabel getIcone() {
		return icone;
	}
	public void setIcone(ImageIcon icone) {
		this.icone.setIcon(icone);
	}
	

}

