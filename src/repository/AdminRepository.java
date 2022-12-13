package repository;

import model.Admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminRepository implements Repository<Admin>{

    private Map<Integer, Admin> adminRepository;

    public AdminRepository() {
        this.adminRepository = new HashMap<>();
        this.salvar(new Admin("Admin", "12345", "1", "Rua dos Admins, 120"));
    }

    @Override
    public List<Admin> buscarTodos() {
        return adminRepository.values().stream().collect(Collectors.toList());
    }

    @Override
    public Admin buscarPorId(Integer id) {
        return adminRepository.get(id);
    }

    @Override
    public void excluirPorId(Integer id) {
        adminRepository.remove(id);
    }

    @Override
    public Admin salvar(Admin admin) {
        adminRepository.put(admin.getId(), admin);
        return admin;
    }

}
