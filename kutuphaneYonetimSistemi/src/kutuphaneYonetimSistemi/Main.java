package kutuphaneYonetimSistemi;

import kutuphaneYonetimSistemi.kutuphaneYonetimSistemi.*;
import kutuphaneYonetimSistemi.service.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Repository'leri oluştur
        UserRepository userRepo = new UserRepository();
        BooksRepository booksRepo = new BooksRepository();
        BorrowRepository borrowRepo = new BorrowRepository();

        // Servisleri oluştur
        UserService userService = new UserService(userRepo);
        BooksService booksService = new BooksService(booksRepo);
        BorrowService borrowService = new BorrowService(borrowRepo);

        // Örnek kullanıcı ve kitap ekle
        userService.add(new User("Admin", 30, 123456789, "admin123", "admin"));
        userService.add(new User("Ali", 25, 987654321, "user123", "user"));

        booksService.add(new Books("1984", "George Orwell", "Distopik roman", 5));
        booksService.add(new Books("Savaş ve Barış", "Tolstoy", "Dünya klasiği", 10));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Kütüphane Yönetim Sistemi ---");
            System.out.println("1. Giriş Yap");
            System.out.println("2. Çıkış");
            System.out.print("Seçiminizi yapın: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Satır sonu karakterini temizle

            switch (choice) {
                case 1:
                    // Kullanıcı giriş işlemi
                    System.out.print("Kullanıcı adı: ");
                    String username = scanner.nextLine();
                    System.out.print("Şifre: ");
                    String password = scanner.nextLine();

                    User currentUser = userService.login(username, password);
                    if (currentUser != null) {
                        System.out.println("Giriş başarılı! Hoş geldiniz, " + currentUser.getName());
                        if (userService.isAdmin(currentUser)) {
                            adminMenu(scanner, userService, booksService, borrowService, currentUser);
                        } else {
                            userMenu(scanner, booksService, borrowService, currentUser);
                        }
                    } else {
                        System.out.println("Geçersiz kullanıcı adı veya şifre!");
                    }
                    break;

                case 2:
                    // Çıkış
                    exit = true;
                    System.out.println("Programdan çıkılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        }

        scanner.close();
    }

    // Admin menüsü
    private static void adminMenu(Scanner scanner, UserService userService, BooksService booksService, BorrowService borrowService, User admin) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Admin Menüsü ---");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Kitap Sil");
            System.out.println("3. Kitapları Listele");
            System.out.println("4. Ödünç Alınan Kitapları Listele");
            System.out.println("5. Çıkış");
            System.out.print("Seçiminizi yapın: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Satır sonu karakterini temizle

            switch (choice) {
                case 1:
                    // Kitap ekleme
                    System.out.print("Kitap adı: ");
                    String bookName = scanner.nextLine();
                    System.out.print("Yazar adı: ");
                    String author = scanner.nextLine();
                    System.out.print("Açıklama: ");
                    String description = scanner.nextLine();
                    System.out.print("Stok sayısı: ");
                    int stocks = scanner.nextInt();
                    booksService.add(new Books(bookName, author, description, stocks));
                    System.out.println("Kitap başarıyla eklendi.");
                    break;

                case 2:
                    // Kitap silme
                    System.out.print("Silmek istediğiniz kitabın adı: ");
                    String bookToDelete = scanner.nextLine();
                    Books book = booksService.findByBookName(bookToDelete);
                    if (book != null) {
                        booksService.deleteBook(book);
                        System.out.println("Kitap başarıyla silindi.");
                    } else {
                        System.out.println("Kitap bulunamadı.");
                    }
                    break;

                case 3:
                    // Kitapları listele
                    System.out.println("\n--- Kitap Listesi ---");
                    booksService.getAll().forEach(b -> System.out.println(b.getBookName() + " (" + b.getStocks() + " adet)"));
                    break;

                case 4:
                    // Ödünç alınan kitapları listele
                    System.out.println("\n--- Ödünç Alınan Kitaplar ---");
                    borrowService.getAll().forEach(b -> System.out.println(
                            "Kitap: " + b.getBook().getBookName() +
                                    ", Kullanıcı: " + b.getUser().getName() +
                                    ", Alış Tarihi: " + b.getBorrowDate()
                    ));
                    break;

                case 5:
                    // Çıkış
                    exit = true;
                    System.out.println("Admin menüsünden çıkılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        }
    }

    // Kullanıcı menüsü
    private static void userMenu(Scanner scanner, BooksService booksService, BorrowService borrowService, User user) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Kullanıcı Menüsü ---");
            System.out.println("1. Kitapları Listele");
            System.out.println("2. Kitap Ödünç Al");
            System.out.println("3. Ödünç Alınan Kitapları Listele");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminizi yapın: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Satır sonu karakterini temizle

            switch (choice) {
                case 1:
                    // Kitapları listele
                    System.out.println("\n--- Kitap Listesi ---");
                    booksService.getAll().forEach(b -> System.out.println(b.getBookName() + " (" + b.getStocks() + " adet)"));
                    break;

                case 2:
                    // Kitap ödünç alma
                    System.out.print("Ödünç almak istediğiniz kitabın adı: ");
                    String bookNameToBorrow = scanner.nextLine();
                    Books book = booksService.findByBookName(bookNameToBorrow);
                    if (book != null && book.getStocks() > 0) {
                        borrowService.borrowBook(user, book);
                        System.out.println("Kitap başarıyla ödünç alındı.");
                    } else {
                        System.out.println("Kitap stokta yok veya bulunamadı.");
                    }
                    break;

                case 3:
                    // Ödünç alınan kitapları listele
                    System.out.println("\n--- Ödünç Alınan Kitaplar ---");
                    borrowService.listBorrowedBooksByUser(user);
                    break;

                case 4:
                    // Çıkış
                    exit = true;
                    System.out.println("Kullanıcı menüsünden çıkılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        }
    }
}
