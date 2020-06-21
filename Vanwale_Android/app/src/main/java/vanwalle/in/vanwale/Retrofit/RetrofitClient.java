package vanwalle.in.vanwale.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AAKASH on 09-03-2018.
 */

public class RetrofitClient {
    private static Retrofit retrofit=null;
    public static Retrofit getClient(String baseurl)
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
