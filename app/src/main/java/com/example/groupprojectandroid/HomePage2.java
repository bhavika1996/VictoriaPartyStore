package com.example.groupprojectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.VolleyError;
import com.example.groupprojectandroid.Model.Inventory;

import java.util.ArrayList;

public class HomePage2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);

        recyclerView = findViewById(R.id.productListRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Data.GetInventories(HomePage2.this, new VolleyCallback() {

            private ArrayList<Inventory> inventories;

            @Override
            public void onSuccess(Object result) {

                inventories = (ArrayList<Inventory>) result;

                mAdapter = new ProductListAdapter(HomePage2.this, inventories);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
}