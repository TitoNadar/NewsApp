package comb.example.tito.newsapp.Remote;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Tito on 18/10/2017.
 */

public class RetrofitClient {
    private static Retrofit retrofit=null;
    public static Retrofit getclient(String baseUrl)
    {
      if(retrofit==null){
          retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
      }
      return retrofit;
    }
}
