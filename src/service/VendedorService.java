package service;

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

    public void gerarComissao(Veiculo veiculo, Integer vendedorId){
        long diasLocacao = LocalDate.now().until(veiculo.getDataEntrega(), ChronoUnit.DAYS);
        double comissao = veiculo.getValor() * diasLocacao * Vendedor.PORCENTAGEM_COMISSAO;
        Vendedor vendedor = repository.buscarPorId(vendedorId);
        vendedor.setComissao(vendedor.getComissao() + comissao);
        System.out.println(vendedor.getComissao());
    }



}
