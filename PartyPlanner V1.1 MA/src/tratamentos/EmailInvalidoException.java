package tratamentos;


public class EmailInvalidoException extends Exception {

	private String valorErrado;
	public EmailInvalidoException(String a) {
		valorErrado = a;
	}
	public String getErro() {
			return " Este Email: "+ valorErrado +" está Incorreto ou Ainda Nao foi Cadrastrado! ";
	}
}
