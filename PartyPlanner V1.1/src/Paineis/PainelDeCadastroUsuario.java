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
	private JRadioButton rbMostrarSenha;


	public PainelDeCadastroUsuario() {
		setSize(800,450);
		setLayout(null);			
		addLabel();
		addRadioButton(); 
		addTextField();
		addButton();
	}
	
	private void addTextField() {
		campoSenha = new MeuJPasswordField(150, 130, 140, 30);
		campoNome = new MeuJTextField(150,50,150,30);
		campoEmail = new MeuJTextField(150,90,150,30);
		campoCpfCnpj = new MeuJTextField(150,170,140,30);
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
		MeuJButton selecionarIMG = new MeuJButton(505, 90, 150, 30, "Selecionar Imagem");
		
		
		botaoCadastrar.addActionListener(opc);
		botaoNovoCadastro.addActionListener(opc);
		selecionarIMG.addActionListener(opc);
		add(botaoCadastrar);
		add(botaoNovoCadastro);
		add(selecionarIMG);

	}
	
	private void addLabel() {
		String[] nomeCamposJLabel = {"Nome:", "E-mail:", "Senha:", "Cpf/Cnpj:", "Sexo:"};
		MeuJLabel imagemPerfil = new MeuJLabel(500, 50, 200, 30, "Imagem do Perfil");
		
		int y = 50;
		int largura = 120;
		int larguraCampo = 250;

		for(int i = 0; i < nomeCamposJLabel.length; i++) {
			MeuJLabel campoLabel = new MeuJLabel(50, y, largura, 30, nomeCamposJLabel[i]);
			
			y += 40;

			add(campoLabel);
		}

		add(imagemPerfil);
	}
	
	private void addRadioButton() {
		rbFisico = new JRadioButton("Fisica",true);
		rbJuridico = new JRadioButton("Juridica",false);
		ButtonGroup rbg = new ButtonGroup();

		rbFisico.setBounds(290, 175, 65, 25);
		rbJuridico.setBounds(355, 175, 80, 25);
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
		
		rbMostrarSenha = new JRadioButton("Ver Senha",false);
		
		rbMostrarSenha.setBounds(290, 130, 90, 30);
		add(rbMostrarSenha);
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
			if (central.getTodosOsClientes().isEmpty()) 
				user = new Administrador(nome,numeroId,email,senha,sexo);
			
			else if (rbFisico.isSelected()) 
				user = new PessoaFisica(nome,numeroId,email,senha,sexo);
			
			else 
				user = new PessoaJuridica(nome,numeroId,email,senha,sexo);
			central.adicionarCliente(user);
			System.out.println(nome + ", "+ email +", "+ senha+", "+numeroId);
			break;
		case "Selecionar Imagem":
		    JFileChooser seletor = new JFileChooser();
		    FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif");

		    seletor.setBounds(500, 50, 200, 250);
		    seletor.setFileFilter(filtro);
		    
		    int result = seletor.showOpenDialog(Janela.getInstance());
		    
		    if (result == JFileChooser.APPROVE_OPTION) {
		        // O usuário selecionou um arquivo
		        String caminhoArquivo = seletor.getSelectedFile().getAbsolutePath();
				ImageIcon imagem = new ImageIcon(caminhoArquivo);
		        // Faça algo com o caminho do arquivo selecionado
		        
		        // Exemplo: exiba o caminho do arquivo em um JOptionPane
		        //JOptionPane.showMessageDialog(this, "Caminho do arquivo selecionado: " + caminhoArquivo);
		    } else if (result == JFileChooser.CANCEL_OPTION) {
		        // O usuário cancelou a operação
		       // JOptionPane.showMessageDialog(this, "Operação cancelada pelo usuário.");
		    }
			
			break;
		case "Cancelar":
			Janela.setPanel(new PainelDoAdm());
			break;
		}
	}

}
}


