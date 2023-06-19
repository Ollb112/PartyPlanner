package Tratamentos;


public class CamposNulosException extends Exception{

	private String localDoErro;
	
	
	public CamposNulosException(String zonaMorta) {
		localDoErro = zonaMorta;
	}
	

	public String getErro() {
		return "digite algo em: "+localDoErro;
	}
}
