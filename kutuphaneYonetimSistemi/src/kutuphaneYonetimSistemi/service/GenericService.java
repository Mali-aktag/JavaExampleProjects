package kutuphaneYonetimSistemi.service;

import java.util.List;
import kutuphaneYonetimSistemi.kutuphaneYonetimSistemi.GenericRepository;

public class GenericService<T> {

    private final GenericRepository<T> repository;

    public GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    // Ekleme işlemi
    public void add(T entity) {
        repository.add(entity);
    }

    // Güncelleme işlemi
    public void update(int index, T entity) {
        repository.update(index, entity);
    }

    // Silme işlemi
    public void delete(T entity) {
        repository.delete(entity);
    }

    // Tüm verileri listele
    public List<T> getAll() {
        return repository.getAll();
    }
}
