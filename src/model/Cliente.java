package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{

    public enum TipoPessoa {PF, PJ}
    private TipoPessoa tipo;
    private List<Veiculo> veiculosAlugados = new ArrayList<>();
    private double debitos;

    public Cliente(String nome, String senha, String cpf, String endereco, TipoPessoa tipo) {
        super(nome, senha, cpf, endereco);
        this.tipo = tipo;
    }

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public List<Veiculo> getVeiculosAlugados() {
        return veiculosAlugados;
    }

    public void setVeiculosAlugados(List<Veiculo> veiculosAlugados) {
        this.veiculosAlugados = veiculosAlugados;
    }

    public double getDebitos() {
        return debitos;
    }

    public void setDebitos(double debitos) {
        this.debitos = debitos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "tipo=" + tipo +
                ", veiculosAlugados=" + veiculosAlugados +
                ", debitos=" + debitos +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
