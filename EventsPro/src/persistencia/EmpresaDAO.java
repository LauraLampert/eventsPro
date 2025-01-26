package persistencia;
import java.util.ArrayList;
import java.util.List;

import apresentacao.PrintTela;
import model.Empresa;

public class EmpresaDAO {
	PrintTela printTela = new PrintTela();
	
	private List<Empresa> listEmpresa;

	public EmpresaDAO() {
		listEmpresa = new ArrayList<>();
	}
	public EmpresaDAO(ArrayList<Empresa> listEmpresa) {
		this.listEmpresa = listEmpresa;
	}
	
	public void criarConta(String nome, String email, String senha, String cnpj){
		boolean cadastrado = false;
		for(Empresa empresa : listEmpresa) {
			if(empresa.getCnpj().equals(cnpj)) {
				printTela.erroCadastro("CNPJ");
				cadastrado = true;
			} else {
				if(empresa.getEmail().equals(email)) {
					printTela.erroCadastro("E-mail");
					cadastrado = true;
				} 
			}
		}
		if(cadastrado != true) {
			Empresa empres = new Empresa(nome, email, senha, cnpj);
			listEmpresa.add(empres);
			printTela.cadastrado("Empresa");
		}
	}
	
	public List<Empresa> listarEmpresas(){
		List<Empresa> listaDeEmpresas = new ArrayList<>();
		for(Empresa empresa : listEmpresa) {
			String nomeE = empresa.getNome();
			String emailE = empresa.getEmail();
			String senhaE = empresa.getSenha();
			String cnpjE = empresa.getCnpj();
			Empresa infoEmpresas = new Empresa(nomeE, emailE, senhaE, cnpjE);
			listaDeEmpresas.add(infoEmpresas);
		}
		return listaDeEmpresas;
	}
	
	public void editarConta(String cnpj, String nome, String email, String senha) {
		for(Empresa empresa : listEmpresa) {
			if(empresa.getCnpj().equals(cnpj)) {
				empresa.setNome(nome);
				empresa.setEmail(email);
				empresa.setSenha(senha);
				printTela.editado("Conta");
			}
			printTela.erroEditar("Conta");
		}
	}
	
	public boolean buscarPorEmail(String email, String senha) {
		for(Empresa empresa : listEmpresa) {
			if(empresa.getEmail().equals(email) && empresa.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;
	}
	
	public Empresa buscarEmpresa(String email) {
		for(Empresa empresa : listEmpresa) {
			if(empresa.getEmail().equals(email)) {
				return empresa;
			}
		}
		return null;
	}
	
	public void excluirConta(String cnpj){
		Empresa empresaExcluir = null;
		for(Empresa empresa : listEmpresa) {
			if(empresa.getCnpj().equals(cnpj)) {
				empresaExcluir = new Empresa();
				empresaExcluir = empresa;
				printTela.excluido("Conta");
			} else {
				printTela.erroExcluir("Conta");
			}
		}
		if(empresaExcluir != null) {
			listEmpresa.remove(empresaExcluir);
		}
	}
}
