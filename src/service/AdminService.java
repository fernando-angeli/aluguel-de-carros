package service;

import model.Admin;
import model.Cliente;
import model.Veiculo;
import model.Vendedor;
import repository.AdminRepository;
import repository.ClienteRepository;
import repository.VeiculoRepository;
import repository.VendedorRepository;

import java.util.List;
import java.util.Scanner;

public class AdminService {

    private AdminRepository repository;
    private ClienteRepository clienteRepository;
    private VendedorRepository vendedorRepository;
    private VeiculoRepository veiculoRepository;
    private Scanner sc;

    public AdminService(Scanner sc, ClienteRepository clienteRepository, VendedorRepository vendedorRepository,
                        VeiculoRepository veiculoRepository){
        this.repository = new AdminRepository();
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
        this.veiculoRepository = veiculoRepository;
        this.sc = sc;
    }

    public Admin buscarAdminPorCpf(String cpf) {
        List<Admin> admins = repository.buscarTodos();
        for(Admin admin : admins){
            if(admin.getCpf().equals(cpf))
                return admin;
        }
        return null;
    }

    public void cadastrarPessoa(boolean cliente) {
        sc.nextLine();
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Digite o endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Digite a senha: ");
        String senha = sc.nextLine();
        if(cliente){
            System.out.print("Digite PF (pessoa física) ou PJ (pessoa jurídica): ");
            String tipo = sc.nextLine().toUpperCase();
            Cliente novoCliente = null;
            try{
                novoCliente = new Cliente(nome, senha, cpf, endereco, Cliente.TipoPessoa.valueOf(tipo));
            } catch (IllegalArgumentException e){
                System.out.println("Tipo inválido, adicionado como Pessoa Física (padrão).");
                novoCliente = new Cliente(nome, senha, cpf, endereco, Cliente.TipoPessoa.PF);
            }
            this.clienteRepository.salvar(novoCliente);
        }
        else{
            System.out.print("Digite o salario do vendedor: ");
            double salario = sc.nextDouble();
            Vendedor vendedor = new Vendedor(nome, senha, cpf, endereco, salario);
            this.vendedorRepository.salvar(vendedor);
        }
        System.out.println("Cadastrado com sucesso");
    }

    public void removerCliente() {
        List<Cliente> clientes = clienteRepository.buscarTodos();
        for(Cliente cliente : clientes){
            System.out.println(cliente.getId() + " - " + cliente.getNome() + " - " + cliente.getCpf());
        }
        System.out.print("Informe o id do cliente para ser removido: ");
        int opcao = sc.nextInt();
        clienteRepository.excluirPorId(opcao);
        System.out.println("Cliente removido com sucesso.");
    }

    public void cadastrarVeiculo() {
        sc.nextLine();
        System.out.print("Digite a marca do veículo: ");
        String marca = sc.nextLine();
        System.out.print("Digite o modelo do veículo: ");
        String modelo = sc.nextLine();
        System.out.print("Digite o a placa do veículo: ");
        String placa = sc.nextLine();
        System.out.print("Digite a cor do veículo: ");
        String cor = sc.nextLine();
        System.out.print("Digite o ano do veículo: ");
        int ano = sc.nextInt();
        System.out.print("Digite o segmento do veículo: ");
        String segmento = sc.next().toUpperCase();
        System.out.print("Digite o valor de locação do veículo: ");
        double valor = sc.nextDouble();
        Veiculo veiculo = null;
        try{
            veiculo = new Veiculo(marca, modelo, placa, cor, ano, Veiculo.Segmento.valueOf(segmento), valor);
        } catch (IllegalArgumentException e){
            System.out.println("Tipo inválido, adicionado como segmento padrão (Carro).");
            veiculo = new Veiculo(marca, modelo, placa, cor, ano, Veiculo.Segmento.CARRO, valor);
        }
        veiculoRepository.salvar(veiculo);
        System.out.println("Veiculo cadastrado com sucesso.");
    }

    public void removerVeiculo() {
        List<Veiculo> veiculos = veiculoRepository.buscarTodos();
        for(Veiculo veiculo : veiculos){
            System.out.println(veiculo.getId() + " - " + veiculo.getMarca() + " - " + veiculo.getModelo() + " - " + veiculo.getPlaca());
        }
        System.out.print("Informe o id do veiculo para ser removido: ");
        int opcao = sc.nextInt();
        veiculoRepository.excluirPorId(opcao);
        System.out.println("Veiculo removido com sucesso.");
    }

    public void removerVendedor() {
        List<Vendedor> vendedores = vendedorRepository.buscarTodos();
        for(Vendedor vendedor : vendedores){
            System.out.println(vendedor.getId() + " - " + vendedor.getNome() + " - " + vendedor.getCpf());
        }
        System.out.print("Informe o id do vendedor para ser removido: ");
        int opcao = sc.nextInt();
        clienteRepository.excluirPorId(opcao);
        System.out.println("Vendedor removido com sucesso.");
    }
}
