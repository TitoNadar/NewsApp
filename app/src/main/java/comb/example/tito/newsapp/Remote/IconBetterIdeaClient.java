package comb.example.tito.newsapp.Remote;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Tito on 18/10/2017.
 */

public class IconBetterIdeaClient {
    private static Retrofit retrofit=null;
    public static Retrofit getclient()
    {
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl("http://icons.better-idea.org/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


}
