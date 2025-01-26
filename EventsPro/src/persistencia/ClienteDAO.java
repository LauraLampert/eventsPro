package persistencia;
import java.util.ArrayList;
import java.util.List;

import apresentacao.PrintTela;
import model.Cliente;

public class ClienteDAO {
	PrintTela printTela = new PrintTela();
	
	private List<Cliente> listClientes;

	public ClienteDAO() {
		listClientes = new ArrayList<>();
	}
	public ClienteDAO(ArrayList<Cliente> listClientes) {
		this.listClientes = listClientes;
	}
	
	
	public void criarConta(String nome, String email, String senha, String cpf){
		boolean cadastrado = false;
		for(Cliente cliente : listClientes) {
			if(cliente.getCpf().equals(cpf)) {
				printTela.erroCadastro("CPF");
				cadastrado = true;
			} else {
				if(cliente.getEmail().equals(email)) {
					printTela.erroCadastro("E-mail");
					cadastrado = true;
				}
			}
		}
		if(cadastrado != true) {
			Cliente client = new Cliente(nome, email, senha, cpf);
			listClientes.add(client);
			printTela.cadastrado("Cliente");
		}
	}
	
	
	public List<Cliente> listarClientes(){
		List<Cliente> listaDeclientes = new ArrayList<>();
		for(Cliente cliente : listClientes) {
			String nomeC = cliente.getNome();
			String emailC = cliente.getEmail();
			String senhaC = cliente.getSenha();
			String cpfC = cliente.getCpf();
			Cliente infoClientes = new Cliente(nomeC, emailC, senhaC, cpfC);
			listaDeclientes.add(infoClientes);
		}
		return listaDeclientes;
	}
	
	public void editarConta(String cpf, String nome, String email, String senha) {
		for(Cliente cliente : listClientes) {
			if(cliente.getCpf().equals(cpf)) {
				cliente.setNome(nome);
				cliente.setEmail(email);
				cliente.setSenha(senha);
				printTela.editado("Conta");
			} else {
				printTela.erroEditar("Conta");
			}
		}
	}
	
	public boolean buscarPorEmail(String email, String senha) {
		for(Cliente cliente : listClientes) {
			if(cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;
	}
	
	public Cliente buscarCliente(String email) {
		for(Cliente cliente : listClientes) {
			if(cliente.getEmail().equals(email)) {
				return cliente;
			}
		}
		return null;
	}
	
	public void excluirConta(String cpf){
		Cliente clientExcluir = null;
		for(Cliente cliente : listClientes) {
			if(cliente.getCpf().equals(cpf)) {
				clientExcluir = new Cliente();
			    clientExcluir = cliente;
			} else {
				printTela.erroExcluir("Conta");
			}
		}
		if(clientExcluir != null) {
			listClientes.remove(clientExcluir);
			printTela.excluido("Conta");
		}
	}
}
