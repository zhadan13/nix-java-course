package dao;

import models.Post;
import models.User;

import java.util.Set;

public interface PostDao {
    Post getPostById(Long id);

    Post getPostByTitle(String title);

    boolean deletePostById(Long id);

    boolean insertPost(Post post);

    boolean updatePost(Post post);

    Set<Post> getAllPosts();

    User getPostAuthor(Long id);
}