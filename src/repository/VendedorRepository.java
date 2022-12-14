package repository;

import model.Vendedor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VendedorRepository implements Repository<Vendedor>{

    private Map<Integer, Vendedor> vendedoresRepository;

    public VendedorRepository(){
        this.vendedoresRepository = new HashMap<>();
        this.salvar(new Vendedor("Joao", "12345", "01", "Rua 1, 30", 2500));
        this.salvar(new Vendedor("Maria", "12345", "02", "Rua 1, 30", 2500));
    }

    @Override
    public List<Vendedor> buscarTodos() {
        return vendedoresRepository.values().stream().collect(Collectors.toList());
    }

    @Override
    public Vendedor buscarPorId(Integer id) {
        return vendedoresRepository.get(id);
    }

    @Override
    public void excluirPorId(Integer id) {
        vendedoresRepository.remove(id);
    }

    @Override
    public Vendedor salvar(Vendedor vendedor) {
        vendedoresRepository.put(vendedor.getId(), vendedor);
        return vendedor;
    }

}
