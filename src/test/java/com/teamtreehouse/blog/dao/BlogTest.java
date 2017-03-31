package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;
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
        System.out.print(newEntry);
    }

//    @Test
//    public void findAllEntries() throws Exception {
//
//    }
//
//    @Test
//    public void findEntryBySlug() throws Exception {
//
//    }

}