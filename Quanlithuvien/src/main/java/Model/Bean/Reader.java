package Model.Bean;

public class Reader {
	private String id;
	private String name;
	private String book_id;
	private String identify;
	private Book book;
	private String status;
	private String start_day;
	private String end_day;
	
	public Reader() {
		
	}

	public Reader(String id, String name, String book_id, String identify, Book book, String status, String start_day,
			String end_day) {
		super();
		this.id = id;
		this.name = name;
		this.book_id = book_id;
		this.identify = identify;
		this.book = book;
		this.status = status;
		this.start_day = start_day;
		this.end_day = end_day;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStart_day() {
		return start_day;
	}

	public void setStart_day(String start_day) {
		this.start_day = start_day;
	}

	public String getEnd_day() {
		return end_day;
	}

	public void setEnd_day(String end_day) {
		this.end_day = end_day;
	}
	
}
