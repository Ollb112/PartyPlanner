package Tela;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Paineis.PainelDeCadastro;
import Paineis.PainelDeLogin;
import Paineis.PainelDoAdm;

public class Tela extends JFrame {
	public Tela(JPanel j) {
		setSize(800,450);
		setLayout(null);
		setResizable(false);
		setTitle("Party Planner");
		setContentPane(j);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		//PainelDeLogin login = new PainelDeLogin();
		//PainelDoAdm adm = new PainelDoAdm();
		PainelDeCadastro cadastro = new PainelDeCadastro();
		//Tela t = new Tela(login);
		//Tela t = new Tela(adm);
		Tela t = new Tela(cadastro);
	}
}
