package model;

public class Vendedor extends Pessoa{

    public static final double PORCENTAGEM_COMISSAO = 0.05;
    private double salario;
    private double comissao;

    public Vendedor(String nome, String senha, String cpf, String endereco, double salario) {
        super(nome, senha, cpf, endereco);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
