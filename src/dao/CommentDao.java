package dao;

import beans.Comment;
import beans.MotherProduct;
import beans.Person;

public interface CommentDao {
	public Boolean Comment(Person pers, MotherProduct mProduct, String comment);

	public Boolean modifiyComment(Comment comment);

	public Boolean deleteComment(Comment comment);
}