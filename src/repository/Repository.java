package repository;

import java.util.List;

public interface Repository<T> {

    List<T> buscarTodos();

    T buscarPorId(Integer id);

    void excluirPorId(Integer id);

    T salvar(T t);
}
