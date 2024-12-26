package kutuphaneYonetimSistemi.kutuphaneYonetimSistemi;

public class IdGenerator {
    private static int currentId = 1; // id için bir sayaç

    // sayacı 1 artırarak basit bir id üretici elde ediyoruz
    public static synchronized int generateId() {
        return currentId++;
    }
}
