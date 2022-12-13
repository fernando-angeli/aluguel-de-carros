package model;

import util.Contador;

public abstract class Pessoa {

    protected Integer id;
    protected String nome;
    protected String senha;
    protected String cpf;
    protected String endereco;

    public Pessoa(String nome, String senha, String cpf, String endereco) {
        this.id = Contador.getId();
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public boolean validarSenha(String senha){
        return this.senha.equals(senha);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
