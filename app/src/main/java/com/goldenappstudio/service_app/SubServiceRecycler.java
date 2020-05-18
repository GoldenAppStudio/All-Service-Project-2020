package com.goldenappstudio.service_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SubServiceRecycler extends RecyclerView.Adapter<SubServiceRecycler.ViewHolder> {

    View view;
    Context context;
    List<SubService> MainImageUploadInfoList;
    public static String SUB_SERVICE_UID;

    public SubServiceRecycler(Context context, List<SubService> TempList) {
        this.MainImageUploadInfoList = TempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_service_list, parent, false);
        ViewHolder viewHolder = new SubServiceRecycler.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SubService subService = MainImageUploadInfoList.get(position);
        holder.cardView.setCardBackgroundColor(Color.WHITE);
        holder.SubServiceName.setText(subService.getSs_name());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference gsReference = storage.getReferenceFromUrl("gs://serviceapp-project.appspot.com/sub_service_images/" + subService.getUID() + ".jpg");

        gsReference.getDownloadUrl().addOnSuccessListener(uri -> Glide.with(context).load(uri.toString()).into(holder.image)).addOnFailureListener(exception -> {
            // Handle any errors
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), LocationChooser.class);
            SUB_SERVICE_UID = subService.getUID();
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView SubServiceName;
        ImageView image;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            SubServiceName = itemView.findViewById(R.id.sub_service_name);
            image = itemView.findViewById(R.id.sub_service_image);
            cardView = itemView.findViewById(R.id.sub_service_card);
        }
    }

    void filterList(ArrayList<SubService> filterdNames) {
        this.MainImageUploadInfoList = filterdNames;
        notifyDataSetChanged();
    }
}


