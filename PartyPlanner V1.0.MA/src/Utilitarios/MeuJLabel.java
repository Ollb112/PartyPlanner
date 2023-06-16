package Utilitarios;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MeuJLabel extends JLabel {
	public MeuJLabel(int x, int y, int l, int a,String nome) {
		this.setBounds(x,y,l,a);
		this.setText(nome);
		this.setForeground(Color.black);
	}
	public MeuJLabel() {

	}
	
	public MeuJLabel(int x, int y, int l, int a,String nome,Color c) {
		this.setBounds(x,y,l,a);
		this.setText(nome);
		this.setForeground(c);
	}
	public MeuJLabel(int x, int y, int l, int a,String nome,Color c,Color d) {
		
		this.setBounds(x,y,l,a);
		this.setText(nome);
		this.setForeground(Color.black);
		this.setBackground(d);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setOpaque(true);
	}

}
