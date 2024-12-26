package kutuphaneYonetimSistemi.service;

import kutuphaneYonetimSistemi.kutuphaneYonetimSistemi.Borrow;
import kutuphaneYonetimSistemi.kutuphaneYonetimSistemi.BorrowRepository;
import kutuphaneYonetimSistemi.kutuphaneYonetimSistemi.Books;
import kutuphaneYonetimSistemi.kutuphaneYonetimSistemi.User;

import java.util.Date;

public class BorrowService extends GenericService<Borrow> {

    public BorrowService(BorrowRepository repository) {
        super(repository);
    }

    // Kitap ödünç alma işlemi
    public boolean borrowBook(User user, Books book) {
        if (book.getStocks() > 0) {
            Borrow borrow = new Borrow(
                    user,
                    book,
                    new Date(),
                    null, // İade tarihi başlangıçta null
                    false // Kitap henüz iade edilmedi
            );
            book.setStocks(book.getStocks() - 1); // Kitap stokları azaltılır
            add(borrow); // Ödünç işlemi kaydedilir
            return true;
        }
        return false; // Stok yetersizse işlem başarısız
    }

    // Kitap iade işlemi
    public boolean returnBook(int borrowId) {
        Borrow borrow = getAll()
                .stream()
                .filter(b -> b.getId() == borrowId && !b.isReturned())
                .findFirst()
                .orElse(null);

        if (borrow != null) {
            borrow.setReturned(true); // Kitap iade edildi olarak işaretlenir
            borrow.getBook().setStocks(borrow.getBook().getStocks() + 1); // Kitap stoğu artırılır
            return true;
        }
        return false; // Kitap zaten iade edilmişse veya bulunamazsa başarısız
    }

    // Kullanıcının ödünç aldığı kitapları listele
    public void listBorrowedBooksByUser(User user) {
        getAll()
                .stream()
                .filter(borrow -> borrow.getUser().equals(user) && !borrow.isReturned())
                .forEach(borrow -> System.out.println(
                        "Kitap: " + borrow.getBook().getBookName() + ", Alış Tarihi: " + borrow.getBorrowDate()
                ));
    }
}
