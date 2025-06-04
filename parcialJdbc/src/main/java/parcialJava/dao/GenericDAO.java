package parcialJava.dao;

import java.util.List;

public interface GenericDAO<T> {
    void crear(T entidad);
    List<T> listarTodos();
    T buscarPorId(int id);
    void editar(T entidad);
    void eliminar(int id);
}