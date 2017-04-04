package com.teamtreehouse.blog.dao;

import com.github.slugify.Slugify;
import com.teamtreehouse.blog.model.BlogEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Blog implements BlogDao {
    private List<BlogEntry> entries;
    public Blog(){
        entries = new ArrayList<>();
    }
    @Override
    public boolean addEntry(BlogEntry blogEntry) {
        boolean result = entries.add(blogEntry);
        Collections.reverse(entries);
        return result;
    }

    @Override
    public List<BlogEntry> findAllEntries() {
        return new ArrayList<BlogEntry>(entries);
    }

    @Override
    public BlogEntry findEntryBySlug(String slug) {
        return (entries.stream()
                .filter(entry -> entry.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public boolean delete(BlogEntry blogEntry) {
        return entries.remove(blogEntry);
    }
}
