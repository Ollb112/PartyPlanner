package Paineis;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import Tela.Janela;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import back.Administrador;
import back.CentralDeInformacoes;
import back.Fornecedor;
import back.Persistencia;

public class PainelDeListagemFornecedor extends JPanel{
	private MeuJButton detalhar;
	private MeuJButton excluir;
	private MeuJButton voltar;
	private JTable tabela;
	private	MeuJButton nome;
	private MeuJButton qtdServicos;
	
	private Administrador adm;
	public PainelDeListagemFornecedor(Administrador user) {
		this.adm = user;
		MeuJLabel filtro = new MeuJLabel(65,8,60,30,"Filtros:");
		add(filtro);
		setSize(800,450);
		setLayout(null);
		addBotao();
		addJTable();
	}
	public void addBotao() {
		OuvinteDoPainelDeListagem opl = new OuvinteDoPainelDeListagem();
		detalhar = new MeuJButton(65, 300, 90, 30, "Detalhar");
		excluir = new MeuJButton(340, 300, 90, 30, "Excluir");
		voltar =  new MeuJButton(624, 300, 90, 30, "Voltar");
		nome = new MeuJButton(115,10,70,30,"Nome");
		qtdServicos= new MeuJButton(195,10,175,30,"Quantidade de Serviços");
		detalhar.addActionListener(opl);
		excluir.addActionListener(opl);
		voltar.addActionListener(opl);

		add(detalhar);
		add(excluir);
		add(voltar);
		//add(nome);
		//add(qtdServicos);
	}

	public void addJTable() {
		Persistencia pe = new Persistencia();
		CentralDeInformacoes central = pe.recuperarCentral(adm.getEmail() + ".xml");

		DefaultTableModel modeloTabela = new DefaultTableModel();
		modeloTabela.addColumn("Nome");
		modeloTabela.addColumn("Fisica/Juridica");
		modeloTabela.addColumn("Numero de Contratos");
		
		for(Fornecedor u: central.getTodosOsFornecedores()) {
			Object[] linha = new Object[3];
			linha[0] = u.getNome();
			linha[1] = u.getTipoDeUsuario();
			linha[2] = u.getServicos().size();
			modeloTabela.addRow(linha);
		}
		tabela = new JTable(modeloTabela);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(65, 50, 650, 240);
		add(painelTabela);
	}
	

	public class OuvinteDoPainelDeListagem implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			Persistencia pe = new Persistencia();
			CentralDeInformacoes central = pe.recuperarCentral(adm.getEmail()+".xml");

			String acao = e.getActionCommand();
			switch (acao){
				case ("Detalhar"):
					int linhaSelecionada = tabela.getSelectedRow();
					if (linhaSelecionada == -1)
						JOptionPane.showMessageDialog(Janela.getInstance(), "Selecione Um Fornecedor");
					else {
					
						Fornecedor f = central.getTodosOsFornecedores().get(linhaSelecionada);
						Janela.setPanel(new PainelListagemServico(adm,f));
					}	
				break;
				case ("Excluir"):
					 linhaSelecionada = tabela.getSelectedRow();
					if (linhaSelecionada == -1)
						JOptionPane.showMessageDialog(Janela.getInstance(), "Selecione Um Fornecedor");
					else {
						central.getTodosOsFornecedores().remove(linhaSelecionada);
						pe.salvarCentral(central, adm.getEmail() + ".xml");
						Janela.setPanel(new PainelDeListagemFornecedor(adm));

					}
					break;
				case("Nome"):
					
					break;
				case ("Voltar"):
					Janela.setPanel(new PainelDoAdm(adm));
					
					break;
			}
			
		}
		
	}

}
