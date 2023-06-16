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
	private MeuJButton planilha;
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
		planilha = new MeuJButton(550, 110, 210,50,"Gerar Planilha de Finanças");
		selecionarImg = new MeuJButton(0, 260, 250, 30, "Selecionar Foto de Perfil");

		selecionarImg.addActionListener(opadm);
		cadastrar.addActionListener(opadm);
		listar.addActionListener(opadm);

		//add(selecionarImg);
		add(cadastrar);
		add(listar);
		add(planilha);
	}
	

public class OuvinteDoPainelAdm implements ActionListener{

	public void actionPerformed(ActionEvent clique) {
		String[] opcoesCadastro = {"Cliente/Fornecedor", "Tipo de Serviço", "Pacotes", "Orçamento"};
		String acao = clique.getActionCommand();

		switch(acao) {
		case "Cadastrar":/*"Login"*/
			String opcao = (String)JOptionPane.showInputDialog(Janela.getInstance(), "Qual desses você irá cadastrar?", null, JOptionPane.QUESTION_MESSAGE, null, opcoesCadastro, "Party Planner");
			
			if (opcao.equals(opcoesCadastro[0]) )
				Janela.setPanel(new PainelDeCadastro((Administrador)adm) );
			else if (opcao.equals(opcoesCadastro[1]))
				Janela.setPanel(new PainelDeListagem((Administrador)adm)); 
			break;	
		case "Listar":
			Janela.setPanel(new PainelDeListagem((Administrador)adm));
			break;
		case "Selecionar Foto de Perfil":
		    JFileChooser seletor = new JFileChooser();
		    FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif");

		    seletor.setBounds(500, 50, 200, 250);
		    seletor.setFileFilter(filtro);
		    
		    int result = seletor.showOpenDialog(Janela.getInstance());
		    
		    if (result == JFileChooser.APPROVE_OPTION) {
		        // O usuário selecionou um arquivo
		        String caminhoArquivo = seletor.getSelectedFile().getAbsolutePath();
				ImageIcon imagem = new ImageIcon(caminhoArquivo);
				JLabel icone = new JLabel(imagem);
				adm.setIcone(imagem);
				SwingUtilities.updateComponentTreeUI(Janela.getInstance());
		    } else if (result == JFileChooser.CANCEL_OPTION) {
		        // O usuário cancelou a operação
		        //JOptionPane.showMessageDialog(this, "Operação cancelada pelo usuário.");
		    }
			
			break;
		}

	}
	
}
}
