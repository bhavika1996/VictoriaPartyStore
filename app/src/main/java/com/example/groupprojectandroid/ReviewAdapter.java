package com.example.groupprojectandroid;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupprojectandroid.Model.Review;

import java.util.ArrayList;

import static com.example.groupprojectandroid.ReviewAdapter.*;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    Review[] reviews;

    ReviewAdapter(Review[] reviews) {

        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ReviewViewHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        if (reviews[position].getUserId().equals(Data.userId)) {

            holder.writerName.setText(Data.name);
            holder.reviewDeleteBtn.setVisibility(ViewGroup.VISIBLE);
        } else {

            holder.writerName.setText("Annonymous");
            holder.reviewDeleteBtn.setVisibility(ViewGroup.GONE);
        }

        holder.description.setText(reviews[position].getDescription());
    }

    @Override
    public int getItemCount() {
        return reviews.length;
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView writerName;
        TextView description;
        Button reviewDeleteBtn;

        ReviewViewHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.review_cardview, parent, false));
            writerName = itemView.findViewById(R.id.writerName);
            description = itemView.findViewById(R.id.reviewDescription);
            reviewDeleteBtn = itemView.findViewById(R.id.reviewDeleteButton);
        }
    }
}
