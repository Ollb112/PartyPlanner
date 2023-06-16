package Paineis;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Tela.Janela;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import Utilitarios.MeuJPasswordField;
import Utilitarios.MeuJTextField;
import back.CentralDeInformacoes;
import back.Persistencia;
import back.Usuario;

public class PainelDeLogin extends JPanel{

	private MeuJTextField campoEmail;
	private MeuJPasswordField campoSenha;
	private boolean controle = false;
	
	public PainelDeLogin() {
		setSize(800,450);
		setLayout(null);
		addBotoes();
		addTextField();
		addJLabel();
		setVisible(true);
	}
	
	public void addTextField() {
		campoEmail = new MeuJTextField(290,100,200,30);
		campoSenha = new MeuJPasswordField(290,160,200,30);
		add(campoEmail);
		add(campoSenha);
	}
	public void addBotoes() {

		OuvinteDoLogin observer= new OuvinteDoLogin();
		MeuJButton botaoLogin = new MeuJButton(400,200,90,30, "Entrar", Color.BLUE);
		MeuJButton botaoCadrastro = new MeuJButton(290,200,100,30, "Cadastrar",Color.BLACK);
		MeuJButton botaoSenha = new MeuJButton(500,160,120,30, "Mostrar Senha");
	
		botaoCadrastro.addActionListener(observer);
		botaoLogin.addActionListener(observer);
		botaoSenha.addActionListener(observer);
		add(botaoSenha);
		add(botaoLogin);
		add(botaoCadrastro);
		
	}
	public void addJLabel() {
		MeuJLabel Email = new MeuJLabel(350,60, 80, 50, "E-mail:", Color.BLACK);
		MeuJLabel senha = new MeuJLabel(350,120,80, 50, "Senha:", Color.BLACK);
		add(Email);
		add(senha);
	}

	
public class OuvinteDoLogin implements ActionListener{
	
	public void actionPerformed(ActionEvent Clique) {
		String play = Clique.getActionCommand();
		switch (play) {
			case "Cadastrar":
				Janela.setPanel(new PainelDeCadastroUsuario());
				break;
			case "Entrar":
				Persistencia pe = new Persistencia();
				CentralDeInformacoes central = pe.recuperarCentral("central.xml");
				String email = campoEmail.getText();
				Usuario ADM = central.recuperarAdministradorPorEmail(email);
				String senha = campoSenha.getText();	
				if(ADM.getSenha().equals(senha)) {	
					Janela.setPanel(new PainelDoAdm(ADM));
				}
				break;
			case "Mostrar Senha":
				controle = !controle;
		        if (controle) {
		            campoSenha.setEchoChar((char) 0); // Mostra a senha
		        } else {
		            campoSenha.setEchoChar('\u2022'); // Oculta a senha
		        }	
				break;
				}	
		}
				
	}
}