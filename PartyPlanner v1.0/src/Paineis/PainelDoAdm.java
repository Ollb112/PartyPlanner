package Paineis;

import java.awt.Color;

import javax.swing.JPanel;

import Ouvintes.OuvinteDoMouse;
import Ouvintes.OuvinteDoPainelAdm;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import Utilitarios.MeuJTextField;

public class PainelDoAdm extends JPanel{
	private MeuJButton cadastrar = new MeuJButton(450, 50, 50, 100,"Cadastrar");
	private MeuJButton listar = new MeuJButton(560, 50, 50, 100,"Listar");
	private MeuJButton planilha = new MeuJButton(450, 110, 50, 210,"Gerar Planilha de Finanças");
	private MeuJLabel nome = new MeuJLabel(10,10, 300, 50,"User: "+"Fulano de Tal");
	
	private OuvinteDoMouse om = new OuvinteDoMouse();
	private OuvinteDoPainelAdm opadm = new OuvinteDoPainelAdm();
	
	public PainelDoAdm() {
		cadastrar.addActionListener(opadm);
		setSize(800,450);
		setLayout(null);
		add(cadastrar);
		add(listar);
		add(planilha);
		add(nome);

		
	}
}
