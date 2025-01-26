package model;
import java.util.ArrayList;
import java.util.List;

public class Empresa extends Conta{
	private String cnpj;
	
	private List <EspacoEvento> listEspacosEventos;
	
	public Empresa() {
		super();
		cnpj = "";
		listEspacosEventos = new ArrayList<>();
	}
	public Empresa(String nome, String email, String senha, String cnpj, ArrayList<EspacoEvento> listEspacosEventos) {
		super(nome, email, senha);
		this.cnpj = cnpj;
		this.listEspacosEventos = listEspacosEventos;
	}
	public Empresa(String nome, String email, String senha, String cnpj) {
		super(nome, email, senha);
		this.cnpj = cnpj;
		this.listEspacosEventos = new ArrayList<EspacoEvento>();
	}
	
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public List<EspacoEvento> getListEspacosEventos() {
		return listEspacosEventos;
	}
	public void setListEspacosEventos(List<EspacoEvento> listEspacosEventos) {
		this.listEspacosEventos = listEspacosEventos;
	}
	
}
