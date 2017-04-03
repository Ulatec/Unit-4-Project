package com.teamtreehouse.blog.model;

import java.util.Date;

public class Comment {
    private String author;
    private String body;
    private Date creationDate;
    public Comment (String author, String body, Date creationDate){
        this.author = author;
        this.body = body;
        this.creationDate = creationDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }
    public Date getCreationDate() {
        return creationDate;
    }
}
