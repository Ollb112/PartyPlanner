package Paineis;


import java.awt.Color;
import Utilitarios.MeuJLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Tela.Janela;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import Utilitarios.MeuJPasswordField;
import Utilitarios.MeuJTextArea;
import Utilitarios.MeuJTextField;
import back.CentralDeInformacoes;
import back.Fornecedor;
import back.Persistencia;
import back.Usuario;

	
public class PainelCadastroServicos extends JPanel{
		
	//private JButton cadastrar;
	private MeuJTextField campoTipoDeServico;
	private MeuJTextField campoEmailFornecedor;
	private MeuJPasswordField campoSenha;
	private MeuJTextArea campoDaDescricao;
		
		
	public PainelCadastroServicos() {
		setSize(800,450);
		setLayout(null);	
		addJLabel();
		addCamposDeTexto();
		addJButton();
		setVisible(true);
		}
	public void addJLabel() {

		MeuJLabel email = new MeuJLabel(20,50, 50, 40, "Email:", Color.BLACK);
		MeuJLabel senha = new MeuJLabel(20,100,50, 40, "Senha:", Color.BLACK);
		
		MeuJLabel tipoServico = new MeuJLabel(550,50, 90, 40, "tipo do Serviço:", Color.BLACK);
		MeuJLabel descricao = new MeuJLabel(535,120,120, 40, "Descriçao do serviço:", Color.BLACK);
		MeuJLabel titulo = new MeuJLabel(0,0, 800, 50, "Serviços", Color.BLACK,Color.GRAY);
		MeuJLabel cadastrados = new MeuJLabel(90,170,120,40, "Lista de Serviços:", Color.BLACK);
		add(tipoServico);
		add(descricao);
		add(titulo);
		add(cadastrados);
		add(email);
		add(senha);
		}
	public void addJButton() {
		OuvinteDeServicos observer = new OuvinteDeServicos();
		MeuJButton cadastrar = new MeuJButton(545, 320, 100, 40,"Cadastrar",Color.BLUE);
		cadastrar.addActionListener(observer);
		add(cadastrar);
		
		}
	public void addCamposDeTexto() {
		campoTipoDeServico = new MeuJTextField(420,90,350,40);
		campoDaDescricao = new MeuJTextArea(420,160, 350, 150);
		campoEmailFornecedor = new MeuJTextField(20,80,250,30);
		campoSenha = new MeuJPasswordField(20,130,250,30);
		add(campoTipoDeServico);
		add(campoDaDescricao);
		add(campoSenha);
		add(campoEmailFornecedor);
		}
	public void addComboBox() {
		
	}
		
	public static void main(String[] testes) {
		Janela.setPanel(new PainelCadastroServicos());	
		}
	public class OuvinteDeServicos implements ActionListener{
		public void actionPerformed(ActionEvent Clique) {
			String acao = Clique.getActionCommand();
			switch (acao) {
				case "Cadastrar":
					//todosOsFornecedores
					Persistencia pe = new Persistencia();
					CentralDeInformacoes central = pe.recuperarCentral("central.xml");
					String email1 =  campoEmailFornecedor.getText();
					String senha2 = campoSenha.getText();
					//Usuario fornecedor =central.recuperarFornecedorPorEmail(email1);
					//if(fornecedor.getSenha().equals(senha2)) {}
					for(Usuario fornecedor: central.getTodosOsFornecedores()) {
						if(fornecedor.getEmail().equals(email1)) {
							//if(fornecedor)
							
						}
					}
						
					}
					
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

