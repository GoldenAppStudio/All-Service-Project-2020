package com.goldenappstudio.service_app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    View view;
    Context context;
    private List<Service> MainImageUploadInfoList;
    public static String SERVICE_UID;

    public CaptionedImagesAdapter(Context context, List<Service> TempList) {
        this.MainImageUploadInfoList = TempList;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Service service = MainImageUploadInfoList.get(position);

        holder.ServiceNameTextView.setText(service.getService_name());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference gsReference = storage.getReferenceFromUrl("gs://serviceapp-project.appspot.com/service_images/" + service.getUID() +  ".jpg");

        gsReference.getDownloadUrl().addOnSuccessListener(uri -> Glide.with(context).load(uri.toString()).into(holder.image)).addOnFailureListener(exception -> {
            StorageReference reference = storage.getReferenceFromUrl("gs://serviceapp-project.appspot.com/service_images/" + ".jpg");
            reference.getDownloadUrl().addOnSuccessListener(uri -> Glide.with(context).load(uri.toString()).into(holder.image)).addOnFailureListener(e -> {});
        });

        holder.itemView.setOnClickListener(v -> {
            SERVICE_UID = service.getUID();
            if(SERVICE_UID.equals("-M6yKbLSl-cDjaVisXXw")) {
                Intent intent = new Intent(v.getContext(), TravelActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(v.getContext(), SubServiceActivity.class);
                SERVICE_UID = service.getUID();
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView ServiceNameTextView;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);

            ServiceNameTextView = itemView.findViewById(R.id.card_text);
            image = itemView.findViewById(R.id.card_image);
        }
    }

    public void filterList(ArrayList<Service> filterdNames) {
        this.MainImageUploadInfoList = filterdNames;
        notifyDataSetChanged();
    }

}


