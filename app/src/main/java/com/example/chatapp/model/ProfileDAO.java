package com.example.chatapp.model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.chatapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileDAO {

    private static final String BASE_URL ="https://api.simplifiedcoding.in/course-apis/tinder/" ;

    public static List<Profile>  createProfiles(Context context) {
        final List<Profile>[] profileList = new List[]{new ArrayList<>()};
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ProfileAPI profileAPI=retrofit.create(ProfileAPI.class);
        Call<List<Profile>> call=profileAPI.getProfiles();
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                try {
                    profileList[0] =response.body();

                }
                catch (Exception e){
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Log.d("Failure",t.toString());

            }
        });
        /*
        Resources res = context.getResources();
        String[] full_names = res.getStringArray(R.array.full_names);
        int[] ages=new int[]{20,20,19,22,26,25,24,21};
        String[] imagesUrl=new String[]{
                "emma_watson","emma_watson","emma_watson","emma_watson","emma_watson",
                "emma_watson","emma_watson","emma_watson"
        };
        for (int i=0; i<full_names.length; i++){
            profileList.add(new Profile(full_names[i],ages[i],imagesUrl[i],true));

        }
         */




        return profileList[0];
    }
    public static List<AvailableProfile> createAvailableProfiles(Context context){

        Resources res = context.getResources();
        List<AvailableProfile> availableProfiles=new ArrayList<>();
        String[] textArray = res.getStringArray(R.array.text_array);
        for (int i=0;i<textArray.length;i++) {
            Profile profile=getRandomProfile(createProfiles(context));
            if(profile!=null) {
                availableProfiles.add(new AvailableProfile(profile,textArray[i]));

            }
                  }
        return availableProfiles;
    }

    private static Profile getRandomProfile(List<Profile> profiles) {
        Random random = new Random();
        int index = random.nextInt(profiles.size());
        return profiles.get(index);
    }

}
