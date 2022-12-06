package repository;

import model.Veiculo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VeiculoRepository implements Repository<Veiculo> {

    private Map<Integer, Veiculo> veiculosRepository;

    public VeiculoRepository(){
        this.veiculosRepository = new HashMap<>();
    }

    @Override
    public List<Veiculo> buscarTodos() {
        return veiculosRepository.values().stream().collect(Collectors.toList());
    }

    @Override
    public Veiculo buscarPorId(Integer id) {
        return veiculosRepository.get(id);
    }

    @Override
    public void excluirPorId(Integer id) {
        veiculosRepository.remove(id);
    }

    @Override
    public Veiculo salvar(Veiculo veiculo) {
        this.veiculosRepository.put(veiculo.getId(), veiculo);
        return veiculo;
    }

}
