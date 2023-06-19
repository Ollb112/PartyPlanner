package Utilitarios;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class MeuJTextField extends JTextField{
	public MeuJTextField(int x, int y, int l, int a) {
		this.setBounds(x, y, l, a);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
