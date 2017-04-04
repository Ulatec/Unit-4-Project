package com.teamtreehouse.blog.model;


import java.util.Date;

public class BlogEntries {
    public static BlogEntry[] load(){
        return new BlogEntry[] {
                new BlogEntry("Hot dog flavored water", "Once upon a time, at a truck-stop, hot dog flavored water was not being sold...", new Date()),
                new BlogEntry("The power of the Nokia 3310","...can be run on a Nokia 3310 that someone forgot in a Tokyo subway station after heroically finding an electrical outlet to charge it with.", new Date()),
                new BlogEntry("Time", "Time is one of the few certanties in life. It is guaranteed to always be passing.", new Date())
        };
    }
}
