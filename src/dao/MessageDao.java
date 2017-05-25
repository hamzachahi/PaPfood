package dao;

import java.util.ArrayList;
import beans.Message;

public interface MessageDao {
	public Boolean sendMessage(Long Id, Long id_Dest, String Message);

	public Boolean receiveMessage(Message message);

	public Boolean readMessage(Message message);

	public Boolean deleteMessage(Message message);

	public ArrayList<Message> receiveMyUnreadMessage(Long Id, Long limit, Long offset);

	public ArrayList<Message> receiveMyMessage(Long Id, Long limit, Long offset);

	public ArrayList<Message> getMySendMessage(Long Id, Long limit, Long offset);

	public Long countNbreMessageById(Long idSender);

}
