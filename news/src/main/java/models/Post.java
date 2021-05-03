package models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Post")
@Table(name = "news1")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private LocalDate date;

    @Column
    private Integer views;

    @Transient
    private User author;

    @Column
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

    @Override
    public String toString() {
        return "Post { " +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", text = '" + text + '\'' +
                ", date = " + date +
                ", views = " + views +
                ", authorId = " + authorId +
                '}';
    }
}