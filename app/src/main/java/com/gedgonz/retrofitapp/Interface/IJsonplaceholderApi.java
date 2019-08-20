package com.gedgonz.retrofitapp.Interface;

import com.gedgonz.retrofitapp.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IJsonplaceholderApi {

    @GET("posts")
    Call<List<Posts>> getPosts();
}
