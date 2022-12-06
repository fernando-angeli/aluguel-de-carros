package menu;

public class Menu {

    public static void bemVindo(){
        System.out.println("===========================================");
        System.out.println("Bem vindo ao Sistema de Aluguel de Veículos");
        System.out.print("\nPara começar, digite seu CPF: ");
    }

    public static void opcoesCliente(){
        System.out.println("Digite uma opção do Menu:");
        System.out.println("1 - Alugar um veículo");
        System.out.println("2 - Devolver um veículo");
    }

    public static void opcoesAdmin(){
        System.out.println("Digite uma opção do Menu:");
        System.out.println("1 - Cadastrar um cliente");
        System.out.println("2 - Remover um cliente");
        System.out.println("3 - Cadastrar um veículo");
        System.out.println("4 - Remover um veículo");
        System.out.println("5 - Cadastrar um vendedor");
        System.out.println("6 - Remover um vendedor");
    }

    public static void opcoesVendedor(){
        System.out.println("Digite uma opção do Menu:");
        System.out.println("1 - Ver veículos alugados");
        System.out.println("2 - Ver salário + comissões (atual)");
    }

}
