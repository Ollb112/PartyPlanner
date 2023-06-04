package Paineis;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Utilitarios.MeuJLabel;
import Utilitarios.MeuJPasswordField;
import Utilitarios.MeuJTextField;

public class PainelDeCadastro extends JPanel{	
	

	private static final long serialVersionUID = 1L;

	public PainelDeCadastro() {
		setLayout(null);			
		addLabelTxtField();
		addRadioButtonSexo();
		addRadioButton(); 
		addRadioButtonSenha();
	}
	

	public void addLabelTxtField() {
		String[] camposJLabel = {"Nome:", "E-mail:", "Senha:", "Cpf/Cnpj:", "Sexo:"};
		
		int y = 50;
		int largura = 120;
		int larguraCampo = 250;
		
		for(int i = 0; i < camposJLabel.length; i++) {
			MeuJLabel campo = new MeuJLabel(10, y, largura, 30, camposJLabel[i]);
			
			if(i < 4) {
					
				if(i > 1 ) {
					larguraCampo = 150;
				}
				if(i == 2) {
					MeuJPasswordField campoSenha = new MeuJPasswordField(100, y, larguraCampo, 30);
					add(campoSenha);
				}
				if(i != 2) {
				MeuJTextField campoTextField = new MeuJTextField(100, y, larguraCampo, 30);
				add(campoTextField);
				}
			}
			y += 40;
			
			add(campo);
		}
	}
	
	
	public void addRadioButton() {
		JRadioButton rbFisico = new JRadioButton("Fisica",true);
		JRadioButton rbJuridico = new JRadioButton("Juridica",false);
		ButtonGroup rbg = new ButtonGroup();

		rbFisico.setBounds(260, 175, 65, 25);
		rbJuridico.setBounds(325, 175, 80, 25);
		rbg.add(rbFisico);
		rbg.add(rbJuridico);
		add(rbFisico);
		add(rbJuridico);	
	}
	public void addRadioButtonSexo() {
		JRadioButton rbMasculino = new JRadioButton("Masculino",false);
		JRadioButton rbFeminino = new JRadioButton("Feminino",false);
		ButtonGroup rbg = new ButtonGroup();

		rbMasculino.setBounds(75, 215, 90, 25);
		rbFeminino.setBounds(165, 215, 80, 25);
		rbg.add(rbMasculino);
		rbg.add(rbFeminino);
		add(rbMasculino);
		add(rbFeminino);	
	}
	public void addRadioButtonSenha() {
		JRadioButton rbMostrarSenha = new JRadioButton("Ver Senha",false);
		
		rbMostrarSenha.setBounds(260, 130, 90, 30);
		add(rbMostrarSenha);
	}
}
