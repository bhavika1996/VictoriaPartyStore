package com.example.groupprojectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.groupprojectandroid.Model.Inventory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ItemDetailActivity extends AppCompatActivity {

    ImageView productImage;
    TextView productName;
    TextView productCategory;
    TextView productPrice;
    Button addReviewButton;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Inventory inventory;
    String inventoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.product_name);
        productCategory = findViewById(R.id.product_cat);
        productPrice = findViewById(R.id.product_price);
        addReviewButton = findViewById(R.id.addReviewButton);

        recyclerView = findViewById(R.id.reviewRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        inventoryId = "5fcd7dd578f8160449c9a004";
        Data.GetInventory(ItemDetailActivity.this, inventoryId, new VolleyCallback() {
            @Override
            public void onSuccess(Object result) {

                inventory = (Inventory) result;

                Glide.with(ItemDetailActivity.this).load(inventory.getImageUrl()).apply(new RequestOptions().override(600, 500)).into(productImage);

                productName.setText("Product Name: " + inventory.getName());
                productCategory.setText("Category: " + inventory.getCategory());
                productPrice.setText("Price: " + "10 CAD");

                UserDetailsSingleton userDetailsSingleton = UserDetailsSingleton.getInstance();
                mAdapter = new ReviewAdapter(ItemDetailActivity.this, inventory.getReviews(), inventoryId, userDetailsSingleton.userDetails.get("username"));
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onError(VolleyError error) {

                Toast.makeText(ItemDetailActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ItemDetailActivity.this, AddReviewActivity.class);
                startActivity(i);
            }
        });
    }
}