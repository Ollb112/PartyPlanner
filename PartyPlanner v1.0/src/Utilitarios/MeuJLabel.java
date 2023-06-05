package Utilitarios;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MeuJLabel extends JLabel {
	public MeuJLabel(int x, int y, int l, int a,String nome) {
		Font fonte = new Font("Negrito", Font.BOLD,20);
		this.setBounds(x,y,l,a);
		this.setText(nome);
		this.setFont(fonte);
	}
	public MeuJLabel() {

	}
	
	public MeuJLabel(int x, int y, int l, int a,String nome,Color c) {
		Font fonte = new Font("Negrito", Font.BOLD,20);
		this.setBounds(x,y,l,a);
		this.setText(nome);
		this.setForeground(c);
		this.setFont(fonte);
	}

}
