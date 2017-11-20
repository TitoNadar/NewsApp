package comb.example.tito.newsapp.Interface;

import java.net.URL;
import java.util.List;

import comb.example.tito.newsapp.Model.IconBetterIdea;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Tito on 18/10/2017.
 */

public interface IconIdeaService
{
       @GET
       Call<IconBetterIdea> getIconUrl(@Url String url);
}
