package code;

public class Regiao {
	
	private String nome;
	private String infecoes;
	private String internamentos;
	private String testes;
	public boolean foiApresentado = false;
	
	public Regiao(String nome) {
		this.nome = nome;
	}
	
	public Regiao(String nome, String infecoes, String internamentos, String testes) {
		this.nome = nome;
		this.infecoes = infecoes;
		this.internamentos = internamentos;
		this.testes = testes;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getInfecoes() {
		return infecoes;
	}
	public void setInfecoes(String infecoes) {
		this.infecoes = infecoes;
	}
	public String getInternamentos() {
		return internamentos;
	}
	public void setInternamentos(String internamentos) {
		this.internamentos = internamentos;
	}
	public String getTestes() {
		return testes;
	}
	public void setTestes(String testes) {
		this.testes = testes;
	}

	
	

}
