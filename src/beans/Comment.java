package beans;

import java.sql.Date;

public class Comment {
	private Long Id;
	private Long Author;
	private String Content;
	private Date datePosted;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getAuthor() {
		return Author;
	}

	public void setAuthor(Long author) {
		Author = author;
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

}
