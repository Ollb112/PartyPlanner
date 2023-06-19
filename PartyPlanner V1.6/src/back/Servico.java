package back;

public class Servico {
	private String tipoServico;
	private String descricao;
	private float precoServico;
	public Servico(String a, String b, float c) {
		tipoServico = a;
		descricao = b;
		precoServico = c;
	}
	public String toString() {
		return tipoServico;
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
	public float getPrecoServico() {
		return precoServico;
	}
	public void setPrecoServico(float precoServico) {
		this.precoServico = precoServico;
	}

}
