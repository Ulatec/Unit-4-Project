package com.teamtreehouse.blog;

import com.teamtreehouse.blog.dao.Blog;
import com.teamtreehouse.blog.model.BlogEntry;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Created by mark on 3/30/17.
 */
public class Main {
    public static void main(String[] args) {
        //get("/", (req,res) -> "Hello");
        staticFileLocation("/public");
        Blog blog = new Blog();
        blog.addEntry(new BlogEntry("this is a slug title", "content1", new Date()));
        blog.addEntry(new BlogEntry("test title is lame", "content2", new Date()));
        get("/", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    model.put("posts", blog.findAllEntries());
                    return new ModelAndView(model, "index.hbs");
                }, new HandlebarsTemplateEngine());
        get("/new", (req, res) -> {
            Map<String, String> model = new HashMap<>();
            return new ModelAndView(model, "new.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new", (req, res) -> {
            String title = req.queryParams("title");
            String body = req.queryParams("entry");
            BlogEntry newEntry = new BlogEntry(title, body, new Date());
            blog.addEntry(newEntry);
            System.out.println("new blogEntry added");
            res.redirect("/");
            return null;
        });
//        post("/detail/:post", (req, res) -> {
//            String slug =
//            return null;
//        });
        get("/detail/:post", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("post", blog.findEntryBySlug(req.params("post")));
        return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/detail/:post/edit", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("post", blog.findEntryBySlug(req.params("post")));
            return new ModelAndView(model, "edit.hbs");
        }, new HandlebarsTemplateEngine());
        post("/detail/:post/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("post", blog.findEntryBySlug(req.params("post")));

            return null;
        });
//        post("/entries", (req,res) -> {
//
//        });
    }
}
