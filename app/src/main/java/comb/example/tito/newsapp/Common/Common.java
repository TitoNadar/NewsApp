package comb.example.tito.newsapp.Common;

import comb.example.tito.newsapp.Interface.IconIdeaService;
import comb.example.tito.newsapp.Interface.NewsService;
import comb.example.tito.newsapp.Remote.IconBetterIdeaClient;
import comb.example.tito.newsapp.Remote.RetrofitClient;

/**
 * Created by Tito on 18/10/2017.
 */

public class Common {
    private static String Base_Url="https://newsapi.org/";
  public static final String API_KEY="44aa4bd036d74cd9a38c0c5f485c3557";

    public static NewsService getNewsService()
    {
        return RetrofitClient.getclient(Base_Url).create(NewsService.class);
    }
    public static IconIdeaService getIconService()
    {
        return IconBetterIdeaClient.getclient().create(IconIdeaService.class);
    }
    public static String getApiUrl(String source,String sortBy,String API_KEY)
    {
        StringBuilder stringBuilder=new StringBuilder(" https://newsapi.org/v1/articles?source=");
        stringBuilder.append(source).append("&sortBy").append(sortBy).append("&apiKey=").append(API_KEY);
        return stringBuilder.toString();
    }

}
