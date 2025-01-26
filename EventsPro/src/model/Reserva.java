package model;

public class Reserva {
	private EspacoEvento espacoEvento;
	private String data;
	private Cliente cliente;
	
	public Reserva() {
		espacoEvento = new EspacoEvento();
		data = "";
		cliente = new Cliente();
	}
	public Reserva(EspacoEvento espacoEvento, String data, Cliente cliente) {
		this.espacoEvento = espacoEvento;
		this.data = data;
		this.cliente = cliente;
	}
	public EspacoEvento getEspacoEvento() {
		return espacoEvento;
	}
	public void setEspacoEvento(EspacoEvento espacoEvento) {
		this.espacoEvento = espacoEvento;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
