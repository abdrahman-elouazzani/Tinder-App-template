package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.chatapp.WebServices.ProfileAPI;
import com.example.chatapp.adapter.AvailableProfileAdapter;
import com.example.chatapp.adapter.ProfileAdapter;
import com.example.chatapp.custom.CustomDialog;
import com.example.chatapp.model.AvailableProfile;
import com.example.chatapp.model.Profile;
import com.example.chatapp.model.ProfileDAO;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AvailableProfileActivity extends AppCompatActivity {

    AvailableProfileAdapter adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    List<Object> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_profile);
        recyclerView=findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        getWebServiceResponseData();
        setUpProgressDialog();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
    /*
    private void addProfilesData() {
        listData.addAll(new ProfileDAO().createAvailableProfiles(this));
    }

     */
    // call the api to get profiles data
    private void getWebServiceResponseData() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ProfileAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ProfileAPI profileAPI=retrofit.create(ProfileAPI.class);
        Call<List<Profile>> call=profileAPI.getProfiles();
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {

                List<Profile> profileList=new ArrayList<>(response.body());
                Resources res = getApplicationContext().getResources();
                List<AvailableProfile> availableProfiles=new ArrayList<>();
                String[] textArray = res.getStringArray(R.array.text_array);
                for (int i=0;i<profileList.size();i++) {
                    Profile profile=getRandomProfile(profileList);
                    if(profile!=null) {
                        availableProfiles.add(new AvailableProfile(profile,textArray[i]));
                    }
                }
                adapter=new AvailableProfileAdapter(AvailableProfileActivity.this,availableProfiles);
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                recyclerView.setAdapter(adapter);

            }


            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Log.d("Failure",t.toString());

            }
        });
    }
    private Profile getRandomProfile(List<Profile> profiles) {
        Random random = new Random();
        int index = random.nextInt(profiles.size());
        return profiles.get(index);
    }
    // set up progress dialog
    private void setUpProgressDialog(){
        progressDialog = new ProgressDialog(AvailableProfileActivity.this);
        progressDialog.setMessage("Fetching profiles data");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                CustomDialog customDialog=new CustomDialog();
                customDialog.createDialog(this);
                customDialog.buttonRate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }

                });
                customDialog.buttonClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         customDialog.alertDialog.dismiss();
                         NavUtils.navigateUpFromSameTask(getParent());

                    }
                });
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
