package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Paineis.PainelDeCadastroUsuario;
import Paineis.PainelDoAdm;
import Tela.Janela;

public class OuvinteDoPainelAdm implements ActionListener{

	public void actionPerformed(ActionEvent clique) {
		String[] opcoesCadastro = {"Cliente", "Fornecedor", "Tipo de Serviço", "Pacotes", "Orçamento"};
		String acao = clique.getActionCommand();

		switch(acao) {
		case "Cadastrar":
			String opcao = (String)JOptionPane.showInputDialog(Janela.getInstance(), "Qual desses você irá cadastrar?", null, JOptionPane.QUESTION_MESSAGE, null, opcoesCadastro, "Party Planner");
			
			if (opcao.equals(opcoesCadastro[0]))
				Janela.setPanel(new PainelDeCadastroUsuario() );
			
			break;	
		case "Listar":
			
			break;
		}

	}
	
}
