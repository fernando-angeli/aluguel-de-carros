package repository;

import model.Cliente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClienteRepository implements Repository<Cliente>{

    private Map<Integer, Cliente> clientesRepository;

    public ClienteRepository(){
        this.clientesRepository = new HashMap<>();
        Cliente cliente = new Cliente("Fernando", "12345", "00011122233", "Rua 1, 30", Cliente.TipoPessoa.PF);
        clientesRepository.put(cliente.getId(), cliente);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return clientesRepository.values().stream().collect(Collectors.toList());
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        return clientesRepository.get(id);
    }

    @Override
    public void excluirPorId(Integer id) {
        clientesRepository.remove(id);
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        clientesRepository.put(cliente.getId(), cliente);
        return cliente;
    }

    public Cliente buscarPorCpf(String cpf){
        List<Cliente> clientes = this.buscarTodos();
        for(Cliente c : clientes)
            if(c.getCpf().equals(cpf))
                return c;
        return null;
    }

}
