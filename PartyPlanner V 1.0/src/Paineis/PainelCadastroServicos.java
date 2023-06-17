package Paineis;


import java.awt.Color;
import Utilitarios.MeuJLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Tela.Janela;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJTextField;
import back.Administrador;
import back.CentralDeInformacoes;
import back.Fornecedor;
import back.Persistencia;
import back.Servico;


	
public class PainelCadastroServicos extends JPanel{
		
	private MeuJButton cadastrar;
	private MeuJTextField campoTipoDeServico;
	private JTextArea campoDescricao;
	private JComboBox<Fornecedor> opcoes;
	private Administrador adm;	
		
	public PainelCadastroServicos(Administrador user) {
		adm = user;
		Persistencia pe = new Persistencia();
		CentralDeInformacoes cdi = pe.recuperarCentral(adm.getEmail() + ".xml");
		setSize(800,450);
		setLayout(null);	
		addJLabel();
		addCamposDeTexto();
		addJButton();
		addComboBox();
		setVisible(true);
		}
	
	public void addJLabel() {
		MeuJLabel tipoServico = new MeuJLabel(325,50, 100, 30, "Tipo do Serviço:");
		MeuJLabel descricao = new MeuJLabel(310,120,130, 30, "Descriçao do serviço:");
		MeuJLabel fornecedor = new MeuJLabel(590, 50, 130, 30, "Escolha o Fornecedor:");

		add(tipoServico);
		add(descricao);
		add(fornecedor);
		}
	public void addJButton() {
		OuvinteDeServicos observer = new OuvinteDeServicos();
		
		cadastrar = new MeuJButton(120, 320, 100, 30,"Cadastrar");
		
		cadastrar.addActionListener(observer);
		add(cadastrar);
		
		}
	
	public void addCamposDeTexto() {
		campoTipoDeServico = new MeuJTextField(250,90,250,40);
		
		campoDescricao = new JTextArea();
		
		campoDescricao.setBounds(250,160, 250, 100);
		campoDescricao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		campoDescricao.setLineWrap(true);
		
		add(campoTipoDeServico);
		add(campoDescricao);

		}
	public void addComboBox() {
		Persistencia pe = new Persistencia();
		CentralDeInformacoes cdi = pe.recuperarCentral("oliverlobo10@gmail.com.xml");
		ArrayList<Fornecedor> fornecedores = cdi.getTodosOsFornecedores();
		DefaultComboBoxModel<Fornecedor> comboBoxModel = new DefaultComboBoxModel<>();
		for (Fornecedor fornecedor : fornecedores) {
		    comboBoxModel.addElement(fornecedor);
		}

		opcoes = new JComboBox<>(comboBoxModel);
		opcoes.setBounds(580, 90, 150, 30);
		opcoes.setBorder(BorderFactory.createLineBorder(Color.black));
		add(opcoes);
	}
	
	public class OuvinteDeServicos implements ActionListener{
		public void actionPerformed(ActionEvent Clique) {
			String acao = Clique.getActionCommand();
			switch (acao) {
			case ("Cadastrar"):
				Persistencia pe = new Persistencia();
				CentralDeInformacoes cdi = pe.recuperarCentral(adm.getEmail() + ".xml");
				ArrayList<Fornecedor> fornecedores = cdi.getTodosOsFornecedores();
				for (int i = 0; i < cdi.getTodosOsFornecedores().size(); i++) {
					if (i == opcoes.getSelectedIndex()) {
						Servico servico = new Servico(campoTipoDeServico.getText(),campoDescricao.getText());
						Fornecedor f = fornecedores.get(i);
						f.adicionarServico(servico);
						pe.salvarCentral(cdi, adm.getEmail() + ".xml");
					}
				}
				int resposta = JOptionPane.showConfirmDialog(Janela.getInstance(), "Gostaria De Continuar Cadastrando?","PartyPlanner",JOptionPane.YES_NO_OPTION);
				if(resposta == JOptionPane.YES_OPTION)
					Janela.setPanel(new PainelCadastroServicos(adm));
				else
					Janela.setPanel(new PainelDoAdm(adm));
				break;
			}
						
		}
					
	}
	
}

	
	
	
	
	
	

