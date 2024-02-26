package com.example.dailyBlog.model;

import java.time.LocalDateTime;

public class Blog {
    private int id;
    private String title;
    private String content;
    private LocalDateTime publishDate;

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Setter for content
    public void setContent(String content) {
        this.content = content;
    }

    // Getter for publishDate
    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    // Setter for publishDate
    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

}
