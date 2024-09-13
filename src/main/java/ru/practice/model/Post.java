package ru.practice.model;

import java.time.LocalDate;
import java.util.Objects;

public class Post {
    private Integer id;
    private final User author;
    private String description;
    private String photoUrl;
    private LocalDate creationDate;

    public Post(User author, String description, String photoUrl, LocalDate creationDate) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
        this.creationDate = creationDate;
    }

    public Post(Integer id, User author, String description, String photoUrl, LocalDate creationDate) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor_id() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto_url() {
        return photoUrl;
    }

    public void setPhoto_url(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDate getCreation_date() {
        return creationDate;
    }

    public void setCreation_date(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //сравнение ссылок
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;// Приведение типа
        return Objects.equals(id, post.id) &&
                Objects.equals(author, post.author) &&
                Objects.equals(description, post.description) &&
                Objects.equals(photoUrl, post.photoUrl) &&
                Objects.equals(creationDate, post.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, description, photoUrl, creationDate);
    }
}
