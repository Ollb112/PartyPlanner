package Paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import Tela.Janela;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import Utilitarios.MeuJPasswordField;
import Utilitarios.MeuJTextField;
import back.Administrador;
import back.CentralDeInformacoes;
import back.Persistencia;
import back.PessoaFisica;
import back.PessoaJuridica;
import back.Sexo;
import back.Usuario;

public class PainelDeCadastroUsuario extends JPanel{
	private MeuJPasswordField campoSenha;
	
	private MeuJTextField campoNome;
	private MeuJTextField campoEmail;
	private MeuJTextField campoCpfCnpj;
	
	private JRadioButton rbFisico;
	private JRadioButton rbJuridico;
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private JRadioButton rbAdm;
	private JRadioButton rbCliente;
	private JRadioButton rbFornecedor;
	
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
		campoSenha = new MeuJPasswordField(150,170,140,30);
		campoNome = new MeuJTextField(150,50,150,30);
		campoEmail = new MeuJTextField(150,90,150,30);
		campoCpfCnpj = new MeuJTextField(150, 130, 140, 30);
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
		//MeuJButton selecionarIMG = new MeuJButton(505, 90, 150, 30, "Selecionar Imagem");
		MeuJButton mostrarSenha = new MeuJButton(290, 170, 130, 30,"Mostrar Senha");
		
		botaoCadastrar.addActionListener(opc);
		botaoNovoCadastro.addActionListener(opc);
		//selecionarIMG.addActionListener(opc);
		mostrarSenha.addActionListener(opc);
		add(botaoCadastrar);
		add(botaoNovoCadastro);
		//add(selecionarIMG);
		add(mostrarSenha);
	}
	
	private void addLabel() {
		String[] nomeCamposJLabel = {"Nome:", "E-mail:", "Cpf/Cnpj:", "Senha:", "Sexo:"};

		int y = 50;
		int largura = 120;

		for(int i = 0; i < nomeCamposJLabel.length; i++) {
			MeuJLabel campoLabel = new MeuJLabel(50, y, largura, 30, nomeCamposJLabel[i]);
			
			y += 40;

			add(campoLabel);
		}

	}
	
	private void addRadioButton() {

		rbFisico = new JRadioButton("Fisica",true);
		rbJuridico = new JRadioButton("Juridica",false);
		ButtonGroup rbg = new ButtonGroup();

		rbFisico.setBounds(290, 130, 65, 25);
		rbJuridico.setBounds(355, 130, 80, 25);
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
		
		rbAdm = new JRadioButton("Administrador",true);
		rbCliente = new JRadioButton("Cliente",false);
		rbFornecedor= new JRadioButton("Fornecedor",false);
		ButtonGroup rbg2 = new ButtonGroup();

		rbCliente.setBounds(45, 245, 70, 30);
		rbFornecedor.setBounds(115, 245, 95, 30);
		rbAdm.setBounds(210, 245, 110, 30);
		rbg2.add(rbCliente);
		rbg2.add(rbFornecedor);
		rbg2.add(rbAdm);
		
		rbAdm.setEnabled(false);
		rbAdm.setVisible(false);
		rbFornecedor.setEnabled(false);
		rbCliente.setEnabled(false);
		
		Persistencia pe = new Persistencia();
		CentralDeInformacoes central = pe.recuperarCentral("central.xml");
		
		if(central.getTodosOsAdministradores().isEmpty()) {
			rbFornecedor.setVisible(false);
			rbCliente.setVisible(false);
		}
		else {
			rbFornecedor.setVisible(true);
			rbCliente.setVisible(true);
			rbFornecedor.setEnabled(true);
			rbCliente.setEnabled(true);

		}
		
		add(rbCliente);
		add(rbFornecedor);
		add(rbAdm);
	}

	public static void main(String[] args) {
		Janela.setPanel(new PainelDeCadastroUsuario());
		
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
			String nome = campoNome.getText();
			String email = campoEmail.getText();
			String senha = campoSenha.getText();
			String numeroId = campoCpfCnpj.getText();
			Sexo sexo;

			if (rbMasculino.isSelected()) 
				sexo = Sexo.MASCULINO;
			else
				sexo = Sexo.FEMININO;
			
			if (central.getTodosOsAdministradores().isEmpty() || rbAdm.isSelected()) {
				user = new Administrador(nome,numeroId,email,senha,sexo);
				central.adicionarAdministrador(user);
				pe.salvarCentral(central, "central.xml");
			}
			
			else if (rbFisico.isSelected()) 
				user = new PessoaFisica(nome,numeroId,email,senha,sexo);
			
			else 
				user = new PessoaJuridica(nome,numeroId,email,senha,sexo);
			
			if (rbCliente.isSelected()) 
				central.adicionarCliente(user);
			else if (rbFornecedor.isSelected())
				central.adicionarFornecedor(user);

			pe.salvarCentral(central, "central.xml");
			System.out.println(nome + ", "+ email +", "+ senha+", "+numeroId);
			break;
			
		case "Cancelar":
			//Janela.setPanel(new PainelDoAdm());
			break;
		}
	}

}
}


