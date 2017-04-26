package com.CCS.LoginWithWeCards.API;

import com.CCS.LoginWithWeCards.Model.LoginRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;


public class RestClient {

    private static GitApiInterface gitApiInterface;

    public static GitApiInterface getClient() {
        if (gitApiInterface == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(urls.LIVE_BASIC_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            gitApiInterface = client.create(GitApiInterface.class);
        }
        return gitApiInterface;
    }

//    public interface GitApiInterface {
//        @POST("open/user/login")
//        Call<ResponseBody> login(@Body LoginRequest body);
//    }


    public interface GitApiInterface {
        @POST("open/user/login")
        Call<ResponseBody> login(@Body LoginRequest body);
    }
}
