package com.example.frontend.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frontend.R;
import com.example.frontend.models.Caftan;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for RecyclerView to display list of caftans
 * Handles the binding of caftan data to card views
 */
public class CaftanAdapter extends RecyclerView.Adapter<CaftanAdapter.CaftanViewHolder> {

    // Context and data
    private Context context;
    private List<Caftan> caftanList;
    private OnItemClickListener listener;

    /**
     * Interface for handling item clicks
     */
    public interface OnItemClickListener {
        void onItemClick(Caftan caftan);
    }

    /**
     * Constructor
     * @param context - Application context
     */
    public CaftanAdapter(Context context) {
        this.context = context;
        this.caftanList = new ArrayList<>();
    }

    /**
     * Set click listener
     * @param listener - Click listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Set caftan list data
     * @param caftanList - List of caftans
     */
    public void setCaftanList(List<Caftan> caftanList) {
        this.caftanList = caftanList;
        notifyDataSetChanged();  // Refresh the list
    }

    @NonNull
    @Override
    public CaftanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_caftan, parent, false);
        return new CaftanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaftanViewHolder holder, int position) {
        // Get caftan at this position
        Caftan caftan = caftanList.get(position);

        // Bind data to views
        holder.tvName.setText(caftan.getName());
        holder.tvSize.setText("Size: " + caftan.getSize());
        holder.tvPrice.setText(caftan.getPrice() + " DH/day");


        // Load image using Glide
        String imageUrl = caftan.getImageUrl();
        android.util.Log.d("CaftanAdapter", "Loading image for " + caftan.getName() + ": " + imageUrl);

        // Load image with Glide
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)  // Placeholder while loading
                .error(R.drawable.ic_launcher_background)  // Error image if load fails
                .into(holder.ivImage);

        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(caftan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return caftanList.size();
    }

    /**
     * ViewHolder class for caching view references
     */
    public static class CaftanViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvName;
        TextView tvSize;
        TextView tvPrice;

        public CaftanViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views
            ivImage = itemView.findViewById(R.id.ivCaftanImage);
            tvName = itemView.findViewById(R.id.tvCaftanName);
            tvSize = itemView.findViewById(R.id.tvCaftanSize);
            tvPrice = itemView.findViewById(R.id.tvCaftanPrice);
        }
    }
}

