package com.example.newsspring.controller;

import com.example.newsspring.model.Post;
import com.example.newsspring.service.PostService;
import com.example.newsspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BlogController {
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public BlogController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/blog")
    public String blogMain(Model model) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String fullText,
                              Model model) {
        if (title.trim().isEmpty() || fullText.trim().isEmpty()) {
            return "redirect:/blog";
        }
        final String dateTimePattern = "dd.MM.yyyy HH:mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
        Post post = new Post(title, fullText, dateTimeFormatter.format(LocalDateTime.now()), 0,
                userService.getUserByEmail("artem@google.com").orElseThrow(RuntimeException::new).getId());
        postService.addNewPost(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") Long id, Model model) {
        if (!isIdExists(id, model)) {
            return "redirect:/blog";
        }
        postService.updatePostViews(id);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model) {
        if (!isIdExists(id, model)) {
            return "redirect:/blog";
        }
        return "blog-edit";
    }

    private boolean isIdExists(@PathVariable("id") Long id, Model model) {
        if (!postService.existsById(id)) {
            return false;
        }
        Post post = postService.getPost(id).orElseThrow();
        model.addAttribute("post", post);
        return true;
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") Long id,
                                 @RequestParam String title,
                                 @RequestParam String fullText,
                                 Model model) {
        Post post = postService.getPost(id).orElseThrow();
        final String dateTimePattern = "dd.MM.yyyy HH:mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
        postService.updatePost(post.getId(), title, fullText, dateTimeFormatter.format(LocalDateTime.now()));
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") Long id, Model model) {
        Post post = postService.getPost(id).orElseThrow();
        postService.deletePost(post.getId());
        return "redirect:/blog";
    }
}
