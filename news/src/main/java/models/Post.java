package models;

import java.time.LocalDate;

public class Post {

    private Long id; // TODO: Generate value
    private String title;
    private String text;
    private LocalDate date;
    private Integer views;
    private User author;

    public Post() {
    }

    public Post(String title, String text, LocalDate date, Integer views, User author) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.views = views;
        this.author = author;
    }

    public Post(Long id, String title, String text, LocalDate date, Integer views, User author) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.views = views;
        this.author = author;
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

    @Override
    public String toString() {
        return "Post {" +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", text = '" + text + '\'' +
                ", date = " + date +
                ", views = " + views +
                ", author = " + (author == null ? "can't find author" : author.toString()) +
                '}' + '\'';
    }
}