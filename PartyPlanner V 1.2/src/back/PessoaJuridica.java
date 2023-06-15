package back;

public class PessoaJuridica extends Usuario {
	public PessoaJuridica(String n, String c, String e,String s, Sexo sx) {
		super(n,e,s,sx);
		setCnpj(c);
	    }
}
