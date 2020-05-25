package com.goldenappstudio.service_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServiceProviderRecycler extends RecyclerView.Adapter<ServiceProviderRecycler.ViewHolder> {
    View view;
    Context context;
    List<ServiceProvider> MainImageUploadInfoList;
    public static String SERVICE_PROVIDER_UID;
    public static String SERVICE_PROVIDER_PHONE_NO;

    public ServiceProviderRecycler(Context context, List<ServiceProvider> TempList) {
        this.MainImageUploadInfoList = TempList;
        this.context = context;
    }

    @Override
    public ServiceProviderRecycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_service_providers, parent, false);
        ServiceProviderRecycler.ViewHolder viewHolder = new ServiceProviderRecycler.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ServiceProvider serviceProvider = MainImageUploadInfoList.get(position);
        holder.ServiceProviderName.setText(serviceProvider.getName());
        holder.ServiceProviderPhone.setText(String.format("+91 %s", serviceProvider.getPhone().substring(3)));
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference gsReference = storage.getReferenceFromUrl("gs://serviceapp-project.appspot.com/sub_service_images/" + SubServiceRecycler.SUB_SERVICE_UID + ".jpg");

        gsReference.getDownloadUrl().addOnSuccessListener(uri -> Glide.with(context).load(uri.toString()).into(holder.ServiceProviderImage)).addOnFailureListener(exception -> {
            // Handle any errors
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ServiceProviderProfile.class);
            SERVICE_PROVIDER_UID = serviceProvider.getUID();
            SERVICE_PROVIDER_PHONE_NO = serviceProvider.getPhone();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        holder.ServiceProviderCall.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + Long.parseLong(serviceProvider.getPhone().substring(3))));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView ServiceProviderName;
        TextView ServiceProviderPhone;
        CircleImageView ServiceProviderImage;
        CircleImageView ServiceProviderCall;

        public ViewHolder(View itemView) {
            super(itemView);
            ServiceProviderName = itemView.findViewById(R.id.sp_recycler_name);
            ServiceProviderImage = itemView.findViewById(R.id.sp_recycler_image);
            ServiceProviderCall = itemView.findViewById(R.id.sp_recycler_call);
            ServiceProviderPhone = itemView.findViewById(R.id.sp_recycler_phone);
        }
    }

    void filterList(ArrayList<ServiceProvider> filterdNames) {
        this.MainImageUploadInfoList = filterdNames;
        notifyDataSetChanged();
    }
}
