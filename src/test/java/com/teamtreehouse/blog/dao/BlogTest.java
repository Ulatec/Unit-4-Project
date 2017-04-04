package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by mark on 3/30/17.
 */
public class BlogTest {
    private Blog blog;
    @Before
    public void setUp() throws Exception{
        blog = new Blog();
    }

    @Test
    public void addEntry() throws Exception {
        BlogEntry newEntry = new BlogEntry("first entry", "This is my first blog entry.", new Date());
        blog.addEntry(newEntry);
    }
    @Test
    public void addComment() throws Exception {
        BlogEntry newEntry = new BlogEntry("first entry", "This is my first blog entry.", new Date());
        blog.addEntry(newEntry);
        newEntry.addComment(new Comment("me", "This is my comment", new Date()));
    }


}