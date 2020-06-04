package com.goldenappstudio.service_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ViewHolder>{

    View view;
    Context context;
    private List<studio.goldenapp.serviceapp.BusDetails> MainImageUploadInfoList;
    public BusAdapter(Context context, List<studio.goldenapp.serviceapp.BusDetails> TempList) {
        this.MainImageUploadInfoList = TempList;
        this.context = context;
    }

    @Override
    public BusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_items, parent, false);
        BusAdapter.ViewHolder viewHolder = new BusAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final studio.goldenapp.serviceapp.BusDetails studentDetails = MainImageUploadInfoList.get(position);
        holder.name.setText(studentDetails.getName());
        holder.fair.setText(String.format("₹%s", studentDetails.getFair()));
        holder.time.setText(String.format("%s - %s", studentDetails.getStart(), studentDetails.getEnd()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), BusFull.class);
            intent.putExtra("link", studentDetails.getStart() + "/" + studentDetails.getEnd() + "/" + studentDetails.getUid());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView fair;
        public TextView time;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bus_name);
            fair = itemView.findViewById(R.id.bus_fair);
            time = itemView.findViewById(R.id.bus_time);
        }
    }
}
