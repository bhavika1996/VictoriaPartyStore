package com.example.groupprojectandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.groupprojectandroid.Model.Inventory;
import com.example.groupprojectandroid.Model.Review;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    ArrayList<Inventory> inventories;
    Context context;
    String userId;
    UserDetailsSingleton userDetailsSingleton = UserDetailsSingleton.getInstance();

    ProductListAdapter(Context context, ArrayList<Inventory> inventories) {

        this.inventories = inventories;
        this.userId = userDetailsSingleton.userDetails.get("username");
        this.context = context;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ProductListViewHolder(layoutInflater, parent, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {

        Inventory inventory = inventories.get(position);

        Glide.with(context).load(inventory.getImageUrl()).apply(new RequestOptions().override(600, 500)).into(holder.product_image);

        holder.product_name.setText(inventory.getName());
        holder.product_price.setText("10 CAD");
        holder.product_category.setText(inventory.getCategory());
    }

    @Override
    public int getItemCount() {
        return this.inventories.size();
    }

    static class ProductListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView product_image;
        TextView product_name, product_price, product_category;

        ProductListViewHolder(LayoutInflater inflater, ViewGroup parent, Context context) {

            super(inflater.inflate(R.layout.list_cardview, parent, false));

            product_image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_category = itemView.findViewById(R.id.product_category);
        }

        @Override
        public void onClick(View v) {




        }
    }
}
