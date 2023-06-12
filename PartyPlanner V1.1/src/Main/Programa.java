package Main;

import Paineis.PainelDeCadastroUsuario;
import Paineis.PainelDeLogin;
import Tela.Janela;
import back.CentralDeInformacoes;
import back.Persistencia;

public class Programa {
	public static void main(String[] args) {
		
		Persistencia pe = new Persistencia();
		CentralDeInformacoes central = pe.recuperarCentral("central.xml");
	
		if(central.getTodosOsClientes().isEmpty()) {
			Janela.setPanel(new PainelDeCadastroUsuario());
		}
		else {
			Janela.setPanel(new PainelDeLogin());
		}		
		
	}

}
