package Paineis;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Utilitarios.MeuJLabel;
import Utilitarios.MeuJPasswordField;
import Utilitarios.MeuJTextField;

public class PainelDeCadastroUsuario extends JPanel{
	
	
	public PainelDeCadastroUsuario() {
		setSize(800,450);
		setLayout(null);			
		addLabelTextField();
		addRadioButtonSexo();
		addRadioButton(); 
		addRadioButtonSenha();
		addJComboBox(680, 50, 100, 30, true);
	}

	private void addLabelTextField() {
		String[] nomeCamposJLabel = {"Nome:", "E-mail:", "Senha:", "Cpf/Cnpj:", "Sexo:"};
		int y = 50;
		int largura = 120;
		int larguraCampo = 250;
		for(int i = 0; i < nomeCamposJLabel.length; i++) {
			MeuJLabel campo = new MeuJLabel(10, y, largura, 30, nomeCamposJLabel[i]);
			
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
	
	private void addRadioButton() {
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
	private void addRadioButtonSexo() {
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
	private void addRadioButtonSenha() {
		JRadioButton rbMostrarSenha = new JRadioButton("Ver Senha",false);
		
		rbMostrarSenha.setBounds(260, 130, 90, 30);
		add(rbMostrarSenha);
	}
	private void addJComboBox(int x, int y, int l, int a, Boolean b) {
		String[] tipos = {"Cliente", "Fornecedor"};
		JComboBox<String> tipoDeUsuario = new JComboBox<String>(tipos);
		tipoDeUsuario.setLayout(null);
		tipoDeUsuario.setBounds(x, y, l, a);
		tipoDeUsuario.setVisible(b);
		add(tipoDeUsuario);
	}

}


