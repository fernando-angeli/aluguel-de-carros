package model;

import util.Contador;

import java.time.LocalDate;

public class Veiculo {

    public enum Segmento {CARRO, MOTO, CAMINHAO}
    public enum Status {LIVRE, ALUGADO}

    private Integer id;
    private String marca;
    private String modelo;
    private String placa;
    private String cor;
    private int ano;
    private Segmento segmento;
    private double valor;
    private LocalDate dataEntrega;
    private Status status;

    public Veiculo(String marca, String modelo, String placa, String cor, int ano, Segmento segmento, double valor) {
        this.id = Contador.getId();
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
        this.ano = ano;
        this.segmento = segmento;
        this.valor = valor;
        this.status = Status.LIVRE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.marca + " - " + this.modelo + " - " + this.ano + " - R$" + String.format("%.2f", this.valor);
    }
}
