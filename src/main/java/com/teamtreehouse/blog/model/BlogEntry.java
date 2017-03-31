package com.teamtreehouse.blog.model;

import java.util.Date;
import java.util.List;

public class BlogEntry {
    private List<Comment> comments;
    private String body;
    private String title;
    private Date creationDate;
    public BlogEntry(String title, String body, Date creationDate ){
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
    }
    public boolean addComment(Comment comment) {
        // Store these comments!
        return comments.add(comment);
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString(){
        return body + " - Created on: " + creationDate;
    }
}
