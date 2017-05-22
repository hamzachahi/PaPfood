package dao;

import java.util.ArrayList;

import beans.Comment;

public interface CommentDao {
	public Boolean CommentService(Long idAuthor, Long IdService, String comment);

	public Boolean CommentProduct(Long idAuthor, Long IdProduct, String comment);

	public Boolean modifiyComment(Comment comment);

	public Boolean deleteComment(Long Id);

	public ArrayList<Comment> selectCommentsByIdProduct(Long Id, Long limit, Long offset);

	public ArrayList<Comment> selectCommentsByIdService(Long Id, Long limit, Long offset);
}
