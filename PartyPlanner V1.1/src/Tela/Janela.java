package Tela;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Paineis.PainelDeCadastroUsuario;
import Paineis.PainelDeLogin;
import Paineis.PainelDoAdm;

public class Janela extends JFrame {
	private static Janela container;
	private Janela() {
		setSize(800,450);
		setLayout(null);
		setResizable(false);
		setTitle("Party Planner");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void setPanel(JPanel painel) {
		SwingUtilities.updateComponentTreeUI(Janela.getInstance());
		getInstance().setContentPane(painel);
	}
	public static Janela getInstance() {
		if(container == null)
			container = new Janela();
		return container;
	}
	
	public static void main(String[] args) {

		Janela.setPanel(new PainelDoAdm());
	}
}
