package com.example.groupprojectandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.groupprojectandroid.Model.Review;

import static com.example.groupprojectandroid.ReviewAdapter.*;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    Review[] reviews;
    String username;
    String userId;
    String inventoryId;
    Context context;
    UserDetailsSingleton userDetailsSingleton = UserDetailsSingleton.getInstance();

    ReviewAdapter(Context context, Review[] reviews, String inventoryId, String username) {

        this.reviews = reviews;
        this.username = username;
        this.userId = userDetailsSingleton.userDetails.get("username");
        this.inventoryId = inventoryId;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ReviewViewHolder(layoutInflater, parent, context, inventoryId);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        if (reviews[position].getUserId().equals(userId)) {

            holder.writerName.setText(username);
            holder.reviewDeleteBtn.setVisibility(ViewGroup.VISIBLE);
            holder.reviewDeleteBtn.setHint(reviews[position].get_id());
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

    static class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView writerName;
        TextView description;
        Button reviewDeleteBtn;
        Context context;
        String inventoryId;

        ReviewViewHolder(LayoutInflater inflater, ViewGroup parent, Context context, String inventoryId) {

            super(inflater.inflate(R.layout.review_cardview, parent, false));

            this.context = context;
            this.inventoryId = inventoryId;

            writerName = itemView.findViewById(R.id.writerName);
            description = itemView.findViewById(R.id.reviewDescription);
            reviewDeleteBtn = itemView.findViewById(R.id.reviewDeleteButton);
            reviewDeleteBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Data.DeleteReview(context, inventoryId, reviewDeleteBtn.getHint().toString(), new VolleyCallback() {
                        @Override
                        public void onSuccess(Object result) {

                        }

                        @Override
                        public void onError(VolleyError error) {

                        }
                    }
            );

        }
    }
}
