package kutuphaneYonetimSistemi.kutuphaneYonetimSistemi;

import java.util.ArrayList;
import java.util.List;

public class GenericRepository<T> {

    private final List<T> dataStore = new ArrayList<>();

    // Veriyi ekle
    public void add(T entity) {
        dataStore.add(entity);
    }

    // Veriyi güncelle
    public void update(int index, T entity) {
        if (index >= 0 && index < dataStore.size()) {
            dataStore.set(index, entity);
        }
    }

    // Veriyi sil
    public void delete(T entity) {
        dataStore.remove(entity);
    }

    // Tüm verileri getir
    public List<T> getAll() {
        return new ArrayList<>(dataStore);
    }
}
