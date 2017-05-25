package beans;

import java.sql.Date;

public class Evaluation {
	protected Long Id;
	protected Long idPerson;
	protected Long idJury;
	protected Double Note;
	protected String Comments;
	protected Date datePosted;
	protected Person Author;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public Long getIdJury() {
		return idJury;
	}

	public void setIdJury(Long idJury) {
		this.idJury = idJury;
	}

	public Double getNote() {
		return Note;
	}

	public void setNote(Double note) {
		Note = note;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDate_posted(Date date_posted) {
		this.datePosted = date_posted;
	}

	public Person getAuthor() {
		return Author;
	}

	public void setAuthor(Person author) {
		Author = author;
	}

}
