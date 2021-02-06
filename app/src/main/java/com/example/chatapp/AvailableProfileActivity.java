package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.chatapp.adapter.AvailableProfileAdapter;
import com.example.chatapp.custom.CustomDialog;
import com.example.chatapp.model.AvailableProfile;
import com.example.chatapp.model.ProfileDAO;


import java.util.ArrayList;
import java.util.List;

public class AvailableProfileActivity extends AppCompatActivity {

    AvailableProfileAdapter adapter;
    RecyclerView recyclerView;
    List<Object> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_profile);
        listData=new ArrayList<>();
        adapter=new AvailableProfileAdapter(this,listData);
        recyclerView=findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addProfilesData();


    }

    private void addProfilesData() {
        listData.addAll(ProfileDAO.createAvailableProfiles(this));
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
