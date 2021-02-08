package com.example.chatapp.WebServices;

import com.example.chatapp.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileAPI {
    public static final String BASE_URL ="https://api.simplifiedcoding.in/course-apis/tinder/" ;

    @GET("profiles")
    public Call<List<Profile>> getProfiles();
}
