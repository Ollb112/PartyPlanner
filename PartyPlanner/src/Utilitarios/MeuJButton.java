package Utilitarios;

import java.awt.Color;

import javax.swing.JButton;

public class MeuJButton extends JButton{



	public MeuJButton(int x, int y, int l, int a, String s) {
		this.setBounds(x,y,a,l);
		this.setText(s);
	}
	public MeuJButton(int x, int y, int l, int a, String s, Color c) {
		this.setBounds(x,y,a,l);
		this.setText(s);
		this.setForeground(c);
	}
	
	public MeuJButton(int x, int y, int l, int a, Color c, String s, Color b) {
		this.setBounds(x,y,a,l);
		this.setText(s);
		this.setForeground(c);
		this.setBackground(b);
	}
}
