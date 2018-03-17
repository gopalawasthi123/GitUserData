package com.example.gopalawasthi.gituserdata;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Gopal Awasthi on 17-03-2018.
 */

public interface MyInterface {
    @GET("rohanraarora")
    Call <UserResponse> getUserResponse();
}
