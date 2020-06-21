package vanwalle.in.vanwale.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aniket on 23-Jan-17.
 */
public class ApiHelperClass {
  // public static final String BASE_URL = "http://websitesupport24x7.com/olso/admin/web_service/";
   public static final String BASE_URL="http://10.0.2.2:3000/";
    private static Retrofit retrofit = null;
    public static final int REQUEST_TIMEOUT = 30000;

    public static Retrofit getClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors
// add logging as last interceptor
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        httpClient.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static ApiWebservices getApiService() {
        return getClient().create(ApiWebservices.class);
    }
}
