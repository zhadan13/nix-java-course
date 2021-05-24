package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Post implements Serializable {
    private static final long serialVersionUID = -2784803217524952255L;

    private Long id;
    private String title;
    private String text;
    private LocalDate date;
    private Integer views;
    private User author;
    private Long authorId;

    public Post() {
    }

    public Post(String title, String text, LocalDate date, Integer views, Long authorId) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.views = views;
        this.authorId = authorId;
    }

    public Post(String title, String text, LocalDate date, Integer views, User author, Long authorId) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.views = views;
        this.author = author;
        this.authorId = authorId;
    }

    public Post(Long id, String title, String text, LocalDate date, Integer views, User author, Long authorId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.views = views;
        this.author = author;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(text, post.text) && Objects.equals(date, post.date) && Objects.equals(views, post.views) && Objects.equals(author, post.author) && Objects.equals(authorId, post.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, date, views, author, authorId);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", text = '" + text + '\'' +
                ", date = " + date +
                ", views = " + views +
                ", author = " + author +
                ", authorId = " + authorId +
                '}';
    }
}
