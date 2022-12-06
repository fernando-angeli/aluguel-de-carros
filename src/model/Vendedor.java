package model;

public class Vendedor extends Pessoa{

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
        return "Vendedor{" +
                "Salario=" + salario +
                ", Comissao=" + comissao +
                ", Id=" + id +
                ", Nome='" + nome + '\'' +
                '}';
    }
}
