package com.example.groupprojectandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        this.userId = userDetailsSingleton.userDetails.get("userId");
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

        if (reviews[position].getUserId().equals(userDetailsSingleton.userDetails.get("userId"))) {

            holder.writerName.setText(userDetailsSingleton.userDetails.get("username"));
            holder.reviewDeleteBtn.setVisibility(ViewGroup.VISIBLE);
        } else {

            holder.writerName.setText("Annonymous");
            holder.reviewDeleteBtn.setVisibility(ViewGroup.GONE);
        }

        holder.reviewId.setText(reviews[position].get_id());
        holder.description.setText(reviews[position].getDescription());

    }

    @Override
    public int getItemCount() {
        return reviews.length;
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView writerName;
        TextView description, reviewId;
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
            reviewId = itemView.findViewById(R.id.reviewId);
            reviewDeleteBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //Toast.makeText(context, reviewId.getText().toString(), Toast.LENGTH_LONG).show();

            Data.DeleteReview(context, inventoryId, reviewId.getText().toString(), new VolleyCallback() {
                        @Override
                        public void onSuccess(Object result) {

                            if ((Boolean) result) {

                                Intent i = new Intent(context, ItemDetailActivity.class);
                                context.startActivity(i);
                                i.putExtra("inventoryId", inventoryId);
                            }
                        }

                        @Override
                        public void onError(VolleyError error) {

                        }
                    }
            );

        }
    }
}
