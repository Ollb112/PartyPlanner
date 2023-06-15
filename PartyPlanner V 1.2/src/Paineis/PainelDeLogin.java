package Paineis;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import Utilitarios.MeuJTextField;

public class PainelDeLogin extends JPanel{
	private MeuJButton botao = new MeuJButton(500,150,100,30, "Entrar", Color.GREEN);
	private MeuJLabel nome = new MeuJLabel(420, 65, 80, 50, "Nome:", Color.black);
	private MeuJLabel senha = new MeuJLabel(420, 100, 80, 50, "Senha:", Color.RED);
	private MeuJTextField campoNome = new MeuJTextField(500,70,150,30);
	private MeuJTextField campoSenha = new MeuJTextField(500,110,150,30);

	public PainelDeLogin() {
		setSize(800,450);
		setLayout(null);
		add(botao);
		add(campoNome);
		add(campoSenha);
		add(nome);
		add(senha);
		
	}

	/*public void addImagem(int x, int y, int l, int a) {
		ImageIcon imagem = new ImageIcon("chave.jpg");
		JLabel icone = new JLabel(imagem);
		icone.setBounds(x, y, l, a);
		getContentPane().add(icone);
	}*/
	public void addTextField(int x, int y, int l, int a) {
		JTextField tf = new JTextField();
		tf.setBounds(x, y, l, a);
		add(tf);
	}
}
