package Paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;

import Tela.Janela;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import Utilitarios.MeuJPasswordField;
import Utilitarios.MeuJTextField;
import back.Administrador;
import back.CentralDeInformacoes;
import back.Persistencia;
import back.Sexo;
import back.Usuario;
import tratamentos.CamposNulosException;

public class PainelDeCadastroUsuario extends JPanel{
	private MeuJPasswordField campoSenha;
	
	private MeuJTextField campoNome;
	private MeuJTextField campoEmail;
	private MeuJTextField campoCpfCnpj;
	
	private JRadioButton rbFisico;
	private JRadioButton rbJuridico;
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	
	private JFormattedTextField campoTelefone;
	private JFormattedTextField campoAno;
	private boolean controle = false;



	public PainelDeCadastroUsuario() {
		setSize(800,450);
		setLayout(null);			
		addLabel();
		addRadioButton(); 
		addTextField();
		addButton();
	}
	
	private void addTextField() {
		campoSenha = new MeuJPasswordField(110,170,140,30);
		campoNome = new MeuJTextField(110,50,150,30);
		campoEmail = new MeuJTextField(110,90,150,30);
		campoCpfCnpj = new MeuJTextField(110, 130, 140, 30);
		
		campoNome.setText("Nome Completo");
		campoEmail.setText("usuario@gmail.com");
		campoCpfCnpj.setText("insira somente os digitos");
		
		add(campoSenha);
		add(campoNome);
		add(campoEmail);
		add(campoCpfCnpj);
	}
	
	private void addButton() {
		OuvinteDoPainelDeCadastro opc = new OuvinteDoPainelDeCadastro();

		MeuJButton botaoCadastrar = new MeuJButton(150, 350, 100, 30, "Cadastrar");
		MeuJButton botaoNovoCadastro = new MeuJButton(550, 350, 100, 30, "Cancelar");
		MeuJButton mostrarSenha = new MeuJButton(260, 170, 130, 30,"Mostrar Senha");
		

		try {
	        MaskFormatter mascaraTelefone = new MaskFormatter("(##)#########");
	        MaskFormatter mascaraAno = new MaskFormatter("##/##/####");
	        
			campoTelefone = new JFormattedTextField(mascaraTelefone);
			campoAno = new JFormattedTextField(mascaraAno);
			
			campoTelefone.setBounds(550,50,120,30);
			campoAno.setBounds(550,90,120,30);
			
			add(campoTelefone);
			add(campoAno);
		} catch (ParseException e) {
			e.printStackTrace();
		}




		


		botaoCadastrar.addActionListener(opc);
		botaoNovoCadastro.addActionListener(opc);
		mostrarSenha.addActionListener(opc);
		add(botaoCadastrar);
		add(botaoNovoCadastro);
		add(mostrarSenha);

	}
	
	private void addLabel() {
		String[] nomeCamposJLabel = {"Nome:", "E-mail:", "Cpf/Cnpj:", "Senha:", "Sexo:"};
		MeuJLabel telefone = new MeuJLabel(420,50,70,30,"Telefone:");
		MeuJLabel ano = new MeuJLabel(420,90,120,30,"Data de Nascimento:");

		int y = 50;
		int largura = 120;

		for(int i = 0; i < nomeCamposJLabel.length; i++) {
			MeuJLabel campoLabel = new MeuJLabel(50, y, largura, 30, nomeCamposJLabel[i]);
			
			y += 40;

			add(campoLabel);
		}
		add(telefone);
		add(ano);

	}
	
	private void addRadioButton() {

		rbFisico = new JRadioButton("Fisica",true);
		rbJuridico = new JRadioButton("Juridica",false);
		ButtonGroup rbg = new ButtonGroup();

		rbFisico.setBounds(260, 130, 65, 25);
		rbJuridico.setBounds(325, 130, 80, 25);
		rbg.add(rbFisico);
		rbg.add(rbJuridico);
		add(rbFisico);
		add(rbJuridico);	
		
		rbMasculino = new JRadioButton("Masculino",false);
		rbFeminino = new JRadioButton("Feminino",false);
		ButtonGroup rbg1 = new ButtonGroup();

		rbMasculino.setBounds(105, 215, 90, 25);
		rbFeminino.setBounds(195, 215, 80, 25);
		rbg1.add(rbMasculino);
		rbg1.add(rbFeminino);
		add(rbMasculino);
		add(rbFeminino);	

	}


public class OuvinteDoPainelDeCadastro implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		Persistencia pe = new Persistencia();
		CentralDeInformacoes central = pe.recuperarCentral("central.xml");
		Usuario user;

		String acao = e.getActionCommand();
		switch (acao) {
		case "Mostrar Senha":
			controle = !controle;
	        if (controle) {
	            campoSenha.setEchoChar((char) 0); // Mostra a senha
	        } else {
	            campoSenha.setEchoChar('\u2022'); // Oculta a senha
	        }	
			break;
			
		case "Cadastrar":
			try {
				CamposNulosException erro;
				String email;
				String nome;
				String senha;
				String telefone;
				String numeroId;
				String nascimento;
				if(!campoNome.getText().equals("")) {
					nome = campoNome.getText();
					
					/**
					 * 
					 * 
					 * 
					 * adicione em cada condicao o valor que fica dentro da caixa com ||
					 * 
					 * 
					 * 
					 * 
					 * */
					if(!campoEmail.getText().equals("")) {
						email = campoEmail.getText();
						if(!campoSenha.getText().equals("")) {
							senha = campoSenha.getText();
							if(!campoCpfCnpj.getText().equals("")) {
								numeroId = campoCpfCnpj.getText();
								if(!campoTelefone.getText().equals("")) {
									telefone = campoTelefone.getText();
									if(!campoAno.getText().equals("")) {	
										nascimento = campoAno.getText();
									}
									else {
										
										throw erro = new CamposNulosException("Ano De Nascimento");
									}	
								}
								else {
									throw erro = new CamposNulosException("Telefone ");
								}	
							}
							else {
								throw erro = new CamposNulosException("CpfCnpj");
							}
							
						}
						else {
							throw erro = new CamposNulosException(" Senha");
						}	
					}			
					else {
						throw erro = new CamposNulosException("E-mail ");
					}
				}
				else {
					throw erro = new CamposNulosException("Nome");
				}
			Sexo sexo;

			if (rbMasculino.isSelected()) 
				sexo = Sexo.MASCULINO;
			else
				sexo = Sexo.FEMININO;
	
			
			if (rbFisico.isSelected()) 
				user = new Administrador(nome,numeroId,email,senha,sexo,telefone, nascimento,"Pessoa Fisica");
				
			else 
				user = new Administrador(nome,numeroId,email,senha,sexo,telefone, nascimento,"Pessoa Juridica");
				
			central.adicionarAdministrador((Administrador)user);
			pe.salvarCentral(central, "central.xml");
			
			Janela.setPanel(new PainelDeLogin());
			}catch(CamposNulosException erro) {
				JOptionPane.showMessageDialog(null, erro.getErro(),"Erro de Cadastro", JOptionPane.WARNING_MESSAGE);
				break;
				}
			
			break;
		
		case "Cancelar":
			Janela.setPanel(new PainelDeLogin());
			break;
		}
	}

}
}


