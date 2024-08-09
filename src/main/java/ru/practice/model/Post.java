package ru.practice.model;

import java.util.Objects;

public class Post {
    private int id;
    private String author_id;
    private String description;
    private String photo_url;
    private String creation_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //сравнение ссылок
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;// Приведение типа
        return id == post.id &&
                Objects.equals(author_id, post.author_id) &&
                Objects.equals(description, post.description) &&
                Objects.equals(photo_url, post.photo_url) &&
                Objects.equals(creation_date, post.creation_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author_id, description, photo_url, creation_date);
    }
}
