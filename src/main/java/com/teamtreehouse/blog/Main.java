package com.teamtreehouse.blog;

import com.teamtreehouse.blog.dao.Blog;
import com.teamtreehouse.blog.model.BlogEntries;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    private static final String IS_ADMIN_KEY = "is_admin";

    public static void main(String[] args) {
        staticFileLocation("/public");

        Blog blog = new Blog();
        for(BlogEntry entry : BlogEntries.load()){
            blog.addEntry(entry);
        }

        before("/new", (req, res) -> {
            if (!isAdmin(req)) {
                res.redirect("/password");
                halt();
            }
        });
        before("/edit/:post", (req, res) -> {
            if (!isAdmin(req)) {
                res.redirect("/password");
                halt();
            }
        });

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
            BlogEntry newEntry = new BlogEntry(req.queryParams("title"), req.queryParams("entry"), new Date());
            blog.addEntry(newEntry);
            res.redirect("/");
            return null;
        });
        get("/detail/:post", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("post", blog.findEntryBySlug(req.params("post")));
            model.put("comments", blog.findEntryBySlug(req.params("post")).getComments());
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/edit/:post", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("post", blog.findEntryBySlug(req.params("post")));
            return new ModelAndView(model, "edit.hbs");
        }, new HandlebarsTemplateEngine());
        post("/edit/:post", (req, res) -> {
            BlogEntry postToEdit = blog.findEntryBySlug(req.params("post"));
            postToEdit.setBody(req.queryParams("entry"));
            postToEdit.setTitle(req.queryParams("title"));
            res.redirect("/detail/" + postToEdit.getSlug());
            return null;
        });
        post("/edit/:post/delete", (req, res) -> {
            BlogEntry postToDelete = blog.findEntryBySlug(req.params("post"));
            blog.delete(postToDelete);
            res.redirect("/");
            return null;
        });
        post("/detail/:post/comment", (req, res) -> {
            BlogEntry postToAddComment = blog.findEntryBySlug(req.params("post"));
            postToAddComment.addComment(new Comment(req.queryParams("name"), req.queryParams("comment"), new Date()));
            res.redirect("/detail/" + postToAddComment.getSlug());
            return null;
        });
        get("/password", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "password.hbs");
        }, new HandlebarsTemplateEngine());
        post("/password", (req, res) -> {
            String password = req.queryParams("password");
            if (password.equals("admin")) {
                req.session().attribute(IS_ADMIN_KEY, true);
                res.redirect("/");
            } else {
                res.redirect("/password");
            }
            return null;
        });
    }
    private static boolean isAdmin(Request req) {
        if (req.session(false) == null) {
            return false;
        }
        if (!req.session().attributes().contains(IS_ADMIN_KEY)) {
            return false;
        }
        ;
        return (Boolean) req.session().attribute(IS_ADMIN_KEY);
    }
}
