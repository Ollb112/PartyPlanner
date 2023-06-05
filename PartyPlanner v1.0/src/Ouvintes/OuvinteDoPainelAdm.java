package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Paineis.PainelDeCadastroUsuario;
import Tela.Janela;

public class OuvinteDoPainelAdm implements ActionListener{
	private PainelDeCadastroUsuario adm = new PainelDeCadastroUsuario();


	public void actionPerformed(ActionEvent e) {
		Janela.setPanel(adm);
	}



}
