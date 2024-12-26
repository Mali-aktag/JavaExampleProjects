package kutuphaneYonetimSistemi.kutuphaneYonetimSistemi;

import java.util.Date;

public class Borrow {
    private int id;
    private User user;
    private Books book;
    private Date borrowDate;
    private Date returnDate;
    private boolean isReturned;

    public Borrow(User user, Books book, Date borrowDate, Date returnDate, boolean isReturned) {
        this.id = IdGenerator.generateId();
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
}
