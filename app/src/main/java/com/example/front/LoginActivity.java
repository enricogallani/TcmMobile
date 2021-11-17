package com.example.front;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btLogin = findViewById(R.id.btnLogin);
        Button btRegistro = findViewById(R.id.btnRegistro);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etSenha = findViewById(R.id.etSenha);

        btLogin.setOnClickListener(v -> {

        });

        btRegistro.setOnClickListener(v -> {

        });

    }
}