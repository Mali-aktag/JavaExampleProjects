package kutuphaneYonetimSistemi.service;

import kutuphaneYonetimSistemi.kutuphaneYonetimSistemi.Books;
import kutuphaneYonetimSistemi.kutuphaneYonetimSistemi.BooksRepository;

public class BooksService extends GenericService<Books> {

    public BooksService(BooksRepository repository) {
        super(repository);
    }

    // Kitabı adına göre bul
    public Books findByBookName(String bookName) {
        return getAll()
                .stream()
                .filter(book -> book.getBookName().equalsIgnoreCase(bookName))
                .findFirst()
                .orElse(null);
    }

    // Kitap stok kontrolü
    public boolean isBookAvailable(String bookName) {
        Books book = findByBookName(bookName);
        return book != null && book.getStocks() > 0;
    }

    // Kitap silme (sadece admin için)
    public boolean deleteBook(Books book) {
        delete(book);
        return true;
    }
}
