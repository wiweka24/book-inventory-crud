package bookinventory.crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "date")
	private java.sql.Date date;
	
	@Column(name = "stock")
	private Long stock;

	@Column(name = "description")
	private String description;

	@Column(name = "cover", length = 64)
    private String cover;

	@ManyToOne
	private Categories categories;

	public Book() {}
	
	public Book(String title, String category, String author, String publisher, java.sql.Date date, Long stock, String description, String cover) {
		super();
		this.title = title;
		this.category = category;
		this.author = author;
		this.publisher = publisher;
		this.date = date;
		this.stock = stock;
		this.description = description;
		this.cover = cover;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}
}
