package com.example.front.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.front.R;
import com.example.front.db.entity.User;
import com.example.front.ui.activity.ActivityActivity;
import com.example.front.ui.client.ClientActivity;
import com.example.front.ui.client.FindClientActivity;
import com.example.front.ui.user.UserActivity;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView hello = findViewById(R.id.hello);
        Button btnClient = findViewById(R.id.btnCliente);
        Button btnConsultor = findViewById(R.id.btnConsultor);
        Button btnAtividade = findViewById(R.id.btnAtividade);
        Button btnServicos = findViewById(R.id.btnServicos);
        Button btnBusca = findViewById(R.id.btnBusca);

        SharedPreferences sp = getSharedPreferences(getString(R.string.name_shared_preference), MODE_PRIVATE);
        String gson = sp.getString(getString(R.string.user_logged), "");
        if (!gson.isEmpty()) {
            User user = new Gson().fromJson(gson, User.class);
            hello.setText(String.format("Olá, %s. Tudo bem?", user.getName()));
        }

        btnClient.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ClientActivity.class)));
        btnBusca.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FindClientActivity.class)));
        btnConsultor.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UserActivity.class)));
        btnAtividade.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ActivityActivity.class)));
    }
}