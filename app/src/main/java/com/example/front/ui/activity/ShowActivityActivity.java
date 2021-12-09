package com.example.front.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.front.R;
import com.example.front.db.entity.Activity;
import com.example.front.db.entity.User;

public class ShowActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Activity activity = (Activity) getIntent().getSerializableExtra("activity");

        TextView tvName = findViewById(R.id.name);
        TextView tvConsultor = findViewById(R.id.consultor);

        tvConsultor.setText(activity.getUser().getName());
        tvName.setText(activity.getName());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}