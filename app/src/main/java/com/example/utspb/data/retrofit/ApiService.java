package com.example.utspb.data.retrofit;

import com.example.utspb.data.response.ItemsItem;
import com.example.utspb.data.response.ResponseUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/users")
    Call<ResponseUser> getUser(@Query("q") String query);

    @GET("users/{username}")
    Call<ItemsItem> getItem(@Path("username") String username);
    }



