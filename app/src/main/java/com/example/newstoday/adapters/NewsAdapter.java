package com.example.newstoday.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newstoday.R;
import com.example.newstoday.models.SingleArticle;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewholder> {
    private Context mContext;
    private ArrayList<SingleArticle> mNewsArrayList;


    public NewsAdapter(Context context, ArrayList<SingleArticle> newsArrayList) {
        this.mContext = context;
        this.mNewsArrayList = newsArrayList;
    }


    @NonNull
    @Override
    public NewsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_item, parent, false);
        return new NewsViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewholder holder, int position) {
        SingleArticle curItem = mNewsArrayList.get(position);

        String title = curItem.getTitle();
        String description = curItem.getDescription();
        String author = curItem.getAuthor();
        String imgUrl = curItem.getUrlToImage();
        String url = curItem.getUrl();

        holder.title_view.setText(title);
        holder.description_view.setText(description);
        holder.author_view.setText(author);
        Glide.with(mContext).load(imgUrl).into(holder.image_view);

        holder.share_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, url);
                mContext.startActivity(Intent.createChooser(intent, "Share this news with..."));

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(mContext, Uri.parse(url));

            }
        });

    }


    @Override
    public int getItemCount() {
        return mNewsArrayList.size();
    }

    public class NewsViewholder extends RecyclerView.ViewHolder {

        public TextView title_view, author_view,description_view;
        public ImageView image_view;
        public ImageButton share_view;

        public NewsViewholder(@NonNull View itemView) {
            super(itemView);

            title_view = itemView.findViewById(R.id.title_view);
            description_view = itemView.findViewById(R.id.description_view);
            author_view = itemView.findViewById(R.id.author_view);
            image_view = itemView.findViewById(R.id.image_view);
            share_view = itemView.findViewById(R.id.share_view);

        }
    }
}

