package com.example.gopalawasthi.gituserdata;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gopal Awasthi on 17-03-2018.
 */

public interface MyInterface {
    @GET("users/{uname}")
    Call <UserResponse> getUserResponse( @Path("uname") String name);

    @GET("users/{uname}/followers")
    Call <ArrayList<Followers>> getfollowers(@Path("uname") String name);

    @GET ("users/{uname}/following")
    Call<ArrayList<Followers>> getfollowing(@Path("uname") String name);
}
