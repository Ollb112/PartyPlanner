package Paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;
import Tela.Janela;
import Tratamentos.CamposNulosException;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import Utilitarios.MeuJPasswordField;
import Utilitarios.MeuJTextField;
import back.Administrador;
import back.CentralDeInformacoes;
import back.Cliente;
import back.Fornecedor;
import back.Persistencia;
import back.Sexo;
import back.Usuario;

/**
 * Classe que representa o painel de cadastro de usuários.
 */
public class PainelDeCadastro extends JPanel {

	private MeuJPasswordField campoSenha;
	private MeuJTextField campoNome;
	private MeuJTextField campoEmail;
	private MeuJTextField campoCpfCnpj;
	private JRadioButton rbFisico;
	private JRadioButton rbJuridico;
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private JRadioButton rbCliente;
	private JRadioButton rbFornecedor;
	private JFormattedTextField campoTelefone;
	private JFormattedTextField campoAno;
	private boolean controle = false;
	private Usuario adm;

	/**
	 * Construtor da classe PainelDeCadastro.
	 * 
	 * @param user O administrador responsável pelo cadastro.
	 */
	public PainelDeCadastro(Administrador user) {
		adm = user;
		setSize(800, 450);
		setLayout(null);
		addLabel();
		addRadioButton();
		addTextField();
		addButton();
	}
	/**
	 * Metodo que adiciona os textfields necessarios ao painel.
	 */
	private void addTextField() {
		campoSenha = new MeuJPasswordField(110, 170, 140, 30);
		campoNome = new MeuJTextField(110, 50, 150, 30);
		campoEmail = new MeuJTextField(110, 90, 150, 30);
		campoCpfCnpj = new MeuJTextField(110, 130, 140, 30);

		campoNome.setText("Nome Completo");
		campoEmail.setText("usuario@gmail.com");
		campoCpfCnpj.setText("insira somente os digitos");

		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##)#########");
			MaskFormatter mascaraAno = new MaskFormatter("##/##/####");

			campoTelefone = new JFormattedTextField(mascaraTelefone);
			campoAno = new JFormattedTextField(mascaraAno);

			campoTelefone.setBounds(110, 170, 140, 30);
			campoAno.setBounds(550, 50, 120, 30);

			add(campoTelefone);
			add(campoAno);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		add(campoSenha);
		add(campoNome);
		add(campoEmail);
		add(campoCpfCnpj);
	}

	/**
	 * Metodo que adiciona os jbuttons necessarios ao painel e já adiciona o ouvinte aos botoes criados.
	 */
	private void addButton() {
		OuvinteDoPainelDeCadastro opc = new OuvinteDoPainelDeCadastro();

		MeuJButton botaoCadastrar = new MeuJButton(150, 350, 100, 30, "Cadastrar");
		MeuJButton botaoCancelar = new MeuJButton(550, 350, 100, 30, "Cancelar");

		botaoCadastrar.addActionListener(opc);
		botaoCancelar.addActionListener(opc);
		add(botaoCadastrar);
		add(botaoCancelar);
	}

	/**
	 * Metodo que adiciona os labels necessarios ao painel.
	 */
	private void addLabel() {
		String[] nomeCamposJLabel = { "Nome:", "E-mail:", "Cpf/Cnpj:", "Telefone:", "Sexo:" };
		MeuJLabel ano = new MeuJLabel(420, 50, 120, 30, "Data de Nascimento:");

		int y = 50;
		int largura = 120;

		for (int i = 0; i < nomeCamposJLabel.length; i++) {
			MeuJLabel campoLabel = new MeuJLabel(50, y, largura, 30, nomeCamposJLabel[i]);

			add(campoLabel);
			y += 40;
		}
		add(ano);
	}

	/**
	 * Metodo que adiciona os radiobutton necessarios ao painel.
	 */
	private void addRadioButton() {

		rbFisico = new JRadioButton("Fisica", true);
		rbJuridico = new JRadioButton("Juridica", false);
		ButtonGroup rbg = new ButtonGroup();

		rbFisico.setBounds(260, 130, 65, 25);
		rbJuridico.setBounds(325, 130, 80, 25);
		rbg.add(rbFisico);
		rbg.add(rbJuridico);
		add(rbFisico);
		add(rbJuridico);

		rbMasculino = new JRadioButton("Masculino", true);
		rbFeminino = new JRadioButton("Feminino", false);
		ButtonGroup rbg1 = new ButtonGroup();

		rbMasculino.setBounds(105, 215, 90, 25);
		rbFeminino.setBounds(195, 215, 80, 25);
		rbg1.add(rbMasculino);
		rbg1.add(rbFeminino);
		add(rbMasculino);
		add(rbFeminino);

		rbCliente = new JRadioButton("Cliente", true);
		rbFornecedor = new JRadioButton("Fornecedor", false);
		ButtonGroup rbg2 = new ButtonGroup();

		rbCliente.setBounds(105, 240, 90, 25);
		rbFornecedor.setBounds(195, 240, 95, 25);
		rbg2.add(rbCliente);
		rbg2.add(rbFornecedor);
		add(rbCliente);
		add(rbFornecedor);
	}

	/**
	 * Classe interna que implementa o ouvinte de eventos para o PainelDeCadastro.
	 */
	public class OuvinteDoPainelDeCadastro implements ActionListener {
		/**
		 * Aqui eu optei por criar um administrador e salvar ele na central.xml e ao mesmo tempo
		 * optei por criar um xml com o email do administrador o qual serve para que cada administrador
		 * possa ter seus respectivos dados em sua classe, isso me permite adicionar servicos, fornecedores
		 * e clientes nelas e que serao somente vistos pelo respectivo administrador.
		 */
		public void actionPerformed(ActionEvent e) {
			Persistencia pe = new Persistencia();
			CentralDeInformacoes central = pe.recuperarCentral("central.xml");
			Usuario user;
			CentralDeInformacoes centralAdm = pe.recuperarCentral(adm.getEmail() + ".xml");
			String acao = e.getActionCommand();
			switch (acao) {

			case "Cadastrar":

				String nome;
				String email;
				String senha =campoSenha.getText();
				String numeroId;
				String telefone;
				String nascimento;
				CamposNulosException erro;
				try {
					if(!campoNome.getText().equals("") && !campoNome.getText().equals("Nome Completo") ) {
						nome = campoNome.getText();
						if(!campoEmail.getText().equals("") && !campoEmail.getText().equals("usuario@gmail.com")) {
							email = campoEmail.getText();
							if(!campoCpfCnpj.getText().equals("") && !campoCpfCnpj.getText().equals("insira somente os digitos")) {
								numeroId = campoCpfCnpj.getText();
								if(!campoTelefone.getText().equals("(  )         ")) {
									telefone = campoTelefone.getText();
									if(!campoAno.getText().equals("  /  /    ")) {	
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
							throw erro = new CamposNulosException("E-mail ");
						}
					}
					else {
						throw erro = new CamposNulosException("Nome");
					}

				
				
				
				
				
				
				String tipoPessoa;
				Sexo sexo;

				if (rbMasculino.isSelected())
					sexo = Sexo.MASCULINO;
				else
					sexo = Sexo.FEMININO;

				if (rbFisico.isSelected())
					tipoPessoa = "Pessoa Fisica";
				else
					tipoPessoa = "Pessoa Juridica";

				if (rbCliente.isSelected()) {
					user = new Cliente(nome, numeroId, email, senha, sexo, telefone, nascimento, tipoPessoa);
					centralAdm.adicionarCliente((Cliente) user);
				} else {
					user = new Fornecedor(nome, numeroId, email, senha, sexo, telefone, nascimento, tipoPessoa);
					centralAdm.adicionarFornecedor((Fornecedor) user);
				}
				pe.salvarCentral(centralAdm, adm.getEmail() + ".xml");

				Janela.setPanel(new PainelDoAdm((Administrador) adm));
				break;
				}
				catch(CamposNulosException erroLocal) {
					JOptionPane.showMessageDialog(null, erroLocal.getErro(),"Campo Invalido ",JOptionPane.WARNING_MESSAGE);
				break;
				}
			case "Cancelar":
				Janela.setPanel(new PainelDoAdm((Administrador) adm));
				break;
			}
		}

	}
}