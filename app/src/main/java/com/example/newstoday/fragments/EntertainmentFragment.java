package com.example.newstoday.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newstoday.R;
import com.example.newstoday.adapters.NewsAdapter;
import com.example.newstoday.data.APIService;
import com.example.newstoday.data.RetrofitInstance;
import com.example.newstoday.models.Articles;
import com.example.newstoday.models.SingleArticle;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntertainmentFragment extends Fragment {

    public EntertainmentFragment() {
    }

    private ArrayList<SingleArticle> allSingleArticleArrayList;
    private RecyclerView entertainment_RV;
    private NewsAdapter entertainment_adapter;
    private String API_KEY = "a13433a845cc4512854b4634c697888a";
    private  String country = "in";
    private String category = "entertainment";
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.entertainment_fragment,container,false);
        entertainment_RV = view.findViewById(R.id.entertainment_RV);
        allSingleArticleArrayList = new ArrayList<>();

        progressBar = view.findViewById(R.id.entertainment_PB);
        progressBar.setVisibility(View.VISIBLE);

        swipeRefreshLayout = view.findViewById(R.id.entertainment_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!allSingleArticleArrayList.isEmpty()) {
                    progressBar.setVisibility(View.VISIBLE);
                    allSingleArticleArrayList.clear();
                    getData();

                }
            }
        });

        getData();

        return view;
    }


    private void prepareRecyclerView() {
        entertainment_RV.setHasFixedSize(true);
        entertainment_RV.setLayoutManager(new LinearLayoutManager(getContext()));
        entertainment_adapter = new NewsAdapter(getContext(), allSingleArticleArrayList);
        entertainment_RV.setAdapter(entertainment_adapter);
        entertainment_adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void getData() {

        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<Articles> call = apiService.getCategoryNews(country,category,API_KEY,100);

        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                Articles articles = response.body();

                ArrayList<SingleArticle> allSingleArticles = articles.getSingleArticleArrayList();

                for (int i = 0; i < allSingleArticles.size(); i++) {
                    allSingleArticleArrayList.add(new SingleArticle(allSingleArticles.get(i).getTitle(), allSingleArticles.get(i).getDescription(), allSingleArticles.get(i).getUrlToImage(), allSingleArticles.get(i).getAuthor(),allSingleArticles.get(i).getUrl()));
                }
                Collections.shuffle(allSingleArticleArrayList);
                progressBar.setVisibility(View.GONE);
                prepareRecyclerView();
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Failed to load data...", Toast.LENGTH_LONG).show();
            }
        });
    }
}
