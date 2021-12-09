package com.example.front.ui.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.db.entity.User;
import com.example.front.db.helper.UserHelper;
import com.example.front.ui.adapter.UserAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(UserActivity.this, CreateUserActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<User> users = new UserHelper(UserActivity.this).getAll();
        UserAdapter adapter = new UserAdapter(users);
        adapter.setOnItemClickListener((view, position) -> {
            final User user = users.get(position);

            Intent intent = new Intent(UserActivity.this, ShowUserActivity.class);
            intent.putExtra("user", user);

            startActivity(intent);
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