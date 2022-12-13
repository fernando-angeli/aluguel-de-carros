package service;

import exception.VendedorException;
import model.Veiculo;
import model.Vendedor;
import repository.VendedorRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class VendedorService {

    private VendedorRepository repository;

    private Scanner sc;

    public VendedorService(Scanner sc){
        this.repository = new VendedorRepository();
        this.sc = sc;
    }
    public void buscarVendedores(){
        repository.buscarTodos().forEach(System.out::println);
    }

    public void gerarComissao(Veiculo veiculo, Integer vendedorId) throws VendedorException {
        long diasLocacao = LocalDate.now().until(veiculo.getDataEntrega(), ChronoUnit.DAYS);
        double comissao = veiculo.getValor() * diasLocacao * Vendedor.PORCENTAGEM_COMISSAO;
        
        Vendedor vendedor = repository.buscarPorId(vendedorId);
        if(vendedor == null){
            throw new VendedorException("Vendedor não localizado");
        }
        vendedor.setComissao(vendedor.getComissao() + comissao);
        
        repository.salvar(vendedor);
    }

    public Vendedor buscarVendedorPorCpf(String cpf) {
        List<Vendedor> vendedores = repository.buscarTodos();
        for(Vendedor vendedor : vendedores){
            if(vendedor.getCpf().equals(cpf))
                return vendedor;
        }
        return null;
    }

    public void mostrarSalarioAtual(Vendedor vendedor){
        System.out.println(vendedor.getNome() + ", seu salário com a comissão atual é de R$ " + String.format("%.2f", (vendedor.getComissao()+vendedor.getSalario())));
    }

    public VendedorRepository getRepository() {
        return repository;
    }
}
