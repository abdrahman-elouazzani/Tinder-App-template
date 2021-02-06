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
import com.google.android.gms.ads.AdView;

import java.util.List;

public class AvailableProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_PROFILE = 0;
    private static final int ITEM_TYPE_BANNER_AD = 1;

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
            case ITEM_TYPE_BANNER_AD:
                //Inflate ad banner container
                View bannerLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_ad_row, parent, false);

                //Create View Holder
               AdViewHolder adViewHolder = new AdViewHolder(bannerLayoutView);

                return adViewHolder;
            case ITEM_TYPE_PROFILE:
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
            case ITEM_TYPE_BANNER_AD:
                if (availableProfiles.get(position) instanceof AdView) {
                    AdViewHolder bannerHolder = (AdViewHolder) holder;
                    AdView adView = (AdView) availableProfiles.get(position);
                    ViewGroup adCardView = (ViewGroup) bannerHolder.itemView;
                    if (adCardView.getChildCount() > 0) {
                        adCardView.removeAllViews();
                    }
                    if (adView.getParent() != null) {
                        ((ViewGroup) adView.getParent()).removeView(adView);
                    }

                    // Add the banner ad to the ad view.
                    adCardView.addView(adView);
                }
                break;

            case ITEM_TYPE_PROFILE:
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
        if (position == 0 || availableProfiles.get(position) instanceof AvailableProfile)
        {
            return ITEM_TYPE_PROFILE;
        } else
        {
            return (position % AvailableProfileActivity.ITEMS_PER_AD == 0) ? ITEM_TYPE_BANNER_AD : ITEM_TYPE_PROFILE;
        }
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

    //Banner Ad View Holder
    class AdViewHolder extends RecyclerView.ViewHolder
    {
        AdViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
