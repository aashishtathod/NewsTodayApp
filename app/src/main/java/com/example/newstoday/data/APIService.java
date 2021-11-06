package com.example.newstoday.data;

import com.example.newstoday.models.Articles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("v2/top-headlines")
    Call<Articles> getAllNews(@Query("country") String country,
                              @Query("apiKey") String apiKey,
                              @Query("pageSize") int pagesize);

    @GET("v2/top-headlines")
    Call<Articles> getCategoryNews(@Query("country") String country,
                                   @Query("category") String category,
                                   @Query("apiKey") String apiKey,
                                   @Query("pageSize") int pagesize);

}
