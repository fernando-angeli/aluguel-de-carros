import menu.Menu;
import model.Cliente;
import model.Veiculo;
import service.ClienteService;
import service.VeiculoService;
import service.VendedorService;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        boolean continua = true;
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ClienteService clienteService = new ClienteService(sc);
        VeiculoService veiculoService = new VeiculoService(sc, formatter);
        VendedorService vendedorService = new VendedorService(sc);

        do{
            Cliente clienteLogado = null;
            boolean senhaValidada = false;

            Menu.tipoUsuario();
            System.out.print("Selecionar opção: ");
            int tipoUsuario = sc.nextInt();

            switch (tipoUsuario){
                case 1:
                    do{
                        Menu.bemVindo();
                        String cpf = sc.next();

                        if(cpf.equals("0")) break;

                        clienteLogado = clienteService.buscarClientePorCpf(cpf);

                        if(clienteLogado != null) {
                            System.out.print("Digite sua senha: ");
                            String senha = sc.next();
                            senhaValidada = clienteService.validarSenha(clienteLogado, senha);
                        }

                    } while(clienteLogado == null || !senhaValidada);

                    Menu.opcoesCliente();
                    System.out.print("Selecionar opção: ");
                    int opcaoCliente = sc.nextInt();

                    if(opcaoCliente == 1){
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
                    }
                    else if(opcaoCliente == 2){
                        System.out.println("Digite a opção referente ao veículo escolhido:");
                        clienteService.buscarVeiculosAlugados(clienteLogado);
                        System.out.print("Selecionar opção: ");
                        int veiculoEscolhido = sc.nextInt();
                        clienteService.devolverVeiculo(clienteLogado, veiculoEscolhido);
                        veiculoService.decolverVeiculo(veiculoEscolhido);
                    }

                    break;
                case 2:
                    //ação
                    break;
                case 3:
                    //ação
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while(continua);

    }
}