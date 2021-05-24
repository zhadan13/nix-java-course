package com.example.newsspring.service;

import com.example.newsspring.model.Post;
import com.example.newsspring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void addNewPost(final Post post) {
        postRepository.save(post);
    }

    public void deletePost(final Long id) {
        if (!postRepository.existsById(id)) {
            throw new IllegalStateException("Post with id " + id + " does not exists!");
        }
        postRepository.deleteById(id);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByAuthorId(final Long id) {
        return postRepository.findAllPostsByAuthorId(id);
    }

    public boolean existsById(final Long id) {
        return postRepository.existsById(id);
    }

    public Optional<Post> getPost(final Long id) {
        if (!postRepository.existsById(id)) {
            throw new IllegalStateException("Post with id " + id + " does not exists!");
        }
        return postRepository.findById(id);
    }

    @Transactional
    public void updatePost(final Long id, final String title, final String fullText, final String date) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Post with id " + id + " does not exists!"));
        if (title != null && title.trim().length() > 0 && !Objects.equals(title, post.getTitle())) {
            post.setTitle(title);
        }
        if (fullText != null && fullText.trim().length() > 0 && !Objects.equals(fullText, post.getFullText())) {
            post.setFullText(fullText);
        }
        if (date != null && date.trim().length() > 0 && !Objects.equals(date, post.getDate())) {
            post.setDate(date);
        }
    }

    @Transactional
    public void updatePostViews(final Long id) {
        postRepository.findById(id).ifPresent(post -> post.setViews(post.getViews() + 1));
    }
}
