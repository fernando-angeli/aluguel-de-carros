import exceptions.CpfInvalidoException;
import exceptions.UsuarioNaoCadastradoException;
import menu.Menu;
import model.Cliente;
import service.ClienteService;
import util.Console;
import util.ValidadorCpf;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ClienteService clienteService = new ClienteService(sc);
        Cliente clienteLogado = null;

        do {
            Menu.bemVindo();
            String cpf = null;
            do {
                try {
                    cpf = sc.next();
                    if (ValidadorCpf.isCPF(cpf)) {
                        clienteLogado = clienteService.buscarClientePorCpf(cpf);
                    } else if (!ValidadorCpf.isCPF(cpf))
                        throw new CpfInvalidoException("O CPF digitado não é válido, tente novamente: ");
                } catch (CpfInvalidoException e) {
                    System.out.print(e.getMessage());
                }
            } while (!ValidadorCpf.isCPF(cpf));

            if (clienteLogado != null) {
                System.out.println("Login efetuado com sucesso!");
            }
        } while(clienteLogado == null);

        Menu.opcoesCliente();
        Menu.opcoesAdmin();
        Menu.opcoesVendedor();

    }
}