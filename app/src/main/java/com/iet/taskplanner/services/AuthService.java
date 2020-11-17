package com.iet.taskplanner.services;

import com.iet.taskplanner.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth")
    Call<Token> getToken(@Body User user);
}
