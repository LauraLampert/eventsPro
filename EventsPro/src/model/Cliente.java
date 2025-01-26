package model;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Conta {
	private String cpf;
	
	private List <Reserva> listReservas;
	
	public Cliente() {
		super();
		cpf = "";
		listReservas = new ArrayList<>();
	}
	public Cliente(String nome, String email, String senha, String cpf, ArrayList<Reserva> listReservas)  {
		super(nome, email, senha);
		this.cpf = cpf;
		this.listReservas = listReservas;
	}
	public Cliente(String nome, String email, String senha, String cpf)  {
		super(nome, email, senha);
		this.cpf = cpf;
		this.listReservas =  new ArrayList<Reserva>();
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<Reserva> getListReservas() {
		return listReservas;
	}
	public void setListReservas(List<Reserva> listReservas) {
		this.listReservas = listReservas;
	}
	
}
