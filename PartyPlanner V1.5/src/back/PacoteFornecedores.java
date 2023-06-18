package back;

import java.util.ArrayList;

public class PacoteFornecedores {
	private ArrayList<Fornecedor> pacoteFornecedores;
	private float valorPromocional;
	private String nomePacote;
	public PacoteFornecedores(Fornecedor fornecedor,Float valor) {
		adicionarFornecedor(fornecedor);
		valorPromocional = valor;
	}
	
	public boolean adicionarFornecedor(Fornecedor f) {
		        for (Fornecedor x: pacoteFornecedores) {
		            if(x.getEmail().equals(f.getEmail())) {
		                return false;
		            }
		        }
		        pacoteFornecedores.add(f);
		        return true;  
	}
	
	public ArrayList<Fornecedor> getPacoteFornecedores() {
		return pacoteFornecedores;
	}

	public void setPacoteFornecedores(ArrayList<Fornecedor> fornecedores) {
		this.pacoteFornecedores = fornecedores;
	}

	public float getValorPromocional() {
		return valorPromocional;
	}

	public void setValorPromocional(float valorPromocional) {
		this.valorPromocional = valorPromocional;
	}

	public String getNomePacote() {
		return nomePacote;
	}

	public void setNomePacote(String nomePacote) {
		this.nomePacote = nomePacote;
	}
	
}
