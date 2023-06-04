package Model.Bean;

public class Book {
	private String id;
	private String name;
	private String image;
	private String amount;
	private Category category;
	private String day;
	
	public Book() {
		
	}

	public Book(String id, String name, String image, String amount, Category category, String day) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.amount = amount;
		this.category = category;
		this.day = day;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	
}
