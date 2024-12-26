package kutuphaneYonetimSistemi.service;

import kutuphaneYonetimSistemi.kutuphaneYonetimSistemi.User;
import kutuphaneYonetimSistemi.kutuphaneYonetimSistemi.UserRepository;

public class UserService extends GenericService<User> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    // Kullanıcıyı kullanıcı adına göre bul
    public User findByUsername(String username) {
        return getAll()
                .stream()
                .filter(user -> user.getName().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    // Kullanıcı giriş kontrolü
    public User login(String username, String password) {
        return getAll()
                .stream()
                .filter(user -> user.getName().equalsIgnoreCase(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // Admin kontrolü
    public boolean isAdmin(User user) {
        return user.getRole().equalsIgnoreCase("admin");
    }
}
