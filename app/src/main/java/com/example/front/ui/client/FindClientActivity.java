package com.example.front.ui.client;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.db.entity.Client;
import com.example.front.db.helper.ClientHelper;
import com.example.front.ui.adapter.ClientAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FindClientActivity extends AppCompatActivity {
    EditText etFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_client);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etFind = findViewById(R.id.etBusca);
    }

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView rv = findViewById(R.id.listView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        etFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                List<Client> clients = new ClientHelper(FindClientActivity.this).getByAny(s.toString());
                Log.d("LSKDLSKD DKSL D", "SLKSLKDS SLDKSLD " + clients.toString());
                ClientAdapter adapter = new ClientAdapter(clients);
                rv.setAdapter(adapter);
                adapter.setOnItemClickListener((view, position) -> {
                    final Client client = clients.get(position);

                    Intent intent = new Intent(FindClientActivity.this, ShowClientActivity.class);
                    intent.putExtra("client", client);

                    startActivity(intent);
                });
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}