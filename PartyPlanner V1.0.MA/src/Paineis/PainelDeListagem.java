package Paineis;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;

import Tela.Janela;

public class PainelDeListagem extends JPanel{
	
	public PainelDeListagem() {
		setSize(800,450);
		setLayout(null);
		addJTable();
	}
	public void addJBotao() {
		
	}
	
	public void addJTable() {
		String[] teste = {"teste1","teste2","teste3","teste4"};
		JTable tabela = new JTable();
		tabela.setBounds(50, 100, 650, 350);
		add(tabela);
	}
	public static void main(String[] a) {
		Janela.setPanel(new PainelDeListagem());
	}
}
