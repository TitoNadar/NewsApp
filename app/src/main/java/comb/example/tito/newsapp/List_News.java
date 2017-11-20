package comb.example.tito.newsapp;

import android.content.Intent;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import comb.example.tito.newsapp.Adapter.ListNewsAdapter;
import comb.example.tito.newsapp.Common.Common;
import comb.example.tito.newsapp.Interface.NewsService;
import comb.example.tito.newsapp.Model.Articles;
import comb.example.tito.newsapp.Model.News;
import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class List_News extends AppCompatActivity {
    KenBurnsView kenBurnsView;
    SpotsDialog spotsDialog;
    DiagonalLayout diagonalLayout;
    TextView top_author, top_title;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    NewsService newsService;
    RecyclerView.LayoutManager layoutManager;
    String source = "", sortBy = "", webHotUrl = "";
    ListNewsAdapter listNewsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__news);

        newsService=Common.getNewsService();

        //dialog
        spotsDialog = new SpotsDialog(this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(source, true);
            }
        });
        diagonalLayout = (DiagonalLayout) findViewById(R.id.daigonal_layout);

        diagonalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),DetailedActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     //Error if not added
                intent.putExtra("webURL",webHotUrl);
             startActivity(intent);


            }
        });


        top_author = (TextView) findViewById(R.id.top_author);
        top_title = (TextView) findViewById(R.id.top_title);
        kenBurnsView = (KenBurnsView) findViewById(R.id.top_image);

        //RecylcerView
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Intent
        if (getIntent() != null) {
            sortBy = getIntent().getStringExtra("sortBy");
            source = getIntent().getStringExtra("source");
            if (!source.isEmpty() && !sortBy.isEmpty()) {

                loadNews(source, false);

            }



            }
    }

    private void loadNews(String source, boolean isRefreshed) {
        if (!isRefreshed) {
            spotsDialog.show();
            newsService.getNewestArticles(Common.getApiUrl(source,sortBy,Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Response<News> response, Retrofit retrofit) {
                    spotsDialog.dismiss();
                    Picasso.with(getBaseContext()).load(response.body().getArticles().get(0).getUrlToImage()).into(kenBurnsView);
                    top_title.setText(response.body().getArticles().get(0).getTitle());
                    top_author.setText(response.body().getArticles().get(0).getAuthor());
                    webHotUrl=response.body().getArticles().get(0).getUrl();

                    //Now v have already used the first article so while loading remaining aerticles u need to remove it
                    List<Articles>  remainingArticles=response.body().getArticles();
                    remainingArticles.remove(0);
                    listNewsAdapter=new ListNewsAdapter(remainingArticles,getBaseContext());
                    recyclerView.setAdapter(listNewsAdapter);
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });

        }
        else
        {
            spotsDialog.show();
            newsService.getNewestArticles(Common.getApiUrl(source,sortBy,Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Response<News> response, Retrofit retrofit) {
                    spotsDialog.dismiss();
                    Picasso.with(getBaseContext()).load(response.body().getArticles().get(0).getUrlToImage()).into(kenBurnsView);
                    top_title.setText(response.body().getArticles().get(0).getTitle());
                    top_author.setText(response.body().getArticles().get(0).getAuthor());
                    webHotUrl=response.body().getArticles().get(0).getUrl();

                    //Now v have already used the first article so while loading remaining aerticles u need to remove it
                    List<Articles>  remainingArticles=response.body().getArticles();
                    remainingArticles.remove(0);
                    listNewsAdapter=new ListNewsAdapter(remainingArticles,getBaseContext());
                    recyclerView.setAdapter(listNewsAdapter);
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}