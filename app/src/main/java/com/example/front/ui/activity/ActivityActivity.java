package com.example.front.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.db.entity.Activity;
import com.example.front.db.entity.User;
import com.example.front.db.helper.ActivityHelper;
import com.example.front.db.helper.UserHelper;
import com.example.front.ui.adapter.ActivityAdapter;
import com.example.front.ui.adapter.UserAdapter;
import com.example.front.ui.user.CreateUserActivity;
import com.example.front.ui.user.ShowUserActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(ActivityActivity.this, CreateActivityActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Activity> activities = new ActivityHelper(ActivityActivity.this).getAll();
        ActivityAdapter adapter = new ActivityAdapter(activities);
        adapter.setOnItemClickListener((view, position) -> {
            final Activity activity = activities.get(position);

            Intent intent = new Intent(ActivityActivity.this, ShowActivityActivity.class);
            intent.putExtra("activity", activity);

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