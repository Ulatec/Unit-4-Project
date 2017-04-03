package com.teamtreehouse.blog;

import com.teamtreehouse.blog.dao.Blog;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by mark on 3/30/17.
 */
public class Main {
    public static void main(String[] args) {
        //get("/", (req,res) -> "Hello");

        before((req, res)->{
            
        });
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
        get("/detail/:post", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("post", blog.findEntryBySlug(req.params("post")));
            model.put("comments", blog.findEntryBySlug(req.params("post")).getComments());
        return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/edit/:post", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("post", blog.findEntryBySlug(req.params("post")));
            return new ModelAndView(model, "edit.hbs");
        }, new HandlebarsTemplateEngine());
        post("/edit/:post", (req, res) -> {
            String title = req.queryParams("title");
            String body = req.queryParams("entry");
            BlogEntry postToEdit = blog.findEntryBySlug(req.params("post"));
            postToEdit.setBody(body);
            postToEdit.setTitle(title);
            res.redirect("/detail/" + postToEdit.getSlug());
            return null;
        });
        post("/detail/:post/comment", (req,res) -> {
            String name = req.queryParams("name");
            String comment = req.queryParams("comment");
            BlogEntry postToEdit = blog.findEntryBySlug(req.params("post"));
            System.out.printf(postToEdit + " " + name + " " + comment);
            postToEdit.addComment(new Comment(name, comment, new Date()));
            res.redirect("/detail/" + postToEdit.getSlug());
            return null;
        });
    }
}
