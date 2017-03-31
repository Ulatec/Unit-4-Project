package com.teamtreehouse.blog.model;

public class Comment {
    private String author;
    private String body;
    public Comment (String author, String body){
        this.author = author;
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }
}
