package vanwalle.in.vanwale.Retrofit;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiWebservices {

    @FormUrlEncoded
    @POST("register")
    Call<String> getRegistrationRes(@Field("name") String name, @Field("email") String email, @Field("phone") String phone, @Field("password") String password);

    @FormUrlEncoded
    @POST("setstudent")
    Call<String> setStudent(@Field("id") String id,@Field("c") String i,@Field("name") String name, @Field("class") String Class, @Field("section") String section, @Field("age") String age,@Field("rollno") String rollno,@Field("schoolname") String schoolname,@Field("start_Date") String start_date);

    @FormUrlEncoded
    @POST("login")
    Call<String> getLoginRes(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("check")
    Call<String> checkuser(@Field("phone") String phone);

}