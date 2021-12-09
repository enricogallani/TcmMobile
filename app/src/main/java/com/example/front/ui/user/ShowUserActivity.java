package com.example.front.ui.user;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.front.R;
import com.example.front.db.entity.User;

public class ShowUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        User user = (User) getIntent().getSerializableExtra("user");

        TextView tvName = findViewById(R.id.name);
        TextView tvEmail = findViewById(R.id.email);
        TextView tvOffice = findViewById(R.id.office);

        tvEmail.setText(user.getMail());
        tvName.setText(user.getName());
        tvOffice.setText(user.getOffice());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}