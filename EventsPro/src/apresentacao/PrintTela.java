package apresentacao;

public class PrintTela {
	
	public void cadastrado(String nome){
		System.out.println(nome + " cadastrado(a) com sucesso!");
	}
	
	public void erroCadastro(String nome) {
		System.out.println(nome + " já cadastrado!");
	}
	public void erroReserva() {
		System.out.println("Este local ja esta reservado para esta data!");
	}
	
	public void editado(String nome) {
		System.out.println(nome + " editado(a) com sucesso!");
	}
	public void erroEditar(String nome) {
		System.out.println(nome + " não encontrado(a)!");
	}
	
	public void excluido(String nome) {
		System.out.println(nome + " excluido(a) com sucesso!");
	}
	public void erroExcluir(String nome) {
		System.out.println(nome + " não cadastrado(a)!");
	}
	
}
