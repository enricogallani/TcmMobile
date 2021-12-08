package com.example.front.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.front.db.entity.Client;
import com.example.front.db.helper.ClientHelper;
import com.example.front.ui.adapter.ClientAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;

import java.util.List;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(ClientActivity.this, CreateClientActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Client> clients = new ClientHelper(ClientActivity.this).getAll();
        ClientAdapter adapter = new ClientAdapter(clients);
        adapter.setOnItemClickListener((view, position) -> {
            final Client food = clients.get(position);

            //Intent intent = new Intent(RestaurantActivity.this, OrderActivity.class);
            //intent.putExtra("restaurant", restaurant);
            //intent.putExtra("food", food);

            //startActivity(intent);
        });

        RecyclerView rv = findViewById(R.id.listView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}