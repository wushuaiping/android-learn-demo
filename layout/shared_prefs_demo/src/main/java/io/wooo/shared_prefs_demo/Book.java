package io.wooo.shared_prefs_demo;

public class Book {

    private String name;

    private String author;

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book(){}

    public Book(String name, String author){
        this.name = name;
        this.author = author;
    }
}
