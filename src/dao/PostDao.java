package dao;

import java.util.ArrayList;

import beans.Post;

public interface PostDao {
	public Boolean Post(Long idAuthor, String content, String Title);

	public Boolean modifiyPost(Long Id, String content, String Title);

	public Boolean deletePost(Long Id);

	public Post selectPostById(Long Id);

	public ArrayList<Post> selectPostsByIdAuthor(Long Id, Integer limit, Integer offset);

	public Long selectNbrePostsByIdAuthor(Long Id);

}
