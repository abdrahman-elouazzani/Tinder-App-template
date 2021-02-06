package com.example.chatapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatapp.AvailableProfileActivity;
import com.example.chatapp.R;
import com.example.chatapp.custom.CustomDialog;
import com.example.chatapp.model.AvailableProfile;
import com.example.chatapp.model.Profile;


import java.util.List;

public class AvailableProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM_TYPE_PROFILE =0 ;
    private Context context;
    private List<Object> availableProfiles;
    public AvailableProfileAdapter(@NonNull Context context, List<Object> availableProfiles) {
        this.context=context;
        this.availableProfiles=availableProfiles;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {

            default:
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View listItem= layoutInflater.inflate(R.layout.card_view_available_profile, parent, false);
                AvailableProfileAdapter.ViewHolder viewHolder = new AvailableProfileAdapter.ViewHolder(listItem);
                return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        switch (viewType) {

            default:
                if( availableProfiles.get(position) instanceof Profile){
                    AvailableProfile availableProfile= (AvailableProfile) availableProfiles.get(position);
                    final Profile profile=availableProfile.getProfile();
                    ViewHolder viewHolder= (ViewHolder) holder;
                    viewHolder.fullNameText.setText(""+profile.getFullName());
                    viewHolder.ageText.setText(""+profile.getAge()+"  year");
                    Glide.with(viewHolder.image_pic).load(getImage(profile.getImageUrl())).into(viewHolder.image_pic);
                    viewHolder.textView.setText(availableProfile.getText());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CustomDialog customDialog =new CustomDialog();
                            customDialog.createDialog(context);
                            customDialog.buttonRate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(context.getApplicationContext(), "Please Rate this Place ", Toast.LENGTH_LONG).show();
                                }

                            });
                            customDialog.buttonClose.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    customDialog.alertDialog.dismiss();
                                }
                            });

                        }
                    });

                }
        }


    }

    public int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }

    @Override
    public int getItemCount() {
        return availableProfiles.size();
    }

    @Override
    public int getItemViewType(int position)
    {

            return ITEM_TYPE_PROFILE;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullNameText, ageText, textView;
        ImageView image_pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_pic=itemView.findViewById(R.id.item_image);
            fullNameText=itemView.findViewById(R.id.item_name);
            ageText=itemView.findViewById(R.id.item_age);
            textView=itemView.findViewById(R.id.item_text);

        }
    }


}
