package com.example.newstoday.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Articles {

    @SerializedName("articles")
    @Expose
    private ArrayList<SingleArticle> singleArticleArrayList;

    public Articles(ArrayList<SingleArticle> singleArticleArrayList) {
        this.singleArticleArrayList = singleArticleArrayList;
    }

    public ArrayList<SingleArticle> getSingleArticleArrayList() {
        return singleArticleArrayList;
    }

    public void setSingleArticleArrayList(ArrayList<SingleArticle> singleArticleArrayList) {
        this.singleArticleArrayList = singleArticleArrayList;
    }
}
