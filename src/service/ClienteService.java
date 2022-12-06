package service;

import model.Cliente;
import repository.ClienteRepository;

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

    public Cliente criarCliente(String cpf){
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
        return repository.salvar(cliente);
    }

}
