package com.example.administrador.retrofitaula12;

import com.example.administrador.retrofitaula12.UserClass;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoint {
    @GET("posts/{id}")
    Call<UserClass> ObterPosts(@Path("id") int userID);
}