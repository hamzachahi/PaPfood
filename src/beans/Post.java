package beans;

import java.sql.Date;

public class Post {
	private Long Id;
	private Long idAuthor;
	private String Content;
	private Date datePosted;
	private Person Author;
	private String Title;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(Long idauthor) {
		idAuthor = idauthor;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public Person getAuthor() {
		return Author;
	}

	public void setAuthor(Person author) {
		Author = author;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

}
