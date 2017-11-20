package comb.example.tito.newsapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

import comb.example.tito.newsapp.Adapter.ListSourceAdapter;
import comb.example.tito.newsapp.Common.Common;
import comb.example.tito.newsapp.Interface.NewsService;
import comb.example.tito.newsapp.Model.Website;
import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    NewsService newsService;
    SpotsDialog alertDialog;
    ListSourceAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //INIT CACHE
        Paper.init(this);



        //Init Servicers
        recyclerView = (RecyclerView) findViewById(R.id.list_sources);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        alertDialog = new SpotsDialog(this);

        //Initialaise newsservice
        newsService= Common.getNewsService();


      loadWebsiteSources(false);
    }

        private void loadWebsiteSources(boolean isRefreshed)
    {  if(!isRefreshed) {
        String cache = Paper.book().read("cache");
        if (cache != null && !cache.isEmpty()) //if CACHE AVAILABLE
        {

            Website website = new Gson().fromJson(cache,Website.class); //CONVERT CACHE JSON TO OBJECT
            Log.d("tito",website.toString());

            adapter = new ListSourceAdapter(getApplicationContext(), website);
            if(adapter==null)
            {Log.e("error","adapter is null");}
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
            Log.d("tito","setAdapter1");

        } else {
            alertDialog.show();
            //  Fetch new DATA
           newsService.getSources().enqueue(new Callback<Website>() {
               @Override
               public void onResponse(Response<Website> response, Retrofit retrofit) {

                   adapter = new ListSourceAdapter(getBaseContext(), response.body());
                   if(adapter==null)
                   {Log.e("error","adapter is null");}
                   adapter.notifyDataSetChanged();
                   recyclerView.setAdapter(adapter);
                   Log.d("tito","setAdapter2");
                   alertDialog.dismiss();

                   //Write to cache
                   Paper.book().write("cache", new Gson().toJson(response.body()));
               }



               @Override
               public void onFailure(Throwable t) {

               }
           });


        }
    }
        else{
        //  Fetch new DATA
        newsService.getSources().enqueue(new Callback<Website>() {
            @Override
            public void onResponse(Response<Website> response, Retrofit retrofit) {
                adapter = new ListSourceAdapter(getBaseContext(), response.body());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                Log.d("tito","setAdapter3");

                //Write to cache
                Paper.book().write("cache", new Gson().toJson(response.body()));

                //dismiss refreshing

            }
            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    }
    }

