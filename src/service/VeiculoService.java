package service;

import exception.VeiculoException;
import model.Veiculo;
import repository.VeiculoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class VeiculoService {
    private VeiculoRepository repository;

    private DateTimeFormatter formatter;

    private Scanner sc;

    public VeiculoService(Scanner sc, DateTimeFormatter formatter){
        this.repository = new VeiculoRepository();
        this.sc = sc;
        this.formatter = formatter;
    }

    public void buscarTodosVeiculosDisponiveis(){
        List<Veiculo> todosVeiculos = repository.buscarTodos();
        todosVeiculos.stream().filter(x -> x.getStatus().equals(Veiculo.Status.LIVRE)).forEach(System.out::println);
    }

    public Veiculo alugarVeiculo(Integer idVeiculo, int diasLocacao) throws VeiculoException {
        Veiculo veiculo = this.repository.buscarPorId(idVeiculo);
        if(veiculo == null){
            throw new VeiculoException("Veículo não encontrado");
        }
        if(veiculo.getStatus() == Veiculo.Status.ALUGADO){
            System.out.println("Veículo alugado");
            return null;
        }
        LocalDate dataDevolucao = LocalDate.now().plusDays(diasLocacao);
        veiculo.setStatus(Veiculo.Status.ALUGADO);
        veiculo.setDataEntrega(dataDevolucao);
        System.out.println("A entrega do veículo deverá ocorrer no dia: " + dataDevolucao.format(formatter));
        return veiculo;
    }

    public void devolverVeiculo(int veiculoEscolhido) {
        repository.buscarPorId(veiculoEscolhido).setStatus(Veiculo.Status.LIVRE);
    }

    public void veiculosAlugados(){
        List<Veiculo> todosVeiculos = repository.buscarTodos();
        todosVeiculos.stream().filter(x -> x.getStatus().equals(Veiculo.Status.ALUGADO)).forEach(System.out::println);
    }

    public VeiculoRepository getRepository() {
        return repository;
    }
}
