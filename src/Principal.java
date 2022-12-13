import exception.VeiculoException;
import exception.VendedorException;
import menu.Menu;
import model.Admin;
import model.Cliente;
import model.Veiculo;
import model.Vendedor;
import service.AdminService;
import service.ClienteService;
import service.VeiculoService;
import service.VendedorService;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws InterruptedException {

        boolean continua = true;
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ClienteService clienteService = new ClienteService(sc);
        VeiculoService veiculoService = new VeiculoService(sc, formatter);
        VendedorService vendedorService = new VendedorService(sc);
        AdminService adminService = new AdminService(sc, clienteService.getRepository(), vendedorService.getRepository(), veiculoService.getRepository());

        do{
            try {
                Cliente clienteLogado = null;
                boolean senhaValidada = false;

                Menu.tipoUsuario();
                System.out.print("Selecionar opção: ");
                int tipoUsuario = sc.nextInt();

                switch (tipoUsuario) {
                    case 1:
                        do {
                            Menu.bemVindo();
                            String cpf = sc.next();

                            if (cpf.equals("0")) break;

                            clienteLogado = clienteService.buscarClientePorCpf(cpf);

                            if (clienteLogado != null) {
                                System.out.print("Digite sua senha: ");
                                String senha = sc.next();
                                senhaValidada = clienteLogado.validarSenha(senha);
                            }

                        } while (clienteLogado == null || !senhaValidada);

                        Menu.opcoesCliente();
                        System.out.print("Selecionar opção: ");
                        int opcaoCliente = sc.nextInt();

                        if (opcaoCliente == 1) {
                            System.out.println("Digite a opção referente ao veículo escolhido");
                            veiculoService.buscarTodosVeiculosDisponiveis();
                            System.out.print("Selecionar opção: ");
                            int veiculoEscolhido = sc.nextInt();
                            System.out.print("Digite quantos dias você ficará com o veículo: ");
                            int diasLocacao = sc.nextInt();
                            Veiculo veiculo = veiculoService.alugarVeiculo(veiculoEscolhido, diasLocacao);
                            clienteService.adicionarVeiculo(clienteLogado, veiculo);
                            System.out.println("Digite a opção referente ao vendedor que lhe atendeu");
                            vendedorService.buscarVendedores();
                            System.out.print("Selecionar opção: ");
                            int vendedor = sc.nextInt();
                            vendedorService.gerarComissao(veiculo, vendedor);
                            System.out.println(clienteLogado);
                        } else if (opcaoCliente == 2) {
                            System.out.println("Digite a opção referente ao veículo escolhido:");
                            clienteService.buscarVeiculosAlugados(clienteLogado);
                            System.out.print("Selecionar opção: ");
                            int veiculoEscolhido = sc.nextInt();
                            clienteService.devolverVeiculo(clienteLogado, veiculoEscolhido);
                            veiculoService.devolverVeiculo(veiculoEscolhido);
                        }
                        break;
                    case 2:
                        Vendedor vendedorLogado = null;
                        do {
                            Menu.bemVindo();
                            String cpf = sc.next();

                            if (cpf.equals("0")) break;

                            vendedorLogado = vendedorService.buscarVendedorPorCpf(cpf);

                            if (vendedorLogado != null) {
                                System.out.print("Digite sua senha: ");
                                String senha = sc.next();
                                senhaValidada = vendedorLogado.validarSenha(senha);
                            } else {
                                System.out.println("Vendedor não encontrado! Tente novamente.");
                            }
                        } while (vendedorLogado == null || !senhaValidada);

                        Menu.opcoesVendedor();
                        System.out.print("Selecionar opção: ");
                        int opcaoVendedor = sc.nextInt();
                        if (opcaoVendedor == 1) {
                            veiculoService.veiculosAlugados();
                        } else if (opcaoVendedor == 2) {
                            vendedorService.mostrarSalarioAtual(vendedorLogado);
                            Thread.sleep(3000);
                        } else System.out.println("Opção inválida.");
                        break;
                    case 3:
                        Admin adminLogado = null;
                        do {
                            Menu.bemVindo();
                            String cpf = sc.next();

                            if (cpf.equals("0")) break;

                            adminLogado = adminService.buscarAdminPorCpf(cpf);

                            if (adminLogado != null) {
                                System.out.print("Digite sua senha: ");
                                String senha = sc.next();
                                senhaValidada = adminLogado.validarSenha(senha);
                            } else {
                                System.out.println("Vendedor não encontrado! Tente novamente.");
                            }
                        } while (adminLogado == null || !senhaValidada);
                        Menu.opcoesAdmin();
                        System.out.print("Selecionar opção: ");
                        int opcaoAdmin = sc.nextInt();
                        switch (opcaoAdmin) {
                            case 1:
                                adminService.cadastrarPessoa(true);
                                break;
                            case 2:
                                adminService.removerCliente();
                                break;
                            case 3:
                                adminService.cadastrarVeiculo();
                                break;
                            case 4:
                                adminService.removerVeiculo();
                                break;
                            case 5:
                                adminService.cadastrarPessoa(false);
                                break;
                            case 6:
                                adminService.removerVendedor();
                                break;
                        }
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            }catch (VeiculoException e){
                System.out.println(e.getMessage());
            } catch (VendedorException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e){
                System.out.println("Tipo de entrada inválida!");
                sc.nextLine();
            }
        } while(continua);

    }
}