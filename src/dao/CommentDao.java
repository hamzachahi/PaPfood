package dao;

import java.util.ArrayList;

import beans.Comment;

public interface CommentDao {
	public Boolean CommentService(Long idAuthor, Long IdService, String comment);

	public Boolean CommentProduct(Long idAuthor, Long IdProduct, String comment);

	public Boolean modifiyComment(Long Id, String content);

	public Boolean deleteComment(Long Id);

	public Comment selectCommentById(Long Id);

	public ArrayList<Comment> selectCommentsByIdProduct(Long Id, Long limit, Long offset);

	public ArrayList<Comment> selectCommentsByIdService(Long Id, Long limit, Long offset);

	public Long selectNbreCommentsByIdService(Long Id);

	public Long selectNbreCommentsByIdProduct(Long Id);

}
