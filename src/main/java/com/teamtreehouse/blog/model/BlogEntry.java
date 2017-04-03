package com.teamtreehouse.blog.model;

import com.github.slugify.Slugify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogEntry implements Comparable<BlogEntry> {
    private List<Comment> comments;
    private String body;
    private String title;
    private Date creationDate;
    private String slug;

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
        Slugify slugify = new Slugify();
        slug = slugify.slugify(title);
    }

    public BlogEntry(String title, String body, Date creationDate ){
        comments = new ArrayList<>();
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
        Slugify slugify = new Slugify();
        slug = slugify.slugify(title);
    }
    public boolean addComment(Comment comment) {
        // Store these comments!
        return comments.add(comment);
    }
    public List<Comment> getComments(){
        return comments;
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

    public String getSlug(){
        return slug;
    }
    @Override
    public String toString(){
        return body + " - Created on: " + creationDate;
    }
    @Override
    public int compareTo(BlogEntry other){
        if(equals(other)){
            return 0;
        }

        int dateCmp = creationDate.compareTo(other.creationDate);
        if(dateCmp == 0){
            return slug.compareTo(other.slug);
        }

        return dateCmp;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlogEntry)) return false;

        BlogEntry entry = (BlogEntry) o;

        if (comments != entry.comments) return false;
        if (body != entry.body) return false;
        if (!title.equals(entry.title)) return false;
        return creationDate.equals(entry.creationDate);

    }
}
