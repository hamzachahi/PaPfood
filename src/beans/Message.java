package beans;

import java.sql.Date;

public class Message {
	private Long Id;
	private Long Sender;
	private Long Receiver;
	private String Summary;
	private String Content;
	private Date sentDate;
	private Date receiveDate;
	private Date readDate;
	private Person realSender;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getSender() {
		return Sender;
	}

	public void setSender(Long sender) {
		Sender = sender;
	}

	public Long getReceiver() {
		return Receiver;
	}

	public void setReceiver(Long receiver) {
		Receiver = receiver;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		int taillesum=content.length()/4;
		Summary=content.substring(0, taillesum);
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

	public Person getRealSender() {
		return realSender;
	}

	public void setRealSender(Person realSender) {
		this.realSender = realSender;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	@Override
	public String toString() {
		return "Message [Id=" + Id + ", Sender=" + Sender + ", Receiver=" + Receiver + ", Summary=" + Summary
				+ ", Content=" + Content + ", sentDate=" + sentDate + ", receiveDate=" + receiveDate + ", readDate="
				+ readDate + ", realSender=" + realSender + "]";
	}

}
