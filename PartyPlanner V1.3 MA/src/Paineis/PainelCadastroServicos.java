package Paineis;


import java.awt.Color;
import Utilitarios.MeuJLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import Tratamentos.CamposNulosException;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJTextField;
import back.Administrador;
import back.CentralDeInformacoes;
import back.Fornecedor;
import back.Persistencia;
import back.Servico;


	
public class PainelCadastroServicos extends JPanel{
		
	private MeuJButton cadastrar;
	private MeuJButton voltar;
	private MeuJTextField campoTipoDeServico;
	private MeuJTextField campoPrecoServico;
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
		MeuJLabel descricao = new MeuJLabel(310,130,130, 30, "Descriçao do serviço:");
		MeuJLabel fornecedor = new MeuJLabel(590, 50, 130, 30, "Escolha o Fornecedor:");
		MeuJLabel valor = new MeuJLabel(315, 260, 130, 30, "Valor do Fornecedor:");

		add(tipoServico);
		add(descricao);
		add(fornecedor);
		add(valor);
		}
	public void addJButton() {
		OuvinteDeServicos observer = new OuvinteDeServicos();
		
		cadastrar = new MeuJButton(120, 340, 100, 30,"Cadastrar");
		voltar = new MeuJButton(525, 340, 100, 30,"Voltar");

		cadastrar.addActionListener(observer);
		voltar.addActionListener(observer);
		add(cadastrar);
		add(voltar);
		
		}
	
	public void addCamposDeTexto() {
		campoTipoDeServico = new MeuJTextField(250,90,250,40);
		campoPrecoServico = new MeuJTextField(250,290,250,40);
		campoDescricao = new JTextArea();
		
		campoDescricao.setBounds(250,160, 250, 100);
		campoDescricao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		campoDescricao.setLineWrap(true);
		
		add(campoTipoDeServico);
		add(campoDescricao);
		add(campoPrecoServico);

		}
	public void addComboBox() {
		Persistencia pe = new Persistencia();
		CentralDeInformacoes cdi = pe.recuperarCentral(adm.getEmail() +".xml");
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
						DecimalFormat df = new DecimalFormat("#,###.##");
						Servico servico;
						CamposNulosException erro;
						try {
							if(!campoTipoDeServico.getText().equals(null) && !campoTipoDeServico.getText().equals("")) {
								if(!campoDescricao.getText().equals(null) && !campoDescricao.getText().equals("")) {
									servico = new Servico(campoTipoDeServico.getText(),campoDescricao.getText(),df.parse(campoPrecoServico.getText()).floatValue());
									Fornecedor f = fornecedores.get(i);
									f.adicionarServico(servico);
									int resposta = JOptionPane.showConfirmDialog(Janela.getInstance(), "Gostaria De Continuar Cadastrando?","PartyPlanner",JOptionPane.YES_NO_OPTION);
									if(resposta == JOptionPane.YES_OPTION)
										Janela.setPanel(new PainelCadastroServicos(adm));
									else if (resposta == JOptionPane.NO_OPTION || resposta == JOptionPane.CLOSED_OPTION)
										Janela.setPanel(new PainelDoAdm(adm));
									pe.salvarCentral(cdi, adm.getEmail() + ".xml");
									break;
								}
								else {
									throw erro = new CamposNulosException("Descriçao invalida");
									}
								}
							else {
								throw erro = new CamposNulosException("tipo de serviço invalido");
							}
							
							
						} catch(CamposNulosException erroLocal) {
							JOptionPane.showMessageDialog(null, erroLocal.getErro(),"Campo Invalido ",JOptionPane.WARNING_MESSAGE);
							break;
							}
							catch (ParseException e) {
								JOptionPane.showMessageDialog(null," preço invalido ou Desconhecido ","Campo Invalido ",JOptionPane.WARNING_MESSAGE);
								break;
						}

					}
				}
				break;
			case ("Voltar"):
				Janela.setPanel(new PainelDoAdm(adm));
				break;
			}
						
		}
					
	}
	
}

	
	
	
	
	
	

