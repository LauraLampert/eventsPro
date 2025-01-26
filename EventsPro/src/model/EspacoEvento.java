package model;

public class EspacoEvento {
	private String nomeEspaco;
	private String endereco;
	private int capacidade;
	private double precoDia;
	private Empresa empresa;
	
	public EspacoEvento() {
		nomeEspaco = "";
		endereco = "";
		empresa = new Empresa();
	}
	public EspacoEvento(String nomeEspaco, String endereco, int capacidade, double precoDia, Empresa empresa) {
		this.nomeEspaco = nomeEspaco;
		this.endereco = endereco;
		this.capacidade = capacidade;
		this.precoDia = precoDia;
		this.empresa = empresa;
	}
	
	public String getNomeEspaco() {
		return nomeEspaco;
	}
	public void setNomeEspaco(String nomeEspaco) {
		this.nomeEspaco = nomeEspaco;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public double getPrecoDia() {
		return precoDia;
	}
	public void setPrecoDia(double precoDia) {
		this.precoDia = precoDia;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@Override
    public boolean equals(Object obj) {
        if(obj instanceof EspacoEvento) {
            if(endereco.equals(((EspacoEvento)obj).getEndereco())) {
                return true;
            }
        }
        return false;
    }

}
