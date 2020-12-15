package com.example.groupprojectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.example.groupprojectandroid.Model.Inventory;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        drawerLayout=findViewById(R.id.drawer_layout);

        recyclerView = findViewById(R.id.productListRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Data.GetInventories(ProductListActivity.this, new VolleyCallback() {

            private ArrayList<Inventory> inventories;
            @Override
            public void onSuccess(Object result) {

                inventories = (ArrayList<Inventory>) result;

                mAdapter = new ProductListAdapter(ProductListActivity.this, inventories);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
    public void ClickMenu(View view){
        HomePage.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        HomePage.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        HomePage.redirectActivity(this,HomePage.class);
    }
    public void ClickProducts(View view){
        recreate();
    }
    public void ClickLogout(View view){
        HomePage.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        HomePage.closeDrawer(drawerLayout);
    }
}