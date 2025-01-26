package apresentacao;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Cliente;
import model.Conta;
import model.Empresa;
import model.EspacoEvento;
import model.Reserva;
import persistencia.ClienteDAO;
import persistencia.EmpresaDAO;
import persistencia.EspacoEventoDAO;
import persistencia.ReservaDAO;

public class TelaPrincipal {

	public static void main(String[] args) {
		ClienteDAO clientDAO = new ClienteDAO();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		EspacoEventoDAO espacoEventoDAO = new EspacoEventoDAO();
		ReservaDAO reservaDAO = new ReservaDAO();
		Conta conta = null;
		Scanner t = new Scanner(System.in);
		boolean login = true;

		do {
			
			boolean loginCliente = false;
			boolean loginEmpresa = false;
			
			System.out.println("Menu:");
			System.out.println("1 - Cadastrar Cliente");
			System.out.println("2 - Cadastrar Empresa");
			System.out.println("3 - Login");
			System.out.println("4 - Sair");
			
			int opcao = t.nextInt();
			
			switch (opcao) {
				case 1: {
					System.out.println("Digite seu nome:");
					String digitNome = t.next();
					System.out.println("Digite seu CPF:");
					String digitCpf = t.next();
					System.out.println("Digite seu e-mail:");
					String digitEmail = t.next();
					System.out.println("Digite sua senha:");
					String digitSenha = t.next();
					
					clientDAO.criarConta(digitNome, digitEmail, digitSenha, digitCpf);
					break;
				}
				case 2: {
					System.out.println("Digite seu nome:");
					String digitNome = t.next();
					System.out.println("Digite seu CNPJ:");
					String digitCnpj = t.next();
					System.out.println("Digite seu e-mail:");
					String digitEmail = t.next();
					System.out.println("Digite sua senha:");
					String digitSenha = t.next();
					
					empresaDAO.criarConta(digitNome, digitEmail, digitSenha, digitCnpj);
					break;
				}
				case 3: {
					System.out.println("Digite seu e-mail:");
					String digitEmail = t.next();
					System.out.println("Digite sua senha:");
					String digitSenha = t.next();
					
					if(clientDAO.buscarPorEmail(digitEmail, digitSenha) != false){
						conta = new Cliente();
						conta = clientDAO.buscarCliente(digitEmail);
						System.out.println("Login concluido com sucesso!");
						loginCliente = true;
					} else {
						if(empresaDAO.buscarPorEmail(digitEmail, digitSenha) != false){
							conta = new Empresa();
							conta = empresaDAO.buscarEmpresa(digitEmail);
							System.out.println("Login concluido com sucesso!");
							loginEmpresa = true;
						} else {
							System.out.println("E-mail ou senha incorretos!");
						}
					}
					
					if(loginCliente == true) {
						boolean continuar = true;
						do {
							
							System.out.println("Menu:");
							System.out.println("1 - Criar reserva");
							System.out.println("2 - Editar reserva");
							System.out.println("3 - Listar reservas");
							System.out.println("4 - Excluir reserva");
							System.out.println("5 - Editar conta");
							System.out.println("6 - Excluir conta");
							System.out.println("7 - Sair");
							
							int opcaoC = t.nextInt();
							
							switch(opcaoC) {
								case 1: {
									int i = 0;
									for(EspacoEvento espacoEvento : espacoEventoDAO.listarEspacos()) {
										System.out.println(i + " - Espaço: " + espacoEvento.getNomeEspaco() +
												"\n * Endereço: " + espacoEvento.getEndereco() + ", Capacidade: " + espacoEvento.getCapacidade() + ", Preço: " + espacoEvento.getPrecoDia() + ", Empresa: " + espacoEvento.getEmpresa().getNome());
										i += 1;
									}
									
									if(espacoEventoDAO.listarEspacos().size() == 0) {
										System.out.println("Ainda não há espaços de evento disponiveis para reserva.");
										break;
									}
									
									int digitNumEspaco;
									boolean sair = false;
									do {
										System.out.println("Digite o numero do espaço escolhido:");
										digitNumEspaco = t.nextInt();
										if(digitNumEspaco >= espacoEventoDAO.listarEspacos().size()) {
											System.out.println("Opção invadida, tente novamente!");
										} else {
											sair = true;
										}
									}while(sair == false);
									
									
									EspacoEvento espacoEscolhido = new EspacoEvento();
									espacoEscolhido = espacoEventoDAO.listarEspacos().get(digitNumEspaco);
									
									System.out.println("Digite a data da reserva:");
									String digitData = t.next();
									
									reservaDAO.reservar(espacoEscolhido, digitData, (Cliente) conta);
									
									if(conta instanceof Cliente) {
										List<Reserva> clientReservas = new ArrayList<>();
										clientReservas = ((Cliente)conta).getListReservas();
										clientReservas.add(reservaDAO.buscarReserva(espacoEscolhido, digitData));
										((Cliente)conta).setListReservas(clientReservas);
									}
									
									break;
								}
								case 2: { //editar reserva
									int posicaoDAO = 0;
									int posicaoClient = 0;
									for(Reserva reserva : reservaDAO.listarReservas()) {
										if(reserva.getCliente().getCpf().equals(((Cliente)conta).getCpf())) {
											System.out.println(posicaoDAO + " - Espaço: " + reserva.getEspacoEvento().getNomeEspaco() +  
													"\n * Endereço: " + reserva.getEspacoEvento().getEndereco() + ", Capacidade: " + reserva.getEspacoEvento().getCapacidade() + ", Preço: " + reserva.getEspacoEvento().getPrecoDia() + 
													"\n * Empresa: " + reserva.getEspacoEvento().getEmpresa().getNome() + ", Data: " + reserva.getData() + ", Nome do Cliente: " + reserva.getCliente().getNome() + ", Email do Cliente: " + reserva.getCliente().getEmail());
											posicaoClient += 1;
										}
										posicaoDAO += 1;
									}
									
									if(posicaoClient == 0) {
										System.out.println("Você ainda não tem nenhuma reserva.");
										break;
									}
									
									int digitNumReserva;
									boolean sair = false;
									do {
										System.out.println("Digite o numero da reserva que deseja editar:");
										digitNumReserva = t.nextInt();
										
										if(digitNumReserva >= reservaDAO.listarReservas().size()) {
											System.out.println("Opção invadida, tente novamente!");
										} else {
											sair = true;
										}
										
									}while(sair == false);
									
									System.out.println("Digite a nova data da sua reserva:");
									String digitData = t.next();
									
									Reserva reservaEscolhida = reservaDAO.listarReservas().get(digitNumReserva);
									String antigaData = reservaEscolhida.getData();
									
									reservaDAO.editarReserva(digitNumReserva, digitData);
									
									if(conta instanceof Cliente) {
										List<Reserva> clientReservas = new ArrayList<>();
										clientReservas = ((Cliente)conta).getListReservas();
										for(Reserva reserva : clientReservas) {
											if(reserva.getEspacoEvento().equals(reservaEscolhida.getEspacoEvento()) && reserva.getData().equals(antigaData)) {
												reserva = reservaDAO.listarReservas().get(digitNumReserva);
											}
										}
										((Cliente)conta).setListReservas(clientReservas);
									}
									
									break;
								}
								case 3: { //listar reserva
									int posicaoDAO = 0;
									int posicaoClient = 0;
									for(Reserva reserva : reservaDAO.listarReservas()) {
										if(reserva.getCliente().getCpf().equals(((Cliente)conta).getCpf())) {
											System.out.println(posicaoDAO + " - Espaço: " + reserva.getEspacoEvento().getNomeEspaco() +  
													"\n * Endereço: " + reserva.getEspacoEvento().getEndereco() + ", Capacidade: " + reserva.getEspacoEvento().getCapacidade() + ", Preço: " + reserva.getEspacoEvento().getPrecoDia() + 
													"\n * Empresa: " + reserva.getEspacoEvento().getEmpresa().getNome() + ", Data: " + reserva.getData() + ", Nome do Cliente: " + reserva.getCliente().getNome() + ", Email do Cliente: " + reserva.getCliente().getEmail());
											posicaoClient += 1;
										}
										posicaoDAO += 1;
									}
									
									if(posicaoClient == 0) {
										System.out.println("Você ainda não tem nenhuma reserva.");
									}
									break;
								}
								case 4: { //excluir reserva
									int posicaoDAO = 0;
									int posicaoClient = 0;
									for(Reserva reserva : reservaDAO.listarReservas()) {
										if(reserva.getCliente().getCpf().equals(((Cliente)conta).getCpf())) {
											System.out.println(posicaoDAO + " - Espaço: " + reserva.getEspacoEvento().getNomeEspaco() +
													"\n * Endereço: " + reserva.getEspacoEvento().getEndereco() + ", Capacidade: " + reserva.getEspacoEvento().getCapacidade() + ", Preço: " + reserva.getEspacoEvento().getPrecoDia() + 
													"\n * Empresa: " + reserva.getEspacoEvento().getEmpresa().getNome() + ", Data: " + reserva.getData() + ", Nome do Cliente: " + reserva.getCliente().getNome() + ", Email do Cliente: " + reserva.getCliente().getEmail());
											posicaoClient += 1;
										}
										posicaoDAO += 1;
									}
									if(posicaoClient == 0) {
										System.out.println("Você ainda não tem nenhuma reserva.");
										break;
									}
									int digitNumReserva;
									boolean sair = false;
									do {
										System.out.println("Digite o numero da reserva que deseja excluir:");
										digitNumReserva = t.nextInt();
										
										if(digitNumReserva >= reservaDAO.listarReservas().size()) {
											System.out.println("Opção invadida, tente novamente!");
										} else {
											sair = true;
										}
									}while(sair == false);
									
									Reserva reservaEscolhida = reservaDAO.listarReservas().get(digitNumReserva);
									String antigaData = reservaEscolhida.getData();
									
									reservaDAO.excluirReserva(digitNumReserva);
									if(conta instanceof Cliente) {
										List<Reserva> clientReservas = new ArrayList<>();
										clientReservas = ((Cliente)conta).getListReservas();
										
										for(Reserva reserva : clientReservas) {
											if(reserva.getEspacoEvento().equals(reservaEscolhida.getEspacoEvento()) && reserva.getData().equals(antigaData)) {
												clientReservas.remove(reserva);
												break;
											}
										}
										((Cliente)conta).setListReservas(clientReservas);
									}
									break;
								}
								case 5: { //editar conta cliente
									String cpfC = ((Cliente)conta).getCpf();
									System.out.println("Insira seu novo nome de usuário:");
									String digitN = t.next();
									System.out.println("Insira seu novo email:");
									String digitE = t.next();
									System.out.println("Insira sua nova senha:");
									String digitS = t.next();
									
									clientDAO.editarConta(cpfC, digitN, digitE, digitS);
									break;
								}
								case 6: { //excluir conta cliente
									String cpfC= ((Cliente)conta).getCpf();
									clientDAO.excluirConta(cpfC);
									conta = null;
									continuar = false;
									break;
								}
								case 7: {
									System.out.println("Voltar para o menu inicial? (S/n)");
									String cont = t.next();
									if(cont.equals("s")) {
										continuar = false;
									}
									break;
								}
								default: {
									System.out.println("Opção inválida! Tente novamente!");
									break;
								}
							}
							
						}while(continuar);
					}
					
					if(loginEmpresa == true) {
						boolean continuar = true;
						do {
							
							System.out.println("Menu:");
							System.out.println("1 - Cadastrar um espaço de evento");
							System.out.println("2 - Editar um espaço de evento");
							System.out.println("3 - Listar espaços de evento");
							System.out.println("4 - Excluir espaço de evento");
							System.out.println("5 - Editar conta");
							System.out.println("6 - Excluir conta");
							System.out.println("7 - Sair");
							
							int opcaoC = t.nextInt();
							
							switch(opcaoC) {
								case 1: {
									System.out.println("Digite o nome do espaço:");
									String digitNE = t.next();
									System.out.println("Digite o endereço:");
									String digitEnd = t.next();
									System.out.println("Digite a capacidade:");
									int digitCapac = t.nextInt();
									System.out.println("Digite o preço por dia:");
									double digitPrec = t.nextDouble();
									
									espacoEventoDAO.criarEspaco(digitNE, digitEnd, digitCapac, digitPrec, (Empresa) conta);
									
									if(conta instanceof Empresa) {
										List<EspacoEvento> empresaEspacos = new ArrayList<>();
										empresaEspacos = ((Empresa)conta).getListEspacosEventos();
										empresaEspacos.add(espacoEventoDAO.buscarEspaco(digitEnd));
										((Empresa)conta).setListEspacosEventos(empresaEspacos);
									}
									
									break;
								}
								case 2: {
									int posicaoDAO = 0;
									int posicaoEmpresa = 0;
									for(EspacoEvento espacoEvento : espacoEventoDAO.listarEspacos()) {
										if(espacoEvento.getEmpresa().getCnpj().equals(((Empresa)conta).getCnpj())) {
											System.out.println(posicaoDAO + " - Espaço: " + espacoEvento.getNomeEspaco() + 
													"\n * Endereço: " + espacoEvento.getEndereco() + ", Capacidade: " + espacoEvento.getCapacidade() + ", Preço: " + espacoEvento.getPrecoDia() + 
													"\n * Empresa: " + espacoEvento.getEmpresa().getNome());
											posicaoEmpresa +=1;
										}
										posicaoDAO += 1;
									}
									
									if(posicaoEmpresa == 0) {
										System.out.println("Você ainda não possui nenhum espaço de evento cadastrado.");
										break;
									}
									
									int digitNumEE;
									boolean sair = false;
									do {
										System.out.println("Digite o numero do espaço de evento que deseja editar:");
										digitNumEE = t.nextInt();
										
										if(digitNumEE >= espacoEventoDAO.listarEspacos().size()) {
											System.out.println("Opção invadida, tente novamente!");
										} else {
											sair = true;
										}
									}while(sair == false);
									
									System.out.println("Digite o novo nome do espaço de evento:");
									String digitNome = t.next();
									System.out.println("Digite o novo endereço do espaço de evento:");
									String digitEnd = t.next();
									System.out.println("Digite a capacidade do espaço de evento:");
									int digitCap = t.nextInt();
									System.out.println("Digite o preço do espaço de evento:");
									double digitPrec = t.nextDouble();

									EspacoEvento espacoEscolhido = espacoEventoDAO.listarEspacos().get(digitNumEE);
									
									espacoEventoDAO.editarEspacoEvento(digitNumEE, digitNome, digitEnd, digitCap, digitPrec);
									
									if(conta instanceof Empresa) {
										List<EspacoEvento> empresaEspacos = new ArrayList<>();
										empresaEspacos = ((Empresa)conta).getListEspacosEventos();
										for(EspacoEvento espacoEvento: empresaEspacos) {
											if(espacoEvento.getEndereco().equals(espacoEscolhido.getEndereco())) {
												espacoEvento = espacoEventoDAO.listarEspacos().get(digitNumEE);
											}
										}
										((Empresa)conta).setListEspacosEventos(empresaEspacos);
									}
									
									break;
								}
								case 3: {
									int posicaoDAO = 0;
									int posicaoEmpresa = 0;
									for(EspacoEvento espacoEvento : espacoEventoDAO.listarEspacos()) {
										if(espacoEvento.getEmpresa().getCnpj().equals(((Empresa)conta).getCnpj())) {
											System.out.println(posicaoDAO + " - Espaço: " + espacoEvento.getNomeEspaco() + ", Endereço: " + espacoEvento.getEndereco() +
													"\n * Capacidade: " + espacoEvento.getCapacidade() + ", Preço: " + espacoEvento.getPrecoDia() + ", Empresa: " + espacoEvento.getEmpresa().getNome());
											posicaoEmpresa +=1;
										}
										posicaoDAO += 1;
									}
									
									if(posicaoEmpresa == 0) {
										System.out.println("Você ainda não possui nenhum espaço de evento cadastrado.");
									}
									break;
								}
								case 4: {
									int posicaoDAO = 0;
									int posicaoEmpresa = 0;
									for(EspacoEvento espacoEvento : espacoEventoDAO.listarEspacos()) {
										if(espacoEvento.getEmpresa().getCnpj().equals(((Empresa)conta).getCnpj())) {
											System.out.println(posicaoDAO + " - Espaço: " + espacoEvento.getNomeEspaco() +  
													"\n * Endereço: " + espacoEvento.getEndereco() + ", Capacidade: " + espacoEvento.getCapacidade() + ", Preço: " + espacoEvento.getPrecoDia() + 
													"\n * Empresa: " + espacoEvento.getEmpresa().getNome());
											posicaoEmpresa +=1;
										}
										posicaoDAO += 1;
									}
									
									if(posicaoEmpresa == 0) {
										System.out.println("Você ainda não possui nenhum espaço de evento cadastrado.");
										break;
									}
									
									int digitNumEE;
									boolean sair = false;
									do {
										System.out.println("Digite o numero do espaço de evento que deseja excluir:");
										digitNumEE = t.nextInt();
										
										if(digitNumEE >= espacoEventoDAO.listarEspacos().size()) {
											System.out.println("Opção invadida, tente novamente!");
										} else {
											sair = true;
										}
									}while(sair == false);
									
									EspacoEvento espacoEscolhido = espacoEventoDAO.listarEspacos().get(digitNumEE);
									
									espacoEventoDAO.excluirEspaco(digitNumEE);
									
									if(conta instanceof Empresa) {
										List<EspacoEvento> empresaEspacos = new ArrayList<>();
										empresaEspacos = ((Empresa)conta).getListEspacosEventos();
										
										for(EspacoEvento espacoEvento : empresaEspacos) {
											if(espacoEvento.getEndereco().equals(espacoEscolhido.getEndereco())) {
												empresaEspacos.remove(espacoEvento);
												break;
											}
										}
										((Empresa)conta).setListEspacosEventos(empresaEspacos);
									}
									
									break;
								}
								case 5: {
									String cnpjE = ((Empresa)conta).getCnpj();
									System.out.println("Insira seu novo nome de usuário:");
									String digitN = t.next();
									System.out.println("Insira seu novo email:");
									String digitE = t.next();
									System.out.println("Insira sua nova senha:");
									String digitS = t.next();
									
									empresaDAO.editarConta(cnpjE, digitN, digitE, digitS);
									break;
								} 
								case 6: {
									String cnpjE= ((Empresa)conta).getCnpj();
									empresaDAO.excluirConta(cnpjE);
									conta = null;
									continuar = false;
									break;
								}
								case 7: {
									System.out.println("Voltar para o menu inicial? (S/n)");
									String cont = t.next();
									if(cont.equals("s")) {
										continuar = false;
									}
									break;
								}
								default: {
									System.out.println("Opção inválida! Tente novamente!");
									break;
								}
							}
						}while(continuar);
					}
					
					break;
				}
				case 4: {
					login = false;
					break;
				}
				default: {
					System.out.println("Opção inválida! Tente novamente!");
					break;
				}
			}
		}while(login);
		t.close();
	}
}