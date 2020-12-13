package com.example.groupprojectandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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

public class ItemDetailActivity extends AppCompatActivity {

    ImageView productImage;
    TextView productName;
    TextView productCategory;
    TextView productPrice;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.product_name);
        productCategory = findViewById(R.id.product_cat);
        productPrice = findViewById(R.id.product_price);

        recyclerView = findViewById(R.id.reviewRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Data.GetInventory(ItemDetailActivity.this, "5fce8294d4ee0c3a24655d09", new VolleyCallback() {
            @Override
            public void onSuccess(Object result) {

                Inventory inventory = (Inventory) result;

                Glide.with(ItemDetailActivity.this).load(inventory.getImageUrl()).apply(new RequestOptions().override(600, 500)).into(productImage);

                productName.setText("Product Name: " + inventory.getName());
                productCategory.setText("Category: " + inventory.getCategory());
                productPrice.setText("Price: " + "10 CAD");

                //mAdapter = new MyAdapter(myDataset);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onError(VolleyError error) {

                Toast.makeText(ItemDetailActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}