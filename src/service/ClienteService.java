package service;

import exception.VeiculoException;
import model.Cliente;
import model.Veiculo;
import repository.ClienteRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class ClienteService {

    private ClienteRepository repository;

    private Scanner sc;

    public ClienteService(Scanner sc){
        this.repository = new ClienteRepository();
        this.sc = sc;
    }

    public Cliente buscarClientePorCpf(String cpf){
        Cliente cliente = repository.buscarPorCpf(cpf);
        if(cliente == null){
            cliente = criarCliente(cpf);
        }
        return cliente;
    }

    private Cliente criarCliente(String cpf){
        sc.nextLine();
        System.out.println("---- Cadastro de clientes ----");
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();
        System.out.print("Digite seu endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();
        System.out.print("Digite PF (pessoa física) ou PJ (pessoa jurídica): ");
        String tipo = sc.nextLine().toUpperCase();
        Cliente.TipoPessoa tipoPessoa = tipo.equals("PF") ? Cliente.TipoPessoa.PF : Cliente.TipoPessoa.PJ;
        Cliente cliente = new Cliente(nome, senha, cpf, endereco, tipoPessoa);
        System.out.println("---- Cliente cadastrado ----");
        return repository.salvar(cliente);
    }

    public void adicionarVeiculo(Cliente cliente, Veiculo veiculo){
        cliente.getVeiculosAlugados().add(veiculo);
        long diasLocacao = LocalDate.now().until(veiculo.getDataEntrega(), ChronoUnit.DAYS);
        cliente.setDebitos(cliente.getDebitos() + (veiculo.getValor()*diasLocacao));
    }

    public void buscarVeiculosAlugados(Cliente clienteLogado) throws VeiculoException {
        List<Veiculo> veiculos = clienteLogado.getVeiculosAlugados();
        if(veiculos.isEmpty()){
            throw new VeiculoException("Sem veículos para devolver");
        }
        veiculos.forEach(System.out::println);
    }

    public void devolverVeiculo(Cliente clienteLogado, int veiculoEscolhido) {
        for(int i = 0; i < clienteLogado.getVeiculosAlugados().size(); i++)
            if(clienteLogado.getVeiculosAlugados().get(i).getId() == veiculoEscolhido) {
                clienteLogado.getVeiculosAlugados().remove(i);
                return;
            }
    }

    public ClienteRepository getRepository() {
        return repository;
    }
}
