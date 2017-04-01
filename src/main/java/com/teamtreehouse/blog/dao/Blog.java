package com.teamtreehouse.blog.dao;

import com.github.slugify.Slugify;
import com.teamtreehouse.blog.model.BlogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 3/30/17.
 */
public class Blog implements BlogDao {
    private List<BlogEntry> entries;
    public Blog(){
        entries = new ArrayList<>();
    }
    @Override
    public boolean addEntry(BlogEntry blogEntry) {
        return entries.add(blogEntry);
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
}
