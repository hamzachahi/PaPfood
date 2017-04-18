package beans;

import java.sql.Date;

public class Message {
	private Long Id;
	private Person Sender;
	private Person Receiver;
	private String Content;
	private Date sentDate;
	private Date receiveDate;
	private Date readDate;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Person getSender() {
		return Sender;
	}

	public void setSender(Person sender) {
		Sender = sender;
	}

	public Person getReceiver() {
		return Receiver;
	}

	public void setReceiver(Person receiver) {
		Receiver = receiver;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

}
