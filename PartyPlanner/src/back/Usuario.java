package back;

public class Usuario {
	private String nome;
	private String cpf;
	private String email;
	private Sexo sexo;
	
	public Usuario(String n, String c, String e, Sexo s) {
		nome = n;
		cpf = c;
		email = e;
		sexo = s;
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
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	

}


