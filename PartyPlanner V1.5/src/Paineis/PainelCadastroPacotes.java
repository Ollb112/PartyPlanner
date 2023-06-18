package Paineis;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import Tela.Janela;
import Utilitarios.MeuJButton;
import Utilitarios.MeuJLabel;
import Utilitarios.MeuJTextField;
import back.Fornecedor;

public class PainelCadastroPacotes extends JPanel{
	private MeuJButton cadastrar;
	private MeuJButton voltar;
	private MeuJTextField campoNome;
	private MeuJTextField campoValor;
	private JComboBox<Fornecedor> comboFornecedor;
	
	public PainelCadastroPacotes() {
		setSize(800,450);
		setLayout(null);
		addJLabel();
		addJtextfield();
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

	public void addJButton() {
		
	}
	public static void main (String[] args) {
		Janela.setPanel(new PainelCadastroPacotes());
	}
}
