package dao;

import beans.Person;

public interface MessageDao {
	public Boolean sendMessage(Person pers, Long id_Dest, String Message);

	public Boolean receiveMessage();

	public Boolean readMessage();

	public Boolean deleteMessage();
}
