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
        this.criarVeiculos();
    }

    private void criarVeiculos(){
        Veiculo veiculo1 = new Veiculo("Chevrolet","Onix", "IVP3455", "Branco", 2020, Veiculo.Segmento.CARRO, 150.00);
        Veiculo veiculo2 = new Veiculo("Hyundai","HB20", "ISP3455", "Preto", 2015, Veiculo.Segmento.CARRO, 160.00);
        Veiculo veiculo3 = new Veiculo("Ford","Fiesta", "IVL3455", "Cinza", 2015, Veiculo.Segmento.CARRO, 125.00);
        Veiculo veiculo4 = new Veiculo("Honda","CG", "IVP3875", "Vermelha", 2020, Veiculo.Segmento.MOTO, 80.00);

        veiculosRepository.put(veiculo1.getId(), veiculo1);
        veiculosRepository.put(veiculo2.getId(), veiculo2);
        veiculosRepository.put(veiculo3.getId(), veiculo3);
        veiculosRepository.put(veiculo4.getId(), veiculo4);
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
