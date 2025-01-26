package persistencia;
import java.util.ArrayList;
import java.util.List;

import apresentacao.PrintTela;
import model.Empresa;
import model.EspacoEvento;

public class EspacoEventoDAO {
	PrintTela printTela = new PrintTela();
	
	private List<EspacoEvento> listEspacoEvento;

	public EspacoEventoDAO() {
		listEspacoEvento = new ArrayList<>();
	}
	public EspacoEventoDAO(ArrayList<EspacoEvento> listEspacoEvento) {
		this.listEspacoEvento = listEspacoEvento;
	}

	public void criarEspaco(String nomeEspaco, String endereco, int capacidade, double precoDia, Empresa empresa) {
		boolean cadastrado = false;
		for(EspacoEvento espacoEvento : listEspacoEvento) {
			if(espacoEvento.getEndereco().equals(endereco)){
				printTela.erroCadastro("Local");
				cadastrado = true;
			} 
		}
		if(!cadastrado) {
			EspacoEvento espacoEvent = new EspacoEvento(nomeEspaco, endereco, capacidade, precoDia, empresa);
			listEspacoEvento.add(espacoEvent);
			printTela.cadastrado("Espaço de evento");
		}
	}
	
	public List<EspacoEvento> listarEspacos(){
		List<EspacoEvento> listaDeEspacos = new ArrayList<>();
		for(EspacoEvento espacoEvento : listEspacoEvento) {
			String nomeEE = espacoEvento.getNomeEspaco();
			String enderecoEE = espacoEvento.getEndereco();
			int capacidadeEE = espacoEvento.getCapacidade();
			double precoEE = espacoEvento.getPrecoDia();
			
			String empresaEEN = espacoEvento.getEmpresa().getNome();
			String empresaEEE = espacoEvento.getEmpresa().getEmail();
			String empresaEES = espacoEvento.getEmpresa().getSenha();
			String empresaEEC = espacoEvento.getEmpresa().getCnpj();
			
			Empresa infoEmpresa = new Empresa(empresaEEN, empresaEEE, empresaEES, empresaEEC);
			EspacoEvento infoEspacoEvent = new EspacoEvento(nomeEE, enderecoEE, capacidadeEE, precoEE, infoEmpresa);
			
			listaDeEspacos.add(infoEspacoEvent);
		}
		return listaDeEspacos;
	}
	
	public EspacoEvento buscarEspaco(String endereco) {
		for(EspacoEvento espacoEvento : listEspacoEvento) {
			if(espacoEvento.getEndereco().equals(endereco)) {
				return espacoEvento;
			}
		}
		return null;
	}
	
	public void editarEspacoEvento(int nEspacoEvento,String nomeEspaco, String endereco, int capacidade, double precoDia) {
		boolean cadastrado = false;
		for(EspacoEvento espacoEvento : listEspacoEvento) {
			if(espacoEvento.getEndereco().equals(endereco)){
				printTela.erroCadastro("Local");	
				cadastrado = true;
			}
		}
		if(cadastrado == false) {
			listEspacoEvento.get(nEspacoEvento).setNomeEspaco(nomeEspaco);
			listEspacoEvento.get(nEspacoEvento).setEndereco(endereco);
			listEspacoEvento.get(nEspacoEvento).setCapacidade(capacidade);
			listEspacoEvento.get(nEspacoEvento).setPrecoDia(precoDia);
			printTela.editado("Espaço de evento");
		}
		
	}
	
	public void excluirEspaco(int nEspacoEvento){
		listEspacoEvento.remove(nEspacoEvento);
		printTela.excluido("Espaço de evento");
	}
}