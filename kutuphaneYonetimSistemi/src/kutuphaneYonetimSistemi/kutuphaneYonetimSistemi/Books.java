package kutuphaneYonetimSistemi.kutuphaneYonetimSistemi;

public class Books {
    private int id;
    private String bookName;
    private String author;
    private String description;
    private int stocks;

    public Books(String bookName, String author, String description, int stocks) {
        this.id = IdGenerator.generateId();
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.stocks = stocks;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStocks() {
		return stocks;
	}

	public void setStocks(int stocks) {
		this.stocks = stocks;
	}

    
}
