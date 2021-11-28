package com.example.front;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CadastroFuncActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_func);

        Button btnCadastro = findViewById(R.id.btnCadastro);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etSenha = findViewById(R.id.etSenha);

        btnCadastro.setOnClickListener(v -> {

        });

    }
}