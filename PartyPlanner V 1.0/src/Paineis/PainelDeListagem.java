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

public class PainelDeListagem extends JPanel{
	private MeuJButton detalhar;
	private MeuJButton excluir;
	private MeuJButton cancelar;
	private JTable tabela;
	
	private Administrador adm;
	public PainelDeListagem(Administrador user) {
		this.adm = user;
		setSize(800,450);
		setLayout(null);
		addBotao();
		addJTable(adm);
	}
	public void addBotao() {
		OuvinteDoPainelDeListagem opl = new OuvinteDoPainelDeListagem();
		detalhar = new MeuJButton(65, 300, 90, 30, "Detalhar");
		excluir = new MeuJButton(340, 300, 90, 30, "Excluir");
		cancelar =  new MeuJButton(624, 300, 90, 30, "Cancelar");
		
		detalhar.addActionListener(opl);
		excluir.addActionListener(opl);
		cancelar.addActionListener(opl);

		add(detalhar);
		add(excluir);
		add(cancelar);
	}
	public void addComboBox() {
	}
	
	public void addJTable(Administrador adm) {
		Persistencia pe = new Persistencia();
		CentralDeInformacoes central = pe.recuperarCentral(adm.getEmail() + ".xml");
		//Object[] linhas = new Object[central.getTodosOsFornecedores().size()];

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
						ArrayList<Servico> servicos = f.getServicos();
						Object opcao =  JOptionPane.showInputDialog(Janela.getInstance(), "Qual desses serviços você gostaria de ver ?", null, JOptionPane.QUESTION_MESSAGE, null, servicos.toArray(), "Party Planner");
						System.out.println(opcao);
						Servico servico = f.getServicos().get(linhaSelecionada);
						if (opcao.equals(servico.getTipoServico()))
							JOptionPane.showMessageDialog(Janela.getInstance(), servico.getDescricao());
						else
							Janela.setPanel(new PainelDeListagem(adm));
					}	
				break;
				case ("Excluir"):
					 linhaSelecionada = tabela.getSelectedRow();
					if (linhaSelecionada == -1)
						JOptionPane.showMessageDialog(Janela.getInstance(), "Selecione Um Fornecedor");
					else {
						central.getTodosOsFornecedores().remove(linhaSelecionada);
						pe.salvarCentral(central, adm.getEmail() + ".xml");
						Janela.setPanel(new PainelDeListagem(adm));

					}
					break;
				case ("Cancelar"):
					Janela.setPanel(new PainelDoAdm(adm));
					
					break;
			}
			
		}
		
	}

}
