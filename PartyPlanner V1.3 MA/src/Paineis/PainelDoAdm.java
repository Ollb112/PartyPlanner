package Paineis;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import Ouvintes.OuvinteDoMouse;
import Tela.Janela;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import Utilitarios.MeuJTextField;
import back.Administrador;
import back.CentralDeInformacoes;
import back.Fornecedor;
import back.Persistencia;
import back.Usuario;

public class PainelDoAdm extends JPanel{
	private MeuJButton cadastrar;
	private MeuJButton listar;
	private MeuJButton sair;
	private MeuJButton selecionarImg;
	
	private Usuario adm;
	private JPanel menuPanel;

	public PainelDoAdm(Administrador user) {
		Persistencia pe = new Persistencia();
		CentralDeInformacoes central = pe.recuperarCentral("central.xml");
		this.adm = central.recuperarAdministradorPorEmail(user.getEmail());
		setSize(800,450);
		setLayout(null);
		addBotao();
		addLabel();
		addImagem(0, 0, 250, 250);

	}
	

	public void addImagem(int x, int y, int l, int a) {
		JLabel icone = new JLabel();
		icone.setBounds(x, y, l, a);
		icone.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		if (adm.getIcone() != null)
			icone.getIcon();
		else
			icone.setIcon(new ImageIcon("PartyPlanner.png"));
		
		add(icone);
	}
	
	public void addLabel(){
		MeuJLabel nome = new MeuJLabel(260,10, 300, 50,"Usuario: " + adm.getNome());
		MeuJLabel email = new MeuJLabel(260,30,300,50,"E-mail: " + adm.getEmail());
		MeuJLabel sexo = new MeuJLabel(260,70,300,50,"Sexo: " + adm.getSexo());
		MeuJLabel telefone = new MeuJLabel(260,90,300,50,"Telefone: " + adm.getTelefone());
		MeuJLabel nascimento = new MeuJLabel(260,110,300,50,"Nascimento: " + adm.getNascimento());

		MeuJLabel numeroId;
		if(adm.getTipoDeUsuario().equals("Pessoa Fisica"))
			numeroId  = new MeuJLabel(260,50,300,50,"Cpf: " + adm.getCpf());
		else
			numeroId  = new MeuJLabel(260,50,300,50,"Cnpj: " + adm.getCnpj());

		
		add(nome);
		add(email);
		add(numeroId);
		add(sexo);
		add(telefone);
		add(nascimento);
	}
	
	public void addBotao() {
		OuvinteDoPainelAdm opadm = new OuvinteDoPainelAdm();
		cadastrar = new MeuJButton(550, 50, 100, 50,"Cadastrar");
		listar = new MeuJButton(660, 50, 100, 50,"Listar");
		sair = new MeuJButton(650, 350, 100,30,"Sair");
		selecionarImg = new MeuJButton(0, 260, 250, 30, "Selecionar Foto de Perfil");

		selecionarImg.addActionListener(opadm);
		cadastrar.addActionListener(opadm);
		listar.addActionListener(opadm);
		sair.addActionListener(opadm);

		//add(selecionarImg);
		add(cadastrar);
		add(listar);
		add(sair);
	}
	

public class OuvinteDoPainelAdm implements ActionListener{

	public void actionPerformed(ActionEvent clique) {
		String[] opcoesCadastro = {"Cliente/Fornecedor", "Cadastrar Serviço", "Pacotes", "Orçamento"};
		String[] opcoesListar = {"Listar Fornecedores", "Listar Serviços", "Listar Pacotes" };

		String acao = clique.getActionCommand();

		switch(acao) {
		case "Cadastrar":
			String opcao = (String)JOptionPane.showInputDialog(Janela.getInstance(), "Qual desses você irá cadastrar?", null, JOptionPane.QUESTION_MESSAGE, null, opcoesCadastro, "Party Planner");
			
			if (opcao.equals(opcoesCadastro[0]) )
				Janela.setPanel(new PainelDeCadastro((Administrador)adm) );
			else if (opcao.equals(opcoesCadastro[1]))
				Janela.setPanel(new PainelCadastroServicos((Administrador)adm)); 
			else 
				Janela.setPanel(new PainelDoAdm((Administrador)adm));
			break;	
		case "Listar":
			String opcaoListar = (String)JOptionPane.showInputDialog(Janela.getInstance(), "Qual desses você gostaria de listar?", null, JOptionPane.QUESTION_MESSAGE, null, opcoesListar, "Party Planner");
			
			if (opcaoListar.equals(opcoesListar[0]) )
				Janela.setPanel(new PainelDeListagemFornecedor((Administrador)adm) );
			else if (opcaoListar.equals(opcoesListar[1]))
				Janela.setPanel(new PainelListagemServico((Administrador)adm,null));			
			else 
				Janela.setPanel(new PainelDoAdm((Administrador)adm));
			break;
		
		case "Sair":
			Janela.setPanel(new PainelDeLogin());
			break;
		}

	}
	
}
}
