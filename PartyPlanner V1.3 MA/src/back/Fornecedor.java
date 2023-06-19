package back;

import java.util.ArrayList;

public class Fornecedor extends Usuario {
	private ArrayList<Servico> servicos = new ArrayList<Servico>();
	
	public Fornecedor(String a, String b, String c,String d, Sexo e, String f, String g,String h) {
		super(a,b,c,d,e,f,g,h);
	}
	public Boolean estaContido(String s) {
        for (Servico x: servicos) {
            if(s.equals(x.getTipoServico())) {
                return true;
            }
        }
        return false;
    }
	
	public void adicionarServico(Servico servico) {
	    for (Servico s : servicos) {
	        if (s.getTipoServico().equals(servico.getTipoServico())) {
	        }
	    }
	    servicos.add(servico);
	}
	public ArrayList<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(ArrayList<Servico> servicos) {
		this.servicos = servicos;
	}
	

}
