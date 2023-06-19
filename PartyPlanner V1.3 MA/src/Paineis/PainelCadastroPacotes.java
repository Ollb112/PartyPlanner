package Paineis;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Tela.Janela;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import Utilitarios.MeuJTextField;
import back.CentralDeInformacoes;
import back.Fornecedor;
import back.PacoteFornecedores;
import back.Persistencia;
import back.Servico;

public class PainelCadastroPacotes extends JPanel{
	private MeuJButton cadastrar;
	private MeuJButton voltar;
	
	private JTable tabelaTodosServicos;
	private MeuJTextField campoNome;
	private MeuJTextField campoValor;
	private CentralDeInformacoes cdi; 
	private JComboBox<Servico> comboServico;
	
	public PainelCadastroPacotes() {
		cdi = new Persistencia().recuperarCentral("oliverlobo10@gmail.com.xml");
		setSize(800,450);
		setLayout(null);
		addJLabel();
		addJtextfield();
		addComboBox();
		addJButton();
	}
	
	public void addComboBox() {
		Persistencia pe = new Persistencia();
		CentralDeInformacoes cdi = pe.recuperarCentral("oliverlobo10@gmail.com.xml");
		ArrayList<Fornecedor> fornecedores = cdi.getTodosOsFornecedores();
		DefaultComboBoxModel<Servico> comboBoxModel = new DefaultComboBoxModel<>();
		for (Fornecedor f: fornecedores) {
			for (Servico servicos : f.getServicos()) {
				comboBoxModel.addElement(servicos);
			}
		}
		comboServico = new JComboBox<>(comboBoxModel);
		comboServico.setBounds(580, 90, 150, 30);
		comboServico.setBorder(BorderFactory.createLineBorder(Color.black));
		add(comboServico);
	}
	
	public void addJLabel() {
		MeuJLabel fornecedor = new MeuJLabel(110,50,90,30,"Fornecedor");
		MeuJLabel listFornecedores = new MeuJLabel(500,50,160,30," Fornecedores e Serviços");

		MeuJLabel nomePacote = new MeuJLabel(100,120,100,30,"Nome do Pacote");
		MeuJLabel valorPacote = new MeuJLabel(100,200,100,30,"Valor do Pacote");
		
		add(fornecedor);
		add(listFornecedores);
		add(nomePacote);
		add(valorPacote);
	}
	
	public void addJtextfield() {
		campoNome = new MeuJTextField(50,150,200,30);
		campoValor = new MeuJTextField(115,230,60,30);
		
		add(campoNome);
		add(campoValor);
		
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
		painelTabela.setBounds(400, 80, 350, 240);
		add(painelTabela);
	}

	public void addJButton() {
		cadastrar = new MeuJButton(100, 380, 100, 30, "Cadastrar");
		
		cadastrar.addActionListener(new OuvintePainelCadastroPacotes());
		
		add(cadastrar);
		
	}
	
	public static void main (String[] args) {
		Janela.setPanel(new PainelCadastroPacotes());
	}
	
	public class OuvintePainelCadastroPacotes implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String acao = e.getActionCommand();
			
			switch (acao) {
			case "Cadastrar":
				
				Servico serv = (Servico) comboServico.getSelectedItem();
				Fornecedor fornecedor = null;
				
				for(Fornecedor f: cdi.getTodosOsFornecedores()) {
					
					for(Servico s: f.getServicos()) {
						
						if (s.equals(serv)) {
							fornecedor = f;
							fornecedor.adicionarServico(serv);
						}
					}
				}
				DecimalFormat df = new DecimalFormat("#,###.##");

				PacoteFornecedores pacote = new PacoteFornecedores(fornecedor,Float.parseFloat(campoValor.getText()));
				
				break;
			}
		}
		
	}
}
