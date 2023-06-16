package back;

public class Servico {
	private String tipoServico;
	private String descricao;
	
	public Servico(String a, String b) {
		tipoServico = a;
		descricao = b;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
