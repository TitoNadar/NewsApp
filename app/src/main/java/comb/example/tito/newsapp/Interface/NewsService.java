package comb.example.tito.newsapp.Interface;

import comb.example.tito.newsapp.Model.News;
import comb.example.tito.newsapp.Model.Website;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Tito on 18/10/2017.
 */

public interface NewsService {


    @GET("v1/sources?language=en")
    Call<Website> getSources();

    @GET("")
    Call<News> getNewestArticles(@Url String url);
}
