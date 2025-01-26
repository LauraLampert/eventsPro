package persistencia;
import java.util.ArrayList;
import java.util.List;

import apresentacao.PrintTela;
import model.Cliente;
import model.Empresa;
import model.EspacoEvento;
import model.Reserva;

public class ReservaDAO {
	PrintTela printTela = new PrintTela();
	
	protected List<Reserva> listReserva;

	public ReservaDAO() {
		listReserva = new ArrayList<>();
	}
	public ReservaDAO(ArrayList<Reserva> listReserva) {
		this.listReserva = listReserva;
	}
	
	public void reservar(EspacoEvento espacoEvento, String data, Cliente cliente) {
		boolean cadastrado = false;
		for(Reserva reserva : listReserva) {
			if(reserva.getEspacoEvento().equals(espacoEvento) && reserva.getData().equals(data)){
				printTela.erroReserva();
				cadastrado = true;
			}
		}
		if(!cadastrado) {
			Reserva reserv = new Reserva(espacoEvento, data, cliente);
			listReserva.add(reserv);
			printTela.cadastrado("Reserva");
			}
	}
	
	
		public List<Reserva> listarReservas(){
			List<Reserva> listaDeReservas = new ArrayList<>();
			for(Reserva reserva : listReserva) {
				String nomeREE = reserva.getEspacoEvento().getNomeEspaco();
				String enderecoREE = reserva.getEspacoEvento().getEndereco();
				int capacidadeREE = reserva.getEspacoEvento().getCapacidade();
				double precoREE = reserva.getEspacoEvento().getPrecoDia();
				
				String empresaRN = reserva.getEspacoEvento().getEmpresa().getNome();
				String empresaRE = reserva.getEspacoEvento().getEmpresa().getEmail();
				
				Empresa infoEmpresa = new Empresa(empresaRN, empresaRE, "", "");
				
				EspacoEvento infoEspacoEvent = new EspacoEvento(nomeREE, enderecoREE, capacidadeREE, precoREE, infoEmpresa);
				
				String dataR = reserva.getData();
				
				String clientRN = reserva.getCliente().getNome();
				String clientRE = reserva.getCliente().getEmail();
				String clientRS = reserva.getCliente().getSenha();
				String clientRC = reserva.getCliente().getCpf();
				
				Cliente infoClient = new Cliente(clientRN, clientRE, clientRS, clientRC);
				
				Reserva infoReserva = new Reserva(infoEspacoEvent, dataR, infoClient);
				listaDeReservas.add(infoReserva);
			}
			return listaDeReservas;
		}
		
		public Reserva buscarReserva(EspacoEvento espacoEvento, String data){
			for(Reserva reserva : listReserva) {
				if(reserva.getEspacoEvento().equals(espacoEvento) && reserva.getData().equals(data)) {
					return reserva;
				}
			}
			return null;
		}
		
		public void editarReserva(int nReserva, String newData) {
			boolean cadastrado = false;
			for(Reserva reserva : listReserva) {
				if(reserva.getEspacoEvento().equals(listReserva.get(nReserva).getEspacoEvento()) && reserva.getData().equals(newData)){
					printTela.erroReserva();
					cadastrado = true;
				}
			}
			if(!cadastrado) {
				listReserva.get(nReserva).setData(newData);
				printTela.editado("Reserva");
			}
			
		}
		
		public void excluirReserva(int nReserva){
			listReserva.remove(nReserva);
			printTela.excluido("Reserva");
		}
		
	
}
