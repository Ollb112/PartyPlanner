package Paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Tela.Janela;
import Utilitarios.MeuJButton;
import back.Administrador;
import back.CentralDeInformacoes;
import back.Fornecedor;
import back.Persistencia;
import back.Servico;

public class PainelListagemServico extends JPanel{
	private MeuJButton cadastrar;
	private MeuJButton excluir;
	private MeuJButton voltar;
	private MeuJButton editar;
	private JTable tabela;
	private JTable tabelaTodosServicos;

	private Administrador adm;
	private Fornecedor fornecedor;
	private CentralDeInformacoes cdi;
	private Persistencia pe;
	public PainelListagemServico(Administrador user,Fornecedor userF) {
		pe = new Persistencia();
		adm = user;
		cdi = pe.recuperarCentral(adm.getEmail() + (".xml"));
		
		setLayout(null);
		setSize(800,450);
		
		if (userF == null)
			addJTableTodosServicos();
		else {
			addJTable(fornecedor);
			fornecedor = (Fornecedor)cdi.recuperarFornecedorPorEmail(userF.getEmail());
		}
		addBotao();
	}
	
	public void addJTable(Fornecedor f) {

		DefaultTableModel modeloTabela = new DefaultTableModel();
		modeloTabela.addColumn("Fornecedor");
		modeloTabela.addColumn("Serviço");
		modeloTabela.addColumn("Descrição");
		modeloTabela.addColumn("Valor");

		for(Servico s: f.getServicos()) {
			Object[] linha = new Object[4];
			linha[0] = f.getNome();
			linha[1] = s.getTipoServico();
			linha[2] = s.getDescricao();
			linha[3] = s.getPrecoServico();
			modeloTabela.addRow(linha);
		}
		tabela = new JTable(modeloTabela);
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(65, 50, 650, 240);
		add(painelTabela);
	}
	
	public void addJTableTodosServicos() {

		DefaultTableModel modeloTabela = new DefaultTableModel();
		modeloTabela.addColumn("Fornecedor");
		modeloTabela.addColumn("Serviço");
		modeloTabela.addColumn("Descrição");
		modeloTabela.addColumn("Valor");

		for (Fornecedor u: cdi.getTodosOsFornecedores()) {
			for(Servico s: u.getServicos()) {
				Object[] linha = new Object[4];
				linha[0] = u.getNome();
				linha[1] = s.getTipoServico();
				linha[2] = s.getDescricao();
				linha[3] = s.getPrecoServico();
				modeloTabela.addRow(linha);
			}
		}
		tabelaTodosServicos = new JTable(modeloTabela);
		JScrollPane painelTabela = new JScrollPane(tabelaTodosServicos);
		painelTabela.setBounds(65, 50, 650, 240);
		add(painelTabela);
	}
	
	public void addBotao() {
		OuvinteDoPainelDeListagemServico opls = new OuvinteDoPainelDeListagemServico();
		cadastrar = new MeuJButton(65, 300, 180, 30, "Cadastrar Novo Serviço");
		excluir = new MeuJButton(455, 300, 90, 30, "Excluir");
		voltar =  new MeuJButton(550, 300, 90, 30, "Voltar");
		editar = new MeuJButton(250, 300, 200, 30, "Editar Tipo do Serviço");
		cadastrar.addActionListener(opls);
		excluir.addActionListener(opls);
		voltar.addActionListener(opls);
		editar.addActionListener(opls);

		add(cadastrar);
		add(excluir);
		add(voltar);
		add(editar);
	}
	
	public class OuvinteDoPainelDeListagemServico implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String acao = e.getActionCommand();
			switch (acao) {
			case "Cadastrar Novo Serviço":
				Janela.setPanel(new PainelCadastroServicos(adm));
				break;
			case "Excluir":
				int linhaSelecionada;
				if(fornecedor == null)
					linhaSelecionada = tabelaTodosServicos.getSelectedRow();
				else
					linhaSelecionada = tabela.getSelectedRow();

				if (linhaSelecionada == -1)
					JOptionPane.showMessageDialog(Janela.getInstance(), "Selecione Um Fornecedor");
				else if (fornecedor != null){
					fornecedor.getServicos().remove(linhaSelecionada);
					pe.salvarCentral(cdi, adm.getEmail() + ".xml");
					Janela.setPanel(new PainelListagemServico(adm,fornecedor));
				}
				else {
					int cont = 0;
					for (Fornecedor u: cdi.getTodosOsFornecedores()) {
						for(Servico s: u.getServicos()) {
							cont++;
							if (cont == linhaSelecionada) {
								u.getServicos().remove(s);
								
								pe.salvarCentral(cdi, adm.getEmail() + ".xml");
								Janela.setPanel(new PainelListagemServico(adm,null));						
							}
						}
					}
				}
				break;
			case "Editar Tipo do Serviço":
				if(fornecedor == null)
					linhaSelecionada = tabelaTodosServicos.getSelectedRow();
				else
					linhaSelecionada = tabela.getSelectedRow();
				
				String opcao;
				
				if (linhaSelecionada == -1)
					JOptionPane.showMessageDialog(Janela.getInstance(), "Selecione Um Serviço");
				else if (fornecedor != null){
					opcao = JOptionPane.showInputDialog(Janela.getInstance(),"Informe o novo tipo do serviço", "PartyPlanner", JOptionPane.OK_CANCEL_OPTION);
					Servico serv = fornecedor.getServicos().get(linhaSelecionada);
						serv.setTipoServico(opcao);
					pe.salvarCentral(cdi, adm.getEmail() + ".xml");
					Janela.setPanel(new PainelListagemServico(adm,fornecedor));
				}
				else {
					opcao = JOptionPane.showInputDialog(Janela.getInstance(),"Informe o novo tipo do serviço", "PartyPlanner", JOptionPane.OK_CANCEL_OPTION);
					int cont = 0;
					for (Fornecedor u: cdi.getTodosOsFornecedores()) {
						for(Servico s: u.getServicos()) {
							cont++;
							if (cont == linhaSelecionada) {
									s.setTipoServico(opcao);
								pe.salvarCentral(cdi, adm.getEmail() + ".xml");
								Janela.setPanel(new PainelListagemServico(adm,null));						
							}
						}
					}
				}
			case "Voltar":
				Janela.setPanel(new PainelDeListagemFornecedor(adm));
				break;
			}
		}
	}

}
