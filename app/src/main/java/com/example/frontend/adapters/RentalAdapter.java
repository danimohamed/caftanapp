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
import com.example.frontend.models.Rental;
import com.google.android.material.button.MaterialButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Adapter for displaying rental items in RecyclerView
 */
public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.RentalViewHolder> {

    private Context context;
    private List<Rental> rentalList;
    private OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Rental rental, int position);
    }

    public RentalAdapter(Context context, List<Rental> rentalList) {
        this.context = context;
        this.rentalList = rentalList;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    @NonNull
    @Override
    public RentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rental, parent, false);
        return new RentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalViewHolder holder, int position) {
        Rental rental = rentalList.get(position);

        if (rental == null) {
            return;
        }

        // Set customer name
        String customerName = rental.getCustomerName() != null ? rental.getCustomerName() : "Unknown";
        holder.tvCustomerName.setText("Customer: " + customerName);

        // Format dates properly
        String startDate = formatDate(rental.getStartDate());
        String endDate = formatDate(rental.getEndDate());

        if (!startDate.isEmpty() && !endDate.isEmpty()) {
            holder.tvDates.setText(startDate + " to " + endDate);
        } else {
            holder.tvDates.setText("Dates not available");
        }

        // Format price
        if (rental.getTotalPrice() != null && !rental.getTotalPrice().isEmpty()) {
            try {
                double price = Double.parseDouble(rental.getTotalPrice());
                holder.tvPrice.setText(String.format(Locale.US, "Total: %.2f DH", price));
            } catch (NumberFormatException e) {
                holder.tvPrice.setText("Total: " + rental.getTotalPrice() + " DH");
            }
        } else {
            holder.tvPrice.setText("Price not available");
        }

        // Set caftan info if available
        if (rental.getCaftan() != null) {
            String caftanName = rental.getCaftan().getName() != null ? rental.getCaftan().getName() : "Caftan";
            holder.tvCaftanName.setText(caftanName);

            // Load caftan image
            String imageUrl = rental.getCaftan().getImageUrl();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Glide.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(holder.ivCaftanImage);
            } else {
                holder.ivCaftanImage.setImageResource(R.drawable.ic_launcher_background);
            }
        } else {
            holder.tvCaftanName.setText("Caftan #" + rental.getCaftanId());
            holder.ivCaftanImage.setImageResource(R.drawable.ic_launcher_background);
        }

        // Set delete button click listener
        holder.btnDelete.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                int adapterPosition = holder.getBindingAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    deleteClickListener.onDeleteClick(rental, adapterPosition);
                }
            }
        });
    }

    /**
     * Format date string from ISO format to readable format
     * Converts "2025-12-23T00:00:00.000000Z" to "Dec 23, 2025"
     */
    private String formatDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return "";
        }

        try {
            // Try to parse different date formats
            SimpleDateFormat inputFormat;

            // Check if it contains time (ISO 8601 format)
            if (dateString.contains("T")) {
                inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            } else {
                inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            }

            Date date = inputFormat.parse(dateString);

            // Format to readable date
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
            return date != null ? outputFormat.format(date) : dateString;

        } catch (ParseException e) {
            // If parsing fails, try to extract just the date part
            if (dateString.length() >= 10) {
                String datePart = dateString.substring(0, 10);
                try {
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    Date date = inputFormat.parse(datePart);
                    SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                    return date != null ? outputFormat.format(date) : datePart;
                } catch (ParseException ex) {
                    return datePart;
                }
            }
            return dateString;
        }
    }

    @Override
    public int getItemCount() {
        return rentalList != null ? rentalList.size() : 0;
    }

    /**
     * ViewHolder for rental items
     */
    public static class RentalViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCaftanImage;
        TextView tvCaftanName;
        TextView tvCustomerName;
        TextView tvDates;
        TextView tvPrice;
        MaterialButton btnDelete;

        public RentalViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCaftanImage = itemView.findViewById(R.id.ivRentalCaftanImage);
            tvCaftanName = itemView.findViewById(R.id.tvRentalCaftanName);
            tvCustomerName = itemView.findViewById(R.id.tvRentalCustomerName);
            tvDates = itemView.findViewById(R.id.tvRentalDates);
            tvPrice = itemView.findViewById(R.id.tvRentalPrice);
            btnDelete = itemView.findViewById(R.id.btnDeleteRental);
        }
    }
}

