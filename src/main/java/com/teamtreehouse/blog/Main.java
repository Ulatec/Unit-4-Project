package com.teamtreehouse.blog;

import com.teamtreehouse.blog.dao.Blog;
import com.teamtreehouse.blog.model.BlogEntry;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by mark on 3/30/17.
 */
public class Main {
    public static void main(String[] args) {
        //get("/", (req,res) -> "Hello");
        //staticFiles.location("")
        Blog blog = new Blog();
        blog.addEntry(new BlogEntry("title1", "content1", new Date()));
        blog.addEntry(new BlogEntry("title2", "content2", new Date()));
        get("/", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    model.put("posts", blog.findAllEntries());
                    return new ModelAndView(model, "index.hbs");
                }, new HandlebarsTemplateEngine());
    }
}
