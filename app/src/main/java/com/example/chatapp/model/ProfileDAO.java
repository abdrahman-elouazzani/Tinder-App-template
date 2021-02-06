package com.example.chatapp.model;

import android.content.Context;
import android.content.res.Resources;

import com.example.chatapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProfileDAO {

    public static List<Profile>  createProfiles(Context context) {
        List<Profile> profileList=new ArrayList<>();
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


        return profileList;
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
