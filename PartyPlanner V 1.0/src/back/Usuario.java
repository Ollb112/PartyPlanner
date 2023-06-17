package back;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Usuario {
	private String nome;
	private String cpf;
	private String cnpj;
	private String senha;
	private String email;
	private String telefone;
	private String nascimento;
	private String tipoDeUsuario;
	private Sexo sexo;
	private JLabel icone;

	
	public Usuario(String a, String b, String c,String d, Sexo e, String f, String g,String h) {
		nome = a;
		if (h.equals("Pessoa Fisica"))
			cpf = b;
		else
			cnpj = b;
		email = c;
		senha =d ;
		sexo = e;
		telefone = f;
		nascimento = g;
		tipoDeUsuario = h;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public String getTipoDeUsuario() {
		return tipoDeUsuario;
	}
	

}


