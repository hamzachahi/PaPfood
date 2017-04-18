package dao;

import beans.Message;
import beans.Person;

public interface MessageDao {
	public Boolean sendMessage(Person pers, Long id_Dest, String Message);

	public Boolean receiveMessage(Message message);

	public Boolean readMessage(Message message);

	public Boolean deleteMessage(Message message);
}
