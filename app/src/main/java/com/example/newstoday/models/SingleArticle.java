package com.example.newstoday.models;

public class SingleArticle {
    private String title;
    private String description;
    private String urlToImage;
    private String author;
    private String url;

    public SingleArticle(String title, String description, String urlToImage, String author,String url) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.author = author;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
