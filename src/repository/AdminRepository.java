package repository;

import model.Admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminRepository implements Repository<Admin>{

    private Map<Integer, Admin> adminsRepository;

    public AdminRepository() {
        this.adminsRepository = new HashMap<>();
    }

    @Override
    public List<Admin> buscarTodos() {
        return adminsRepository.values().stream().collect(Collectors.toList());
    }

    @Override
    public Admin buscarPorId(Integer id) {
        return adminsRepository.get(id);
    }

    @Override
    public void excluirPorId(Integer id) {
        adminsRepository.remove(id);
    }

    @Override
    public Admin salvar(Admin admin) {
        adminsRepository.put(admin.getId(), admin);
        return admin;
    }

}
